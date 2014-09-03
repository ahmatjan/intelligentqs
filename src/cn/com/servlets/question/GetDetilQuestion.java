package cn.com.servlets.question;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.beans.AnswerAllInfoBean;
import cn.com.beans.AnswerBean;
import cn.com.beans.DiscussAllInfoBean;
import cn.com.beans.DiscussBean;
import cn.com.beans.QuestionAllInfoBean;
import cn.com.beans.QuestionBean;
import cn.com.beans.QuestionsKeywordsBean;
import cn.com.beans.UserInfoBean;
import cn.com.daos.AnswerDaoImp;
import cn.com.daos.DiscussDaoImp;
import cn.com.daos.QuestionDaoImp;
import cn.com.daos.QuestionsKeywordsDaoImp;
import cn.com.daos.TagsInfoDaoImp;
import cn.com.daos.UserInfoDaoImp;

import redis.clients.jedis.Jedis;
import cn.com.util.RUtil;

public class GetDetilQuestion extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// �õ���������ID
		HttpSession session = request.getSession();
       UserInfoBean uib = (UserInfoBean) session.getAttribute("userBean");
		QuestionDaoImp questionDaoImp = new QuestionDaoImp();
		// System.out.println(request.getParameter("question_id"));
		int question_id;
		try {
			question_id = Integer.parseInt(request.getParameter("question_id"));
		} catch (NumberFormatException e) {
			// ��������ֵ,����ҳ
			response.sendRedirect("index");
			return;
		}

		// ���ҳ��С��0
		if (question_id < 0) {
			response.sendRedirect("index");
			return;
		}
		QuestionBean questionBean = questionDaoImp
				.getQuestionByQuestionId(question_id);

		// �õ������ߵ�����
		UserInfoDaoImp userInfoDaoImp = new UserInfoDaoImp();
		String userName = userInfoDaoImp.getUserInfoByUserId(
				questionBean.getQuestion_user_id()).getUser_name();

		// �õ�������ı�ǩ
		String tagsId[] = null;
		TagsInfoDaoImp tagsInfoDaoImp = new TagsInfoDaoImp();
		// System.out.println(questionBean.getQuestion_tags());
		if (questionBean.getQuestion_tags() == null
				|| questionBean.getQuestion_tags().indexOf(",") == -1) {
			// ����Ĭ��Ϊ�ޣ���ݿ�Ӧ�ò���0Ϊ�ޱ�ǩ�����������11Ӧ�ÿ��Ը�Ϊ0

		} else {
			tagsId = questionBean.getQuestion_tags().split(",");
			StringBuffer tagStrBuffer = new StringBuffer();
			for (int i = 0; i < tagsId.length; i++) {
				int tagsIdInt = Integer.parseInt(tagsId[i]);
				String tagsStr = tagsInfoDaoImp.getTagsInfoByTagsId(tagsIdInt)
						.getTags_name() + " ";
				tagStrBuffer.append(tagsStr);
			}
			// ���ñ�ǩΪ������ʾ
			questionBean.setQuestion_tags(tagStrBuffer.toString());
		}

		// �������������������
		QuestionAllInfoBean questionAllInfoBean = new QuestionAllInfoBean();
		questionAllInfoBean.setQuestionUserName(userName);
		questionAllInfoBean.setBestAnswer("��");
		questionAllInfoBean.setCountOfAnswers(23);
		questionAllInfoBean.setVpOfQuestion(50);
		questionAllInfoBean.setQuestionBean(questionBean);

		// �õ�����������д�
		AnswerDaoImp answerDaoImp = new AnswerDaoImp();
		List<AnswerBean> listAnswer = answerDaoImp
				.findAnswerByQuestionId(question_id);
		List<AnswerAllInfoBean> listAllAnswer = new ArrayList<AnswerAllInfoBean>();

		// ���ۻ�ȡ
		List<DiscussAllInfoBean> listAllDiscuss = new ArrayList<DiscussAllInfoBean>();
		List<DiscussBean> listdiscussBean = new ArrayList<DiscussBean>();
		DiscussDaoImp discussDao = new DiscussDaoImp();
		DiscussAllInfoBean discussBean = null;

		// �û�Dao
		UserInfoDaoImp userDao = new UserInfoDaoImp();

		AnswerAllInfoBean answerAllBean = null;
		for (int i = 0; i < listAnswer.size(); i++) {
			answerAllBean = new AnswerAllInfoBean();
			String answerUserName = userInfoDaoImp.getUserInfoByUserId(
					listAnswer.get(i).getAnswer_user_id()).getUser_name();
			answerAllBean.setAnswerBean(listAnswer.get(i));
			answerAllBean.setUserName(answerUserName);
			listAllAnswer.add(answerAllBean);
			// �õ���ǰѭ�����������
			listdiscussBean = discussDao.getDiscussByAnswerId(listAnswer.get(i)
					.getAnswer_id());
			for (int j = 0; j < listdiscussBean.size(); j++) {
				// ���۽��з�װ
				discussBean = new DiscussAllInfoBean();
				String userDiscussName = userDao.getUserInfoByUserId(
						listdiscussBean.get(j).getUser_id()).getUser_name();
				discussBean.setUserName(userDiscussName);
				discussBean.setDiscussBean(listdiscussBean.get(j));
				listAllDiscuss.add(discussBean);
			}
		}
		
		//����������õ���Ϣ
		List<AnswerBean> listHotAnswer = answerDaoImp
				.findHotAnswerByQuestionId(question_id);
		List<AnswerAllInfoBean> listHotAllAnswer = new ArrayList<AnswerAllInfoBean>();

		// ���ۻ�ȡ
		List<DiscussAllInfoBean> listHotAllDiscuss = new ArrayList<DiscussAllInfoBean>();
		List<DiscussBean> listHotdiscussBean = new ArrayList<DiscussBean>();
		DiscussAllInfoBean discussHotBean = null;

		AnswerAllInfoBean answerHotAllBean = null;
		for (int i = 0; i < listHotAnswer.size(); i++) {
			answerHotAllBean = new AnswerAllInfoBean();
			String answerUserName = userInfoDaoImp.getUserInfoByUserId(
					listHotAnswer.get(i).getAnswer_user_id()).getUser_name();
			answerHotAllBean.setAnswerBean(listHotAnswer.get(i));
			answerHotAllBean.setUserName(answerUserName);
			listHotAllAnswer.add(answerHotAllBean);
			// �õ���ǰѭ�����������
			listHotdiscussBean = discussDao.getDiscussByAnswerId(listAnswer.get(i)
					.getAnswer_id());
			for (int j = 0; j < listHotdiscussBean.size(); j++) {
				// ���۽��з�װ
				discussHotBean = new DiscussAllInfoBean();
				String userDiscussName = userDao.getUserInfoByUserId(
						listHotdiscussBean.get(j).getUser_id()).getUser_name();
				discussHotBean.setUserName(userDiscussName);
				discussHotBean.setDiscussBean(listHotdiscussBean.get(j));
				listHotAllDiscuss.add(discussHotBean);
			}
		}
		
		int coutAnswer = listAnswer.size();
		// ͨ��request��������Ϣ���͸���ϸ����ҳ��

		// �õ������Ƽ�************************
		QuestionsKeywordsDaoImp questionKeyDao = new QuestionsKeywordsDaoImp();
		QuestionsKeywordsBean questionsKeywordsBean = new QuestionsKeywordsBean();
		// �õ���ǰ����ķִʴ�����
		questionsKeywordsBean = questionKeyDao
				.getQuestionKeyByQuestionId(question_id);
		String[] tmp = questionsKeywordsBean.getQuesitons_keywords_topN()
				.split(",");

		// ��ݵ�ǰ������ȥƥ����������ִ�
		List<QuestionsKeywordsBean> listTemp = new ArrayList<QuestionsKeywordsBean>();
		// ��װ����ƥ��õ�������ID
		List<Integer> listQuestionId = new ArrayList<Integer>();
		for (int j = 0; j < tmp.length; j++) {
			listTemp = questionKeyDao.getQuestionsKeywordsByKeyWords(tmp[j]);
			for (int i = 0; i < listTemp.size(); i++) {
				int empId = listTemp.get(i).getQuestion_id();
				// ���ȡ������ID�����ڵ�ǰ����ID�������
				if (empId != question_id) {
					listQuestionId.add(listTemp.get(i).getQuestion_id());
				}

			}

		}
		// ��ݵõ�������ID���ϵõ����������ȥ����������Ϣ
		List<QuestionBean> questionKeyList = new ArrayList<QuestionBean>();
		// ȥ���ظ���ID
		listQuestionId = this.removeDuplicateWithOrder(listQuestionId);
		for (int i = 0; i < listQuestionId.size(); i++) {
			questionKeyList.add(questionDaoImp
					.getQuestionByQuestionId(listQuestionId.get(i)));
		}

		// ***************whether the question belongs to the logined people*****************
		if (uib != null) {
			if (uib.getUser_id() == questionAllInfoBean.getQuestionBean().getQuestion_user_id()){
				request.setAttribute("belong", "1");
			}
			else {
				request.setAttribute("belong", "0");
			}
		}
		else {
			request.setAttribute("belong", "0");
		}
		
		RUtil redis = new RUtil();
		Jedis rdb = redis.con();
		String best = "questionid:" + questionAllInfoBean.getQuestionBean().getQuestion_id();
		String bestcode = rdb.hget("accept", best);
		if ( bestcode == null){
			request.setAttribute("best", "-1");
		}
		else {
			request.setAttribute("best", bestcode);
		}
	
		request.setAttribute("questionKeyList", questionKeyList);
		request.setAttribute("listAllDiscuss", listAllDiscuss);
		request.setAttribute("coutAnswer", coutAnswer);
		request.setAttribute("question", questionAllInfoBean);
		request.setAttribute("listAnswer", listAllAnswer);
		request.setAttribute("listHotAllDiscuss", listHotAllDiscuss);
		request.setAttribute("listHotAllAnswer", listHotAllAnswer);
		request.getRequestDispatcher("question_contents.jsp").forward(request,
				response);
	}

	// ȥ���ظ�������ID
	public static List removeDuplicateWithOrder(List list) {
		Set set = new HashSet();
		List newList = new ArrayList();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (set.add(element))
				newList.add(element);
		}
		return newList;
	}

}

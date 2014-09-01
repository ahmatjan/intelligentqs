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

import cn.com.beans.AnswerAllInfoBean;
import cn.com.beans.AnswerBean;
import cn.com.beans.DiscussAllInfoBean;
import cn.com.beans.DiscussBean;
import cn.com.beans.QuestionAllInfoBean;
import cn.com.beans.QuestionBean;
import cn.com.beans.QuestionsKeywordsBean;
import cn.com.daos.AnswerDaoImp;
import cn.com.daos.DiscussDaoImp;
import cn.com.daos.QuestionDaoImp;
import cn.com.daos.QuestionsKeywordsDaoImp;
import cn.com.daos.TagsInfoDaoImp;
import cn.com.daos.UserInfoDaoImp;

public class GetDetilQuestion extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 得到传入问题ID
		QuestionDaoImp questionDaoImp = new QuestionDaoImp();
		// System.out.println(request.getParameter("question_id"));
		int question_id;
		try {
			question_id = Integer.parseInt(request.getParameter("question_id"));
		} catch (NumberFormatException e) {
			// 如果传入非数值,跳到首页
			response.sendRedirect("index");
			return;
		}

		// 如果页数小于0
		if (question_id < 0) {
			response.sendRedirect("index");
			return;
		}
		QuestionBean questionBean = questionDaoImp
				.getQuestionByQuestionId(question_id);

		// 得到提问者的名字
		UserInfoDaoImp userInfoDaoImp = new UserInfoDaoImp();
		String userName = userInfoDaoImp.getUserInfoByUserId(
				questionBean.getQuestion_user_id()).getUser_name();

		// 得到该问题的标签
		String tagsId[] = null;
		TagsInfoDaoImp tagsInfoDaoImp = new TagsInfoDaoImp();
		// System.out.println(questionBean.getQuestion_tags());
		if (questionBean.getQuestion_tags() == null
				|| questionBean.getQuestion_tags().indexOf(",") == -1) {
			// 这里默认为无，数据库应该插入0为无标签描述，下面的11应该可以改为0

		} else {
			tagsId = questionBean.getQuestion_tags().split(",");
			StringBuffer tagStrBuffer = new StringBuffer();
			for (int i = 0; i < tagsId.length; i++) {
				int tagsIdInt = Integer.parseInt(tagsId[i]);
				String tagsStr = tagsInfoDaoImp.getTagsInfoByTagsId(tagsIdInt)
						.getTags_name() + " ";
				tagStrBuffer.append(tagsStr);
			}
			// 设置标签为文字显示
			questionBean.setQuestion_tags(tagStrBuffer.toString());
		}

		// 放入完整的问题描述中
		QuestionAllInfoBean questionAllInfoBean = new QuestionAllInfoBean();
		questionAllInfoBean.setQuestionUserName(userName);
		questionAllInfoBean.setBestAnswer("无");
		questionAllInfoBean.setCountOfAnswers(23);
		questionAllInfoBean.setVpOfQuestion(50);
		questionAllInfoBean.setQuestionBean(questionBean);

		// 得到该问题的所有答案
		AnswerDaoImp answerDaoImp = new AnswerDaoImp();
		List<AnswerBean> listAnswer = answerDaoImp
				.findAnswerByQuestionId(question_id);
		List<AnswerAllInfoBean> listAllAnswer = new ArrayList<AnswerAllInfoBean>();

		// 评论获取
		List<DiscussAllInfoBean> listAllDiscuss = new ArrayList<DiscussAllInfoBean>();
		List<DiscussBean> listdiscussBean = new ArrayList<DiscussBean>();
		DiscussDaoImp discussDao = new DiscussDaoImp();
		DiscussAllInfoBean discussBean = null;

		// 用户Dao
		UserInfoDaoImp userDao = new UserInfoDaoImp();

		AnswerAllInfoBean answerAllBean = null;
		for (int i = 0; i < listAnswer.size(); i++) {
			answerAllBean = new AnswerAllInfoBean();
			String answerUserName = userInfoDaoImp.getUserInfoByUserId(
					listAnswer.get(i).getAnswer_user_id()).getUser_name();
			answerAllBean.setAnswerBean(listAnswer.get(i));
			answerAllBean.setUserName(answerUserName);
			listAllAnswer.add(answerAllBean);
			// 得到当前循环问题的评论
			listdiscussBean = discussDao.getDiscussByAnswerId(listAnswer.get(i)
					.getAnswer_id());
			for (int j = 0; j < listdiscussBean.size(); j++) {
				// 评论进行封装
				discussBean = new DiscussAllInfoBean();
				String userDiscussName = userDao.getUserInfoByUserId(
						listdiscussBean.get(j).getUser_id()).getUser_name();
				discussBean.setUserName(userDiscussName);
				discussBean.setDiscussBean(listdiscussBean.get(j));
				listAllDiscuss.add(discussBean);
			}
		}
		
		//按热门问题得到信息
		List<AnswerBean> listHotAnswer = answerDaoImp
				.findHotAnswerByQuestionId(question_id);
		List<AnswerAllInfoBean> listHotAllAnswer = new ArrayList<AnswerAllInfoBean>();

		// 评论获取
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
			// 得到当前循环问题的评论
			listHotdiscussBean = discussDao.getDiscussByAnswerId(listAnswer.get(i)
					.getAnswer_id());
			for (int j = 0; j < listHotdiscussBean.size(); j++) {
				// 评论进行封装
				discussHotBean = new DiscussAllInfoBean();
				String userDiscussName = userDao.getUserInfoByUserId(
						listHotdiscussBean.get(j).getUser_id()).getUser_name();
				discussHotBean.setUserName(userDiscussName);
				discussHotBean.setDiscussBean(listHotdiscussBean.get(j));
				listHotAllDiscuss.add(discussHotBean);
			}
		}
		
		int coutAnswer = listAnswer.size();
		// 通过request把所需信息发送给详细问题页面

		// 得到问题推荐************************
		QuestionsKeywordsDaoImp questionKeyDao = new QuestionsKeywordsDaoImp();
		QuestionsKeywordsBean questionsKeywordsBean = new QuestionsKeywordsBean();
		// 得到当前问题的分词处理结果
		questionsKeywordsBean = questionKeyDao
				.getQuestionKeyByQuestionId(question_id);
		String[] tmp = questionsKeywordsBean.getQuesitons_keywords_topN()
				.split(",");

		// 根据当前问题结果去匹配所有问题分词
		List<QuestionsKeywordsBean> listTemp = new ArrayList<QuestionsKeywordsBean>();
		// 封装所有匹配得到的问题ID
		List<Integer> listQuestionId = new ArrayList<Integer>();
		for (int j = 0; j < tmp.length; j++) {
			listTemp = questionKeyDao.getQuestionsKeywordsByKeyWords(tmp[j]);
			for (int i = 0; i < listTemp.size(); i++) {
				int empId = listTemp.get(i).getQuestion_id();
				// 如果取得问题ID不等于当前问题ID，则存入
				if (empId != question_id) {
					listQuestionId.add(listTemp.get(i).getQuestion_id());
				}

			}

		}
		// 根据得到的问题ID集合得到所有问题后去查找问题信息
		List<QuestionBean> questionKeyList = new ArrayList<QuestionBean>();
		// 去除重复的ID
		listQuestionId = this.removeDuplicateWithOrder(listQuestionId);
		for (int i = 0; i < listQuestionId.size(); i++) {
			questionKeyList.add(questionDaoImp
					.getQuestionByQuestionId(listQuestionId.get(i)));
		}

		// ********************************

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

	// 去除重复的问题ID
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

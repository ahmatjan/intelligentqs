package cn.com.servlets.question;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.beans.QuestionAllInfoBean;
import cn.com.beans.QuestionBean;
import cn.com.beans.QuestionsKeywordsBean;
import cn.com.beans.TagsInfoBean;
import cn.com.daos.AnswerDaoImp;
import cn.com.daos.QuestionDaoImp;
import cn.com.daos.QuestionsKeywordsDaoImp;
import cn.com.daos.TagsInfoDaoImp;
import cn.com.daos.UserInfoDaoImp;
import cn.com.util.ChineseAnalyzerUtil;

public class SearchQuestionServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String context = request.getParameter("context");
		
		if(context == null||context.equals("")){
			request.getRequestDispatcher("index").forward(request, response);
			return;
		}
	
		System.out.println("You input words is "+context);
		QuestionDaoImp questionDaoImp = new QuestionDaoImp();
		AnswerDaoImp answerDaoImp = new AnswerDaoImp();
		UserInfoDaoImp userInfoDaoImp = new UserInfoDaoImp();
		// �õ�����������ϸ��Ϣ����ҳ��ʾ
		List<QuestionAllInfoBean> listAllQuestions = new ArrayList<QuestionAllInfoBean>();

		// ������ʽƥ��
		Pattern pattern = Pattern.compile("[0-9]*");
		TagsInfoDaoImp tagsInfoDaoImp = new TagsInfoDaoImp();

		QuestionAllInfoBean questionAllInfoBean = null;
		QuestionBean questionBean = new QuestionBean();

		// �Ե�ǰ����ִʴ���
		ChineseAnalyzerUtil chineseAnalyzerUtil = new ChineseAnalyzerUtil();
		// �õ�����ƥ�������************************
		QuestionsKeywordsDaoImp questionKeyDao = new QuestionsKeywordsDaoImp();
		QuestionsKeywordsBean questionsKeywordsBean = new QuestionsKeywordsBean();
		List<String> listKeyWords = chineseAnalyzerUtil
				.getSearchKeyWords(context);

		/* ���ݵ�ǰ������ȥƥ����������ִ� */

		// ��װ����ƥ��õ�������ID
		List<Integer> listQuestionId = new ArrayList<Integer>();
		// ���ݵ�ǰ������ȥƥ����������ִ�
		List<QuestionsKeywordsBean> listTemp = new ArrayList<QuestionsKeywordsBean>();
		// ѭ����������������
		StringBuffer tempKeywords = new StringBuffer();
		// �������������˳�����
		for (int i = 0; i < listKeyWords.size(); i++) {
			tempKeywords.append(listKeyWords.get(i) + "%");
		}
		// ���������ķִʽ��ȥƥ��
		System.out.println(tempKeywords);
		// listTemp = questionKeyDao
		// .getQuestionsKeywordsByKeyWords(tempKeywords.toString());
		// for (int i = 0; i < listTemp.size(); i++) {
		// listQuestionId.add(listTemp.get(i).getQuestion_id());
		//
		// }
		// // ���ݵõ�������ID���ϵõ����������ȥ����������Ϣ
		List<QuestionBean> questionKeyList = new ArrayList<QuestionBean>();
		questionKeyList = questionDaoImp.getQuestionByQuestionName(tempKeywords
				.toString());
		// // ȥ���ظ���ID
		// listQuestionId = this.removeDuplicateWithOrder(listQuestionId);
		// for (int i = 0; i < listQuestionId.size(); i++) {
		// questionKeyList.add(questionDaoImp
		// .getQuestionByQuestionId(listQuestionId.get(i)));
		// }

		System.out.println("Search keywords:" + tempKeywords);
		System.out.println("Result total:" + questionKeyList.size());

		// List<QuestionBean> listAllQuestion =
		// questionDaoImp.getQuestionByQuestionName(context);

		// ����õ�������������Ϣ
		for (int i = 0; i < questionKeyList.size(); i++) {
			questionAllInfoBean = new QuestionAllInfoBean();
			questionBean = questionKeyList.get(i);
			if (questionBean.getQuestion_description().length() > 100) {
				questionBean.setQuestion_description(questionBean
						.getQuestion_description().substring(0, 100) + "...");
			}
			// �õ�������ı�ǩ
			String tagsId[] = null;
			// System.out.println(questionBean.getQuestion_tags());
			if (questionBean.getQuestion_tags() == null
					|| questionBean.getQuestion_tags().equals("")) {
				// ����Ĭ��Ϊ�ޣ����ݿ�Ӧ�ò���0Ϊ�ޱ�ǩ�����������11Ӧ�ÿ��Ը�Ϊ0
				questionBean.setQuestion_tags("����");
			} else {
				if (questionBean.getQuestion_tags() != null
						&& questionBean.getQuestion_tags().indexOf(",") == -1
						&& pattern.matcher(questionBean.getQuestion_tags())
								.matches()) {
					String tagsStr = tagsInfoDaoImp.getTagsInfoByTagsId(
							Integer.parseInt(questionBean.getQuestion_tags()))
							.getTags_name();
					questionBean.setQuestion_tags(tagsStr);
				} else {
					if (questionBean.getQuestion_tags().indexOf(",") != -1) {
						tagsId = questionBean.getQuestion_tags().split(",");
						StringBuffer tagStrBuffer = new StringBuffer();
						for (int i1 = 0; i1 < tagsId.length; i1++) {
							int tagsIdInt = Integer.parseInt(tagsId[i1]);
							String tagsStr = tagsInfoDaoImp
									.getTagsInfoByTagsId(tagsIdInt)
									.getTags_name()
									+ " ";
							tagStrBuffer.append(tagsStr);
						}
						// ���ñ�ǩΪ������ʾ
						questionBean.setQuestion_tags(tagStrBuffer.toString());
					}
				}
			}

			String userName = userInfoDaoImp.getUserInfoByUserId(
					questionBean.getQuestion_user_id()).getUser_name();
			int countOfAnswer = answerDaoImp.getContOfAnswer(questionBean
					.getQuestion_id());

			questionAllInfoBean.setQuestionUserName(userName);
			questionAllInfoBean.setCountOfAnswers(countOfAnswer);
			questionAllInfoBean.setVpOfQuestion(50);
			questionAllInfoBean.setBestAnswer("����");
			questionAllInfoBean.setQuestionBean(questionBean);
			listAllQuestions.add(questionAllInfoBean);
		}

		// *****************��Χ�Ĵ������ɾ����GetHotServlet���**********************
		// QuestionDaoImp questionDaoImp = new QuestionDaoImp();
		// �������������ϸ��Ϣ��ʾ����ҳ
		List<QuestionAllInfoBean> listHotQuestions = new ArrayList<QuestionAllInfoBean>();

		// QuestionAllInfoBean questionAllInfoBean = null;
		// questionBean = new QuestionBean();
		// AnswerDaoImp answerDaoImp = new AnswerDaoImp();

		// �������������Ϣ�������û�������ǩ���ش����ȵȣ�
		List<QuestionBean> listHotQuestion = questionDaoImp
				.getHotQuestionsBySearch(tempKeywords.toString());

		// UserInfoDaoImp userInfoDaoImp = new UserInfoDaoImp();
		int len = 0;
		if (listHotQuestion.size() < 10) {
			len = listHotQuestion.size();
		} else {
			len = 10;
		}
		// ����õ�����������������Ϣ
		for (int i = 0; i < len; i++) {
			questionAllInfoBean = new QuestionAllInfoBean();
			questionBean = listHotQuestion.get(i);
			// �õ�������ı�ǩ
			String tagsId[] = null;
			// System.out.println(questionBean.getQuestion_tags());
			if (questionBean.getQuestion_tags() == null
					|| questionBean.getQuestion_tags().equals("")) {
				// ����Ĭ��Ϊ�ޣ����ݿ�Ӧ�ò���0Ϊ�ޱ�ǩ�����������11Ӧ�ÿ��Ը�Ϊ0
				questionBean.setQuestion_tags("����");
			} else {
				if (questionBean.getQuestion_tags() != null
						&& questionBean.getQuestion_tags().indexOf(",") == -1
						&& pattern.matcher(questionBean.getQuestion_tags())
								.matches()) {
					String tagsStr = tagsInfoDaoImp.getTagsInfoByTagsId(
							Integer.parseInt(questionBean.getQuestion_tags()))
							.getTags_name();
					questionBean.setQuestion_tags(tagsStr);
				} else {
					if (questionBean.getQuestion_tags().indexOf(",") != -1) {
						tagsId = questionBean.getQuestion_tags().split(",");
						StringBuffer tagStrBuffer = new StringBuffer();
						for (int i1 = 0; i1 < tagsId.length; i1++) {
							int tagsIdInt = Integer.parseInt(tagsId[i1]);
							String tagsStr = tagsInfoDaoImp
									.getTagsInfoByTagsId(tagsIdInt)
									.getTags_name()
									+ " ";
							tagStrBuffer.append(tagsStr);
						}
						// ���ñ�ǩΪ������ʾ
						questionBean.setQuestion_tags(tagStrBuffer.toString());
					}
				}
			}

			String userName = userInfoDaoImp.getUserInfoByUserId(
					questionBean.getQuestion_user_id()).getUser_name();
			int countOfAnswer = answerDaoImp.getContOfAnswer(questionBean
					.getQuestion_id());

			questionAllInfoBean.setQuestionUserName(userName);
			questionAllInfoBean.setCountOfAnswers(countOfAnswer);
			questionAllInfoBean.setVpOfQuestion(50);
			questionAllInfoBean.setBestAnswer("����");
			questionAllInfoBean.setQuestionBean(questionBean);
			listHotQuestions.add(questionAllInfoBean);
		}

		request.setAttribute("searchKeyWords", context);
		request.setAttribute("listHotQuestions", listHotQuestions);
		// ************************************************
		request.setAttribute("listQuestions", listAllQuestions);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	// // ȥ���ظ�������ID
	// public static List removeDuplicateWithOrder(List list) {
	// Set set = new HashSet();
	// List newList = new ArrayList();
	// for (Iterator iter = list.iterator(); iter.hasNext();) {
	// Object element = iter.next();
	// if (set.add(element))
	// newList.add(element);
	// }
	// return newList;
	// }

}

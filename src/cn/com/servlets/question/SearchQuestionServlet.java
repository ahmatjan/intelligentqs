package cn.com.servlets.question;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
		System.out.println("您输入的内容为："+context);
		if (context == null) {
			request.getRequestDispatcher("index").forward(request, response);
		}
		QuestionDaoImp questionDaoImp = new QuestionDaoImp();
		AnswerDaoImp answerDaoImp = new AnswerDaoImp();
		UserInfoDaoImp userInfoDaoImp = new UserInfoDaoImp();
		// 得到所有问题详细信息在主页显示
		List<QuestionAllInfoBean> listAllQuestions = new ArrayList<QuestionAllInfoBean>();

		QuestionAllInfoBean questionAllInfoBean = null;
		QuestionBean questionBean = new QuestionBean();

		
		// 对当前问题分词处理
		ChineseAnalyzerUtil chineseAnalyzerUtil = new ChineseAnalyzerUtil();
		// 得到搜索匹配的问题************************
		QuestionsKeywordsDaoImp questionKeyDao = new QuestionsKeywordsDaoImp();
		QuestionsKeywordsBean questionsKeywordsBean = new QuestionsKeywordsBean();
		List<String> listKeyWords = chineseAnalyzerUtil.getSearchKeyWords(context);

		/* 根据当前问题结果去匹配所有问题分词 */

		// 封装所有匹配得到的问题ID
		List<Integer> listQuestionId = new ArrayList<Integer>();
		// 根据当前问题结果去匹配所有问题分词
		List<QuestionsKeywordsBean> listTemp = new ArrayList<QuestionsKeywordsBean>();
		// 循环遍历搜索的内容
		StringBuffer tempKeywords = new StringBuffer();
		//按照搜索输入的顺序存入
		for(int i = 0;i <listKeyWords.size() ;i++){
			tempKeywords.append(listKeyWords.get(i)+"%");
		}
		//根据搜索的分词结果去匹配
		System.out.println(tempKeywords);
//		listTemp = questionKeyDao
//				.getQuestionsKeywordsByKeyWords(tempKeywords.toString());
//		for (int i = 0; i < listTemp.size(); i++) {
//			listQuestionId.add(listTemp.get(i).getQuestion_id());
//
//		}
//		// 根据得到的问题ID集合得到所有问题后去查找问题信息
		List<QuestionBean> questionKeyList = new ArrayList<QuestionBean>();
		questionKeyList = questionDaoImp.getQuestionByQuestionName(tempKeywords.toString());
//		// 去除重复的ID
//		listQuestionId = this.removeDuplicateWithOrder(listQuestionId);
//		for (int i = 0; i < listQuestionId.size(); i++) {
//			questionKeyList.add(questionDaoImp
//					.getQuestionByQuestionId(listQuestionId.get(i)));
//		}

		System.out.println("Search keywords:" + tempKeywords);
		System.out.println("Result total:" + questionKeyList.size());

		
//		List<QuestionBean> listAllQuestion =
//		questionDaoImp.getQuestionByQuestionName(context);
		 
		// 处理得到的所有问题信息
		for (int i = 0; i < questionKeyList.size(); i++) {
			questionAllInfoBean = new QuestionAllInfoBean();
			questionBean = questionKeyList.get(i);
			if(questionBean.getQuestion_description().length() > 100){
				questionBean.setQuestion_description(questionBean.getQuestion_description().substring(0, 100)+"...");
			}
			String userName = userInfoDaoImp.getUserInfoByUserId(
					questionBean.getQuestion_user_id()).getUser_name();
			int countOfAnswer = answerDaoImp.getContOfAnswer(questionBean
					.getQuestion_id());

			questionAllInfoBean.setQuestionUserName(userName);
			questionAllInfoBean.setCountOfAnswers(countOfAnswer);
			questionAllInfoBean.setVpOfQuestion(50);
			questionAllInfoBean.setBestAnswer("暂无");
			questionAllInfoBean.setQuestionBean(questionBean);
			listAllQuestions.add(questionAllInfoBean);
		}

		
		// *****************包围的代码可以删除用GetHotServlet解决**********************
		// QuestionDaoImp questionDaoImp = new QuestionDaoImp();
		// 获得热门问题详细信息显示在主页
		List<QuestionAllInfoBean> listHotQuestions = new ArrayList<QuestionAllInfoBean>();

		// QuestionAllInfoBean questionAllInfoBean = null;
		// questionBean = new QuestionBean();
		// AnswerDaoImp answerDaoImp = new AnswerDaoImp();

		// 获得热门问题信息（包括用户名，标签，回答数等等）
		List<QuestionBean> listHotQuestion = questionDaoImp
				.getHotQuestionsBySearch(tempKeywords.toString());

		// UserInfoDaoImp userInfoDaoImp = new UserInfoDaoImp();
		int len = 0;
		if (listHotQuestion.size() < 10) {
			len = listHotQuestion.size();
		} else {
			len = 10;
		}
		// 处理得到的所有热门问题信息
		for (int i = 0; i < len; i++) {
			questionAllInfoBean = new QuestionAllInfoBean();
			questionBean = listHotQuestion.get(i);
			if(questionBean.getQuestion_description().length() > 100){
				questionBean.setQuestion_description(questionBean.getQuestion_description().substring(0, 100)+"...");
			}
			String userName = userInfoDaoImp.getUserInfoByUserId(
					questionBean.getQuestion_user_id()).getUser_name();
			int countOfAnswer = answerDaoImp.getContOfAnswer(questionBean
					.getQuestion_id());

			questionAllInfoBean.setQuestionUserName(userName);
			questionAllInfoBean.setCountOfAnswers(countOfAnswer);
			questionAllInfoBean.setVpOfQuestion(50);
			questionAllInfoBean.setBestAnswer("暂无");
			questionAllInfoBean.setQuestionBean(questionBean);
			listHotQuestions.add(questionAllInfoBean);
		}
		
		request.setAttribute("searchKeyWords", context);
		request.setAttribute("listHotQuestions", listHotQuestions);
		// ************************************************
		request.setAttribute("listQuestions", listAllQuestions);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

//	// 去除重复的问题ID
//	public static List removeDuplicateWithOrder(List list) {
//		Set set = new HashSet();
//		List newList = new ArrayList();
//		for (Iterator iter = list.iterator(); iter.hasNext();) {
//			Object element = iter.next();
//			if (set.add(element))
//				newList.add(element);
//		}
//		return newList;
//	}

}

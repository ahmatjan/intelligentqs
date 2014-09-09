package cn.com.mservlets.index;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.beans.QuestionBean;
import cn.com.beans.QuestionAllInfoBean;
import cn.com.beans.TagsInfoBean;
import cn.com.daos.AnswerDaoImp;
import cn.com.daos.QuestionDaoImp;
import cn.com.daos.TagsInfoDaoImp;
import cn.com.daos.UserInfoDaoImp;

/**
 * @author Xianxiaofei
 * @date:2014-5-21 下午7:41:41
 */
public class IndexServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		TagsInfoDaoImp tagsInfoDaoImp = new TagsInfoDaoImp();
		QuestionDaoImp questionDaoImp = new QuestionDaoImp();
		AnswerDaoImp answerDaoImp = new AnswerDaoImp();
		UserInfoDaoImp userInfoDaoImp = new UserInfoDaoImp();
		//得到所有问题详细信息在主页显示
		List<QuestionAllInfoBean> listAllQuestions =new ArrayList<QuestionAllInfoBean>();
		
		QuestionAllInfoBean questionAllInfoBean = null;
		QuestionBean  questionBean = new QuestionBean();
		
		List<QuestionBean> listAllQuestion = questionDaoImp.getAllQuestions(0,10);
		List<TagsInfoBean> listTags = tagsInfoDaoImp.getHotTags();
		
		//处理得到的所有问题信息
		for(int i=0; i<listAllQuestion.size();i++){
			questionAllInfoBean = new QuestionAllInfoBean();
			questionBean = listAllQuestion.get(i);
			String userName = userInfoDaoImp.getUserInfoByUserId(questionBean.getQuestion_user_id()).getUser_name();
			int countOfAnswer = answerDaoImp.getContOfAnswer(questionBean.getQuestion_id());
			
			questionAllInfoBean.setQuestionUserName(userName);
			questionAllInfoBean.setCountOfAnswers(countOfAnswer);
			questionAllInfoBean.setVpOfQuestion(50);
			questionAllInfoBean.setBestAnswer("暂无");
			questionAllInfoBean.setQuestionBean(questionBean);
			listAllQuestions.add(questionAllInfoBean);
		}
		
		
		//分别得到问题，已解决，用户总数
		int totalOfQuestion = questionDaoImp.getContOfQuestion();
		int countOfSolution = answerDaoImp.getContOfsolution();
		int totalOfUser = userInfoDaoImp.getCountOfUser();
		
		HttpSession session = request.getSession();
		session.setAttribute("totalOfQuestion", totalOfQuestion);
		session.setAttribute("countOfSolution", countOfSolution);
		session.setAttribute("totalOfUser", totalOfUser);
		session.setAttribute("listTags", listTags);
		
		
		//*****************包围的代码可以删除用GetHotServlet解决**********************
		//QuestionDaoImp questionDaoImp = new QuestionDaoImp();
		//获得热门问题详细信息显示在主页
		List<QuestionAllInfoBean> listHotQuestions =new ArrayList<QuestionAllInfoBean>();
		
		//QuestionAllInfoBean questionAllInfoBean = null;
		//  questionBean = new QuestionBean();
		//AnswerDaoImp answerDaoImp = new AnswerDaoImp();
		
		//获得热门问题信息（包括用户名，标签，回答数等等）
		List<QuestionBean> listHotQuestion = questionDaoImp.getHotQuestions();
		
		//UserInfoDaoImp userInfoDaoImp = new UserInfoDaoImp();
		
		//处理得到的所有热门问题信息
		for(int i=0; i<10;i++){
			questionAllInfoBean = new QuestionAllInfoBean();
			questionBean = listHotQuestion.get(i);
			String userName = userInfoDaoImp.getUserInfoByUserId(questionBean.getQuestion_user_id()).getUser_name();
			int countOfAnswer = answerDaoImp.getContOfAnswer(questionBean.getQuestion_id());
			
			questionAllInfoBean.setQuestionUserName(userName);
			questionAllInfoBean.setCountOfAnswers(countOfAnswer);
			questionAllInfoBean.setVpOfQuestion(50);
			questionAllInfoBean.setBestAnswer("暂无");
			questionAllInfoBean.setQuestionBean(questionBean);
			listHotQuestions.add(questionAllInfoBean);
		}
		request.setAttribute("listHotQuestions", listHotQuestions);
		//************************************************
		
		
		//总页数
		int len = questionDaoImp.getContOfQuestion()/10;
//		System.out.println("总页数："+len);
		request.setAttribute("currentPage", 0);
		session.setAttribute("len", len);
		request.setAttribute("listQuestions", listAllQuestions);	
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}

package cn.com.mservlets.question;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.beans.QuestionAllInfoBean;
import cn.com.beans.QuestionBean;
import cn.com.daos.AnswerDaoImp;
import cn.com.daos.QuestionDaoImp;
import cn.com.daos.UserInfoDaoImp;

/**
 * @author Xianxiaofei
 * @date:2014-6-4 下午1:45:59
 */
public class GetHotQuestionsServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * 
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		QuestionDaoImp questionDaoImp = new QuestionDaoImp();
		//获得热门问题详细信息显示在主页
		List<QuestionAllInfoBean> listHotQuestions =new ArrayList<QuestionAllInfoBean>();
		
		QuestionAllInfoBean questionAllInfoBean = null;
		QuestionBean  questionBean = new QuestionBean();
		AnswerDaoImp answerDaoImp = new AnswerDaoImp();
		
		//获得热门问题信息（包括用户名，标签，回答数等等）
		List<QuestionBean> listHotQuestion = questionDaoImp.getHotQuestions();
		
		UserInfoDaoImp userInfoDaoImp = new UserInfoDaoImp();
		
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
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}

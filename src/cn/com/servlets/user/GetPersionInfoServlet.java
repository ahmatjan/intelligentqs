package cn.com.servlets.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.beans.AnswerBean;
import cn.com.beans.QuestionBean;
import cn.com.beans.UserInfoBean;
import cn.com.daos.AnswerDaoImp;
import cn.com.daos.QuestionDaoImp;

public class GetPersionInfoServlet extends HttpServlet {
 
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		UserInfoBean uib = (UserInfoBean) session.getAttribute("userBean");
		if(uib == null){
			request.getRequestDispatcher("userLogin.jsp").forward(request, response);
			return;
		}
		int userID = uib.getUser_id();
		QuestionDaoImp questionDao = new QuestionDaoImp();
		AnswerDaoImp answerDao = new AnswerDaoImp();
		
		List<QuestionBean> listQuestion = new ArrayList<QuestionBean>();
		List<AnswerBean> listAnswer = new ArrayList<AnswerBean>();
		listQuestion = questionDao.getQuestionByUserId(userID,0,10);
		listAnswer = answerDao.getAnswerByUserId(userID);
		if(listAnswer.size()>10){
			listAnswer = listAnswer.subList(0, 10);
		}
		
		QuestionDaoImp questionDaoImp = new QuestionDaoImp();
		int len = questionDaoImp.getContOfQuestionByUserId(uib.getUser_id())/10;
		int countOfQuestion = questionDaoImp.getContOfQuestionByUserId(uib.getUser_id());
//		System.out.println("×ÜÒ³Êý£º"+len);
		request.setAttribute("currentPage", 0);
		session.setAttribute("len", len);
		request.setAttribute("listQuestion", listQuestion);
		request.setAttribute("listAnswer", listAnswer);
		session.setAttribute("countOfQuestion",countOfQuestion);
		request.setAttribute("countOfAuswer", listAnswer.size());
		
		request.getRequestDispatcher("person.jsp").forward(request, response);
		
	}
 
}

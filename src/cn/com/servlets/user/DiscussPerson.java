package cn.com.servlets.user;

import java.io.IOException;
import java.io.PrintWriter;
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

public class DiscussPerson extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserInfoBean uib = (UserInfoBean) session.getAttribute("userBean");
		if (uib == null) {
			request.getRequestDispatcher("userLogin.jsp").forward(request,
					response);
			return;
		}

		QuestionDaoImp questionDaoImp = new QuestionDaoImp();
		// 总页数
		int len = (Integer) session.getAttribute("len");

		// 得到要查看的某页
		int currentPage;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (NumberFormatException e) {
			// 如果页数非数值,跳到首页
			currentPage = 0;
		}

		// 如果页数大于总数,跳到末页
		if (currentPage > len) {
			currentPage = len;
		}

		// 如果页数小于0
		if (currentPage < 0) {
			currentPage = 0;
		}
		int userID = uib.getUser_id();
		QuestionDaoImp questionDao = new QuestionDaoImp();
		AnswerDaoImp answerDao = new AnswerDaoImp();

		List<QuestionBean> listQuestion = new ArrayList<QuestionBean>();
		List<AnswerBean> listAnswer = new ArrayList<AnswerBean>();
		listQuestion = questionDao.getQuestionByUserId(userID,currentPage*10,10);
		listAnswer = answerDao.getAnswerByUserId(userID);
		if (listAnswer.size() > 10) {
			listAnswer = listAnswer.subList(0, 10);
		}
		
		System.out.println("當前頁數："+currentPage);
		System.out.println("總頁數："+len);
		session.setAttribute("currentPage", currentPage);
		request.setAttribute("listQuestion", listQuestion);
		request.setAttribute("listAnswer", listAnswer);
		request.setAttribute("countOfAuswer", listAnswer.size());

		request.getRequestDispatcher("person.jsp").forward(request, response);

	}

}

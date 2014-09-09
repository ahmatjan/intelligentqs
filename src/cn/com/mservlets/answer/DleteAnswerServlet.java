package cn.com.mservlets.answer;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.daos.AnswerDaoImp;

/**
 * @author Xianxiaofei
 * @date:2014-5-20 ÏÂÎç1:01:32
 */
public class DleteAnswerServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		int answerId = Integer.parseInt(request.getParameter("answerId"));
		AnswerDaoImp answerDaoImp = new AnswerDaoImp();
		if(answerDaoImp.deleteAnswer(answerId)){
			request.getRequestDispatcher("getHotAnswersInfo").forward(request, response);
		}else {
			request.setAttribute("Msg", "É¾³ýÊ§°Ü");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		doGet(request, response);
	}

}

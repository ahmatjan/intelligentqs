/**
 * 
 */
package cn.com.servlets.answer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.beans.AnswerBean;
import cn.com.daos.AnswerDaoImp;

/***********************
 * @author butterfly   
 * @version£º1.0        
 * @created£º2014-5-14    
 ***********************
 */

/**
 * @author butterfly
 *
 */
public class GetHotAnswersInfoServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		AnswerDaoImp answerDaoImp = new AnswerDaoImp();
		List<AnswerBean> listAnswersInfo = answerDaoImp.getHotAnswersByAnswerId(0);
		request.setAttribute("hotAnswers", listAnswersInfo);
		request.getRequestDispatcher("index.jsp").forward(request, response);
		//response.sendRedirect("index.jsp");
	}

}


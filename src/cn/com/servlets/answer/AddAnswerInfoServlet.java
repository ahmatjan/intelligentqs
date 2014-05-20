/**
 * 
 */
package cn.com.servlets.answer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.beans.AnswerBean;
import cn.com.daos.AnswerDaoImp;

/***********************
 * @author butterfly   
 * @version£º1.0        
 * @created£º2014-5-17    
 ***********************
 */

/**
 * @author butterfly
 *
 */
public class AddAnswerInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		String answerDescription = request.getParameter("answer_description");
		Date date = new Date();
		AnswerDaoImp answerDaoImp = new AnswerDaoImp();
		AnswerBean answerBean = new AnswerBean();
		String dateTimeString = (date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate()+"-"+"  "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
		answerBean.setAnswer_description(answerDescription);
		answerBean.setAnswer_time(dateTimeString);
		answerBean.setAnswer_user_id(1);
		answerBean.setQuestion__id(0);
		
		if(answerDaoImp.addAnswer(answerBean)){
			request.getRequestDispatcher("getHotAnswersInfo").forward(request, response);
		}else{
			request.setAttribute("Msg", "ÐÂÔöÊ§°Ü");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}


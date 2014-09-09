/**
 * 
 */
package cn.com.mservlets.answer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.beans.AnswerBean;
import cn.com.beans.UserInfoBean;
import cn.com.daos.AnswerDaoImp;

/***********************
 * @author butterfly   
 * @version：1.0        
 * @created：2014-5-17    
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
		
//		HttpSession session = request.getSession();
//		UserInfoBean userInfoBean = (UserInfoBean) session.getAttribute("userBean");
//		if(userInfoBean == null){
//			request.getRequestDispatcher("userLogin.jsp").forward(request, response);
//			return;
//		}
		
		String answerDescription = request.getParameter("answer_description");
		int user_id = Integer.parseInt(request.getParameter("userId"));
		int question_id = Integer.parseInt(request.getParameter("question_id"));
		Date date = new Date();
		AnswerDaoImp answerDaoImp = new AnswerDaoImp();
		AnswerBean answerBean = new AnswerBean();
		String dateTimeString = (date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate()+"-"+"  "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
		answerBean.setAnswer_description(answerDescription);
		answerBean.setAnswer_time(dateTimeString);
		answerBean.setAnswer_user_id(user_id);
		answerBean.setQuestion_id(question_id);
		
		if(answerDaoImp.addAnswer(answerBean)){
//			request.setAttribute("question_id", question_id);
//			request.getRequestDispatcher("getDetilQuestion").forward(request, response);
//			return ;
			System.out.println("新增成功");
		}else{
//			request.setAttribute("Msg", "新增失败");
//			request.getRequestDispatcher("index.jsp").forward(request, response);
			System.out.println("新增失败");
		}
	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}


package cn.com.servlets.discuss;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.beans.DiscussBean;
import cn.com.beans.UserInfoBean;
import cn.com.daos.DiscussDaoImp;
import cn.com.interfaces.DiscussDaoInf;

/**
 * Servlet implementation class AddDiscussServlet
 */
public class AddDiscussServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String discuss_content = request.getParameter("discuss_content");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//设置日期格式
		String discuss_time = sdf.format(new Date());
		int answer_id = Integer.parseInt(request.getParameter("answer_id"));
		int question_id = Integer.parseInt(request.getParameter("question_id"));
		
		HttpSession session = request.getSession();
		UserInfoBean userInfoBean = (UserInfoBean) session.getAttribute("userBean");
		if(userInfoBean == null){
			request.getRequestDispatcher("userLogin.jsp").forward(request, response);
			return;
		}
		int user_id = userInfoBean.getUser_id();
		
		DiscussBean discussBean = new DiscussBean();
		discussBean.setContent(discuss_content);
		discussBean.setTime(discuss_time);
		discussBean.setUser_id(user_id);
		discussBean.setAnswer_id(answer_id);
		
		DiscussDaoImp discussDao = new DiscussDaoImp();
		if(discussDao.addDiscuss(discussBean)){
			request.setAttribute("question_id", question_id);
			request.setAttribute("msg", "<font color='green'>评论成功！</font>");
			request.getRequestDispatcher("getDetilQuestion").forward(request,response);
			return;
		}else{
			request.setAttribute("msg", "<font color='red'>评论失败！</font>");
			request.getRequestDispatcher("getDetilQuestion").forward(request,response);
		}
	}

}

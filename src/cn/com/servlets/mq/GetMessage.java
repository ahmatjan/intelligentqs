package cn.com.servlets.mq;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.beans.UserInfoBean;
import cn.com.daos.UserInfoDaoImp;

public class GetMessage extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		//dPrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		UserInfoBean userInfoBean = (UserInfoBean) session.getAttribute("userBean");
		if(userInfoBean == null){
			request.getRequestDispatcher("userLogin.jsp").forward(request, response);
			return;
		}
		else{
			int userID = userInfoBean.getUser_id();
			UserInfoDaoImp userdao = new UserInfoDaoImp();
			UserInfoBean userbean = userdao.getUserInfoByUserId(userID);
			
			request.setAttribute("user", userbean);
			
			request.getRequestDispatcher("message.jsp").forward(request, response);
		}
	}
}

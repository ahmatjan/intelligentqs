package cn.com.servlets.mq;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.beans.UserInfoBean;
import redis.clients.jedis.Jedis;
import cn.com.util.RUtil;

public class Message extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		UserInfoBean userInfoBean = (UserInfoBean) session.getAttribute("userBean");
		if(userInfoBean == null){
			request.getRequestDispatcher("userLogin.jsp").forward(request, response);
			return;
		}
		else{
			RUtil redis = new RUtil();
			Jedis rdb = redis.con();
			String message = "userid:" + userInfoBean.getUser_id() + ":mq";
			ArrayList lists =  (ArrayList) rdb.lrange(message, 0, -1);
			out.write(lists.toString());
		}
	}
}

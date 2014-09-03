package cn.com.servlets.mq;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.beans.UserInfoBean;
import cn.com.util.RUtil;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;

public class Messages extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RUtil redis = new RUtil();
		Jedis rdb = redis.con();
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		UserInfoBean uib = (UserInfoBean) session.getAttribute("userBean");
	        
		if (uib != null) {
			String message = "userid:" + uib.getUser_id() + ":mq";
			Long messages = rdb.llen(message);
			out.println(messages);
		} 
		else{
			out.println(0);
		}
	}
}

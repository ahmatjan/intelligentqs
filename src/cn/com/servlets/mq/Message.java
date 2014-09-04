package cn.com.servlets.mq;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import cn.com.beans.UserInfoBean;
import redis.clients.jedis.Jedis;
import cn.com.util.RUtil;
import cn.com.servlets.mq.HandleMessage;

import cn.com.daos.MessageDao;
import cn.com.beans.MessageBean;

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
			HandleMessage handlemessage = new HandleMessage();
			ArrayList mqlist = new ArrayList();

			for(int i = 0; i < lists.size(); i++ ){
				handlemessage.set_str(((String) lists.get(i)).split(":"));
				mqlist.add(handlemessage.handle());
			}
				
			MessageBean msbean = new MessageBean();
			MessageDao msdao = new MessageDao();
			boolean mark = true;
			for(int j=0; j < mqlist.size(); j++ ){
				msbean.set_message(mqlist.get(j).toString());
				msbean.set_user_id(userInfoBean.getUser_id());
				if(!msdao.addMessage(msbean)){
					mark = false;
					break;
				}
			}
			if(mark){
				JSONObject json = new JSONObject();
				try {
					Collections.reverse(mqlist);
					json.put("mq", mqlist);		
				} catch (JSONException e) {
					e.printStackTrace();
				}
				rdb.ltrim(message, 1, 0);
				
				out.write(json.toString());
			}
			else{
				out.write(0);
			}
		}
	}
}

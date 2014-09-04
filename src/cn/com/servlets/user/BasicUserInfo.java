package cn.com.servlets.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import cn.com.beans.UserInfoBean;
import cn.com.daos.UserInfoDaoImp;
import cn.com.beans.BasicUserInfoBean;

import redis.clients.jedis.Jedis;
import cn.com.util.RUtil;
/**
 * @author banama
 */

public class BasicUserInfo extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			PrintWriter out = response.getWriter();
			int user_id =  Integer.parseInt(request.getParameter("user_id"));
			UserInfoDaoImp userDao = new UserInfoDaoImp();
			BasicUserInfoBean basicInfo = userDao.getBasicUserInfoByUserId(user_id);
			JSONObject json = new JSONObject();
			
			RUtil redis = new RUtil();
			Jedis rdb = redis.con();
			
			try {
				String select_solve = "userid:" + request.getParameter("user_id") + ":solve";
				String solves = rdb.hget("solve", select_solve);
				json.put("questions",basicInfo.get_questions());
				//json.put("solve",basicInfo.get_solve());
				json.put("solve",solves);
				json.put("stars",basicInfo.get_stars()); 
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				
			}
			out.write(json.toString());
		} 
}


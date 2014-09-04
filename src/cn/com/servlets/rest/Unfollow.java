package cn.com.servlets.rest;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import redis.clients.jedis.Jedis;
import cn.com.beans.QuestionBean;
import cn.com.beans.QuestionsKeywordsBean;
import cn.com.beans.UserInfoBean;
import cn.com.daos.QuestionDaoImp;
import cn.com.daos.QuestionsKeywordsDaoImp;
import cn.com.daos.UserInfoDaoImp;
import cn.com.util.ChineseAnalyzerUtil;
import redis.clients.jedis.Jedis;  
import cn.com.util.RUtil;

import org.json.JSONException;
import org.json.JSONObject;
/**
 * @author Banama
 * 
 * 		cancle follow a people (the people you have followed)
 * 		POST /unfollow following (following is the user_id)
 * 		success ? return 1 : return something other.
 */
public class Unfollow extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
           doPost(request, response);
	}
	

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RUtil redis = new RUtil();
		Jedis rdb = redis.con();
		
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        UserInfoBean uib = (UserInfoBean) session.getAttribute("userBean");
        
        if (uib == null) {
        	out.write("require login");
        }
        else {
        	String following = request.getParameter("following");
        	String uid = Integer.toString(uib.getUser_id());
        	String select_following = "user:" + uid + ":following";
            String select_follower = "user:" + following + ":follower";
            
            rdb.srem(select_following, following);
            rdb.srem(select_follower, uid);
            out.println(1);
        }
        
        out.flush();
        out.close();
	}
}
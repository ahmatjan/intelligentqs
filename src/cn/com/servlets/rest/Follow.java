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
import cn.com.util.RUtil;

import cn.com.mq.Notify;
/**
 * @author Banama
 */
public class Follow extends HttpServlet {

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
            
           // info notify
        	Notify notifys = new Notify();
        	notifys.set_userid(uib.getUser_id());
        	notifys.set_followingid(Integer.parseInt(following));
        	notifys.following();
        	
        	rdb.sadd(select_following, following);
        	rdb.sadd(select_follower, uid);
        	out.println(1);
        }
        out.flush();
        out.close();
	}
}
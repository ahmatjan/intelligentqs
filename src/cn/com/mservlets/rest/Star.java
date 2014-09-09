package cn.com.mservlets.rest;

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
 * 		
 * 		collect/star a question
 * 		POST /star question_id
 * 		success : return 1 : reutrun something other
 * 
 */
public class Star extends HttpServlet {

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
//        HttpSession session = request.getSession();
//        UserInfoBean uib = (UserInfoBean) session.getAttribute("userBean");
        
//        if (uib == null) {
//        	out.write("require login");
//        }
//        else {
        	String question_id = request.getParameter("question_id");
        	String uid = request.getParameter("userid");
        	String select_star = "user:" + uid + ":star";
            String select_srats = "questionid:" + question_id;
            System.out.println("id:"+uid);
            System.out.println(question_id);
            //star info notify
            Notify notifys = new Notify();
            notifys.set_userid(Integer.parseInt(uid));
            notifys.set_questionid(Integer.parseInt(question_id));
            notifys.star();
            
            int mark = (int) (rdb.zcard(select_star) + 1);
            rdb.zadd(select_star, mark, question_id);
            rdb.hincrBy("stars", select_srats, 1);
            out.println(1);
//        }
        
        out.flush();
        out.close();
	}
}
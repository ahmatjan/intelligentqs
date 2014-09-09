package cn.com.mservlets.rest;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
/**
 * @author Banama
 * 
 * 		if the question have praised by me 
 * 		POST /prepraise question_id 
 * 		yes ? return 1 : return 0  (and -1 is tread)
 */
public class Prepraise extends HttpServlet {

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
//        
//        if (uib == null) {
//        	out.write("require login");
//        }
//        else {
        	String question_id = request.getParameter("question_id");
        	String userId = request.getParameter("userid");
        	String select_praise = "userid:" + userId + ":questionid:" + question_id;
        	
        	//hget�������Ϊkey��hash��field��Ӧ��value
           	String mark = (String) rdb.hget("praise", select_praise);
        	if (mark == null || mark.equals("0")) {
        		out.println(0);
        	}
        	else if (mark.equals("-1")) {
        		out.println(-1);
        	}
        	else {
        		out.println(1);
        	}
//        }
        
        out.flush();
        out.close();
	}
}
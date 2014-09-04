package cn.com.servlets.rest;

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
import cn.com.mq.Notify;
/**
 * @author Banama
 * 
 * 		praise( zan ) a question
 * 		POST praise question_id
 * 		if the event success return "True", or return something other.
 */
public class Praise extends HttpServlet {

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
        	String question_id = request.getParameter("question_id");
        	String select_praise = "userid:" + uib.getUser_id() + ":questionid:" + question_id;
        	String select_praises = "questionid:" + question_id;
        	
        	QuestionDaoImp qsDao = new QuestionDaoImp();
            QuestionBean qs = qsDao.getQuestionByQuestionId(Integer.parseInt(question_id));
            int qs_mark = qs.getQuestion_mark();
        	//hget��������Ϊkey��hash��field��Ӧ��value
           	String mark = (String) rdb.hget("praise", select_praise);
        	if (mark == null || mark.equals("0")) {
        		// praise notify
        		Notify notifys = new Notify();
        		notifys.set_questionid(Integer.parseInt(question_id));
        		notifys.set_userid(uib.getUser_id());
        		notifys.praise();
        		
        		rdb.hset("praise", select_praise, "1");
        		rdb.hincrBy("praises", select_praises, 1);//hincrBy������Ϊpraises��hash��select_praises��value����1
        		String marks = (String) rdb.hget("praises", select_praises);
        		qsDao.updateQS_remark(Integer.parseInt(marks), Integer.parseInt(question_id)); 
        		out.write("True");
        	}
        	else if (mark.equals("-1")) {
        		// praise notify
        		Notify notifys = new Notify();
        		notifys.set_questionid(Integer.parseInt(question_id));
        		notifys.set_userid(uib.getUser_id());
        		notifys.praise();
        		
        		rdb.hset("praise", select_praise, "1");
        		rdb.hincrBy("praises", select_praises, 2);
        		String marks = (String) rdb.hget("praises", select_praises);
        		qsDao.updateQS_remark(Integer.parseInt(marks), Integer.parseInt(question_id)); 
        		out.write("True");
        	}
        	else {
        		rdb.hset("praise", select_praise, "0");
        		rdb.hincrBy("praises", select_praises, -1);
        		String marks = (String) rdb.hget("praises", select_praises);
        		qsDao.updateQS_remark(Integer.parseInt(marks), Integer.parseInt(question_id)); 
        		out.write("True");
        	}
        }
        
        out.flush();
        out.close();
	}
}
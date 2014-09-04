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
import cn.com.beans.AnswerBean;
import cn.com.beans.QuestionBean;
import cn.com.beans.QuestionsKeywordsBean;
import cn.com.beans.UserInfoBean;
import cn.com.daos.AnswerDaoImp;
import cn.com.daos.QuestionDaoImp;
import cn.com.daos.QuestionsKeywordsDaoImp;
import cn.com.daos.UserInfoDaoImp;
import cn.com.util.ChineseAnalyzerUtil;
import cn.com.util.RUtil;
import cn.com.mq.Notify;
/**
 * @author Banama
 * 
 * 		(choose the answer of question as the best one)
 * 		POST /accept question_id answer_id
 * 		susscess ? return 1 : return 0
 */
public class Accept extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RUtil redis = new RUtil();
		Jedis rdb = redis.con();
		
		response.setContentType("text/html");
       PrintWriter out = response.getWriter();
       HttpSession session = request.getSession();
       UserInfoBean uib = (UserInfoBean) session.getAttribute("userBean");
        
       if (uib == null) {
        	out.println(0);
        } 
        else {
        	String questionid = request.getParameter("question_id");
        	String answerid = request.getParameter("answer_id");
        	//String uid = Integer.toString(uib.getUser_id());
        	
        	QuestionDaoImp qsdao = new QuestionDaoImp();
        	QuestionBean qsbean = qsdao.getQuestionByQuestionId(Integer.parseInt(questionid));
        	
        	AnswerDaoImp asdao = new AnswerDaoImp();
        	AnswerBean asbean = asdao.getAnswerByAnswerId(Integer.parseInt(answerid.split("_")[1]));
        	
        	if (qsbean.getQuestion_user_id() != uib.getUser_id()) {
        		out.println(0);
        	}
        	else {
        		// accept notify (info system)
        		Notify notifys = new Notify();
        		notifys.set_answerid(Integer.parseInt(answerid.split("_")[1]));
        		notifys.set_userid(uib.getUser_id());
        		notifys.accept();
        		
        		String select_accept = "questionid:" + questionid;
              String select_solve = "userid:" + asbean.getAnswer_user_id() + ":solve";
              rdb.hset("accept", select_accept, answerid.split("_")[1]);
              rdb.hincrBy("solve", select_solve, 1);
              out.println(1);	
        	}
        }
        
        out.flush();
        out.close();
	}
}

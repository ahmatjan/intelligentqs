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
import cn.com.beans.AnswerBean;
import cn.com.beans.QuestionsKeywordsBean;
import cn.com.beans.UserInfoBean;
import cn.com.daos.AnswerDaoImp;
import cn.com.daos.QuestionDaoImp;
import cn.com.daos.QuestionsKeywordsDaoImp;
import cn.com.daos.UserInfoDaoImp;
import cn.com.util.ChineseAnalyzerUtil;
import redis.clients.jedis.Jedis;  
import cn.com.util.RUtil;
/**
 * @author Banama
 * 
 * 		praise( zan ) a answer
 * 		POST /praiseas answerid
 * 		if the event success return "True", or return something other.
 */
public class PraiseAS extends HttpServlet {

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
        	String answerid = request.getParameter("answerid");
        	String select_praiseas = "userid:" + uib.getUser_id() + ":answerid:" + answerid;
        	String select_praisesas = "answerid:" + answerid;
        	
            AnswerDaoImp ansDao = new AnswerDaoImp();
            int answer_id = Integer.parseInt(answerid.split("_")[1]);
            System.out.print(answer_id);
            AnswerBean ans = ansDao.getAnswerByAnswerId(answer_id);
            int ans_mark = ans.getAnswer_mark();
           	
          	//if no praise and no tread
    		String mark = (String) rdb.hget("praiseas", select_praiseas);
        	if (mark == null || mark.equals("0")) {
        		rdb.hset("praiseas", select_praiseas, "1");
        		rdb.hincrBy("praisesas", select_praisesas, 1);
        		String marks = (String) rdb.hget("praiseas", select_praiseas);
        		ansDao.updateAns_remark(Integer.parseInt(marks), answer_id);
        		out.write("True");
        	}

        	//if have treaded
        	else if (mark.equals("-1")) {        		
        		rdb.hset("praiseas", select_praiseas, "1");
        		rdb.hincrBy("praisesas", select_praisesas, 2);
        		String marks = (String) rdb.hget("praiseas", select_praiseas);
        		ansDao.updateAns_remark(Integer.parseInt(marks), answer_id);       		
        		out.write("True");
        	}
        	
        	//if have praised
        	else {     		
        		rdb.hset("praiseas", select_praiseas, "0");
        		rdb.hincrBy("praisesas", select_praisesas, -1);
        		String marks = (String) rdb.hget("praiseas", select_praiseas);
        		ansDao.updateAns_remark(Integer.parseInt(marks), answer_id);        		
        		out.write("True");
        	}
        }
        
        out.flush();
        out.close();
	}
}
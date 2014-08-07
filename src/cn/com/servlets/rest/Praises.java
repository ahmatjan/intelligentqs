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
 */
public class Praises extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//doPost(request, response);
		RUtil redis = new RUtil();
		Jedis rd = redis.con();
		rd.set("name", "1");
		String t = rd.get("name");
		PrintWriter out = response.getWriter();
		String text = "1";
		if (t.equals(text)){
			out.write("asas");
		}
		else {
			out.write(t + 's');
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RUtil redis = new RUtil();
		Jedis rd = redis.con();
		
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        String question_id = request.getParameter("question_id");
        String select_praises = "questionid:" + question_id;
        if (rd.hget("praises", select_praises) == null){
        	out.write("0");
        }
        else {
        	out.write(rd.hget("praises", select_praises));
        }
        
        out.flush();
        out.close();
	}
}
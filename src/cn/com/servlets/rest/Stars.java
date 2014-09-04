package cn.com.servlets.rest;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Collection;
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

import org.json.JSONException;
import org.json.JSONObject;
/**
 * @author Banama
 * 
 * 		the questions that someone collected/stared
 * 		POST /stars  rank user_id
 * 			Ps: the rank is for paging, for example, one is the first ten
 * 		return a json that the value of list is a question-list
 * 			{
 * 				"list":[
 * 					"questionid": <the questionid>,
 * 					"question": <the question title>
 * 				]
 * 			}
 */
public class Stars extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RUtil redis = new RUtil();
		Jedis rdb = redis.con();
		JSONObject json = new JSONObject();
		QuestionDaoImp questionDaoImp = new QuestionDaoImp();
		
		response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        UserInfoBean uib = (UserInfoBean) session.getAttribute("userBean");
        
        if (uib == null) {
        	out.write("require login");
        }
        else {
        	String rank = request.getParameter("rank");
        	String uid = request.getParameter("user_id");
        	String select_star = "user:" + uid + ":star";
            System.out.println(select_star);
        	int begin = 10 * Integer.parseInt(rank);
        	int end = 10 * (Integer.parseInt(rank) + 1) + 1;
            ArrayList stars = new ArrayList(rdb.zrange(select_star, begin, end));
        
            try {
            	ArrayList lists = new ArrayList(); 
            	for(int i=0; i < stars.size(); i++){
            		int questionid = Integer.parseInt((String) stars.get(i)); 
            		QuestionBean  questionBean = questionDaoImp.getQuestionByQuestionId(questionid);
            		JSONObject qs = new JSONObject();
            		qs.put("questionid", questionid);
            		qs.put("question", questionBean.getQuestion_title());
            		lists.add(qs);
            	}
				json.put("list", lists);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            System.out.print(json.toString());
            out.println(json.toString());
        }
        
        out.flush();
        out.close(); 
	}
}
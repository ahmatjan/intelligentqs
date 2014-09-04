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

import org.json.JSONException;
import org.json.JSONObject;
/**
 * @author Banama
 * 
 * 		how mant numbers the question hd been collected/stared\
 * 		POST /stared question_id
 * 		return the number 
 */
public class Stared extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RUtil redis = new RUtil();
		Jedis rdb = redis.con();

        PrintWriter out = response.getWriter();
        String question_id = request.getParameter("question_id");
        String select_star = "questionid:" + question_id;
        System.out.println(select_star);
        String stareds = rdb.hget("stars", select_star);
        System.out.println(stareds);
        String mark = "0";
        if (stareds != null) {
        	mark = stareds;
        }
        out.println(mark);
        out.flush();
        out.close();
	}
}
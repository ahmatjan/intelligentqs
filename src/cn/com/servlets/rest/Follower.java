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
 * 		the list for someone that have following user
 * 		POST /followers  user_id (the user_id is the one' id that you want to know who has followed it)
 * 		return a json that the value of 'follower' is a id-list
 */
public class Follower extends HttpServlet {

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
            JSONObject json = new JSONObject();
            
            String uid = request.getParameter("user_id");
            String select_follower = "user:" + uid + ":follower";  
            ArrayList follower = new ArrayList(rdb.smembers(select_follower));
            try {
                    json.put("follower", follower);
            } catch (JSONException e) {
                    // TODO Auto-generated catch block
            }
            out.println(json.toString());
            out.flush();
            out.close();
        }
}
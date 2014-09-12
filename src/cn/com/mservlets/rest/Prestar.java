package cn.com.mservlets.rest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;
import cn.com.util.RUtil;
/**
 * @author Banama
 * 
 * 		if the question had been collected/stared by me 
 * 		POST /prestar questionid
 * 		yes : return 1 : return 0
 * 		
 */
public class Prestar extends HttpServlet {

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
        	String questionid = request.getParameter("id");
        	String userid = request.getParameter("userid");
        	String uid = userid;
        	String select_following = "user:" + uid + ":star";
            boolean mark = rdb.zscore(select_following, questionid) == null;
            
            Map<String,String> map = new HashMap<String,String>();
            
            if (mark){
            	map.put("StarStatus", "0");
            }
            else{
            	map.put("StarStatus", "1");
            }
            JSONObject json = JSONObject.fromObject(map);
            out.println(json);
//        }
        out.flush();
        out.close();
	}
}
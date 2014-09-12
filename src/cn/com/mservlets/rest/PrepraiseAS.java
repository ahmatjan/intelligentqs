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
 *		if the answer have praised by me 
 * 		POST /prepraiseas answerid 
 * 		yes ? return 1 : return 0  (and -1 is tread)
 */
public class PrepraiseAS extends HttpServlet {

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
        	String answerids = request.getParameter("id");
        	String userid = request.getParameter("userid");
        	String answerid = "";
        	
        	if (answerids.split("_")[1] == "hot"){
        		answerid = answerids.split("_")[0] + "_" + answerids.split("_")[2];
        	}
        	else{
        		answerid = answerids;
        	}
        	System.out.print("~~" + answerid);
        	String select_praiseas = "userid:" + userid + ":answerid:" + answerid;
        	System.out.println("se-----" + select_praiseas);
           String mark = (String) rdb.hget("praiseas", select_praiseas);
            System.out.println("mark-" + mark);
            
            Map<String,String> map = new HashMap<String,String>();
            
        	if (mark == null || mark.equals("0")) {
        		map.put("status", "0");
        	}
        	else if (mark.equals("-1")) {
        		map.put("status", "-1");
        	}
        	else {
        		map.put("status", "1");
        	}
        	
        	JSONObject json = JSONObject.fromObject(map);
        	out.println(json);
        	System.out.println(json);
//        }
        
        out.flush();
        out.close();
	}
}
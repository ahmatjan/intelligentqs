package cn.com.servlets.rest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import redis.clients.jedis.Jedis;
import cn.com.util.RUtil;

public class AjaxTag extends HttpServlet{

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		RUtil redis = new RUtil();
		Jedis rdb = redis.con();
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String tagSinnpt = request.getParameter("tag_sinnpt").toLowerCase();
		
		String regex = "^" + tagSinnpt + "+";
		ArrayList<String> tagList = new ArrayList<String>(rdb.hkeys("tag"));
		ArrayList tags = new ArrayList();
		
		for( int i=0; i < tagList.size(); i++){
			JSONObject json = new JSONObject();
			Pattern p = Pattern.compile(regex);  
            Matcher m = p.matcher(tagList.get(i).toLowerCase());
            if(m.find()){
            	try {
            		json.put("tag", tagList.get(i).toString());
					json.put("info", rdb.hget("tag", tagList.get(i).toString()));
					tags.add(json);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
            
		}
		
		JSONObject jsons = new JSONObject();
		try {
			jsons.put("tag", tags);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.write(jsons.toString());
		
	}
}

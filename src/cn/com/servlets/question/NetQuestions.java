package cn.com.servlets.question;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import redis.clients.jedis.Jedis;
import cn.com.crawl.Crawl;
import cn.com.crawl.CrawlHandle;
import cn.com.util.RUtil;
import cn.com.daos.CrawlDao;
import cn.com.beans.CrawlBean;

public class NetQuestions extends HttpServlet{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RUtil redis = new RUtil();
		Jedis rdb = redis.con();
		PrintWriter out = response.getWriter();
		int question_id = Integer.parseInt(request.getParameter("question_id"));
		String select_crawl = "questionid:" + question_id + ":crawl";
		String mark = rdb.hget("crawl", select_crawl);
		
		// if the mysql no crawl question
		if (mark == null || mark == "0"){
			
			String keywords = request.getParameter("question_title");
			Crawl crawl = new Crawl();
			crawl.set_keywords(keywords);
			try {				
				JSONObject json = (JSONObject) crawl.Q360Crawl();
				// handle and crawl
				CrawlHandle handle = new CrawlHandle();
				
				handle.set_question_id(question_id);
				handle.set_crawljson(json);
				if(handle.handleq360()){
					rdb.hset("crawl", select_crawl, "1");
				}
				// handle and crawl end
					
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// if the question has been crawled
		JSONObject jsons = new JSONObject();
		CrawlDao cdao = new CrawlDao();
		CrawlBean cbean = new CrawlBean();
		List<CrawlBean> lists = cdao.getCrawlBeanByQuestionid(question_id);
		ArrayList<JSONObject> ls = new ArrayList<JSONObject>();
		
		for(int i=0; i < lists.size(); i++){
			cbean = lists.get(i);	
			try {
				JSONObject json = new JSONObject();
				json.put("question_id", cbean.get_question_id());
				json.put("title", cbean.get_title());
				json.put("url", cbean.get_url());
				ls.add(json);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		try {
			jsons.put("lists", ls);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.write(jsons.toString());
	}
	
}

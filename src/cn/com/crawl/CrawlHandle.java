package cn.com.crawl;

import org.json.JSONArray;
import org.json.JSONObject;

import cn.com.daos.CrawlDao;
import cn.com.beans.CrawlBean;
import cn.com.crawl.Crawl;

public class CrawlHandle {
	
	CrawlBean cbean = new CrawlBean();
	CrawlDao cdao = new CrawlDao();
	Crawl crawl = new Crawl();
	JSONObject crawljson = new JSONObject();
	
	private int question_id;
	
	public void set_question_id(int question_id){
		this.question_id = question_id;
	}
	
	public void set_crawljson(JSONObject crawljson){
		this.crawljson = crawljson;
	}
	
	public boolean handleq360(){
		try{
			JSONObject json = crawljson;
			JSONArray jsonarry = json.getJSONArray("1");
			for(int i=0; i<jsonarry.length(); i++){
				cbean.set_title(jsonarry.getJSONObject(i).get("title").toString());
				cbean.set_url(jsonarry.getJSONObject(i).get("url").toString());
				cbean.set_question_id(question_id);
				cdao.addCrawl(cbean);
			}
			return true;
		}
		catch(Exception e){
			System.out.print("\nhandle:" + e);
			return false;
		}
	}
}

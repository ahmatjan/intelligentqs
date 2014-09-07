package cn.com.crawl;


import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

import cn.com.crawl.BingCrawl;
import cn.com.crawl.Q360Crawl;

public class Crawl {
	
	private static String keywords;
	
	public void set_keywords(String keywords){
		this.keywords = keywords;
	}
	 
	public static Object Q360Crawl() throws IOException, JSONException{
		Q360Crawl crawl = new Q360Crawl();
		crawl.set_keywords(keywords);
		JSONObject json = crawl.run();
		return json;
	}
	
	public static void BingCrawl() throws IOException{
		BingCrawl crawl = new BingCrawl();
		crawl.set_keywords(keywords);
		crawl.run();
	}
}


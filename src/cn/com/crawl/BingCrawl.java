package cn.com.crawl;

import java.io.*;

import javax.swing.text.Document;

import org.jsoup.Jsoup;

import java.net.URLEncoder;
import java.util.HashMap;
import it.sauronsoftware.base64.Base64;

public class BingCrawl{
	
	private String accountKey = "SwRr/BwE1gHSA6EehbtNBK8sL1YN7CG/dcc0lrDcZeY=";
	private String top = "0";
	private String skip = "10";
	private String format = "json";
	
	private String url = "https://api.datamarket.azure.com/Bing/Search/Web?";
	private String keywords;
	
	public void set_url(String url){
		this.url = url;
	}
	
	public void set_keywords(String keywords){
		this.keywords = keywords;
	}
	
	public String make_url(){
		HashMap<String, String> payload = new HashMap<String, String>();
		payload.put("Query", "'" + keywords +"'");
		payload.put("$top", top);
		payload.put("$skip", skip);
		payload.put("$format", format);
		
		String urls = url + URLEncoder.encode(payload.toString());
		System.out.print(urls + "\n");
		return urls;		
	}
	
	public void run() throws IOException{
		String sAuth = "Basic " + Base64.encode(accountKey);  
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", sAuth);
		make_url();
		//String fetch_url = make_url();
		//org.jsoup.nodes.Document doc = Jsoup.connect(fetch_url).header(headers.toString(), fetch_url).get(); 
		System.out.print(sAuth);
	}
}


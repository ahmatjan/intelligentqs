package cn.com.crawl;

import java.io.IOException;
import java.net.URLEncoder;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.json.JSONException;
import org.json.JSONObject;


public class Q360Crawl {
	
	private String keywords;
	private String url = "http://brisk.eu.org/api/360.php?q=";
	
	public void set_keywords(String keywords){
		this.keywords = URLEncoder.encode(keywords);
	}
	
	public JSONObject run() throws IOException, JSONException{
		String urls = url + keywords;
		HttpClient httpclient = new HttpClient();  
		httpclient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);  
		GetMethod getMethod = new GetMethod(urls);  
		getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,5000);  
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());  
		try{  
			int statusCode = httpclient.executeMethod(getMethod);  
			if (statusCode != HttpStatus.SC_OK){  
				System.err.println("Method failed: " + getMethod.getStatusLine());  
			}
			byte[] responseBody = getMethod.getResponseBody();    
		      String body = new String(responseBody);  
		      JSONObject json = new JSONObject("{1:" + body + "}");
		      return json;
		}catch(HttpException e){  
			System.out.println("Please check your provided http address!");  
			e.printStackTrace();  
		}catch(IOException e){  
			e.printStackTrace();  
		}finally{   
			getMethod.releaseConnection();  
		}
		return null;  
		
	}

}

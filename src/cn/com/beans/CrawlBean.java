package cn.com.beans;

public class CrawlBean {
	
	private int question_id;
	private String title;
	private String url;
	
	public int get_question_id(){
		return this.question_id;
	}
	
	public String get_title(){
		return this.title;
	}
	
	public String get_url(){
		return this.url;
	}
	
	public void set_question_id(int question_id){
		this.question_id = question_id;
	}
	
	public void set_title(String title){
		this.title = title;
	}
	
	public void set_url(String url){
		this.url = url;
	}
}

package cn.com.beans;

public class MessageBean {
	private int user_id;
	private String message;
	
	public void set_user_id(int user_id){
		this.user_id = user_id;
	}
	
	public int get_user_id(){
		return this.user_id;
	}
	
	public void set_message(String message){
		this.message = message;
	}
	
	public String get_message(){
		return this.message;
	}
}

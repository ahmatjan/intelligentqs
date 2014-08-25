package cn.com.beans;

public class DiscussBean {
	private int discuss_id;
	private String content;
	private String time;
	private int user_id;
	private int answer_id;
	
	public int getDiscuss_id() {
		return discuss_id;
	}
	public void setDiscuss_id(int discuss_id) {
		this.discuss_id = discuss_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getAnswer_id() {
		return answer_id;
	}
	public void setAnswer_id(int answer_id) {
		this.answer_id = answer_id;
	}
}

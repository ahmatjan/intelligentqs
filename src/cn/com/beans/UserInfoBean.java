package cn.com.beans;
/**
 * 用户信息实体
 * @author Xianxiaofei
 * @date:2014-5-10 上午9:49:18
 */
public class UserInfoBean {
	//用户编号
	private int user_id;
	//用户名称
	private String user_name;
	//用户密码
	private String user_password;
	//用户邮箱
	private String user_email;
	//用户声望值,初始为0
	private int user_mark = 0;
	//兴趣标签（最多五个）
	private String user_tags;
	//用户头像地址
	private String user_logo;
	//对应的Get、Set方法
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public int getUser_mark() {
		return user_mark;
	}
	public void setUser_mark(int user_mark) {
		this.user_mark = user_mark;
	}
	public String getUser_tags() {
		return user_tags;
	}
	public void setUser_tags(String user_tags) {
		this.user_tags = user_tags;
	}
	public String getUser_logo() {
		return user_logo;
	}
	public void setUser_logo(String user_logo) {
		this.user_logo = user_logo;
	}
	
}

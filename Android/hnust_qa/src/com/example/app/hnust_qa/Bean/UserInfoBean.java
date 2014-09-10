package com.example.app.hnust_qa.Bean;
/**
 * �û���Ϣʵ��
 * @author Xianxiaofei
 * @date:2014-5-10 ����9:49:18
 */
public class UserInfoBean {
	//�û����
	private int user_id;
	//�û�����
	private String user_name;
	//�û�����
	private String user_password;
	//�û�����
	private String user_email;
	//�û�����ֵ,��ʼΪ0
	private int user_mark = 0;
	//��Ȥ��ǩ����������
	private String user_tags;
	//�û�ͷ���ַ
	private String user_logo;
	//��Ӧ��Get��Set����
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

package cn.com.beans;

public class UserAllInfoBean {
	private UserInfoBean userInfo;
	private int askTotal;
	private int ansTotal;
	public int getAskTotal() {
		return askTotal;
	}
	public void setAskTotal(int askTotal) {
		this.askTotal = askTotal;
	}
	public int getAnsTotal() {
		return ansTotal;
	}
	public void setAnsTotal(int ansTotal) {
		this.ansTotal = ansTotal;
	}
	public UserInfoBean getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfoBean userInfo) {
		this.userInfo = userInfo;
	}
}

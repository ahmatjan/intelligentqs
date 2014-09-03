package cn.com.mq;

import cn.com.daos.UserInfoDaoImp;
import cn.com.beans.UserInfoBean;


public class FollowSubject extends Subject{
	UserInfoDaoImp userdao = new UserInfoDaoImp();
	UserInfoBean userbean = new UserInfoBean();
	
	public void bong(int userid, int followingid){
		System.out.print("bong");
		userbean = userdao.getUserInfoByUserId(followingid);
		String str = "你被" + userbean.getUser_name() + "关注了";
		this.change(userid, str);
	}
}
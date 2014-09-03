package cn.com.mq;

import cn.com.mq.FollowSubject;
import cn.com.mq.FollowObserve;

public class Notify {
	
	private int userid;
	private int followingid;
	
	public void set_userid(int userid){
		this.userid = userid;
	}
	
	public void set_followingid(int followingid){
		this.followingid = followingid;
	}
	
	public void following(){
		FollowSubject subject = new FollowSubject();
		FollowObserve observe = new FollowObserve();
		
		System.out.print("notify");
		subject.attach(observe);
		subject.bong(userid, followingid);
	}
}

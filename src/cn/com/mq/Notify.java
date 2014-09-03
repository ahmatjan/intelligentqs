package cn.com.mq;

import cn.com.mq.FollowSubject;
import cn.com.mq.FollowObserve;

import cn.com.mq.StarSubject;
import cn.com.mq.StarObserve;

import cn.com.mq.AnswerSubject;
import cn.com.mq.AnswerObserve;

import cn.com.mq.PraiseSubject;
import cn.com.mq.PraiseObserve;

import cn.com.mq.TreadSubject;
import cn.com.mq.TreadObserve;

public class Notify {
	
	private int userid;
	private int followingid;
	private int questionid;
	
	public void set_userid(int userid){
		this.userid = userid;
	}
	
	public void set_followingid(int followingid){
		this.followingid = followingid;
	}
	
	public void set_questionid(int questionid){
		this.questionid = questionid;
	}
	
	
	// notify method
	public void following(){
		FollowSubject subject = new FollowSubject();
		FollowObserve observe = new FollowObserve();
		
		subject.attach(observe);
		subject.bong(userid, followingid);
		subject.remove(observe);
	}
	
	public void star(){
		StarSubject subject = new StarSubject();
		StarObserve observe = new StarObserve();
		
		subject.attach(observe);
		subject.bong(userid, questionid);
		subject.remove(observe);
	}
	
	public void answer(){
		AnswerSubject subject = new AnswerSubject();
		AnswerObserve observe = new AnswerObserve();
		
		subject.attach(observe);
		subject.bong(userid, questionid);
		subject.remove(observe);
	}
	
	public void praise(){
		PraiseSubject subject = new PraiseSubject();
		PraiseObserve observe = new PraiseObserve();
		
		subject.attach(observe);
		subject.bong(userid, questionid);
		subject.remove(observe);
	}
	
	public void tread(){
		TreadSubject subject = new TreadSubject();
		TreadObserve observe = new TreadObserve();
		
		subject.attach(observe);
		subject.bong(userid, questionid);
		subject.remove(observe);
	}
}

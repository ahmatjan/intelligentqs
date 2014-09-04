package cn.com.mq;

import cn.com.beans.AnswerBean;
import cn.com.beans.UserInfoBean;
import cn.com.daos.AnswerDaoImp;
import cn.com.daos.UserInfoDaoImp;

public class AcceptSubject extends Subject{
	AnswerDaoImp asdao = new AnswerDaoImp();
	AnswerBean asbean = new AnswerBean();
	
	UserInfoDaoImp userdao = new UserInfoDaoImp();
	UserInfoBean userbean =  new UserInfoBean();
	
	public void bong(int userid, int answerid){
		userbean = userdao.getUserInfoByUserId(userid);
		asbean = asdao.getAnswerByAnswerId(answerid);
		String str = "0:你的回答被采纳了:" + asbean.getQuestion_id();
		this.info(asbean.getAnswer_user_id(), str);
	}
}

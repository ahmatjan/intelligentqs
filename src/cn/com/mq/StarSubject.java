package cn.com.mq;

import cn.com.daos.QuestionDaoImp;
import cn.com.beans.QuestionBean;
import cn.com.daos.UserInfoDaoImp;
import cn.com.beans.UserInfoBean;

public class StarSubject extends Subject{
	QuestionDaoImp qsdao = new QuestionDaoImp();
	QuestionBean qsbean = new QuestionBean();
	
	UserInfoDaoImp userdao = new UserInfoDaoImp();
	UserInfoBean userbean =  new UserInfoBean();
	
	public void bong(int userid, int questionid){
		qsbean = qsdao.getQuestionByQuestionId(questionid);
		userbean = userdao.getUserInfoByUserId(userid);
		String str = "4:你的问题:" + qsbean.getQuestion_title() + ":被:" + userbean.getUser_name() + ":收藏了:" + questionid + ":" + userid;
		this.info(qsbean.getQuestion_user_id(), str);
	}
}

package cn.com.mq;

import cn.com.daos.QuestionDaoImp;
import cn.com.beans.QuestionBean;
import cn.com.daos.UserInfoDaoImp;
import cn.com.beans.UserInfoBean;

public class TreadSubject extends Subject{
	QuestionDaoImp qsdao = new QuestionDaoImp();
	QuestionBean qsbean = new QuestionBean();
	
	public void bong(int userid, int questionid){
		qsbean = qsdao.getQuestionByQuestionId(questionid);
		String str =  "你的问题" + qsbean.getQuestion_title() + "被踩了";
		this.info(qsbean.getQuestion_user_id(), str);
	}
}

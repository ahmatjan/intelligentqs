package cn.com.util;

import java.util.ArrayList;
import java.util.List;

import cn.com.beans.QuestionAllInfoBean;
import cn.com.beans.UserAllInfoBean;
import cn.com.beans.UserInfoBean;
import cn.com.daos.AnswerDaoImp;
import cn.com.daos.QuestionDaoImp;
import cn.com.daos.UserInfoDaoImp;

/**
 * 
 * @author Xianxiaofei007
 * 得到热门用户信息
 *
 */
public class GetHotUserInfoUtil {
	
	@SuppressWarnings("null")
	public static List<UserAllInfoBean> getHotUserInfoList(){
		List<UserAllInfoBean> list = new ArrayList<UserAllInfoBean>();
		UserInfoBean userInfo = null;
		AnswerDaoImp answerDao = new AnswerDaoImp();
		UserInfoDaoImp userDao = new UserInfoDaoImp();
		UserAllInfoBean userAllBean = null;
		
		List<UserInfoBean> listUser = userDao.getHotUserInfos();
		int len = listUser.size();
		if(len > 7){
			len = 7;
		}
		for(int i = 0; i < len;i++){
			userInfo = new UserInfoBean();
			userInfo = listUser.get(i);
			int ansTotal = answerDao.getAnswerByUserId(userInfo.getUser_id()).size();
			int askTotal = userDao.getBasicUserInfoByUserId(userInfo.getUser_id()).get_questions();
			userAllBean = new UserAllInfoBean();
			userAllBean.setAnsTotal(ansTotal);
			userAllBean.setAskTotal(askTotal);
			userAllBean.setUserInfo(userInfo);
			
			list.add(userAllBean);
		}
		return list;
	}
}

package cn.com.interfaces;

import java.util.List;

import cn.com.beans.UserInfoBean;

/**
 * @author Xianxiaofei
 * @date:2014-5-10 上午10:14:24
 */
public interface UserInfoDaoInf {

	// 通过用户名验证用户是否存在
	boolean validataByUserName(String user_name);

	// 登录验证
	boolean validateByUserNameAndUserPassword(String user_name,
			String user_password);

	// 根据用户名得到用户信息
	UserInfoBean getUserInfoByUserName(String user_name);

	// 根据id得到用户信息
	UserInfoBean getUserInfoByUserId(int user_id);

	// 得到所有信息
	List<UserInfoBean> getAllUserInfo();

	// 删除用户信息
	boolean deleteUserInfoByUserId(int user_id);

	// 按姓名模糊查询
	List<UserInfoBean> getUserInfoAllInfoByUserName(String user_name);

	// 新增用户信息(注册用)
	boolean addUserInfo(UserInfoBean uib);

	// 修改用户信息
	boolean updateUserInfoByUserId(UserInfoBean uib);

	// 获得用户总数
	int getCountOfUser();

	// 获得热门的用户
	List<UserInfoBean> getHotUserInfos();
}

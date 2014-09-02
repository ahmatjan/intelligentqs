package cn.com.interfaces;

import java.util.List;

import cn.com.beans.BasicUserInfoBean;
import cn.com.beans.UserInfoBean;

/**
 * @author Xianxiaofei
 * @date:2014-5-10 ����10:14:24
 */
public interface UserInfoDaoInf {

	// ͨ���û�����֤�û��Ƿ����
	boolean validataByUserName(String user_name);

	// ��¼��֤
	boolean validateByUserNameAndUserPassword(String user_name,
			String user_password);

	// ����û���õ��û���Ϣ
	UserInfoBean getUserInfoByUserName(String user_name);

	// ���id�õ��û���Ϣ
	UserInfoBean getUserInfoByUserId(int user_id);

	// �õ�������Ϣ
	List<UserInfoBean> getAllUserInfo();

	// ɾ���û���Ϣ
	boolean deleteUserInfoByUserId(int user_id);

	// ������ģ���ѯ
	List<UserInfoBean> getUserInfoAllInfoByUserName(String user_name);

	// �����û���Ϣ(ע����)
	boolean addUserInfo(UserInfoBean uib);

	// �޸��û���Ϣ
	boolean updateUserInfoByUserId(UserInfoBean uib);

	// ����û�����
	int getCountOfUser();

	// ������ŵ��û�
	List<UserInfoBean> getHotUserInfos();
	
	BasicUserInfoBean getBasicUserInfoByUserId(int user_id);
}

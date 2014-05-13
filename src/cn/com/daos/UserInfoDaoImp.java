package cn.com.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cn.com.beans.UserInfoBean;
import cn.com.db.DBUtil;
import cn.com.interfaces.UserInfoDaoInf;

/**
 * @author Xianxiaofei
 * @date:2014-5-10 ����10:16:17
 */
public class UserInfoDaoImp implements UserInfoDaoInf{
	
	private DBUtil db;
	
	public UserInfoDaoImp(){
		db = new DBUtil();
	}
	
	
	//ͨ���û�����֤�û��Ƿ����
	public boolean validataByUserName(String user_name) {
		Boolean bool=false;
		Connection conn=db.getConn();
		PreparedStatement   pst=null;
		ResultSet rs=null;
		String sql="select *from user_info  where user_name=?";
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1, user_name);
			rs=pst.executeQuery();
			bool=rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.free(rs, pst, conn);
		}
		return bool;
	}

	//��¼��֤
	public boolean validateByUserNameAndUserPassword(String user_name,
			String user_password) {
		Boolean bool=false;
		Connection conn=db.getConn();
		PreparedStatement   pst=null;
		ResultSet rs=null;
		String sql="select *from user_info  where user_name=? and user_password=?";
		try {
			pst=conn.prepareStatement(sql);
			pst.setString(1, user_name);
			pst.setString(2, user_password);
			rs=pst.executeQuery();
			bool=rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.free(rs, pst, conn);
		}
		return bool;
	}

	//�����û����õ��û���Ϣ
		public UserInfoBean getUserInfoByUserName(String user_name) {
			Connection conn=db.getConn();
			PreparedStatement   pst=null;
			ResultSet rs=null;
			UserInfoBean userBean = new UserInfoBean();
			String sql="select *from user_info  where user_name=?";
			try {
				pst=conn.prepareStatement(sql);
				pst.setString(1, user_name);
				rs=pst.executeQuery();
				while (rs.next()) {
					userBean.setUser_id(rs.getInt("user_id"));
					userBean.setUser_name(rs.getString("user_name"));
					userBean.setUser_password(rs.getString("user_password"));
					userBean.setUser_email(rs.getString("user_email"));
					userBean.setUser_mark(rs.getInt("user_mark"));
					userBean.setUser_tags(rs.getString("user_tags"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				db.free(rs, pst, conn);
			}
			return userBean;
		}

	
	//����id�õ��û���Ϣ
	public UserInfoBean getUserInfoByUserId(int user_id) {
		return null;
	}

	//ɾ���û���Ϣ
	public boolean deleteUserInfoByUserId(int user_id) {
		return false;
	}


	//���������õ��û���Ϣ
	public List<UserInfoBean> getUserInfoAllInfoByUserName(String user_name) {
		return null;
	}

	//�޸��û���Ϣ
	public boolean updateUserInfoByUserId(UserInfoBean uib) {
		return false;
	}

	//�õ�������Ϣ
	public List<UserInfoBean> getAllUserInfo() {
		return null;
	}

	//�����Ñ���Ϣ,ע�Ṧ��
	public boolean addUserInfo(UserInfoBean uib) {
		return false;
	}

	//ͳ���û�����
	public int getCountOfUser() {
		return 0;
	}


	/* (non-Javadoc)
	 * @see cn.com.interfaces.UserInfoDaoInf#getUserInfoByUserName(java.lang.String)
	 */
	
	
}

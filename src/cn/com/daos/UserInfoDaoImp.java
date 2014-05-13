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
 * @date:2014-5-10 上午10:16:17
 */
public class UserInfoDaoImp implements UserInfoDaoInf{
	
	private DBUtil db;
	
	public UserInfoDaoImp(){
		db = new DBUtil();
	}
	
	
	//通过用户名验证用户是否存在
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

	//登录验证
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

	//根据用户名得到用户信息
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

	
	//根据id得到用户信息
	public UserInfoBean getUserInfoByUserId(int user_id) {
		return null;
	}

	//删除用户信息
	public boolean deleteUserInfoByUserId(int user_id) {
		return false;
	}


	//根据姓名得到用户信息
	public List<UserInfoBean> getUserInfoAllInfoByUserName(String user_name) {
		return null;
	}

	//修改用户信息
	public boolean updateUserInfoByUserId(UserInfoBean uib) {
		return false;
	}

	//得到所有信息
	public List<UserInfoBean> getAllUserInfo() {
		return null;
	}

	//增加用戶信息,注册功能
	public boolean addUserInfo(UserInfoBean uib) {
		return false;
	}

	//统计用户总数
	public int getCountOfUser() {
		return 0;
	}


	/* (non-Javadoc)
	 * @see cn.com.interfaces.UserInfoDaoInf#getUserInfoByUserName(java.lang.String)
	 */
	
	
}

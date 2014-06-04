package cn.com.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.com.beans.UserInfoBean;
import cn.com.db.DBUtil;
import cn.com.interfaces.UserInfoDaoInf;

/**
 * @author Xianxiaofei
 * @date:2014-5-10 上午10:16:17
 */

/**
 * @author Friday
 * @date 2014-5-18 下午4:12:33
 */
public class UserInfoDaoImp implements UserInfoDaoInf {

	private DBUtil db;

	public UserInfoDaoImp() {
		db = new DBUtil();
	}

	// 通过用户名验证用户是否存在
	public boolean validataByUserName(String user_name) {
		Boolean bool = false;
		Connection conn = db.getConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select *from user_info  where user_name=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, user_name);
			rs = pst.executeQuery();
			bool = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.free(rs, pst, conn);
		}
		return bool;
	}

	// 登录验证
	public boolean validateByUserNameAndUserPassword(String user_name,
			String user_password) {
		Boolean bool = false;
		Connection conn = db.getConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select *from user_info  where user_name=? and user_password=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, user_name);
			pst.setString(2, user_password);
			rs = pst.executeQuery();
			bool = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.free(rs, pst, conn);
		}
		return bool;
	}

	// 根据用户名得到用户信息
	public UserInfoBean getUserInfoByUserName(String user_name) {
		Connection conn = db.getConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		UserInfoBean userBean = new UserInfoBean();
		String sql = "select *from user_info  where user_name=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, user_name);
			rs = pst.executeQuery();
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
		} finally {
			db.free(rs, pst, conn);
		}
		return userBean;
	}

	// 根据id得到用户信息
	public UserInfoBean getUserInfoByUserId(int user_id) {
		Connection conn = db.getConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		UserInfoBean userBean = new UserInfoBean();
		String sql = "select *from user_info  where user_id=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, user_id);
			rs = pst.executeQuery();
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
		} finally {
			db.free(rs, pst, conn);
		}
		return userBean;
	}

	// 删除用户信息
	public boolean deleteUserInfoByUserId(int user_id) {
		boolean bool = false;
		Connection conn = db.getConn();
		PreparedStatement pst = null;
		try {
			String sql = "delete from user_info where user_id = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, user_id);
			int len = pst.executeUpdate();
			if (len > 0) {
				bool = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.free(pst, conn);
		}
		return bool;
	}

	// 根据姓名模糊查询得到用户信息列表
	public List<UserInfoBean> getUserInfoAllInfoByUserName(String user_name) {
		Connection conn = db.getConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<UserInfoBean> list = null;
		UserInfoBean userBean = null;
		String sql = "select * from user_info where user_name like ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, "%" + user_name + "%");
			rs = pst.executeQuery();
			if (rs != null) {
				list = new ArrayList<UserInfoBean>();
				while (rs.next()) {
					userBean = new UserInfoBean();
					userBean.setUser_id(rs.getInt("user_id"));
					userBean.setUser_name(rs.getString("user_name"));
					userBean.setUser_password(rs.getString("user_password"));
					userBean.setUser_email(rs.getString("user_email"));
					userBean.setUser_mark(rs.getInt("user_mark"));
					userBean.setUser_tags(rs.getString("user_tags"));
					list.add(userBean);
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.free(rs, pst, conn);
		}
		return list;
	}

	// 修改用户信息
	public boolean updateUserInfoByUserId(UserInfoBean uib) {
		boolean bool = false;
		Connection conn = db.getConn();
		PreparedStatement pst = null;
		try {
			String sql = "update user_info set user_name=?,user_password=?,user_email=?,user_mark=?,user_tags=? where user_id=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, uib.getUser_name());
			pst.setString(2, uib.getUser_password());
			pst.setString(3, uib.getUser_email());
			pst.setInt(4, uib.getUser_mark());
			pst.setString(5, uib.getUser_tags());
			pst.setInt(6, uib.getUser_id());
			int len = pst.executeUpdate();
			if (len > 0) {
				bool = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.free(pst, conn);
		}
		return bool;
	}

	// 得到所有用户信息
	public List<UserInfoBean> getAllUserInfo() {
		List<UserInfoBean> list = null;
		UserInfoBean userBean = null;
		Connection conn = db.getConn();
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from user_info";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs != null) {
				list = new ArrayList<UserInfoBean>();
				while (rs.next()) {
					userBean = new UserInfoBean();
					userBean.setUser_id(rs.getInt("user_id"));
					userBean.setUser_name(rs.getString("user_name"));
					userBean.setUser_password(rs.getString("user_password"));
					userBean.setUser_email(rs.getString("user_email"));
					userBean.setUser_mark(rs.getInt("user_mark"));
					userBean.setUser_tags(rs.getString("user_tags"));
					list.add(userBean);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.free(rs, st, conn);
		}
		return list;
	}

	// 增加用戶信息,注册功能
	public boolean addUserInfo(UserInfoBean uib) {
		boolean bool = false;
		Connection conn = db.getConn();
		PreparedStatement pst = null;
		try {
			String sql = "Insert into user_info (user_name,user_password,user_email,user_mark,user_tags) values(?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, uib.getUser_name());
			pst.setString(2, uib.getUser_password());
			pst.setString(3, uib.getUser_email());
			pst.setInt(4, uib.getUser_mark());
			pst.setString(5, uib.getUser_tags());
			int len = pst.executeUpdate();
			if (len > 0) {
				bool = true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.free(pst, conn);
		}
		return bool;
	}

	// 统计用户总数
	public int getCountOfUser() {
		int count = 0;
		Connection conn = db.getConn();
		Statement st = null;
		ResultSet rs = null;
		String sql = "select count(*) totalUser from user_info";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs != null) {
				while (rs.next()) {
					count = rs.getInt("totalUser");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.free(rs, st, conn);
		}
		return count;
	}

	//获得热门的用户
	public List<UserInfoBean> getHotUserInfos() {
		// TODO Auto-generated method stub
		return null;
	}
}

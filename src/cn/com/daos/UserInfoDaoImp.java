package cn.com.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.com.beans.UserInfoBean;
import cn.com.interfaces.UserInfoDaoInf;
import cn.com.util.DBUtil;

/**
 * @author Xianxiaofei
 * @date:2014-5-10 ����10:16:17
 */

/**
 * @author Friday
 * @date 2014-5-18 ����4:12:33
 */
public class UserInfoDaoImp implements UserInfoDaoInf {

	private DBUtil db;

	public UserInfoDaoImp() {
		db = new DBUtil();
	}

	// ͨ���û�����֤�û��Ƿ����
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

	// ��¼��֤
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
	
	// �����һ�
		public boolean validateByUserNameAndUserEmail(String user_name,
				String user_email) {
			Boolean bool = false;
			Connection conn = db.getConn();
			PreparedStatement pst = null;
			ResultSet rs = null;
			String sql = "select *from user_info  where user_name=? and user_email=?";
			try {
				pst = conn.prepareStatement(sql);
				pst.setString(1, user_name);
				pst.setString(2, user_email);
				rs = pst.executeQuery();
				bool = rs.next();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				db.free(rs, pst, conn);
			}
			return bool;
		}

	// �����û����õ��û���Ϣ
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
				userBean.setUser_logo(rs.getString("user_logo"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.free(rs, pst, conn);
		}
		return userBean;
	}

	// ����id�õ��û���Ϣ
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
				userBean.setUser_logo(rs.getString("user_logo"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.free(rs, pst, conn);
		}
		return userBean;
	}

	// ɾ���û���Ϣ
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

	// ��������ģ����ѯ�õ��û���Ϣ�б�
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
					userBean.setUser_logo(rs.getString("user_logo"));
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

	// �޸��û���Ϣ
	public boolean updateUserInfoByUserId(UserInfoBean uib) {
		boolean bool = false;
		Connection conn = db.getConn();
		PreparedStatement pst = null;
		try {
			String sql = "update user_info set user_name=?,user_password=?,user_email=?,user_mark=?,user_tags=?,user_logo=? where user_id=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, uib.getUser_name());
			pst.setString(2, uib.getUser_password());
			pst.setString(3, uib.getUser_email());
			pst.setInt(4, uib.getUser_mark());
			pst.setString(5, uib.getUser_tags());
			pst.setString(6,uib.getUser_logo());
			pst.setInt(7, uib.getUser_id());
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

	// �õ������û���Ϣ
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
					userBean.setUser_logo(rs.getString("user_logo"));
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

	// �����Ñ���Ϣ,ע�Ṧ��
	public boolean addUserInfo(UserInfoBean uib) {
		boolean bool = false;
		Connection conn = db.getConn();
		PreparedStatement pst = null;
		try {
			String sql = "Insert into user_info (user_name,user_password,user_email,user_mark,user_tags,user_logo) values(?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, uib.getUser_name());
			pst.setString(2, uib.getUser_password());
			pst.setString(3, uib.getUser_email());
			pst.setInt(4, uib.getUser_mark());
			pst.setString(5, uib.getUser_tags());
			pst.setString(6,uib.getUser_logo());
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

	// ͳ���û�����
	public int getCountOfUser() {
		int count = 0;
		Connection conn = db.getConn();
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from user_info";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs != null) {
				while (rs.next()) {
					count = count + 1;
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

	//������ŵ��û�
	public List<UserInfoBean> getHotUserInfos() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//�ж��ַ����Ƿ�����ĸ�����ֹ���
	public boolean  isIleagle(String str){
		boolean bool = false;
		bool = str.matches("^[\\da-zA-Z]*$"); //û�����ַ��򷵻�true
		return bool;
	}
	
}

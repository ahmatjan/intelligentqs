package cn.com.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.beans.DiscussBean;
import cn.com.interfaces.DiscussDaoInf;
import cn.com.util.DBUtil;

public class DiscussDaoImp implements DiscussDaoInf{

	private  DBUtil db;
	
	public  DiscussDaoImp() {
		db = new DBUtil();
	}
	
	public DiscussBean getDiscussByDiscussId(int discuss_id) {
		DiscussBean discussBean = null;
		Connection con = db.getConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql= "select * from discuss where discuss_id = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, discuss_id);
			rs = pst.executeQuery();
			while (rs.next()) {
				discussBean = new DiscussBean();
				discussBean.setDiscuss_id(rs.getInt("discuss_id"));
				discussBean.setContent(rs.getString("discuss_content"));
				discussBean.setTime(rs.getString("discuss_time"));
				discussBean.setUser_id(rs.getInt("discuss_user_id"));
				discussBean.setAnswer_id(rs.getShort("discuss_answer_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.free(rs, pst, con);
		}
		return discussBean;
	}

	public List<DiscussBean> getDiscussByDiscussContent(String discuss_content) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean addDiscuss(DiscussBean discussBean) {
		Boolean bool = false;
		Connection conn = db.getConn();
		PreparedStatement pst = null;
		String sql = "insert into discuss(discuss_content, discuss_time, discuss_user_id, discuss_answer_id)VALUES(?,?,?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, discussBean.getContent());
			pst.setString(2, discussBean.getTime());
			pst.setInt(3, discussBean.getUser_id());
			pst.setInt(4, discussBean.getAnswer_id());
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

	public boolean deleteDiscussr(int answer_id) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateDiscuss(DiscussBean discussBean) {
		// TODO Auto-generated method stub
		return false;
	}

	public int getContOfAnswerDiscuss(int disscussId) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<DiscussBean> getHotDiscussByAnswerDiscussId(int discussId) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<DiscussBean> getDiscussByAnswerId(int answer_id) {
		List<DiscussBean> listDiscuss = new ArrayList<DiscussBean>();
		DiscussBean discussBean = null;
		Connection con = db.getConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql= "select * from discuss where discuss_answer_id = ? order by discuss_id desc";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, answer_id);
			rs = pst.executeQuery();
			while (rs.next()) {
				discussBean = new DiscussBean();
				discussBean.setDiscuss_id(rs.getInt("discuss_id"));
				discussBean.setContent(rs.getString("discuss_content"));
				discussBean.setTime(rs.getString("discuss_time"));
				discussBean.setUser_id(rs.getInt("discuss_user_id"));
				discussBean.setAnswer_id(rs.getShort("discuss_answer_id"));
				listDiscuss.add(discussBean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.free(rs, pst, con);
		}
		return listDiscuss;
	}

}

package cn.com.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.beans.MessageBean;
import cn.com.beans.QuestionBean;
import cn.com.util.DBUtil;

public class MessageDao {

	private  DBUtil db;
	
	public MessageDao(){
		db = new DBUtil();
	}
	
	public boolean addMessage(MessageBean message){
		Boolean bool = false;
		Connection conn = db.getConn();
		PreparedStatement pst = null;
		String sql = "insert into message(user_id, message)values(?,?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, message.get_user_id());
			pst.setString(2, message.get_message());
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
	
	public List<MessageBean> getMessageByUserid(int userid){
		Connection conn = db.getConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<MessageBean> list = null;
		MessageBean ms = null;
		String sql = "select * from message where user_id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, userid);
			rs = pst.executeQuery();
			if( rs != null){
				list = new ArrayList<MessageBean>();
				while (rs.next()) {
					ms = new MessageBean();
					ms.set_user_id(rs.getInt("user_id"));
					ms.set_message(rs.getString("message"));
					list.add(ms);
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
}

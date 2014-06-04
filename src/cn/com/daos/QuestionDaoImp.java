package cn.com.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.com.beans.QuestionBean;
import cn.com.db.DBUtil;
import cn.com.interfaces.QuestionDaoInf;

/**
 * @author Friday
 * @date 2014-5-13 下午5:17:25
 */
public class QuestionDaoImp implements QuestionDaoInf {

	private DBUtil db;

	public QuestionDaoImp() {
		db = new DBUtil();
	}

	// 根据id得到问题信息
	public QuestionBean getQuestionByQuestionId(int question_id) {
		Connection conn = db.getConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		QuestionBean qb = new QuestionBean();
		String sql = "select *from question  where question_id=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, question_id);
			rs = pst.executeQuery();
			while (rs.next()) {
				qb.setQuestion_id(rs.getInt("question_id"));
				qb.setQuestion_title(rs.getString("question_title"));
				qb.setQuestion_description(rs.getString("question_description"));
				qb.setQuestion_time(rs.getString("question_time"));
				qb.setQuestion_user_id(rs.getInt("question_user_id"));
				qb.setQuestion_mark(rs.getInt("question_mark"));
				qb.setQuestion_tags(rs.getString("question_tags"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.free(rs, pst, conn);
		}
		return qb;
	}

	// 根据标问题题目得到问题信息
	public List<QuestionBean> getQuestionByQuestionName(String question_title) {
		Connection conn = db.getConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<QuestionBean> list = null;
		QuestionBean qb = null;
		String sql = "select * from question where question_title like ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, "%" + question_title + "%");
			rs = pst.executeQuery();
			if (rs != null) {
				list = new ArrayList<QuestionBean>();
				while (rs.next()) {
					qb = new QuestionBean();
					qb.setQuestion_id(rs.getInt("question_id"));
					qb.setQuestion_title(rs.getString("question_title"));
					qb.setQuestion_description(rs
							.getString("question_description"));
					qb.setQuestion_time(rs.getString("question_time"));
					qb.setQuestion_user_id(rs.getInt("question_user_id"));
					qb.setQuestion_mark(rs.getInt("question_mark"));
					qb.setQuestion_tags(rs.getString("question_tags"));
					list.add(qb);
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

	// 根据标问题内容得到问题信息
	public List<QuestionBean> getQuestionByQuestionDescription(
			String question_description) {
		Connection conn = db.getConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<QuestionBean> list = null;
		QuestionBean qb = null;
		String sql = "select * from question where question_description like ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, "%" + question_description + "%");
			rs = pst.executeQuery();
			if (rs != null) {
				list = new ArrayList<QuestionBean>();
				while (rs.next()) {
					qb = new QuestionBean();
					qb.setQuestion_id(rs.getInt("question_id"));
					qb.setQuestion_title(rs.getString("question_title"));
					qb.setQuestion_description(rs
							.getString("question_description"));
					qb.setQuestion_time(rs.getString("question_time"));
					qb.setQuestion_user_id(rs.getInt("question_user_id"));
					qb.setQuestion_mark(rs.getInt("question_mark"));
					qb.setQuestion_tags(rs.getString("question_tags"));
					list.add(qb);
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

	// 新增问题信息
	public boolean addQuestion(QuestionBean questionBean) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection connection = db.getConn();
		PreparedStatement pstm = null;
		try {
			String sql = "Insert into question (question_title,question_description,question_time,question_mark,question_user_id,question_tags) values(?,?,?,?,?,?)";
			pstm = connection.prepareStatement(sql);
			pstm.setString(1, questionBean.getQuestion_title());
			pstm.setString(2, questionBean.getQuestion_description());
			pstm.setString(3, questionBean.getQuestion_time());
			pstm.setInt(4, questionBean.getQuestion_mark());
			pstm.setInt(5, questionBean.getQuestion_user_id());
			pstm.setString(6, questionBean.getQuestion_tags());
			int len = pstm.executeUpdate();
			if (len > 0) {
				bool = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.free(pstm, connection);
		}
		return bool;
	}

	// 删除问题信息
	public boolean deleteQuestion(int question_id) {
		boolean bool = false;
		Connection conn = db.getConn();
		PreparedStatement pst = null;
		try {
			String sql = "delete from question where question_id = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, question_id);
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

	// 修改问题信息
	public boolean updateQuestion(QuestionBean questionBean) {
		boolean bool = false;
		Connection conn = db.getConn();
		PreparedStatement pst = null;
		try {
			String sql = "update question set question_title = ?,question_description = ?,question_time = ?,question_mark = ?,question_user_id = ?,question_tags = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, questionBean.getQuestion_title());
			pst.setString(2, questionBean.getQuestion_description());
			pst.setString(3, questionBean.getQuestion_time());
			pst.setInt(4, questionBean.getQuestion_mark());
			pst.setInt(5, questionBean.getQuestion_user_id());
			pst.setString(6, questionBean.getQuestion_tags());
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

	// 统计问题总数
	public int getContOfQuestion() {
		int count = 0;
		Connection conn = db.getConn();
		Statement st = null;
		ResultSet rs = null;
		String sql = "select count(*) totalQuestion from question";
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			if (rs != null) {
				while (rs.next()) {
					count = rs.getInt("totalQuestion");
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
	
	//获得热门问题
	public List<QuestionBean> getHotQuestions() {
			Connection conn = db.getConn();
			PreparedStatement pst = null;
			ResultSet rs = null;
			List<QuestionBean> list = null;
			QuestionBean qb = null;
			String sql = "select * from question order by question_mark desc";
			try {
				pst = conn.prepareStatement(sql);
				rs = pst.executeQuery();
				if (rs != null) {
					list = new ArrayList<QuestionBean>();
					while (rs.next()) {
						qb = new QuestionBean();
						qb.setQuestion_id(rs.getInt("question_id"));
						qb.setQuestion_title(rs.getString("question_title"));
						qb.setQuestion_description(rs
								.getString("question_description"));
						qb.setQuestion_time(rs.getString("question_time"));
						qb.setQuestion_user_id(rs.getInt("question_user_id"));
						qb.setQuestion_mark(rs.getInt("question_mark"));
						qb.setQuestion_tags(rs.getString("question_tags"));
						list.add(qb);
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
	
	//获得所有问题
	public List<QuestionBean> getAllQuestions(int row, int countPage) {
		Connection conn = db.getConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<QuestionBean> list = null;
		QuestionBean qb = null;
		String sql = "select * from question order by question_id desc limit ?,? ";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, row);
			pst.setInt(2, countPage);
			rs = pst.executeQuery();
			if (rs != null) {
				list = new ArrayList<QuestionBean>();
				while (rs.next()) {
					qb = new QuestionBean();
					qb.setQuestion_id(rs.getInt("question_id"));
					qb.setQuestion_title(rs.getString("question_title"));
					qb.setQuestion_description(rs
							.getString("question_description"));
					qb.setQuestion_time(rs.getString("question_time"));
					qb.setQuestion_user_id(rs.getInt("question_user_id"));
					qb.setQuestion_mark(rs.getInt("question_mark"));
					qb.setQuestion_tags(rs.getString("question_tags"));
					list.add(qb);
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

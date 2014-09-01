package cn.com.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.com.beans.QuestionBean;
import cn.com.interfaces.QuestionDaoInf;
import cn.com.util.DBUtil;

/**
 * @author Friday
 * @date 2014-5-13 ����5:17:25
 */
public class QuestionDaoImp implements QuestionDaoInf {

	private DBUtil db;

	public QuestionDaoImp() {
		db = new DBUtil();
	}

	// ����id�õ�������Ϣ
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
				qb.setQuestion_categories_id(rs.getInt("question_categories_id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			db.free(rs, pst, conn);
		}
		return qb;
	}

	// ���ݱ�������Ŀ�õ�������Ϣ
	public List<QuestionBean> getQuestionByQuestionName(String question_title) {
		Connection conn = db.getConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<QuestionBean> list = null;
		QuestionBean qb = null;
		String sql = "select * from question where question_title like ? or question_description like ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, "%" + question_title + "%");
			pst.setString(2, "%" + question_title + "%");
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
					qb.setQuestion_categories_id(rs.getInt("question_categories_id"));
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

	// ���ݱ��������ݵõ�������Ϣ
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
					qb.setQuestion_categories_id(rs.getInt("question_categories_id"));
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

	// ����������Ϣ
	public boolean addQuestion(QuestionBean questionBean) {
		// TODO Auto-generated method stub
		boolean bool = false;
		Connection connection = db.getConn();
		PreparedStatement pstm = null;
		try {
			String sql = "Insert into question (question_title,question_description,question_time,question_mark,question_user_id,question_tags,question_categories_id) values(?,?,?,?,?,?,?)";
			pstm = connection.prepareStatement(sql);
			pstm.setString(1, questionBean.getQuestion_title());
			pstm.setString(2, questionBean.getQuestion_description());
			pstm.setString(3, questionBean.getQuestion_time());
			pstm.setInt(4, questionBean.getQuestion_mark());
			pstm.setInt(5, questionBean.getQuestion_user_id());
			pstm.setString(6, questionBean.getQuestion_tags());
			pstm.setInt(7, questionBean.getQuestion_categories_id());
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

	// ɾ��������Ϣ
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

	// �޸�������Ϣ
	public boolean updateQuestion(QuestionBean questionBean) {
		boolean bool = false;
		Connection conn = db.getConn();
		PreparedStatement pst = null;
		try {
			String sql = "update question set question_title = ?,question_description = ?,question_time = ?,question_mark = ?,question_user_id = ?,question_tags = ?,question_categories_id = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, questionBean.getQuestion_title());
			pst.setString(2, questionBean.getQuestion_description());
			pst.setString(3, questionBean.getQuestion_time());
			pst.setInt(4, questionBean.getQuestion_mark());
			pst.setInt(5, questionBean.getQuestion_user_id());
			pst.setString(6, questionBean.getQuestion_tags());
			pst.setInt(7, questionBean.getQuestion_categories_id());
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

	// ͳ����������
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

	// ����������ID�õ����û����ʵ�������Ϣ
	public int getContOfQuestionByUserId(int question_user_id) {
		int count = 0;
		Connection conn = db.getConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select count(*) totalQuestion from question where question_user_id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, question_user_id);
			rs = pst.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					count = rs.getInt("totalQuestion");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.free(rs, pst, conn);
		}
		return count;
	}

	// �����������
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
					qb.setQuestion_categories_id(rs.getInt("question_categories_id"));
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

	// �����������
	public List<QuestionBean> getAllQuestions(int row, int countPage) {
		Connection conn = db.getConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<QuestionBean> list = null;
		QuestionBean qb = null;
		String sql = "select * from question order by question_id desc limit ?,? ";
		// ��һ�Σ�ϵͳ���������������SQL���
		// String sql = "select * from question order by question_id desc";
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
					qb.setQuestion_categories_id(rs.getInt("question_categories_id"));
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

	// ����������ID�õ�����
	public List<QuestionBean> getQuestionByUserId(int userId, int row,
			int countPage) {
		Connection conn = db.getConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<QuestionBean> list = null;
		QuestionBean qb = null;
		String sql = "select * from question where question_user_id = ? order by question_id desc limit ?,?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, userId);
			pst.setInt(2, row);
			pst.setInt(3, countPage);
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
					qb.setQuestion_categories_id(rs.getInt("question_categories_id"));
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

	// �������������Ϣ
	public List<QuestionBean> getHotQuestionsBySearch(String context) {
		Connection conn = db.getConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<QuestionBean> list = null;
		QuestionBean qb = null;
		String sql = "select * from question where question_title like ? or question_description like ? order by question_mark desc";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, "%" + context + "%");
			pst.setString(2, "%" + context + "%");
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
					qb.setQuestion_categories_id(rs.getInt("question_categories_id"));
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

	// �����������ݵõ�������Ϣ
	public QuestionBean getQuestionByQuestionByDescription(
			String question_description) {
		Connection conn = db.getConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		QuestionBean qb = new QuestionBean();
		String sql = "select * from question where question_description = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, question_description);
			rs = pst.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					qb.setQuestion_id(rs.getInt("question_id"));
					qb.setQuestion_title(rs.getString("question_title"));
					qb.setQuestion_description(rs
							.getString("question_description"));
					qb.setQuestion_time(rs.getString("question_time"));
					qb.setQuestion_user_id(rs.getInt("question_user_id"));
					qb.setQuestion_mark(rs.getInt("question_mark"));
					qb.setQuestion_tags(rs.getString("question_tags"));
					qb.setQuestion_categories_id(rs.getInt("question_categories_id"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.free(rs, pst, conn);
		}
		return qb;
	}

	// �����������õ�������Ϣ
	public boolean getQuestionByQuestionByTitle(String question_title) {
		Connection conn = db.getConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		QuestionBean qb = new QuestionBean();
		boolean bool = false;
		String sql = "select * from question where question_title = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, question_title);
			rs = pst.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					// qb.setQuestion_id(rs.getInt("question_id"));
					// qb.setQuestion_title(rs.getString("question_title"));
					// qb.setQuestion_description(rs
					// .getString("question_description"));
					// qb.setQuestion_time(rs.getString("question_time"));
					// qb.setQuestion_user_id(rs.getInt("question_user_id"));
					// qb.setQuestion_mark(rs.getInt("question_mark"));
					// qb.setQuestion_tags(rs.getString("question_tags"));
					bool = true;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.free(rs, pst, conn);
		}
		return bool;
	}

	public String updateQS_remark(int remark, int question_id) {
		Connection conn = db.getConn();
		PreparedStatement pst = null;
		boolean rs = true;
		QuestionBean qb = new QuestionBean();
		String sql = "update question set question_mark = ? where question_id = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setLong(1, remark);
			pst.setLong(2, question_id);
			rs = pst.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.free(pst, conn);
		}
		return "True";
	}

}

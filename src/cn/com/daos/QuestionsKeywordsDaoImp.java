package cn.com.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.beans.AnswersKeywordsBean;
import cn.com.beans.QuestionBean;
import cn.com.beans.QuestionsKeywordsBean;
import cn.com.interfaces.QuesionsKeywordsInf;
import cn.com.util.DBUtil;

/**
 * @author Xianxiaofei
 * @date:2014年7月30日 下午5:57:41
 */
public class QuestionsKeywordsDaoImp implements QuesionsKeywordsInf {

	private DBUtil db;

	public QuestionsKeywordsDaoImp() {
		db = new DBUtil();
	}
	
	public QuestionsKeywordsBean getQuestionKeyByQuestionId(int questions_id) {
		// 根据标问题题目得到问题信息
		Connection conn = db.getConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		QuestionsKeywordsBean qb = new QuestionsKeywordsBean();
		String sql = "select * from questions_keywords where questions_id =  ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, questions_id);
			rs = pst.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					
					qb.setQuestions_keywords_id(rs.getInt("questions_keywords_id"));
					qb.setQuestion_id(rs.getInt("questions_id"));
					qb.setQuesitons_keywords_topN(rs.getString("quesitons_keywords_topN"));
					qb.setQuestions_keywords_counts(rs.getString("questions_keywords_counts"));
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

	public List<QuestionsKeywordsBean> getQuestionsKeywordsByKeyWords(
			String keywords) {
		// 根据标问题题目得到问题信息
			Connection conn = db.getConn();
			PreparedStatement pst = null;
			ResultSet rs = null;
			List<QuestionsKeywordsBean> list = null;
			QuestionsKeywordsBean qb = null;
			String sql = "select * from questions_keywords where quesitons_keywords_topN like ?";
			try {
				pst = conn.prepareStatement(sql);
				pst.setString(1, "%" + keywords + "%");
				rs = pst.executeQuery();
				if (rs != null) {
					list = new ArrayList<QuestionsKeywordsBean>();
					while (rs.next()) {
						qb = new QuestionsKeywordsBean();
						qb.setQuestions_keywords_id(rs.getInt("questions_keywords_id"));
						qb.setQuestion_id(rs.getInt("questions_id"));
						qb.setQuesitons_keywords_topN(rs.getString("quesitons_keywords_topN"));
						qb.setQuestions_keywords_counts(rs.getString("questions_keywords_counts"));
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

	public boolean addQuestionsKeywordsBean(
			QuestionsKeywordsBean questionsKeywordsBean) {
		// TODO Auto-generated method stub
					boolean bool = false;
					Connection connection = db.getConn();
					PreparedStatement pstm = null;
					try {
						String sql = "Insert into questions_keywords (questions_id,quesitons_keywords_topN,questions_keywords_counts) values(?,?,?)";
						pstm = connection.prepareStatement(sql);
						pstm.setInt(1, questionsKeywordsBean.getQuestion_id());
						pstm.setString(2, questionsKeywordsBean.getQuesitons_keywords_topN());
						pstm.setString(3, questionsKeywordsBean.getQuestions_keywords_counts());
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

	public boolean deleteQuestionsKeywordsBean(int questions_keywords_id){
		return false;
	}

}

package cn.com.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.beans.AnswersKeywordsBean;
import cn.com.beans.QuestionsKeywordsBean;
import cn.com.interfaces.AnswersKeywordsInf;
import cn.com.util.DBUtil;

/**
 * @author Xianxiaofei
 * @date:2014年7月30日 下午5:58:09
 */
public class AnswersKeywordsDaoImp implements AnswersKeywordsInf {

	private DBUtil db;

	public AnswersKeywordsDaoImp() {
		db = new DBUtil();
	}
	public AnswersKeywordsBean getDiscussByAnswersKeywordsId(
			int answers_keywords_id) {
		return null;
	}

	public List<AnswersKeywordsBean> AnswersKeywords(String keywords) {
			// 根据标问题题目得到问题信息
				Connection conn = db.getConn();
				PreparedStatement pst = null;
				ResultSet rs = null;
				List<AnswersKeywordsBean> list = null;
				AnswersKeywordsBean qb = null;
				String sql = "select * from questions_keywords where quesitons_keywords_topN like ?";
				try {
					pst = conn.prepareStatement(sql);
					pst.setString(1, "%" + keywords + "%");
					rs = pst.executeQuery();
					if (rs != null) {
						list = new ArrayList<AnswersKeywordsBean>();
						while (rs.next()) {
							qb = new AnswersKeywordsBean();
							qb.setAnswers_keywords_id(rs.getInt("questions_keywords_id"));
							qb.setAnswers_id(rs.getInt("questions_id"));
							qb.setAnswers_keywords_topN(rs.getString("quesitons_keywords_topN"));
							qb.setAnswers_keywords_counts(rs.getString("questions_keywords_counts"));
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

	public boolean addAnswersKeywordsBean(
			AnswersKeywordsBean answersKeywordsBean) {
		// TODO Auto-generated method stub
				boolean bool = false;
				Connection connection = db.getConn();
				PreparedStatement pstm = null;
				try {
					String sql = "Insert into answers_keywords (answers_id,answers_keywords_topN,answers_keywords_counts) values(?,?,?)";
					pstm = connection.prepareStatement(sql);
					pstm.setInt(1, answersKeywordsBean.getAnswers_id());
					pstm.setString(2, answersKeywordsBean.getAnswers_keywords_topN());
					pstm.setString(3, answersKeywordsBean.getAnswers_keywords_counts());
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

	public boolean deleteAnswersKeywordsBean(int answers_keywords_id) {
		return false;
	}

}

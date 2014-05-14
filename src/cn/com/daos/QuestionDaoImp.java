package cn.com.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import cn.com.beans.QuestionBean;
import cn.com.beans.UserInfoBean;
import cn.com.db.DBUtil;
import cn.com.interfaces.QuestionDaoInf;
import cn.com.interfaces.UserInfoDaoInf;

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
		// TODO Auto-generated method stub
		return null;
	}

	// 根据标问题题目得到问题信息
	public List<QuestionBean> getQuestionByQuestionName(String question_title) {
		// TODO Auto-generated method stub
		return null;
	}

	// 根据标问题内容得到问题信息
	public List<QuestionBean> getQuestionByQuestionDescription(
			String question_description) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return false;
	}

	// 修改问题信息
	public boolean updateQuestion(QuestionBean questionBean) {
		// TODO Auto-generated method stub
		return false;
	}

	// 统计问题总数
	public int getContOfQuestion() {
		// TODO Auto-generated method stub
		return 0;
	}

}

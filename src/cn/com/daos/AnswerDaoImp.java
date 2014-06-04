package cn.com.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.beans.AnswerBean;
import cn.com.beans.TagsInfoBean;
import cn.com.db.DBUtil;
import cn.com.interfaces.AnswerDaoInf;

/**
 * @author Xianxiaofei
 * @date:2014-5-13 下午5:26:19
 */
public class AnswerDaoImp implements AnswerDaoInf{
	
	private  DBUtil db;
	
	public  AnswerDaoImp(){
		db = new DBUtil();
	}

	//根据id得到问题信息
	public AnswerBean getAnswerByAnswerId(int answer_id) {
		AnswerBean answerBean = null;
		Connection con = db.getConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql= "select * from answer where tag_id = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, answer_id);
			rs = pst.executeQuery();
			while (rs.next()) {
				answerBean = new AnswerBean();
				answerBean.setAnswer_id(rs.getInt("answer_id"));
				answerBean.setAnswer_description(rs.getString("answer_description"));
				answerBean.setAnswer_time(rs.getString("answer_time"));
				answerBean.setAnswer_mark(rs.getInt("answer_mark"));
				answerBean.setAnswer_user_id(rs.getInt("answer_user_id"));
				answerBean.setQuestion__id(rs.getInt("question_id"));
				answerBean.setAnswer_best(rs.getInt("answer_best"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.free(rs, pst, con);
		}
		return answerBean;

	}
	//根据标回答描述得到回答信息
	public List<AnswerBean> getAnswerByAnswerDescription(String answer_description) {
		List<AnswerBean> listAnswerBeans = new ArrayList<AnswerBean>();
		AnswerBean answerBean = null;
		Connection con = db.getConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql= "select * from answer where answer_description = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, answer_description);
			rs = pst.executeQuery();
			while (rs.next()) {
				answerBean = new AnswerBean();
				answerBean.setAnswer_id(rs.getInt("answer_id"));
				answerBean.setAnswer_description(rs.getString("answer_description"));
				answerBean.setAnswer_time(rs.getString("answer_time"));
				answerBean.setAnswer_mark(rs.getInt("answer_mark"));
				answerBean.setAnswer_user_id(rs.getInt("answer_user_id"));
				answerBean.setQuestion__id(rs.getInt("question_id"));
				answerBean.setAnswer_best(rs.getInt("answer_best"));
				listAnswerBeans.add(answerBean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.free(rs, pst, con);
		}
		return listAnswerBeans;
	}
	//新增回答信息
	public boolean addAnswer(AnswerBean answerBean) {
		Boolean bool = false;
		Connection conn = db.getConn();
		PreparedStatement pst = null;
		String sql = "insert into answer(answer_description, answer_time, answer_mark, answer_user_id, question_id, answer_best)VALUES(?, ?, 0,? , ?, 0)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, answerBean.getAnswer_description());
			pst.setString(2, answerBean.getAnswer_time());
			pst.setInt(3, answerBean.getAnswer_user_id());
			pst.setInt(4, answerBean.getQuestion_id());
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
	//删除回答信息
	public boolean deleteAnswer(int answer_id) {
		boolean bool = false;
		Connection conn = db.getConn();
		PreparedStatement pstm = null;

		try {

			String sql = "delete from answer where answer_id = ?";

			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, answer_id);
			int len = pstm.executeUpdate();
			if (len > 0) {
				bool = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.free(pstm, conn);
		}
		return bool;
	}
	
	//查找回答信息
	public List<AnswerBean> findAnswerByAnswerName(String answer_name) {
		List<AnswerBean> listAnswerBeans = new ArrayList<AnswerBean>();
		AnswerBean answerBean = null;
		Connection con = db.getConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql= "select * from answer where answer_description = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, answer_name);
			rs = pst.executeQuery();
			while (rs.next()) {
				answerBean = new AnswerBean();
				answerBean.setAnswer_id(rs.getInt("answer_id"));
				answerBean.setAnswer_description(rs.getString("answer_description"));
				answerBean.setAnswer_time(rs.getString("answer_time"));
				answerBean.setAnswer_mark(rs.getInt("answer_mark"));
				answerBean.setAnswer_user_id(rs.getInt("answer_user_id"));
				answerBean.setQuestion__id(rs.getInt("answer_question_id"));
				answerBean.setAnswer_best(rs.getInt("answer_best"));
				listAnswerBeans.add(answerBean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.free(rs, pst, con);
		}
		return listAnswerBeans;
	}
	
	//修改回答信息
	public boolean updateAnswer(AnswerBean answerBean) {
		Boolean bool = false;
		Connection conn = db.getConn();
		PreparedStatement pst = null;
		try {

			String sql = "update answer set answer_description=?,answer_time=?,answer_mark=?,answer_best=?  where answer_id = ?;";
			pst = conn.prepareStatement(sql);
			pst.setString(1, answerBean.getAnswer_description());
			pst.setString(2, answerBean.getAnswer_time());
			pst.setInt(3, answerBean.getAnswer_mark());
			pst.setInt(4, answerBean.getAnswer_best());
			pst.setInt(5, answerBean.getAnswer_id());
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
	//统计某个问题回答总数
	public int getContOfAnswer(int questionId) {
		int tagsCount = 0;
		Connection conn = db.getConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {

			String sql = "select count(*) totalCount from answer where question_id = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, questionId);
			rs = pst.executeQuery();
			while(rs.next()){
				tagsCount = rs.getInt("totalCount");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			db.free(pst, conn);

		}
		return tagsCount;
	}
	//获得热门问题
	public List<AnswerBean> getHotAnswersByAnswerId(int answerId) {
		List<AnswerBean> listAnswerBeans = new ArrayList<AnswerBean>();
		AnswerBean answerBean = null;
		Connection con = db.getConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql= "select * from answer order by answer_mark desc";
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				answerBean = new AnswerBean();
				answerBean.setAnswer_id(rs.getInt("answer_id"));
				answerBean.setAnswer_description(rs.getString("answer_description"));
				answerBean.setAnswer_time(rs.getString("answer_time"));
				answerBean.setAnswer_mark(rs.getInt("answer_mark"));
				answerBean.setAnswer_user_id(rs.getInt("answer_user_id"));
				answerBean.setQuestion__id(rs.getInt("question_id"));
				answerBean.setAnswer_best(rs.getInt("answer_best"));
				listAnswerBeans.add(answerBean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.free(rs, pst, con);
		}
		return listAnswerBeans;
	}
	
	//获得已解决的问题
		public int getContOfsolution() {
			int tagsCount = 0;
			Connection conn = db.getConn();
			PreparedStatement pst = null;
			ResultSet rs = null;
			try {

				String sql = "select count(*) totalCount from answer where answer_best = 1";
				pst = conn.prepareStatement(sql);
				rs = pst.executeQuery();
				while(rs.next()){
					tagsCount = rs.getInt("totalCount");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				db.free(pst, conn);

			}
			return tagsCount;
		}
}

package cn.com.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.beans.AnswerBean;
import cn.com.beans.QuestionCategoriesBean;
import cn.com.util.DBUtil;

public class QuestionCategoriesDaoImp {
	private  DBUtil db;
	
	public  QuestionCategoriesDaoImp(){
		db = new DBUtil();
	}
	
	//根据id得到问题分类
	public QuestionCategoriesBean getQuestionCategoriesrById(int quesion_categories_id) {
		QuestionCategoriesBean questionCategoriesBean = null;
		Connection con = db.getConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql= "select * from question_categories where quesion_categories_id = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, quesion_categories_id);
			rs = pst.executeQuery();
			while (rs.next()) {
				questionCategoriesBean = new QuestionCategoriesBean();
				questionCategoriesBean.setQuestion_categories_id(rs.getInt("question_categories_id"));
				questionCategoriesBean.setQuestion_categories_name(rs.getString("question_categories_name"));
				questionCategoriesBean.setQuestion_categories_description(rs.getString("question_categories_description"));
				questionCategoriesBean.setLast_categories_id(rs.getInt("last_categories_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.free(rs, pst, con);
		}
		return questionCategoriesBean;					

	}
	
	//根据上一级id得到问题分类
		public List<QuestionCategoriesBean>  getQuestionCategoriesrByLastId(int last_categories_id) {
			List<QuestionCategoriesBean> list = new ArrayList<QuestionCategoriesBean>();
			QuestionCategoriesBean questionCategoriesBean = null;
			Connection con = db.getConn();;
			PreparedStatement pst = null;
			ResultSet rs = null;
			String sql= "select * from question_categories where answer_id = ?";
			try {
				pst = con.prepareStatement(sql);
				pst.setInt(1, last_categories_id);
				rs = pst.executeQuery();
				while (rs.next()) {
					questionCategoriesBean = new QuestionCategoriesBean();
					questionCategoriesBean.setQuestion_categories_id(rs.getInt("question_categories_id"));
					questionCategoriesBean.setQuestion_categories_name(rs.getString("question_categories_name"));
					questionCategoriesBean.setQuestion_categories_description(rs.getString("question_categories_description"));
					questionCategoriesBean.setLast_categories_id(rs.getInt("last_categories_id"));
					list.add(questionCategoriesBean);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				db.free(rs, pst, con);
			}
			return list;					
		}
}

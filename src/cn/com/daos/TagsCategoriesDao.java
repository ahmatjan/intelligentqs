package cn.com.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.beans.TagsCategoriesBean;
import cn.com.util.DBUtil;

public class TagsCategoriesDao {
	private  DBUtil db;
	public  TagsCategoriesDao(){
		db = new DBUtil();
	}
	
	//根据id得到便签分类
		public TagsCategoriesBean getTagsCategoriesrById(int tags_categories_id) {
			TagsCategoriesBean tagsCategoriesBean = null;
			Connection con = db.getConn();
			PreparedStatement pst = null;
			ResultSet rs = null;
			String sql= "select * from tags_categories where tags_categories_id = ?";
			try {
				pst = con.prepareStatement(sql);
				pst.setInt(1, tags_categories_id);
				rs = pst.executeQuery();
				while (rs.next()) {
					tagsCategoriesBean = new TagsCategoriesBean();
					tagsCategoriesBean.setTags_categories_id(rs.getInt("tags_categories_id"));
					tagsCategoriesBean.setTags_categories_name(rs.getString("tags_categories_name"));
					tagsCategoriesBean.setTags_categories_description(rs.getString("tags_categories_description"));
					tagsCategoriesBean.setLast_categories_id(rs.getInt("last_categories_id"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				db.free(rs, pst, con);
			}
			return tagsCategoriesBean;					

		}
		
		//根据上一级id得到问题分类
			public List<TagsCategoriesBean>  getTagsCategoriesrByLastId(int last_categories_id) {
				List<TagsCategoriesBean> list = new ArrayList<TagsCategoriesBean>();
				TagsCategoriesBean tagsCategoriesBean = null;
				Connection con = db.getConn();;
				PreparedStatement pst = null;
				ResultSet rs = null;
				String sql= "select * from tags_categories where last_categories_id = ?";
				try {
					pst = con.prepareStatement(sql);
					pst.setInt(1, last_categories_id);
					rs = pst.executeQuery();
					while (rs.next()) {
						tagsCategoriesBean = new TagsCategoriesBean();
						tagsCategoriesBean.setTags_categories_id(rs.getInt("tags_categories_id"));
						tagsCategoriesBean.setTags_categories_name(rs.getString("tags_categories_name"));
						tagsCategoriesBean.setTags_categories_description(rs.getString("tags_categories_description"));
						tagsCategoriesBean.setLast_categories_id(rs.getInt("last_categories_id"));
						list.add(tagsCategoriesBean);
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

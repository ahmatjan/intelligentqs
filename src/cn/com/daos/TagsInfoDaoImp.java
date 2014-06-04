package cn.com.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.com.beans.TagsInfoBean;
import cn.com.db.DBUtil;
import cn.com.interfaces.TagsInfoDaoInf;

/**
 * @author Xianxiaofei
 * @date:2014-5-13 ����5:27:20
 */
public class TagsInfoDaoImp implements TagsInfoDaoInf{
	

	private DBUtil db;
	
	public TagsInfoDaoImp(){
		db = new DBUtil();
	}
	
	//����id�õ���ǩ��Ϣ
	public TagsInfoBean getTagsInfoByTagsId(int tags_id) {
		TagsInfoBean tagBean = null;
		Connection con = db.getConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql= "select * from tags_info where tag_id = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, tags_id);
			rs = pst.executeQuery();
			while (rs.next()) {
				tagBean = new TagsInfoBean();
				tagBean.setTags_id(rs.getInt("tag_id"));
				tagBean.setTags_name(rs.getString("tag_name"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.free(rs, pst, con);
		}
		return tagBean;
	}
	
	//���ݱ�ǩ���õ���ǩ��Ϣ
	public List<TagsInfoBean> getTagsInfoByTagsName(String tags_name) {
		List<TagsInfoBean> listTagBeans = new ArrayList<TagsInfoBean>();
		TagsInfoBean tagBean = null;
		Connection con = db.getConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql= "select * from tags_info where tag_name = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, tags_name);
			rs = pst.executeQuery();
			while (rs.next()) {
				tagBean = new TagsInfoBean();
				tagBean.setTags_id(rs.getInt("tag_id"));
				tagBean.setTags_name(rs.getString("tag_name"));
				listTagBeans.add(tagBean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.free(rs, pst, con);
		}
		return listTagBeans;
	}
	
	//������ǩ��Ϣ
	public boolean addTagsInfo(TagsInfoBean tagsInfoBean) {
		Boolean bool = false;
		Connection conn = db.getConn();
		PreparedStatement pst = null;
		String sql = "insert into tags_info(tag_name) VALUES(?)";
		String tag_name = tagsInfoBean.getTags_name();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, tag_name);
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
	
	//ɾ����ǩ��Ϣ
	public boolean deleteTagsInfo(int tags_id) {
		boolean bool = false;
		Connection conn = db.getConn();
		PreparedStatement pstm = null;

		try {

			String sql = "delete from tags_info where tag_id = ?";

			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, tags_id);
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
	
	//������б�ǩ��Ϣ
    public List<TagsInfoBean> getAllTagsInfo(){
	    List<TagsInfoBean> listTagBeans = new ArrayList<TagsInfoBean>();
		TagsInfoBean tagBean = null;
		Connection con = db.getConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql= "select * from tags_info";
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				tagBean = new TagsInfoBean();
				tagBean.setTags_id(rs.getInt("tag_id"));
				tagBean.setTags_name(rs.getString("tag_name"));
				listTagBeans.add(tagBean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			db.free(rs, pst, con);
		}
		return listTagBeans;
    }
    
	//�޸ı�ǩ��Ϣ
	public boolean updateTagsInfo(TagsInfoBean tagsInfoBean) {
		Boolean bool = false;
		Connection conn = db.getConn();
		PreparedStatement pst = null;
		try {

			String sql = "update tags_info set tag_name = ? where tag_id = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, tagsInfoBean.getTags_name());
			pst.setInt(2, tagsInfoBean.getTags_id());
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
    
	//ͳ�Ʊ�ǩ����
	public int getContOfTags() {
		int tagsCount = 0;
		Connection conn = db.getConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {

			String sql = "select count(*) totalCount from tags_info";
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
	
	//������ű�ǩ
	public List<TagsInfoBean> getHotTags() {
		   List<TagsInfoBean> listTagBeans = new ArrayList<TagsInfoBean>();
			TagsInfoBean tagBean = null;
			Connection con = db.getConn();
			PreparedStatement pst = null;
			ResultSet rs = null;
			String sql= "select * from tags_info";
			try {
				pst = con.prepareStatement(sql);
				rs = pst.executeQuery();
				while (rs.next()) {
					tagBean = new TagsInfoBean();
					tagBean.setTags_id(rs.getInt("tag_id"));
					tagBean.setTags_name(rs.getString("tag_name"));
					listTagBeans.add(tagBean);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				db.free(rs, pst, con);
			}
			return listTagBeans;
	}

}

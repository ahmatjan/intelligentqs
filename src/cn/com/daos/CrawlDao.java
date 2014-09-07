package cn.com.daos;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cn.com.beans.AnswerBean;
import cn.com.beans.QuestionBean;
import cn.com.util.DBUtil;

import cn.com.beans.CrawlBean;

public class CrawlDao {
private  DBUtil db;
	
	public  CrawlDao(){
		db = new DBUtil();
	}

	public Boolean addCrawl(CrawlBean crawlbean) {
		Boolean bool = false;
		Connection conn = db.getConn();
		PreparedStatement pst = null;
		String sql = "insert into crawl(question_id, title, url)VALUES(?, ?, ?)";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, crawlbean.get_question_id());
			pst.setString(2, crawlbean.get_title());
			pst.setString(3, crawlbean.get_url());
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
	
	public List<CrawlBean> getCrawlBeanByQuestionid(int question_id){
		Connection con = db.getConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		CrawlBean crawlbean = null;
		List<CrawlBean> list = null;
		String sql= "select * from crawl where question_id = ?";
		
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, question_id);
			rs = pst.executeQuery();
			if (rs!=null){
				list = new ArrayList<CrawlBean>();
				while (rs.next()) {
					crawlbean = new CrawlBean();
					crawlbean.set_question_id(question_id);
					crawlbean.set_title(rs.getString("title"));
					crawlbean.set_url(rs.getString("url"));
					list.add(crawlbean);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			db.free(rs, pst, con);
		}
		return list;				
	}
}

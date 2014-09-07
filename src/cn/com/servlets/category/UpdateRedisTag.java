package cn.com.servlets.category;

import java.util.ArrayList;

import redis.clients.jedis.Jedis;
import cn.com.beans.TagsInfoBean;
import cn.com.daos.TagsInfoDaoImp;
import cn.com.util.RUtil;

public class UpdateRedisTag {
	
	private TagsInfoBean tagbean;
	
	public void verify(){
		RUtil redis = new RUtil();
		Jedis rdb = redis.con();
		String mark = rdb.hget("mark","tag");
		if (mark == "1" || mark == null){
			update();
		}
		else{
			update();
		}
	}

	public void update(){
		RUtil redis = new RUtil();
		Jedis rdb = redis.con();
		
		TagsInfoDaoImp tagdao = new TagsInfoDaoImp();
		ArrayList<TagsInfoBean> tagbeanlist = (ArrayList<TagsInfoBean>) tagdao.getAllTagsInfo();
		for(int i=0; i < tagbeanlist.size(); i++){
			tagbean = new TagsInfoBean();
			tagbean = tagbeanlist.get(i);
			String select = "id:" + tagbean.getTags_id() + ":category:" + 
					tagbean.getTags_categories_id() + ":decr:" + tagbean.getTags_description();
			System.out.println(select);
			rdb.hset("tag", tagbean.getTags_name(), select);
		}
		rdb.hset("mark","tag", "0");
	}
	
}

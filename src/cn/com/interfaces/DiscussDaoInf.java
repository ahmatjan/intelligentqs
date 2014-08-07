package cn.com.interfaces;

import java.util.List;
import cn.com.beans.DiscussBean;

public interface DiscussDaoInf {
	//根据id得到评论
    DiscussBean getDiscussByDiscussId(int discuss_id);
    
    //根评论内容得到评论
    List<DiscussBean> getDiscussByDiscussContent(String discuss_content);
    
    //根评论问题id得到评论
    List<DiscussBean> getDiscussByAnswerId(int answer_id);
    
    //新增评论信息
    boolean addDiscuss(DiscussBean discussBean);
    
    //删除评论信息
    boolean deleteDiscussr(int answer_id);
    
    
    //修改评论信息
    boolean updateDiscuss(DiscussBean discussBean);
    
    //统计某个问题评论总数
  	int getContOfAnswerDiscuss(int disscussId);
    //获得热门评论
    List<DiscussBean> getHotDiscussByAnswerDiscussId(int discussId);
}

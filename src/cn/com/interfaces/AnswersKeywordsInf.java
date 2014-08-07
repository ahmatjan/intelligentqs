package cn.com.interfaces;

import java.util.List;

import cn.com.beans.AnswersKeywordsBean;

/**
 * @author Xianxiaofei
 * @date:2014年7月30日 下午5:54:13
 */
public interface AnswersKeywordsInf {
	//根据id得到问题
	AnswersKeywordsBean getDiscussByAnswersKeywordsId(int answers_keywords_id);
    
    //根据问题分词得到问题内容
    List<AnswersKeywordsBean> AnswersKeywords(String keywords);
    
    
    //新增评论问题分词信息
    boolean addAnswersKeywordsBean(AnswersKeywordsBean answersKeywordsBean);
    
    //删除评论信息
    boolean deleteAnswersKeywordsBean(int answers_keywords_id);
}

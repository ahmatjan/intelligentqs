package cn.com.interfaces;

import java.util.List;

import cn.com.beans.QuestionsKeywordsBean;

/**
 * @author Xianxiaofei
 * @date:2014年7月30日 下午5:49:53
 */
public interface QuesionsKeywordsInf {
	//根据id得到问题
    QuestionsKeywordsBean getQuestionKeyByQuestionId(int questions_keywords_id);
    
    //根据问题分词得到问题内容
    List<QuestionsKeywordsBean> getQuestionsKeywordsByKeyWords(String keywords);
    
    
    //新增评论问题分词信息
    boolean addQuestionsKeywordsBean(QuestionsKeywordsBean questionsKeywordsBean);
    
    //删除评论信息
    boolean deleteQuestionsKeywordsBean(int questions_keywords_id);
}

package cn.com.interfaces;

import java.util.List;
import cn.com.beans.QuestionBean;

/**
 * @author Xianxiaofei
 * @date:2014-5-10 上午10:14:44
 */
public interface QuestionDaoInf {
	//根据id得到问题信息
    QuestionBean getQuestionByQuestionId(int question_id);
    
    //根据标问题题目得到问题信息
    List<QuestionBean> getQuestionByQuestionName(String question_title);
    
    //根据标问题内容得到问题信息
    List<QuestionBean> getQuestionByQuestionDescription(String question_description);
    
    //新增问题信息
    boolean addQuestion(QuestionBean questionBean);
    
    //删除问题信息
    boolean deleteQuestion(int question_id);
    
    //修改问题信息
    boolean updateQuestion(QuestionBean questionBean);
    
    //统计问题总数
    int getContOfQuestion();
    
    //获得热门问题
    List<QuestionBean> getHotQuestions();
    
  //获得所有问题
    List<QuestionBean> getAllQuestions(int row, int countPage);
}

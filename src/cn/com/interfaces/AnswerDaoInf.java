package cn.com.interfaces;

import java.util.List;
import cn.com.beans.AnswerBean;

/**
 * @author Xianxiaofei
 * @date:2014-5-10 上午10:15:09
 */
public interface AnswerDaoInf {
	
	//根据id得到问题信息
    AnswerBean getAnswerByAnswerId(int answer_id);
    
    //根据标回答描述得到回答信息
    List<AnswerBean> getAnswerByAnswerDescription(String answer_description);
    
    //新增回答信息
    boolean addAnswer(AnswerBean answerBean);
    
    //删除回答信息
    boolean deleteAnswer(int answer_id);
    
    //查找回答信息
    List<AnswerBean> findAnswerByAnswerName(String answer_name);
    
    //修改回答信息
    boolean updateAnswer(AnswerBean answerBean);
    
    //统计回答总数
    int getContOfAnswer();
    //获得热门问题
    List<AnswerBean> getHotAnswers();
}

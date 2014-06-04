package cn.com.interfaces;

import java.util.List;
import cn.com.beans.QuestionBean;

/**
 * @author Xianxiaofei
 * @date:2014-5-10 ����10:14:44
 */
public interface QuestionDaoInf {
	//����id�õ�������Ϣ
    QuestionBean getQuestionByQuestionId(int question_id);
    
    //���ݱ�������Ŀ�õ�������Ϣ
    List<QuestionBean> getQuestionByQuestionName(String question_title);
    
    //���ݱ��������ݵõ�������Ϣ
    List<QuestionBean> getQuestionByQuestionDescription(String question_description);
    
    //����������Ϣ
    boolean addQuestion(QuestionBean questionBean);
    
    //ɾ��������Ϣ
    boolean deleteQuestion(int question_id);
    
    //�޸�������Ϣ
    boolean updateQuestion(QuestionBean questionBean);
    
    //ͳ����������
    int getContOfQuestion();
    
    //�����������
    List<QuestionBean> getHotQuestions();
    
  //�����������
    List<QuestionBean> getAllQuestions(int row, int countPage);
}

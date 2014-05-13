package cn.com.interfaces;

import java.util.List;
import cn.com.beans.AnswerBean;

/**
 * @author Xianxiaofei
 * @date:2014-5-10 ����10:15:09
 */
public interface AnswerDaoInf {
	
	//����id�õ���ǩ��Ϣ
    AnswerBean getAnswerByAnswerId(int answer_id);
    
    //���ݱ�ش������õ��ش���Ϣ
    List<AnswerBean> getAnswerByAnswerName(String answer_description);
    
    //�����ش���Ϣ
    boolean addAnswer(AnswerBean answerBean);
    
    //ɾ���ش���Ϣ
    boolean deleteAnswer(int answer_id);
    
    //���һش���Ϣ
    List<AnswerBean> findAnswerByAnswerName(String answer_name);
    
    //�޸Ļش���Ϣ
    boolean updateAnswer(AnswerBean answerBean);
    
    //ͳ�ƻش�����
    int getContOfAnswer();
}

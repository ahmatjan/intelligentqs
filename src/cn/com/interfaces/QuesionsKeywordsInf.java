package cn.com.interfaces;

import java.util.List;

import cn.com.beans.QuestionsKeywordsBean;

/**
 * @author Xianxiaofei
 * @date:2014��7��30�� ����5:49:53
 */
public interface QuesionsKeywordsInf {
	//����id�õ�����
    QuestionsKeywordsBean getQuestionKeyByQuestionId(int questions_keywords_id);
    
    //��������ִʵõ���������
    List<QuestionsKeywordsBean> getQuestionsKeywordsByKeyWords(String keywords);
    
    
    //������������ִ���Ϣ
    boolean addQuestionsKeywordsBean(QuestionsKeywordsBean questionsKeywordsBean);
    
    //ɾ��������Ϣ
    boolean deleteQuestionsKeywordsBean(int questions_keywords_id);
}

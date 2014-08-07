package cn.com.interfaces;

import java.util.List;

import cn.com.beans.AnswersKeywordsBean;

/**
 * @author Xianxiaofei
 * @date:2014��7��30�� ����5:54:13
 */
public interface AnswersKeywordsInf {
	//����id�õ�����
	AnswersKeywordsBean getDiscussByAnswersKeywordsId(int answers_keywords_id);
    
    //��������ִʵõ���������
    List<AnswersKeywordsBean> AnswersKeywords(String keywords);
    
    
    //������������ִ���Ϣ
    boolean addAnswersKeywordsBean(AnswersKeywordsBean answersKeywordsBean);
    
    //ɾ��������Ϣ
    boolean deleteAnswersKeywordsBean(int answers_keywords_id);
}

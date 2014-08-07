package cn.com.interfaces;

import java.util.List;
import cn.com.beans.DiscussBean;

public interface DiscussDaoInf {
	//����id�õ�����
    DiscussBean getDiscussByDiscussId(int discuss_id);
    
    //���������ݵõ�����
    List<DiscussBean> getDiscussByDiscussContent(String discuss_content);
    
    //����������id�õ�����
    List<DiscussBean> getDiscussByAnswerId(int answer_id);
    
    //����������Ϣ
    boolean addDiscuss(DiscussBean discussBean);
    
    //ɾ��������Ϣ
    boolean deleteDiscussr(int answer_id);
    
    
    //�޸�������Ϣ
    boolean updateDiscuss(DiscussBean discussBean);
    
    //ͳ��ĳ��������������
  	int getContOfAnswerDiscuss(int disscussId);
    //�����������
    List<DiscussBean> getHotDiscussByAnswerDiscussId(int discussId);
}

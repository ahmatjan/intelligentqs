package cn.com.interfaces;

import java.util.List;

import cn.com.beans.TagsInfoBean;

/**
 * @author Xianxiaofei
 * @date:2014-5-10 ����10:15:27
 */
public interface TagsInfoDaoInf {
	
	//����id�õ���ǩ��Ϣ
    TagsInfoDaoInf getTagsInfoByTagsId(int tags_id);
    
    //���ݱ�ǩ���õ���ǩ��Ϣ
    List<TagsInfoDaoInf> getTagsInfoByTagsName(String tags_name);
    
    //������ǩ��Ϣ
    boolean addTagsInfo(TagsInfoBean tagsInfoBean);
    
    //ɾ����ǩ��Ϣ
    boolean deleteTagsInfo(int tags_id);
    
    //���ұ�ǩ��Ϣ
    List<TagsInfoBean> findTagsInfoByTagsName(String tags_name);
    
    //�޸ı�ǩ��Ϣ
    boolean updateTagsInfo(int tags_id);
    
    //ͳ�Ʊ�ǩ����
    int getContOfTags();
    
}

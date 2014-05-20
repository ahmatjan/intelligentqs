package cn.com.interfaces;

import java.util.List;
import cn.com.beans.TagsInfoBean;

/**
 * @author Xianxiaofei
 * @date:2014-5-10 ����10:15:27
 */
public interface TagsInfoDaoInf {
	
	//����id�õ���ǩ��Ϣ
    TagsInfoBean getTagsInfoByTagsId(int tags_id);
    
    //���ݱ�ǩ���õ���ǩ��Ϣ
    List<TagsInfoBean> getTagsInfoByTagsName(String tags_name);
    
    //������ǩ��Ϣ
    boolean addTagsInfo(TagsInfoBean tagsInfoBean);
    
    //ɾ����ǩ��Ϣ
    boolean deleteTagsInfo(int tags_id);
    
    //������б�ǩ��Ϣ
    List<TagsInfoBean> getAllTagsInfo();
    
    //�޸ı�ǩ��Ϣ
    boolean updateTagsInfo(TagsInfoBean tagsInfoBean);
    
    //ͳ�Ʊ�ǩ����
    int getContOfTags();
    
    //������ű�ǩ
    List<TagsInfoBean> getHotTags();
    
}

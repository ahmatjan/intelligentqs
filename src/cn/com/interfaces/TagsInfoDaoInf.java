package cn.com.interfaces;

import java.util.List;

import cn.com.beans.TagsInfoBean;

/**
 * @author Xianxiaofei
 * @date:2014-5-10 上午10:15:27
 */
public interface TagsInfoDaoInf {
	
	//根据id得到标签信息
    TagsInfoDaoInf getTagsInfoByTagsId(int tags_id);
    
    //根据标签名得到标签信息
    List<TagsInfoDaoInf> getTagsInfoByTagsName(String tags_name);
    
    //新增便签信息
    boolean addTagsInfo(TagsInfoBean tagsInfoBean);
    
    //删除便签信息
    boolean deleteTagsInfo(int tags_id);
    
    //查找标签信息
    List<TagsInfoBean> findTagsInfoByTagsName(String tags_name);
    
    //修改标签信息
    boolean updateTagsInfo(int tags_id);
    
    //统计标签总数
    int getContOfTags();
    
}

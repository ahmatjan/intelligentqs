package cn.com.interfaces;

import java.util.List;
import cn.com.beans.TagsInfoBean;

/**
 * @author Xianxiaofei
 * @date:2014-5-10 上午10:15:27
 */
public interface TagsInfoDaoInf {
	
	//根据id得到标签信息
    TagsInfoBean getTagsInfoByTagsId(int tags_id);
    
    //根据标签名得到标签信息
    List<TagsInfoBean> getTagsInfoByTagsName(String tags_name);
    
    //新增标签信息
    boolean addTagsInfo(TagsInfoBean tagsInfoBean);
    
    //删除标签信息
    boolean deleteTagsInfo(int tags_id);
    
    //获得所有标签信息
    List<TagsInfoBean> getAllTagsInfo();
    
    //修改标签信息
    boolean updateTagsInfo(TagsInfoBean tagsInfoBean);
    
    //统计标签总数
    int getContOfTags();
    
    //获得热门标签
    List<TagsInfoBean> getHotTags();
    
}

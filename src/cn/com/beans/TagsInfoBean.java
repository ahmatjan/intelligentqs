package cn.com.beans;
/**
 * @author Xianxiaofei
 * @date:2014-5-10 上午10:03:34
 */
public class TagsInfoBean {
	//标签编号
	private int tags_id;
	//标签名称
	private String tags_name;
	//标签分类id
	private int tags_categories_id;
	public int getTags_categories_id() {
		return tags_categories_id;
	}
	public void setTags_categories_id(int tags_categories_id) {
		this.tags_categories_id = tags_categories_id;
	}
	//对应的Get、Set方法
	public int getTags_id() {
		return tags_id;
	}
	public void setTags_id(int tags_id) {
		this.tags_id = tags_id;
	}
	public String getTags_name() {
		return tags_name;
	}
	public void setTags_name(String tags_name) {
		this.tags_name = tags_name;
	}
	

}

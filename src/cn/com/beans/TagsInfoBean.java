package cn.com.beans;
/**
 * @author Xianxiaofei
 * @date:2014-5-10 ����10:03:34
 */
public class TagsInfoBean {
	//��ǩ���
	private int tags_id;
	//��ǩ����
	private String tags_name;
	//��ǩ����id
	private int tags_categories_id;
	public int getTags_categories_id() {
		return tags_categories_id;
	}
	public void setTags_categories_id(int tags_categories_id) {
		this.tags_categories_id = tags_categories_id;
	}
	//��Ӧ��Get��Set����
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

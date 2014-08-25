package cn.com.beans;

public class TagsCategoriesBean {
	//标签分类ID
	private int tags_categories_id; 
	//标签分类名称
	private String tags_categories_name;
	//标签分类描述
	private String tags_categories_description;

	//对应的get()与set()方法
	public int getTags_categories_id() {
		return tags_categories_id;
	}
	public void setTags_categories_id(int tags_categories_id) {
		this.tags_categories_id = tags_categories_id;
	}
	public String getTags_categories_name() {
		return tags_categories_name;
	}
	public void setTags_categories_name(String tags_categories_name) {
		this.tags_categories_name = tags_categories_name;
	}
	public String getTags_categories_description() {
		return tags_categories_description;
	}
	public void setTags_categories_description(String tags_categories_description) {
		this.tags_categories_description = tags_categories_description;
	}
	
}

package cn.com.beans;

public class QuestionCategoriesBean {
	//问题分类ID
	private int question_categories_id;
	//问题分类名称
	private String question_categories_name;
	//问题分类描述
	private String question_categories_description;
	
	//对应的get()与set()方法
	public int getQuestion_categories_id() {
		return question_categories_id;
	}
	public void setQuestion_categories_id(int question_categories_id) {
		this.question_categories_id = question_categories_id;
	}
	public String getQuestion_categories_name() {
		return question_categories_name;
	}
	public void setQuestion_categories_name(String question_categories_name) {
		this.question_categories_name = question_categories_name;
	}
	public String getQuestion_categories_description() {
		return question_categories_description;
	}
	public void setQuestion_categories_description(
			String question_categories_description) {
		this.question_categories_description = question_categories_description;
	}
	

}

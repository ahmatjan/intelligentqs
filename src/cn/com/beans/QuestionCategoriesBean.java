package cn.com.beans;

public class QuestionCategoriesBean {
	private int question_categories_id;
	private String question_categories_name;
	private String question_categories_description;
	private int last_categories_id;
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
	public int getLast_categories_id() {
		return last_categories_id;
	}
	public void setLast_categories_id(int last_categories_id) {
		this.last_categories_id = last_categories_id;
	}
}

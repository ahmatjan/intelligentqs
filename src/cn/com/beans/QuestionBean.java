package cn.com.beans;
/**
 * ����ʵ��
 * @author Xianxiaofei
 * @date:2014-5-10 ����9:56:45
 */
public class QuestionBean {
	//������
	private int question_id;
	//������Ŀ
	private String question_title;
	//��������
	private String question_description;
	//����ʱ��
	private String question_time;
	//�������֣��ޡ��ȣ�
	private int question_mark;
	//�����û����
	private int question_user_id;
	//�����ǩ����������
	private String question_tags;
	
	//��Ӧ��Get��Set����
	public int getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	public String getQuestion_title() {
		return question_title;
	}
	public void setQuestion_title(String question_title) {
		this.question_title = question_title;
	}
	public String getQuestion_description() {
		return question_description;
	}
	public void setQuestion_description(String question_description) {
		this.question_description = question_description;
	}
	public String getQuestion_time() {
		return question_time;
	}
	public void setQuestion_time(String question_time) {
		this.question_time = question_time;
	}
	public int getQuestion_mark() {
		return question_mark;
	}
	public void setQuestion_mark(int question_mark) {
		this.question_mark = question_mark;
	}
	public int getQuestion_user_id() {
		return question_user_id;
	}
	public void setQuestion_user_id(int question_user_id) {
		this.question_user_id = question_user_id;
	}
	public String getQuestion_tags() {
		return question_tags;
	}
	public void setQuestion_tags(String question_tags) {
		this.question_tags = question_tags;
	}
}


package com.example.app.hnust_qa.Bean;
/**
 * @author Xianxiaofei
 * @date:2014-5-10 ����10:00:48
 */


public class AnswerBean {
	//�𰸱��
	private int answer_id;
	//������
	private String answer_description;
	//�ش�ʱ��
	private String answer_time;
	//�����֣��ޡ��ȣ�
	private int answer_mark;
	//�����û����
	private int answer_user_id;
	//����������
	private int question_id;
	//�Ƿ�Ϊ��Ѵ�
	private int answer_best;

    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    //��Ӧ��Get��Set����
	public int getAnswer_id() {
		return answer_id;
	}
	public void setAnswer_id(int answer_id) {
		this.answer_id = answer_id;
	}
	public String getAnswer_description() {
		return answer_description;
	}
	public void setAnswer_description(String answer_description) {
		this.answer_description = answer_description;
	}
	public String getAnswer_time() {
		return answer_time;
	}
	public void setAnswer_time(String answer_time) {
		this.answer_time = answer_time;
	}
	public int getAnswer_mark() {
		return answer_mark;
	}
	public void setAnswer_mark(int answer_mark) {
		this.answer_mark = answer_mark;
	}
	public int getAnswer_user_id() {
		return answer_user_id;
	}
	public void setAnswer_user_id(int answer_user_id) {
		this.answer_user_id = answer_user_id;
	}
	public int getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	public int getAnswer_best() {
		return answer_best;
	}
	public void setAnswer_best(int answer_best) {
		this.answer_best = answer_best;
	}

}

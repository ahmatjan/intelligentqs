package com.example.app.hnust_qa.Bean;
/**
 * @author Xianxiaofei
 * @date:2014-5-21 ����8:35:19
 */
/**
 * 
 * @author Xianxiaofei
 *	QuestionOthesBean������ҳ��Ϣ��ʾ
 */
public class QuestionAllInfoBean {
	//����ش�����
	private int countOfAnswers;
	//����������
	private String QuestionUserName;
	//��Ѵ�
	private String bestAnswer;
	//���������
	private int vpOfQuestion;
	//���������Ϣ
	private QuestionBean questionBean;
	
	public QuestionBean getQuestionBean() {
		return questionBean;
	}
	public void setQuestionBean(QuestionBean questionBean) {
		this.questionBean = questionBean;
	}
	public int getCountOfAnswers() {
		return countOfAnswers;
	}
	public void setCountOfAnswers(int countOfAnswers) {
		this.countOfAnswers = countOfAnswers;
	}
	public String getQuestionUserName() {
		return QuestionUserName;
	}
	public void setQuestionUserName(String questionUserName) {
		QuestionUserName = questionUserName;
	}
	public String getBestAnswer() {
		return bestAnswer;
	}
	public void setBestAnswer(String bestAnswer) {
		this.bestAnswer = bestAnswer;
	}
	public int getVpOfQuestion() {
		return vpOfQuestion;
	}
	public void setVpOfQuestion(int vpOfQuestion) {
		this.vpOfQuestion = vpOfQuestion;
	}

}

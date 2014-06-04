package cn.com.beans;
/**
 * @author Xianxiaofei
 * @date:2014-5-21 下午8:35:19
 */
/**
 * 
 * @author Xianxiaofei
 *	QuestionOthesBean用于主页信息显示
 */
public class QuestionAllInfoBean {
	//问题回答数量
	private int countOfAnswers;
	//提问者名称
	private String QuestionUserName;
	//最佳答案
	private String bestAnswer;
	//问题访问量
	private int vpOfQuestion;
	//问题基本信息
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

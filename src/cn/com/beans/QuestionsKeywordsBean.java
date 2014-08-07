package cn.com.beans;
/**
 * @author Xianxiaofei
 * @date:2014年7月30日 下午5:44:11
 */
public class QuestionsKeywordsBean {
	private int questions_keywords_id;
	private int question_id;
	private String quesitons_keywords_topN;
	private String questions_keywords_counts;
	public int getQuestions_keywords_id() {
		return questions_keywords_id;
	}
	public void setQuestions_keywords_id(int questions_keywords_id) {
		this.questions_keywords_id = questions_keywords_id;
	}
	public int getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	public String getQuesitons_keywords_topN() {
		return quesitons_keywords_topN;
	}
	public void setQuesitons_keywords_topN(String quesitons_keywords_topN) {
		this.quesitons_keywords_topN = quesitons_keywords_topN;
	}
	public String getQuestions_keywords_counts() {
		return questions_keywords_counts;
	}
	public void setQuestions_keywords_counts(String questions_keywords_counts) {
		this.questions_keywords_counts = questions_keywords_counts;
	}
	
}

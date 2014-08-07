package cn.com.beans;
/**
 * @author Xianxiaofei
 * @date:2014年7月30日 下午5:44:32
 */
public class AnswersKeywordsBean {
	private int answers_keywords_id;
	private int answers_id;
	private String answers_keywords_topN;
	private String answers_keywords_counts;
	public int getAnswers_keywords_id() {
		return answers_keywords_id;
	}
	public void setAnswers_keywords_id(int answers_keywords_id) {
		this.answers_keywords_id = answers_keywords_id;
	}
	public int getAnswers_id() {
		return answers_id;
	}
	public void setAnswers_id(int answers_id) {
		this.answers_id = answers_id;
	}
	public String getAnswers_keywords_topN() {
		return answers_keywords_topN;
	}
	public void setAnswers_keywords_topN(String answers_keywords_topN) {
		this.answers_keywords_topN = answers_keywords_topN;
	}
	public String getAnswers_keywords_counts() {
		return answers_keywords_counts;
	}
	public void setAnswers_keywords_counts(String answers_keywords_counts) {
		this.answers_keywords_counts = answers_keywords_counts;
	}
}

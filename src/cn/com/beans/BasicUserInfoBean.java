package cn.com.beans;


public class BasicUserInfoBean {
	//ask
	private int questions = 0;
	//solve
	private int solve = 0;
	//star
	private int stars = 0;

	public int get_questions() {
		return questions;
	}
	public void set_questions(int questions) {
		this.questions = questions;
	}

	public int get_stars(){
		return stars;
	}
	public void set_stars(int stars){
		this.stars = stars;
	}

	public int get_solve() {
		return solve;
	}
	public void set_solve(int solve) {
		this.solve = solve;
	}
	
}

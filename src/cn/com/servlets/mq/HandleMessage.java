package cn.com.servlets.mq;

public class HandleMessage {
	private String[] str;
	
	public void set_str(String[] str){
		this.str = str;
	}
	
	public String handle(){
		int code = Integer.parseInt(str[0]);
		String message = "";
		switch (code) {
			case 0:{
				message = "<a href='getDetilQuestion?question_id=" + str[2] + "'>" + str[1] + "</a>";
				break;
			}
			case 1:{
				message = str[1] + "<a href='getDetilQuestion?question_id=" + str[6] + "'>" + str[2] + "</a>" + str[3] + "<a href='GetPersionInfoServlet?user_id=" + str[7] + "'>" + str[4] + "</a>" + str[5];
				break;
			}
			case 2:{
				message = str[1] + "<a href='GetPersionInfoServlet?user_id=" + str[4] + "'>" + str[2] + "</a>" + str[3];
				break;
			}
			case 3:{
				message = str[1] + "<a href='getDetilQuestion?question_id=" + str[6] + "'>" + str[2] + "</a>" + str[3] + "<a href='GetPersionInfoServlet?user_id=" + str[7] + "'>" + str[4] + "</a>" + str[5];
				break;
			}
			case 4:{
				message = str[1] + "<a href='getDetilQuestion?question_id=" + str[6] + "'>" + str[2] + "</a>" + str[3] + "<a href='GetPersionInfoServlet?user_id=" + str[7] + "'>" + str[4] + "</a>" + str[5];
				break;
			}
			default :{
				message = str[1] + "<a href='getDetilQuestion?question_id=" + str[4] + "'>" + str[2] + "</a>" + str[3];
				break;
			}
		}
		return message;
	}
}

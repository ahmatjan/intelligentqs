/**
 * 
 */
package cn.com.servlet.question;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.beans.QuestionBean;
import cn.com.beans.UserInfoBean;
import cn.com.daos.QuestionDaoImp;
import cn.com.daos.UserInfoDaoImp;

/**
 * @author Friday
 * @date 2014-5-13 下午5:06:38
 */
public class AskQuestionServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String question_title = request.getParameter("question_title");
		String question_description = request
				.getParameter("question_description");
		String question_tags = request.getParameter("question_tags");
		HttpSession session = request.getSession();
		int question_user_id = ((UserInfoBean)session.getAttribute("userBean")).getUser_id();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//设置日期格式
		String question_time = sdf.format(new Date());
		int question_mark = 0;
		QuestionBean questionBean = new QuestionBean();
		questionBean.setQuestion_title(question_title);
		questionBean.setQuestion_description(question_description);
		questionBean.setQuestion_tags(question_tags);
		questionBean.setQuestion_user_id(question_user_id);
		questionBean.setQuestion_time(question_time);
		questionBean.setQuestion_mark(question_mark);
		QuestionDaoImp questionDao = new QuestionDaoImp();
		questionDao.addQuestion(questionBean);
		request.getRequestDispatcher("main.jsp").forward(request, response);
	}

}

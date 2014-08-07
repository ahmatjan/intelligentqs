package cn.com.servlets.user;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.beans.AnswerBean;
import cn.com.beans.QuestionBean;
import cn.com.beans.UserInfoBean;
import cn.com.daos.AnswerDaoImp;
import cn.com.daos.QuestionDaoImp;

public class DiscussPerson extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserInfoBean uib = (UserInfoBean) session.getAttribute("userBean");
		if (uib == null) {
			request.getRequestDispatcher("userLogin.jsp").forward(request,
					response);
			return;
		}

		QuestionDaoImp questionDaoImp = new QuestionDaoImp();
		// ��ҳ��
		int len = (Integer) session.getAttribute("len");

		// �õ�Ҫ�鿴��ĳҳ
		int currentPage;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (NumberFormatException e) {
			// ���ҳ������ֵ,������ҳ
			currentPage = 0;
		}

		// ���ҳ����������,����ĩҳ
		if (currentPage > len) {
			currentPage = len;
		}

		// ���ҳ��С��0
		if (currentPage < 0) {
			currentPage = 0;
		}
		int userID = uib.getUser_id();
		QuestionDaoImp questionDao = new QuestionDaoImp();
		AnswerDaoImp answerDao = new AnswerDaoImp();

		List<QuestionBean> listQuestion = new ArrayList<QuestionBean>();
		List<AnswerBean> listAnswer = new ArrayList<AnswerBean>();
		listQuestion = questionDao.getQuestionByUserId(userID,currentPage*10,10);
		listAnswer = answerDao.getAnswerByUserId(userID);
		if (listAnswer.size() > 10) {
			listAnswer = listAnswer.subList(0, 10);
		}
		
		System.out.println("��ǰ퓔���"+currentPage);
		System.out.println("��퓔���"+len);
		session.setAttribute("currentPage", currentPage);
		request.setAttribute("listQuestion", listQuestion);
		request.setAttribute("listAnswer", listAnswer);
		request.setAttribute("countOfAuswer", listAnswer.size());

		request.getRequestDispatcher("person.jsp").forward(request, response);

	}

}

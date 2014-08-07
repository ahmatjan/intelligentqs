package cn.com.servlets.index;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.beans.QuestionAllInfoBean;
import cn.com.beans.QuestionBean;
import cn.com.beans.TagsInfoBean;
import cn.com.daos.AnswerDaoImp;
import cn.com.daos.QuestionDaoImp;
import cn.com.daos.TagsInfoDaoImp;
import cn.com.daos.UserInfoDaoImp;

/**
 * @author Xianxiaofei
 * @date:2014-5-21 下午11:21:13
 */
public class DisscussQuestionServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		TagsInfoDaoImp tagsInfoDaoImp = new TagsInfoDaoImp();
		QuestionDaoImp questionDaoImp = new QuestionDaoImp();
		AnswerDaoImp answerDaoImp = new AnswerDaoImp();
		//总页数
		int len = (Integer) session.getAttribute("len");
		
		//得到要查看的某页
		int currentPage;
		try {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		} catch (NumberFormatException e) {
					//如果页数非数值,跳到首页
			currentPage = 0;
		}
				
		//如果页数大于总数,跳到末页
		if(currentPage > len) {
			currentPage = len;
		}
				
		//如果页数小于0
		if(currentPage < 0) {
			currentPage = len;
		}

		
		
		UserInfoDaoImp userInfoDaoImp = new UserInfoDaoImp();
		//得到所有问题详细信息在主页显示
		List<QuestionAllInfoBean> listAllQuestions =new ArrayList<QuestionAllInfoBean>();
		//获得热门问题详细信息显示在主页
		List<QuestionAllInfoBean> listHotQuestions =new ArrayList<QuestionAllInfoBean>();
		
		QuestionAllInfoBean questionAllInfoBean = null;
		QuestionBean  questionBean = new QuestionBean();
		 

		List<QuestionBean> listAllQuestion = questionDaoImp.getAllQuestions(currentPage*10,10);
		List<QuestionBean> listHotQuestion = questionDaoImp.getHotQuestions();
		List<TagsInfoBean> listTags = tagsInfoDaoImp.getHotTags();
		
		//处理得到的所有问题西信息
		for(int i=0; i<listAllQuestion.size();i++){
			questionAllInfoBean = new QuestionAllInfoBean();
			questionBean = listAllQuestion.get(i);
			String userName = userInfoDaoImp.getUserInfoByUserId(questionBean.getQuestion_user_id()).getUser_name();
			int countOfAnswer = answerDaoImp.getContOfAnswer(questionBean.getQuestion_id());
			
			questionAllInfoBean.setQuestionUserName(userName);
			questionAllInfoBean.setCountOfAnswers(countOfAnswer);
			questionAllInfoBean.setVpOfQuestion(50);
			questionAllInfoBean.setBestAnswer("暂无");
			questionAllInfoBean.setQuestionBean(questionBean);
			listAllQuestions.add(questionAllInfoBean);
		}
		
		//处理得到的所有热门问题信息
		for(int i=0; i<10;i++){
			questionAllInfoBean = new QuestionAllInfoBean();
			questionBean = listHotQuestion.get(i);
			String userName = userInfoDaoImp.getUserInfoByUserId(questionBean.getQuestion_user_id()).getUser_name();
			int countOfAnswer = answerDaoImp.getContOfAnswer(questionBean.getQuestion_id());
			
			questionAllInfoBean.setQuestionUserName(userName);
			questionAllInfoBean.setCountOfAnswers(countOfAnswer);
			questionAllInfoBean.setVpOfQuestion(50);
			questionAllInfoBean.setBestAnswer("暂无");
			questionAllInfoBean.setQuestionBean(questionBean);
			listHotQuestions.add(questionAllInfoBean);
		}
		
		//currentPage = currentPage+10;
		
		System.out.println("总页数："+len);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("listQuestions", listAllQuestions);
		request.setAttribute("listHotQuestions", listHotQuestions);
		request.setAttribute("listTags", listTags);
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

/**
 * 
 */
package cn.com.servlets.question;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.beans.QuestionBean;
import cn.com.beans.QuestionsKeywordsBean;
import cn.com.beans.UserInfoBean;
import cn.com.daos.QuestionDaoImp;
import cn.com.daos.QuestionsKeywordsDaoImp;
import cn.com.daos.UserInfoDaoImp;
import cn.com.util.ChineseAnalyzerUtil;

/**
 * @author Xianxiaofei
 * @date 2014-5-13 下午5:06:38
 */
public class AskQuestionServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserInfoBean uib = (UserInfoBean)session.getAttribute("userBean");
		if(uib == null){
			request.getRequestDispatcher("userLogin.jsp").forward(request, response);
			return;
		}
		String question_title = request.getParameter("question_title");
		String question_description = request
				.getParameter("question_description");
		String question_tags = request.getParameter("question_tags");

		//登录后使用注释的语句
		int question_user_id = (uib.getUser_id());
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
		if(questionDao.addQuestion(questionBean)){
			
			QuestionBean questionBean1 = new QuestionBean();
			questionBean1 = questionDao.getQuestionByQuestionKey(question_description);
			
			//对当前问题分词处理
			ChineseAnalyzerUtil chineseAnalyzerUtil = new ChineseAnalyzerUtil();
			StringBuffer questionContext = new StringBuffer(questionBean1.getQuestion_description()+questionBean1.getQuestion_title());
			StringBuffer questionContext2 = new StringBuffer();
			StringBuffer countTopN = new StringBuffer();
			int questionId = questionBean1.getQuestion_id();
			List<Map.Entry<String, Integer>> map = chineseAnalyzerUtil.getAnalyzerKeywordsString(chineseAnalyzerUtil.getTextDef(questionContext.toString()));

			
			QuestionsKeywordsBean questionKeywords = new QuestionsKeywordsBean();
			
			questionKeywords.setQuestion_id(questionId);
			//取分词频率最高的前20
			for (int i1 = 0; i1 < 20 && i1 < map.size(); i1++) {
				Map.Entry<String, Integer> wordFrenEntry = map.get(i1);
				questionContext2.append(wordFrenEntry.getKey()+",");
				countTopN.append(wordFrenEntry.getValue()+",");
				}
			questionKeywords.setQuesitons_keywords_topN(questionContext2.toString());
			questionKeywords.setQuestions_keywords_counts(countTopN.toString());
			
			//存入数据库中
			QuestionsKeywordsDaoImp questionsKeywordsDaoImp = new QuestionsKeywordsDaoImp();
			
			if(questionsKeywordsDaoImp.addQuestionsKeywordsBean(questionKeywords)){
				System.out.println("新增问题分词处理成功！");
			}
			
			request.getRequestDispatcher("index").forward(request, response);
		}else {
			request.getRequestDispatcher("question_ask.jsp").forward(request, response);
		}
		
	}

}

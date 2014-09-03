/**
 * 
 */
package cn.com.servlets.answer;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.beans.AnswerBean;
import cn.com.beans.AnswersKeywordsBean;
import cn.com.beans.QuestionBean;
import cn.com.beans.QuestionsKeywordsBean;
import cn.com.beans.UserInfoBean;
import cn.com.daos.AnswerDaoImp;
import cn.com.daos.AnswersKeywordsDaoImp;
import cn.com.daos.QuestionsKeywordsDaoImp;
import cn.com.util.ChineseAnalyzerUtil;

import cn.com.util.RUtil;
import redis.clients.jedis.Jedis;
import cn.com.mq.Notify;
/***********************
 * @author butterfly   
 * @version��1.0        
 * @created��2014-5-17    
 ***********************
 */

/**
 * @author butterfly
 *
 */
public class AddAnswerInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		response.setContentType("text/html");
		
		HttpSession session = request.getSession();
		UserInfoBean userInfoBean = (UserInfoBean) session.getAttribute("userBean");
		if(userInfoBean == null){
			request.getRequestDispatcher("userLogin.jsp").forward(request, response);
			return;
		}
		String answerDescription;
		int question_id = Integer.parseInt(request.getParameter("question_id"));
		try{
			answerDescription = request.getParameter("answer_description");
			System.out.println(answerDescription);
		}catch(Exception e){
			request.setAttribute("question_id", question_id);
			request.getRequestDispatcher("getDetilQuestion").forward(request, response);
			return;
		}
		
		System.out.println(question_id);
		Date date = new Date();
		AnswerDaoImp answerDaoImp = new AnswerDaoImp();
		AnswerBean answerBean = new AnswerBean();
		String dateTimeString = (date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate()+"-"+"  "+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
		answerBean.setAnswer_description(answerDescription);
		answerBean.setAnswer_time(dateTimeString);
		answerBean.setAnswer_user_id(userInfoBean.getUser_id());
		answerBean.setQuestion_id(question_id);
		
		//answer info notify
		Notify notifys = new Notify();
		notifys.set_questionid(question_id);
		notifys.set_userid(userInfoBean.getUser_id());
		notifys.answer();
		
		
		
		if(answerDaoImp.addAnswer(answerBean)){
			
			//�Ե�ǰ����ִʴ���
			ChineseAnalyzerUtil chineseAnalyzerUtil = new ChineseAnalyzerUtil();
			AnswerBean answerBean1 = new AnswerBean();
			answerBean1 = answerDaoImp.getAnswerByAnswerKeywords(answerDescription);	
			int answers_id = answerBean1.getAnswer_id();
			StringBuffer answerContext = new StringBuffer(answerBean1.getAnswer_description());
			StringBuffer answerContext2 = new StringBuffer();
			StringBuffer countTopN = new StringBuffer();
			List<Map.Entry<String, Integer>> map = chineseAnalyzerUtil.getAnalyzerKeywordsString(chineseAnalyzerUtil.getTextDef(answerContext.toString()));

			AnswersKeywordsBean answersKeywordsBean = new AnswersKeywordsBean();
			
			answersKeywordsBean.setAnswers_id(answers_id);
			//ȡ�ִ�Ƶ����ߵ�ǰ20
			for (int i1 = 0; i1 < 20 && i1 < map.size(); i1++) {
				Map.Entry<String, Integer> wordFrenEntry = map.get(i1);
				answerContext2.append(wordFrenEntry.getKey()+",");
				countTopN.append(wordFrenEntry.getValue()+",");
				}
			answersKeywordsBean.setAnswers_keywords_topN(answerContext2.toString());
			answersKeywordsBean.setAnswers_keywords_counts(countTopN.toString());
			
			////�������ݿ���
			AnswersKeywordsDaoImp answersKeywordsDaoImp = new AnswersKeywordsDaoImp();
			if(answersKeywordsDaoImp.addAnswersKeywordsBean(answersKeywordsBean)){
				System.out.println("�����ش�ִʴ���success��");
			}
			
			request.setAttribute("question_id", question_id);
			request.getRequestDispatcher("getDetilQuestion").forward(request, response);
			return ;
		}else{
			request.setAttribute("Msg", "����ʧ��");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}


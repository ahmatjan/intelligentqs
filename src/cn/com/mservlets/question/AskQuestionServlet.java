/**
 * 
 */
package cn.com.mservlets.question;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import cn.com.beans.QuestionBean;
import cn.com.beans.QuestionsKeywordsBean;
import cn.com.beans.UserInfoBean;
import cn.com.daos.QuestionDaoImp;
import cn.com.daos.QuestionsKeywordsDaoImp;
import cn.com.util.ChineseAnalyzerUtil;

/**
 * @author Xianxiaofei
 * @date 2014-5-13 ����5:06:38
 */
@SuppressWarnings("serial")
public class AskQuestionServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		HttpSession session = request.getSession();
//		UserInfoBean uib = (UserInfoBean)session.getAttribute("userBean");
//		if(uib == null){
//			request.getRequestDispatcher("userLogin.jsp").forward(request, response);
//			return;
//		}
		String question_title = request.getParameter("question_title");
		String question_description = request
				.getParameter("question_description");
		String question_tags = request.getParameter("question_tags");

		//��¼��ʹ��ע�͵����
		int question_user_id = Integer.parseInt(request.getParameter("userId"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");//�������ڸ�ʽ
		String question_time = sdf.format(new Date());
		int question_mark = 0;
		QuestionBean questionBean = new QuestionBean();
		questionBean.setQuestion_title(question_title);
		questionBean.setQuestion_description(question_description);
		questionBean.setQuestion_tags(question_tags);
		questionBean.setQuestion_user_id(question_user_id);
		questionBean.setQuestion_time(question_time);
		questionBean.setQuestion_mark(question_mark);
		
		//�������Ĭ��Ϊ0
		questionBean.setQuestion_categories_id(0);
		
		QuestionDaoImp questionDao = new QuestionDaoImp();
//		boolean status = questionDao.addQuestion(questionBean);
//		request.getRequestDispatcher("index").forward(request, response);
		boolean status;
		if( status = questionDao.addQuestion(questionBean)){
			
			QuestionBean questionBean1 = new QuestionBean();
			questionBean1 = questionDao.getQuestionByQuestionByDescription(question_description);
			
			//�Ե�ǰ����ִʴ���
			ChineseAnalyzerUtil chineseAnalyzerUtil = new ChineseAnalyzerUtil();
			StringBuffer questionContext = new StringBuffer(questionBean1.getQuestion_description()+questionBean1.getQuestion_title());
			StringBuffer questionContext2 = new StringBuffer();
			StringBuffer countTopN = new StringBuffer();
			int questionId = questionBean1.getQuestion_id();
			List<Map.Entry<String, Integer>> map = chineseAnalyzerUtil.getAnalyzerKeywordsString(chineseAnalyzerUtil.getTextDef(questionContext.toString()));

			
			QuestionsKeywordsBean questionKeywords = new QuestionsKeywordsBean();
			
			questionKeywords.setQuestion_id(questionId);
			//ȡ�ִ�Ƶ����ߵ�ǰ20
			for (int i1 = 0; i1 < 20 && i1 < map.size(); i1++) {
				Map.Entry<String, Integer> wordFrenEntry = map.get(i1);
				questionContext2.append(wordFrenEntry.getKey()+",");
				countTopN.append(wordFrenEntry.getValue()+",");
				}
			questionKeywords.setQuesitons_keywords_topN(questionContext2.toString());
			questionKeywords.setQuestions_keywords_counts(countTopN.toString());
			
			//�������ݿ���
			QuestionsKeywordsDaoImp questionsKeywordsDaoImp = new QuestionsKeywordsDaoImp();
			
			if(questionsKeywordsDaoImp.addQuestionsKeywordsBean(questionKeywords)){
				System.out.println("��������ִʴ���ɹ���");
			}
			
			Map<String,Boolean> map1 = new HashMap<String,Boolean>();
			map1.put("message", status);
			
			JSONObject json = JSONObject.fromObject(map1);
			
			response.getWriter().println(json);
		}else {
			
		}
		

		
		
		
	}

}

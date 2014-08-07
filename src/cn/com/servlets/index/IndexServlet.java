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

import cn.com.beans.QuestionBean;
import cn.com.beans.QuestionAllInfoBean;
import cn.com.beans.TagsInfoBean;
import cn.com.daos.AnswerDaoImp;
import cn.com.daos.QuestionDaoImp;
import cn.com.daos.TagsInfoDaoImp;
import cn.com.daos.UserInfoDaoImp;

/**
 * @author Xianxiaofei
 * @date:2014-5-21 ����7:41:41
 */
public class IndexServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		TagsInfoDaoImp tagsInfoDaoImp = new TagsInfoDaoImp();
		QuestionDaoImp questionDaoImp = new QuestionDaoImp();
		AnswerDaoImp answerDaoImp = new AnswerDaoImp();
		UserInfoDaoImp userInfoDaoImp = new UserInfoDaoImp();
		//�õ�����������ϸ��Ϣ����ҳ��ʾ
		List<QuestionAllInfoBean> listAllQuestions =new ArrayList<QuestionAllInfoBean>();
		
		QuestionAllInfoBean questionAllInfoBean = null;
		QuestionBean  questionBean = new QuestionBean();
		
		List<QuestionBean> listAllQuestion = questionDaoImp.getAllQuestions(0,10);
		List<TagsInfoBean> listTags = tagsInfoDaoImp.getHotTags();
		
		//����õ�������������Ϣ
		for(int i=0; i<listAllQuestion.size();i++){
			questionAllInfoBean = new QuestionAllInfoBean();
			questionBean = listAllQuestion.get(i);
			
			//�õ�������ı�ǩ
			String tagsId [] = null;
			//System.out.println(questionBean.getQuestion_tags());
			if(questionBean.getQuestion_tags()== null || questionBean.getQuestion_tags().indexOf(",")==-1){
				//����Ĭ��Ϊ�ޣ����ݿ�Ӧ�ò���0Ϊ�ޱ�ǩ�����������11Ӧ�ÿ��Ը�Ϊ0
				
			}else{
				tagsId = questionBean.getQuestion_tags().split(",");
				StringBuffer tagStrBuffer = new StringBuffer();
				for(int i1=0; i1< tagsId.length;i1++){
					int tagsIdInt = Integer.parseInt(tagsId[i1]);
					String tagsStr = tagsInfoDaoImp.getTagsInfoByTagsId(tagsIdInt).getTags_name()+" ";
					tagStrBuffer.append(tagsStr);
				}
				//���ñ�ǩΪ������ʾ
				questionBean.setQuestion_tags(tagStrBuffer.toString());
			}
			
			
			String userName = userInfoDaoImp.getUserInfoByUserId(questionBean.getQuestion_user_id()).getUser_name();
			int countOfAnswer = answerDaoImp.getContOfAnswer(questionBean.getQuestion_id());
			
			questionAllInfoBean.setQuestionUserName(userName);
			questionAllInfoBean.setCountOfAnswers(countOfAnswer);
			questionAllInfoBean.setVpOfQuestion(50);
			questionAllInfoBean.setBestAnswer("����");
			questionAllInfoBean.setQuestionBean(questionBean);
			listAllQuestions.add(questionAllInfoBean);
		}
		
		
		//�ֱ�õ����⣬�ѽ�����û�����
		int totalOfQuestion = questionDaoImp.getContOfQuestion();
		int countOfSolution = answerDaoImp.getContOfsolution();
		int totalOfUser = userInfoDaoImp.getCountOfUser();
		
		HttpSession session = request.getSession();
		session.setAttribute("totalOfQuestion", totalOfQuestion);
		session.setAttribute("countOfSolution", countOfSolution);
		session.setAttribute("totalOfUser", totalOfUser);
		session.setAttribute("listTags", listTags);
		
		
		//*****************��Χ�Ĵ������ɾ����GetHotServlet���**********************
		//QuestionDaoImp questionDaoImp = new QuestionDaoImp();
		//�������������ϸ��Ϣ��ʾ����ҳ
		List<QuestionAllInfoBean> listHotQuestions =new ArrayList<QuestionAllInfoBean>();
		
		//QuestionAllInfoBean questionAllInfoBean = null;
		//  questionBean = new QuestionBean();
		//AnswerDaoImp answerDaoImp = new AnswerDaoImp();
		
		//�������������Ϣ�������û�������ǩ���ش����ȵȣ�
		List<QuestionBean> listHotQuestion = questionDaoImp.getHotQuestions();
		
		//UserInfoDaoImp userInfoDaoImp = new UserInfoDaoImp();
		
		//����õ�����������������Ϣ
		for(int i=0; i<10;i++){
			questionAllInfoBean = new QuestionAllInfoBean();
			questionBean = listHotQuestion.get(i);
			
			//�õ�������ı�ǩ
			String tagsId [] = null;
			//System.out.println(questionBean.getQuestion_tags());
			if(questionBean.getQuestion_tags()== null || questionBean.getQuestion_tags().indexOf(",")==-1){
				//����Ĭ��Ϊ�ޣ����ݿ�Ӧ�ò���0Ϊ�ޱ�ǩ�����������11Ӧ�ÿ��Ը�Ϊ0
				
			}else{
				tagsId = questionBean.getQuestion_tags().split(",");
				StringBuffer tagStrBuffer = new StringBuffer();
				for(int i1=0; i1< tagsId.length;i1++){
					int tagsIdInt = Integer.parseInt(tagsId[i1]);
					String tagsStr = tagsInfoDaoImp.getTagsInfoByTagsId(tagsIdInt).getTags_name()+" ";
					tagStrBuffer.append(tagsStr);
				}
				//���ñ�ǩΪ������ʾ
				questionBean.setQuestion_tags(tagStrBuffer.toString());
			}
			
			
			String userName = userInfoDaoImp.getUserInfoByUserId(questionBean.getQuestion_user_id()).getUser_name();
			int countOfAnswer = answerDaoImp.getContOfAnswer(questionBean.getQuestion_id());
			
			questionAllInfoBean.setQuestionUserName(userName);
			questionAllInfoBean.setCountOfAnswers(countOfAnswer);
			questionAllInfoBean.setVpOfQuestion(50);
			questionAllInfoBean.setBestAnswer("����");
			questionAllInfoBean.setQuestionBean(questionBean);
			listHotQuestions.add(questionAllInfoBean);
		}
		request.setAttribute("listHotQuestions", listHotQuestions);
		//************************************************
		
		
		//��ҳ��
		int len = questionDaoImp.getContOfQuestion()/10;
//		System.out.println("��ҳ����"+len);
		request.setAttribute("currentPage", 0);
		session.setAttribute("len", len);
		request.setAttribute("listQuestions", listAllQuestions);	
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}

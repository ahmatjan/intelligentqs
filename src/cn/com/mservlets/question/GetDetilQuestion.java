package cn.com.mservlets.question;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import cn.com.beans.AnswerAllInfoBean;
import cn.com.beans.AnswerBean;
import cn.com.beans.DiscussAllInfoBean;
import cn.com.beans.DiscussBean;
import cn.com.beans.QuestionAllInfoBean;
import cn.com.beans.QuestionBean;
import cn.com.daos.AnswerDaoImp;
import cn.com.daos.DiscussDaoImp;
import cn.com.daos.QuestionDaoImp;
import cn.com.daos.TagsInfoDaoImp;
import cn.com.daos.UserInfoDaoImp;

public class GetDetilQuestion extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//得到传入问题ID
		QuestionDaoImp questionDaoImp = new QuestionDaoImp();
//		System.out.println(request.getParameter("question_id"));
		int question_id = Integer.parseInt(request.getParameter("question_id"));
		
		QuestionBean  questionBean = questionDaoImp.getQuestionByQuestionId(question_id);
		
		//得到提问者的名字
		UserInfoDaoImp userInfoDaoImp = new UserInfoDaoImp();
		String userName = userInfoDaoImp.getUserInfoByUserId(questionBean.getQuestion_user_id()).getUser_name();
		
		//得到该问题的标签
		String tagsId [] = null;
		TagsInfoDaoImp tagsInfoDaoImp = new TagsInfoDaoImp();
		//System.out.println(questionBean.getQuestion_tags());
		if(questionBean.getQuestion_tags()== null || questionBean.getQuestion_tags().indexOf(",")==-1){
			//这里默认为无，数据库应该插入0为无标签描述，下面的11应该可以改为0
			tagsId = new String[1];
			tagsId[0]= "11";
		}else{
			tagsId = questionBean.getQuestion_tags().split(",");
		}
		StringBuffer tagStrBuffer = new StringBuffer();
		for(int i=0; i< tagsId.length;i++){
			int tagsIdInt = Integer.parseInt(tagsId[i]);
			String tagsStr = tagsInfoDaoImp.getTagsInfoByTagsId(tagsIdInt).getTags_name()+" ";
			tagStrBuffer.append(tagsStr);
		}
		//设置标签为文字显示
		questionBean.setQuestion_tags(tagStrBuffer.toString());
		
		
		//放入完整的问题描述中
		QuestionAllInfoBean questionAllInfoBean = new QuestionAllInfoBean();
		questionAllInfoBean.setQuestionUserName(userName);
		questionAllInfoBean.setBestAnswer("无");
		questionAllInfoBean.setCountOfAnswers(23);
		questionAllInfoBean.setVpOfQuestion(50);
		questionAllInfoBean.setQuestionBean(questionBean);
		
		//得到该问题的所有答案
		AnswerDaoImp answerDaoImp = new AnswerDaoImp();
		List<AnswerBean> listAnswer = answerDaoImp.findAnswerByQuestionId(question_id);
		List<AnswerAllInfoBean> listAllAnswer = new ArrayList<AnswerAllInfoBean>();
		
		//评论获取
		List<DiscussAllInfoBean> listAllDiscuss = new ArrayList<DiscussAllInfoBean>();
		List<DiscussBean> listdiscussBean = new ArrayList<DiscussBean>();
		DiscussDaoImp discussDao = new DiscussDaoImp();
		DiscussAllInfoBean discussBean = null;
		
		//用户Dao
		UserInfoDaoImp userDao = new UserInfoDaoImp();
		
		
		AnswerAllInfoBean answerAllBean = null;
		for(int i=0; i < listAnswer.size();i++){
			answerAllBean = new AnswerAllInfoBean();
			String answerUserName = userInfoDaoImp.getUserInfoByUserId(listAnswer.get(i).getAnswer_user_id()).getUser_name();
			answerAllBean.setAnswerBean(listAnswer.get(i));
			answerAllBean.setUserName(answerUserName);
			listAllAnswer.add(answerAllBean);
			//得到当前循环问题的评论
			listdiscussBean = discussDao.getDiscussByAnswerId(listAnswer.get(i).getAnswer_id());
			for(int j = 0 ; j < listdiscussBean.size(); j++){
				//评论进行封装
				 discussBean = new DiscussAllInfoBean();
				String userDiscussName = userDao.getUserInfoByUserId(listdiscussBean.get(j).getUser_id()).getUser_name();
				discussBean.setUserName(userDiscussName);
				discussBean.setDiscussBean(listdiscussBean.get(j));
				listAllDiscuss.add(discussBean);
			}
		}
		int coutAnswer = listAnswer.size();
		//通过request把所需信息发送给详细问题页面
		
		request.setAttribute("listAllDiscuss", listAllDiscuss);
		request.setAttribute("coutAnswer", coutAnswer);
		request.setAttribute("question", questionAllInfoBean);
		request.setAttribute("listAnswer", listAllAnswer);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("listAllDiscuss", listAllDiscuss);
		map.put("coutAnswer", coutAnswer);
		map.put("question", questionAllInfoBean);
		map.put("listAnswer", listAllAnswer);
		
		JSONObject json = JSONObject.fromObject(map);
		
		response.getWriter().println(json);
		System.out.println("question_id: " + question_id);
		System.out.println(json);
		
		//request.getRequestDispatcher("question_contents.jsp").forward(request, response);
		
	}

}

package cn.com.servlets.category;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.beans.QuestionAllInfoBean;
import cn.com.beans.QuestionBean;
import cn.com.beans.TagsCategoriesBean;
import cn.com.beans.TagsInfoBean;
import cn.com.beans.UserAllInfoBean;
import cn.com.daos.AnswerDaoImp;
import cn.com.daos.QuestionDaoImp;
import cn.com.daos.TagsCategoriesDao;
import cn.com.daos.TagsInfoDaoImp;
import cn.com.daos.UserInfoDaoImp;
import cn.com.util.GetHotUserInfoUtil;

/**
 * Servlet implementation class GetDetailTagsServlet
 */
public class GetDetailTagsServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		int tags_categories_id = Integer.parseInt(request
				.getParameter("tags_categories_id"));

		//编码转换
		String tags_name = request.getParameter("tags_name");
		String result = new String(tags_name.getBytes("ISO-8859-1"),"UTF-8"); 
		String tags_id = request.getParameter("tags_id");
		System.out.println(result+":"+tags_id);
		System.out.println(tags_categories_id);

		QuestionAllInfoBean questionAllInfoBean = null;
		TagsInfoDaoImp tagsInfoDaoImp = new TagsInfoDaoImp();
		TagsCategoriesDao tagsCaregory = new TagsCategoriesDao();

		TagsCategoriesBean tagsCB = new TagsCategoriesBean();
		QuestionBean questionBean = new QuestionBean();
		tagsCB = tagsCaregory.getTagsCategoriesrById(tags_categories_id);

		UserInfoDaoImp userInfoDaoImp = new UserInfoDaoImp();
		QuestionDaoImp questionDao = new QuestionDaoImp();
		AnswerDaoImp answerDaoImp = new AnswerDaoImp();
		List<QuestionAllInfoBean> listAllQuestions = new ArrayList<QuestionAllInfoBean>();
		
		//正则表达式匹配
		Pattern pattern = Pattern.compile("[0-9]*"); 
				
		List<QuestionBean> listTime = new ArrayList<QuestionBean>();
		listTime = questionDao.getQuestionByQuestionByTags(tags_id);

		// 处理得到的所有问题信息
		for (int i = 0; i < listTime.size(); i++) {
			questionAllInfoBean = new QuestionAllInfoBean();
			questionBean = listTime.get(i);
			if (questionBean.getQuestion_description().length() > 100) {
				questionBean.setQuestion_description(questionBean
						.getQuestion_description().substring(0, 100) + "...");
			}
			//得到该问题的标签
			String tagsId [] = null;
//			System.out.println(questionBean.getQuestion_tags());
			if(questionBean.getQuestion_tags() == null||questionBean.getQuestion_tags().equals("")){
				//这里默认为无，数据库应该插入0为无标签描述，下面的11应该可以改为0
				questionBean.setQuestion_tags("暂无");
			}else{
				if(questionBean.getQuestion_tags() != null&&questionBean.getQuestion_tags().indexOf(",")==-1&&pattern.matcher(questionBean.getQuestion_tags()).matches()){
					String tagsStr = tagsInfoDaoImp.getTagsInfoByTagsId(Integer.parseInt(questionBean.getQuestion_tags())).getTags_name();
					questionBean.setQuestion_tags(tagsStr);
				}
				else{
					if(questionBean.getQuestion_tags().indexOf(",")!=-1){
						tagsId = questionBean.getQuestion_tags().split(",");
						StringBuffer tagStrBuffer = new StringBuffer();
						for(int i1=0; i1< tagsId.length;i1++){
							int tagsIdInt = Integer.parseInt(tagsId[i1]);
							String tagsStr = tagsInfoDaoImp.getTagsInfoByTagsId(tagsIdInt).getTags_name()+" ";
							tagStrBuffer.append(tagsStr);
						}
						//设置标签为文字显示
						questionBean.setQuestion_tags(tagStrBuffer.toString());
					}
				}
			}
			String userName = userInfoDaoImp.getUserInfoByUserId(
					questionBean.getQuestion_user_id()).getUser_name();
			int countOfAnswer = answerDaoImp.getContOfAnswer(questionBean
					.getQuestion_id());

			questionAllInfoBean.setQuestionUserName(userName);
			questionAllInfoBean.setCountOfAnswers(countOfAnswer);
			questionAllInfoBean.setVpOfQuestion(50);
			questionAllInfoBean.setBestAnswer("暂无");
			questionAllInfoBean.setQuestionBean(questionBean);
			listAllQuestions.add(questionAllInfoBean);
		}

		List<QuestionBean> listHot = new ArrayList<QuestionBean>();
		List<QuestionAllInfoBean> listHotQuestions = new ArrayList<QuestionAllInfoBean>();
		listHot = questionDao.getHotQuestionByQuestionByTags(tags_id);
		// 处理得到的所有热门问题信息
		for (int i = 0; i < listHot.size(); i++) {
			questionAllInfoBean = new QuestionAllInfoBean();
			questionBean = listHot.get(i);
			if (questionBean.getQuestion_description().length() > 100) {
				questionBean.setQuestion_description(questionBean
						.getQuestion_description().substring(0, 100) + "...");
			}
			//得到该问题的标签
			String tagsId [] = null;
//			System.out.println(questionBean.getQuestion_tags());
			if(questionBean.getQuestion_tags() == null||questionBean.getQuestion_tags().equals("")){
				//这里默认为无，数据库应该插入0为无标签描述，下面的11应该可以改为0
				questionBean.setQuestion_tags("暂无");
			}else{
				if(questionBean.getQuestion_tags() != null&&questionBean.getQuestion_tags().indexOf(",")==-1&&pattern.matcher(questionBean.getQuestion_tags()).matches()){
					String tagsStr = tagsInfoDaoImp.getTagsInfoByTagsId(Integer.parseInt(questionBean.getQuestion_tags())).getTags_name();
					questionBean.setQuestion_tags(tagsStr);
				}
				else{
					if(questionBean.getQuestion_tags().indexOf(",")!=-1){
						tagsId = questionBean.getQuestion_tags().split(",");
						StringBuffer tagStrBuffer = new StringBuffer();
						for(int i1=0; i1< tagsId.length;i1++){
							int tagsIdInt = Integer.parseInt(tagsId[i1]);
							String tagsStr = tagsInfoDaoImp.getTagsInfoByTagsId(tagsIdInt).getTags_name()+" ";
							tagStrBuffer.append(tagsStr);
						}
						//设置标签为文字显示
						questionBean.setQuestion_tags(tagStrBuffer.toString());
					}
				}
			}
			String userName = userInfoDaoImp.getUserInfoByUserId(
					questionBean.getQuestion_user_id()).getUser_name();
			int countOfAnswer = answerDaoImp.getContOfAnswer(questionBean
					.getQuestion_id());

			questionAllInfoBean.setQuestionUserName(userName);
			questionAllInfoBean.setCountOfAnswers(countOfAnswer);
			questionAllInfoBean.setVpOfQuestion(50);
			questionAllInfoBean.setBestAnswer("暂无");
			questionAllInfoBean.setQuestionBean(questionBean);
			listHotQuestions.add(questionAllInfoBean);
		}

		System.out.println(tagsCB.getTags_categories_description());
		request.setAttribute("tagsDec", tagsCB.getTags_categories_description());
		request.setAttribute("listQuestionTime", listAllQuestions);
		request.setAttribute("listQuestionHot", listHotQuestions);
		request.setAttribute("tags_name", result);
		request.getRequestDispatcher("tag_category_detail.jsp").forward(request, response);
	}
	
	

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

}

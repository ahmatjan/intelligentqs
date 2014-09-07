package cn.com.servlets.category;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.beans.TagsCategoriesBean;
import cn.com.beans.TagsInfoBean;
import cn.com.daos.TagsCategoriesDao;
import cn.com.daos.TagsInfoDaoImp;

/**
 * Servlet implementation class InitCartgoryServlet
 */
public class InitCartgoryServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<TagsCategoriesBean> listTagCaetgory = new ArrayList<TagsCategoriesBean>();
		TagsCategoriesDao tagsCategoryDao = new TagsCategoriesDao();
		listTagCaetgory = tagsCategoryDao.getTagsCategoriesrByLastId(0);
		TagsInfoDaoImp tagsDao = new TagsInfoDaoImp();
		
		List<TagsInfoBean> listTagsInfo = new ArrayList<TagsInfoBean>();
		List<TagsInfoBean> listTemp = new ArrayList<TagsInfoBean>();
		for(int i= 0;i<listTagCaetgory.size();i++){
			listTemp = tagsDao.getTagsInfoByCategory(listTagCaetgory.get(i).getTags_categories_id());
			for(int j = 0; j < listTemp.size();j++ ){
				listTagsInfo.add(listTemp.get(j));
			}
		}
		
		System.out.println(listTagCaetgory.size());
		request.setAttribute("listCategory",listTagCaetgory );
		request.setAttribute("listAll", listTagsInfo);
		request.getRequestDispatcher("category.jsp").forward(request, response);
	}

}

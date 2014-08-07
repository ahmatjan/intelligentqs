/**
 * 
 */
package cn.com.servlets.tags;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.beans.TagsInfoBean;
import cn.com.daos.TagsInfoDaoImp;

/***********************
 * @author butterfly   
 * @version：1.0        
 * @created：2014-5-14    
 ***********************
 */

/**
 * @author butterfly
 *	得到所有标签信息
 */
public class GetAllTagsInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		TagsInfoDaoImp tagsInfoDaoImp = new TagsInfoDaoImp();
		List<TagsInfoBean> list = tagsInfoDaoImp.getAllTagsInfo();
		request.setAttribute("tagsInfoList", list);
		request.getRequestDispatcher("question_ask.jsp").forward(request, response);
	
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}


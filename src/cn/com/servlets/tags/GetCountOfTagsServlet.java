/**
 * 
 */
package cn.com.servlets.tags;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.daos.TagsInfoDaoImp;

/***********************
 * @author butterfly   
 * @version：1.0        
 * @created：2014-5-14    
 ***********************
 */

/**
 * @author butterfly
 * 得到总数
 *	
 */
public class GetCountOfTagsServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		TagsInfoDaoImp tagsDaoImp = new TagsInfoDaoImp();
		int tagsCount = tagsDaoImp.getContOfTags();
		request.setAttribute("countOfTags", tagsCount);
		request.getRequestDispatcher("index.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}


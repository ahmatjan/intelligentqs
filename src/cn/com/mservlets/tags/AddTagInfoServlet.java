/**
 * 
 */
package cn.com.mservlets.tags;

import java.io.IOException;
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
 *
 */
public class AddTagInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String tags_name = request.getParameter("tags_name");
		if(tags_name == null|| tags_name == ""){
			request.setAttribute("Msg", "标签已不能为空");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}
		TagsInfoBean tagsBean = new TagsInfoBean();
		tagsBean.setTags_name(tags_name);
		
		TagsInfoDaoImp tagsInfoDaoImp = new TagsInfoDaoImp();
		List<TagsInfoBean> list = tagsInfoDaoImp.getTagsInfoByTagsName(tags_name);

		if(list.size() > 0){
			request.setAttribute("Msg", "该标签已经存在");
			request.getRequestDispatcher("index.jsp").forward(request, response);
			return;
		}else{
			if(tagsInfoDaoImp.addTagsInfo(tagsBean));
			request.setAttribute("Msg", "新增成功");
			request.getRequestDispatcher("getAllTagsInfo").forward(request, response);
		}
		
	}

}


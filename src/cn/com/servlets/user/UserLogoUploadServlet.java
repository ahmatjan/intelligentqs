package cn.com.servlets.user;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.com.beans.UserInfoBean;
import cn.com.daos.UserInfoDaoImp;

/**
 * @author Friday
 * @date 2014-6-19 下午7:27:13
 */
public class UserLogoUploadServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String path = request.getContextPath();
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		UserInfoBean userBean = (UserInfoBean) session.getAttribute("userBean");
//		System.out.println("-------userBean.getUser_id()-----"
//				+ userBean.getUser_id());
		String imageURL = null;// 头像的存放地址
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(4096);
		factory.setRepository(new File("c:\\"));
		ServletFileUpload upload = new ServletFileUpload(factory);
		String newName = new Date().getTime() + "";

		upload.setSizeMax(10 * 1024 * 1024);
		try {
			List<FileItem> fileItems = upload.parseRequest(request);
			int i = 1;
//			System.out.println(fileItems.size());
			FileItem o = fileItems.get(i);

			File f = new File(o.getName());
			String filePath = this.getServletConfig().getServletContext()
					.getRealPath("/");//tomcat路径
//			String filePath = "E:/MyEclipse10.0/Workspaces/hnust_qa/WebRoot/";//工程路径
			String[] imageName = f.getName().split("\\.");
			String imageClass = imageName[1];
			String imageNewName = newName + "." + imageClass;
			File fs = new File(filePath + "userLogos/" + imageNewName);
			o.write(fs);
			imageURL = "userLogos/" + imageNewName;

			userBean.setUser_logo(imageURL);
			UserInfoDaoImp userDao = new UserInfoDaoImp();

			if (userDao.updateUserInfoByUserId(userBean)) {
				request.setAttribute("Msg", "头像上传成功！");
				request.getRequestDispatcher("accountSettings.jsp").forward(
						request, response);
			} else {
				request.setAttribute("Msg", "头像上传失败！");

				request.getRequestDispatcher("updateUserLogo.jsp").forward(
						request, response);
			}

		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.close();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}

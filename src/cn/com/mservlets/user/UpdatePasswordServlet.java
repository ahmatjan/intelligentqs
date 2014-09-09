package cn.com.mservlets.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.beans.UserInfoBean;
import cn.com.daos.UserInfoDaoImp;

/**
 * @author Friday
 * @date 2014-6-19  ����4:14:57
 */
public class UpdatePasswordServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserInfoBean userBean = new UserInfoBean();
		userBean = (UserInfoBean)session.getAttribute("userBean");
		String oldPassword = request.getParameter("oldPassword");
		if(oldPassword.equals(userBean.getUser_password())){
			String newPassword = request.getParameter("newPassword");
			System.out.println(userBean.getUser_name()+":"+newPassword);
			userBean.setUser_password(newPassword);
			UserInfoDaoImp userDao = new UserInfoDaoImp();
			boolean bool = userDao.updateUserInfoByUserId(userBean);
			if (bool) {
				userBean = userDao.getUserInfoByUserId(userBean.getUser_id());
				session.setAttribute("userBean", userBean);
				request.setAttribute("Msg", "�����޸ĳɹ��������µ�¼");
				session.removeAttribute("userBean");
				System.out.println("�����޸ĳɹ��������µ�¼");
				request.getRequestDispatcher("userLogin.jsp").forward(request, response);
			} else {
				request.setAttribute("Msg", "�����޸�ʧ��");
				request.getRequestDispatcher("updatePassword.jsp")
						.forward(request, response);
			}
		}else{
			request.setAttribute("Msg", "��������֤����");
			request.getRequestDispatcher("updatePassword.jsp")
					.forward(request, response);
			
		}
		
	}

}

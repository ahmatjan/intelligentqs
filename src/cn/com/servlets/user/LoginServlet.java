/**
 * 
 */
package cn.com.servlets.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.beans.UserInfoBean;
import cn.com.daos.UserInfoDaoImp;

/**
 * @author Friday
 * @date 2014-5-13 ����4:02:19
 */
public class LoginServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String ver = (String) session.getAttribute("rand");
		String vercode = request.getParameter("vercode");
		// �ж���֤���Ƿ���ȷ  
		if(!vercode.equalsIgnoreCase(ver)){
			request.setAttribute("Msg", "<font color='red'>��֤�����</font>");
			session.removeAttribute("vercode");
			request.getRequestDispatcher("userLogin.jsp").forward(request, response);
			return;
		}
		String user_name = request.getParameter("user_name");
		String user_password = request.getParameter("user_password");
		System.out.println(user_name+":"+user_password);
		
        
		UserInfoDaoImp userDao = new UserInfoDaoImp();
		boolean bool = userDao.validateByUserNameAndUserPassword(user_name,
				user_password);

		if (bool) {
			UserInfoBean userBean = new UserInfoBean();
			session.removeAttribute("vercode");
			userBean = userDao.getUserInfoByUserName(user_name);
			session.setAttribute("userBean", userBean);
			request.getRequestDispatcher("index").forward(request, response);
			System.out.println("��¼�ɹ�");
		} else {
			session.removeAttribute("vercode");
			request.setAttribute("Msg", "�û�������������");
			request.getRequestDispatcher("userLogin.jsp")
					.forward(request, response);
		}
	}

}


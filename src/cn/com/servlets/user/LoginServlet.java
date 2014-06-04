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
 * @date 2014-5-13 下午4:02:19
 */
public class LoginServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String user_name = request.getParameter("user_name");
		String user_password = request.getParameter("user_password");
		System.out.println(user_name+":"+user_password);
		UserInfoDaoImp userDao = new UserInfoDaoImp();
		boolean bool = userDao.validateByUserNameAndUserPassword(user_name,
				user_password);

		if (bool) {
			HttpSession session = request.getSession();
			UserInfoBean userBean = new UserInfoBean();
			userBean = userDao.getUserInfoByUserName(user_name);
			session.setAttribute("userBean", userBean);
			request.getRequestDispatcher("main.jsp").forward(request, response);
			System.out.println("登录成功");
		} else {
			request.setAttribute("Msg", "用户名或密码有误");
			request.getRequestDispatcher("login.jsp")
					.forward(request, response);
		}
	}

}

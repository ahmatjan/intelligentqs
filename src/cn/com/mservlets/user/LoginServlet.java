/**
 * 
 */
package cn.com.mservlets.user;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
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
		HttpSession session = request.getSession();
		
		String ver = (String) session.getAttribute("rand");
		String vercode = request.getParameter("vercode");
		// 判断验证码是否正确  
/*		if(!vercode.equalsIgnoreCase(ver)){
			request.setAttribute("Msg", "<font color='red'>验证码错误！</font>");
			session.removeAttribute("vercode");
			request.getRequestDispatcher("userLogin.jsp").forward(request, response);
			return;
		}*/
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
//			request.getRequestDispatcher("index").forward(request, response);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("message", true);
			map.put("userId", userBean.getUser_id());
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().println(json);
			System.out.println("登录成功");
		} else {
			session.removeAttribute("vercode");
			request.setAttribute("Msg", "用户名或密码有误");
//			request.getRequestDispatcher("userLogin.jsp")
//					.forward(request, response);
			Map<String,Boolean> map = new HashMap<String,Boolean>();
			map.put("message", false);
			JSONObject json = JSONObject.fromObject(map);
			response.getWriter().println(json);
		}
	}

}


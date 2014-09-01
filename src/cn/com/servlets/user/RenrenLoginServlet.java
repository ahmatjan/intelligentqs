package cn.com.servlets.user;
/**
 * @author Xianxiaofei
 * @date:2014年8月5日 下午5:28:37
 */

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.omg.PortableInterceptor.USER_EXCEPTION;

import cn.com.beans.UserInfoBean;
import cn.com.daos.UserInfoDaoImp;
import cn.com.renrenConfig.AppConfig;

import com.renren.api.AuthorizationException;
import com.renren.api.RennClient;
import com.renren.api.RennException;

/**
 * 用于处理用人人网帐号登录的Servlet，
 * 当从人人网的OAuth 2.0服务器跳转回来时，会到达这个Servlet，参数中会有code
 */
@SuppressWarnings("serial")
public class RenrenLoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, java.io.IOException {
		String code = request.getParameter("code");
		if (code == null || code.length() == 0) {
			//缺乏有效参数，跳转到登录页去
			response.sendRedirect("userLogin.jsp");
			return;
		}
		//到人人网的OAuth 2.0的token endpoint用code换取access token
		RennClient client = new RennClient(AppConfig.API_KEY, AppConfig.APP_SECRET);
		try {
			client.authorizeWithAuthorizationCode(code, "http://hnustqa.duapp.com/rr_login");
			com.renren.api.service.User user = client.getUserService().getUserLogin ();
			
			String userName = user.getName();
			String userPassword = Long.toString(user.getId());
			
			System.out.println("用户姓名："+userName);
			System.out.println("用户密码："+userPassword);
			request.setAttribute("userName", userName);
			request.setAttribute("userPassword", userPassword);
			//判断该账号是否已绑定
			UserInfoDaoImp userDaoImp = new UserInfoDaoImp();
			UserInfoBean userInfoBean = new UserInfoBean();
			//如果已经绑定
			if(userDaoImp.validateByUserNameAndUserPassword(userName, userPassword)){
				userInfoBean = userDaoImp.getUserInfoByUserName(userName);
				HttpSession session = request.getSession();
				session.setAttribute("userBean", userInfoBean);
				request.getRequestDispatcher("index").forward(request, response);

			}
			else{
				request.getRequestDispatcher("UserBind.jsp").forward(request, response);
			}
			
		} catch (AuthorizationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RennException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


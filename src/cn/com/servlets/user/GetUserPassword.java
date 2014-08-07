package cn.com.servlets.user;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.beans.UserInfoBean;
import cn.com.daos.UserInfoDaoImp;
import cn.com.util.MailSenderInfo;
import cn.com.util.SimpleMailSender;

/**
 * Servlet implementation class GetUserPassword
 */
public class GetUserPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		String ver = (String) session.getAttribute("rand");
		String vercode = request.getParameter("vercode");

		String user_name = request.getParameter("user_name");
		String user_email = request.getParameter("user_email");

		// 判断验证码是否正确
		if (!vercode.equalsIgnoreCase(ver)) {
			request.setAttribute("Msg", "<font color='red'>验证码错误！</font>");
			request.setAttribute("user_name", user_name);
			request.setAttribute("user_email", user_email);
			session.removeAttribute("vercode");
			request.getRequestDispatcher("findPassWord.jsp").forward(request,
					response);
			return;
		}

		UserInfoDaoImp userDao = new UserInfoDaoImp();
		boolean bool = userDao.validateByUserNameAndUserEmail(user_name,
				user_email);
		UserInfoBean userBean = new UserInfoBean();
		userBean = userDao.getUserInfoByUserName(user_name);
		if (bool) {
			session.removeAttribute("vercode");

			MailSenderInfo mailInfo = new MailSenderInfo();
			mailInfo.setMailServerHost("smtp.qq.com");
			mailInfo.setMailServerPort("25");
			mailInfo.setValidate(true);
			mailInfo.setUserName("707406343@qq.com");
			mailInfo.setPassword("bubulovedashu");// 您的邮箱密码
			mailInfo.setFromAddress("707406343@qq.com");
			mailInfo.setToAddress(userBean.getUser_email());
			mailInfo.setSubject("Find Password ID:"+new Date().getTime());

			mailInfo.setContent("<div style='background-color: white; width: 100%;'><div style='background-color: #3276b1;'><div style='text-align: left;padding-top: 0px;'><font style='color: white;font-family: cursive;' size='5px;'><b>湖南科技大学智能问答平台</b></font></div><div style='background-color: white;text-align: left;'><span style='color:#333;font-size:20px;font-weight:bold;text-decoration:none'>"+userBean.getUser_name()+"，你好！ </span></div><div style='background-color: white;'><span style='text-decoration:none;color:#000;font-size:14px;line-height:20px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您的密码为：<font color='red'>"+userBean.getUser_password()+"。</font><a href='http://hnustqa.duapp.com/userLogin.jsp'>点击登录</a>。</span><br><span style='text-decoration:none;color:#666;font-size:13px;line-height:20px'>如无法点击，请将<a target='_blank' style='color:#0077B5;text-decoration:none;' href='http://hnustqa.duapp.com/userLogin.jsp'>http://hnustqa.duapp.com/userLogin.jsp</a>&nbsp;拷贝到浏览器地址栏中直接访问。</span></div><div style='background-color: white;text-align: right;'><span style='color:#333;font-size:20px;font-weight:bold;text-decoration:none'><b >From:湖南科技大学智能问答平台团队</b></span></div></div><div> </div></div>" + new Date());
			SimpleMailSender sms = new SimpleMailSender();
//			sms.sendTextMail(mailInfo);// 发送文体格式
			sms.sendHtmlMail(mailInfo);// 发送html格式
			request.getRequestDispatcher("userLogin.jsp").forward(request,
					response);

		} else {
			session.removeAttribute("vercode");
			request.setAttribute("Msg", "用户名或邮箱有误！");
			request.setAttribute("user_name", user_name);
			request.setAttribute("user_email", user_email);
			request.getRequestDispatcher("findPassWord.jsp").forward(request,
					response);
		}
	}

}

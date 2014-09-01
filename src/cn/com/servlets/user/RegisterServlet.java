package cn.com.servlets.user;

import java.io.IOException;
import java.io.PrintWriter;
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
 * @author xianxiaofei
 * @date 2014-6-4 ����3:00:15
 */
public class RegisterServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String passwd1 = request.getParameter("passwd1");
		String passwd2 = request.getParameter("passwd2");
		String userEmail = request.getParameter("userEmail");
		System.out.println(userName+userEmail+passwd2);
		UserInfoDaoImp userDao = new UserInfoDaoImp();
		if (passwd1 != null & passwd2 != null) {
			if (!passwd1.equals("") & !passwd2.equals("")
					& userDao.isIleagle(passwd1) & userDao.isIleagle(passwd2)) {
				if (passwd1.equals(passwd2)) {
					System.out.println(userName + ":" + passwd1);

					if (!userDao.validataByUserName(userName)) {
						UserInfoBean uib = new UserInfoBean();
						uib.setUser_name(userName);
						uib.setUser_password(passwd1);
						uib.setUser_email(userEmail);
						uib.setUser_mark(0);
						
						boolean bool = userDao.addUserInfo(uib);
						if (bool) {
							HttpSession session = request.getSession();
							UserInfoBean userBean = new UserInfoBean();
							userBean = userDao.getUserInfoByUserName(userName);
							session.setAttribute("userBean", userBean);
							request.getRequestDispatcher("index").forward(
									request, response);
							System.out.println("Welcome to HNUST-QA");
							
							MailSenderInfo mailInfo = new MailSenderInfo();
							mailInfo.setMailServerHost("smtp.qq.com");
							mailInfo.setMailServerPort("25");
							mailInfo.setValidate(true);
							mailInfo.setUserName("707406343@qq.com");
							mailInfo.setPassword("bubulovedashu");// ������������
							mailInfo.setFromAddress("707406343@qq.com");
							mailInfo.setToAddress(userBean.getUser_email());
							mailInfo.setSubject("Register Message"+new Date().getTime());
					
							mailInfo.setContent("<div style='background-color: white; width: 100%;'><div style='background-color: #3276b1;'><div style='text-align: left;padding-top: 0px;'><font style='color: white;font-family: cursive;' size='5px;'><b>���ϿƼ���ѧ�����ʴ�ƽ̨</b></font></div><div style='background-color: white;text-align: left;'><span style='color:#333;font-size:20px;font-weight:bold;text-decoration:none'>"+userBean.getUser_name()+"����ã� </span></div><div style='background-color: white;'><span style='text-decoration:none;color:#000;font-size:14px;line-height:20px;'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;��ϲ���ɹ�ע����ϿƼ���ѧ�����ʴ�ƽ̨���������㽫���ڶ��hnuster�����ƴ�ѧ�ӣ�һ��̽���ƴ��ܱߵİ��ء���ӭ���ʣ�<a href='http://hnustqa.duapp.com'>���ϿƼ���ѧ�����ʴ�ƽ̨</a>����<a href='http://hnustqa.duapp.com'>http://www.hnust-qa.tk</a>��</span><br><span style='text-decoration:none;color:#666;font-size:13px;line-height:20px'>���޷�������뽫<a target='_blank' style='color:#0077B5;text-decoration:none;' href='http://www.hnust-qa.tk'>http://www.hnust-qa.tk</a>&nbsp;�������������ַ����ֱ�ӷ��ʡ�</span></div><div style='background-color: white;text-align: right;'><span style='color:#333;font-size:20px;font-weight:bold;text-decoration:none'><b >From:���ϿƼ���ѧ�����ʴ�ƽ̨�Ŷ�</b></span></div></div><div> </div></div>"+new Date());		
							SimpleMailSender sms = new SimpleMailSender();
//							sms.sendTextMail(mailInfo);// ���������ʽ
							sms.sendHtmlMail(mailInfo);// ����html��ʽ
							System.out.println("send msg success!");
							
						} else {
							request.setAttribute("Msg", "ע��ʧ��");
							request.getRequestDispatcher("userRegister.jsp")
									.forward(request, response);
						}
					} else {
						request.setAttribute("Msg", "�û����Ѵ���");
						request.getRequestDispatcher("userRegister.jsp")
								.forward(request, response);
					}
				} else {
					request.setAttribute("Msg", "�������벻һ��");
					request.getRequestDispatcher("userRegister.jsp").forward(
							request, response);
				}
			} else {
				request.setAttribute("Msg", "�������벻�Ϸ�");
				request.getRequestDispatcher("userRegister.jsp").forward(
						request, response);

			}
		}
	}

}

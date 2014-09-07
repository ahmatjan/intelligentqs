package cn.com.servlets.user;

import cn.com.daos.UserInfoDaoImp;

import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Date: 12-12-4
 * Time: ����4:36
 */
public class QQLoginRedirectServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=utf-8");

        try {
            AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);

            String accessToken   = null,
                   openID        = null;
            long tokenExpireIn = 0L;




            if (accessTokenObj.getAccessToken().equals("")) {
//                ���ǵ���վ��CSRF�����˻����û�ȡ������Ȩ
//                ��һЩ����ͳ�ƹ���
                System.out.print("û�л�ȡ����Ӧ����");
            } else {
                accessToken = accessTokenObj.getAccessToken();
                tokenExpireIn = accessTokenObj.getExpireIn();

                request.getSession().setAttribute("demo_access_token", accessToken);
                request.getSession().setAttribute("demo_token_expirein", String.valueOf(tokenExpireIn));

                // ���û�ȡ����accessToken ȥ��ȡ��ǰ�õ�openid -------- start
                OpenID openIDObj =  new OpenID(accessToken);
                openID = openIDObj.getUserOpenID();

                System.out.println("��ӭ�㣬����Ϊ " + openID + " ���û�!");
                request.getSession().setAttribute("demo_openid", openID);
                // ���û�ȡ����accessToken ȥ��ȡ��ǰ�û���openid --------- end


                System.out.println("<p> start -----------------------------------���û�ȡ����accessToken,openid ȥ��ȡ�û���Qzone���ǳƵ���Ϣ ---------------------------- start </p>");
                UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
                UserInfoBean qqUserInfoBean = qzoneUserInfo.getUserInfo();
                System.out.println("<br/>");
                if (qqUserInfoBean.getRet() == 0) {
                    String userName = qqUserInfoBean.getNickname();
                    String userPassword =  openID;
                    userPassword = userPassword.substring(0,6);
                    request.setAttribute("userName", userName);
        			request.setAttribute("userPassword", userPassword);
        			System.out.println(userPassword);
        			//�жϸ��˺��Ƿ��Ѱ�
        			UserInfoDaoImp userDaoImp = new UserInfoDaoImp();
        			cn.com.beans.UserInfoBean userInfoBean = new cn.com.beans.UserInfoBean();
        			//����Ѿ���
        			if(userDaoImp.validateByUserNameAndUserPassword(userName, userPassword)){
        				userInfoBean = userDaoImp.getUserInfoByUserName(userName);
        				HttpSession session = request.getSession();
        				session.setAttribute("userBean", userInfoBean);
        				request.getRequestDispatcher("index").forward(request, response);
        				return;
        			}
        			else{
        				request.getRequestDispatcher("UserBind.jsp").forward(request, response);
        				return;
        			}
                } else {
                    System.out.println("�ܱ�Ǹ������û����ȷ��ȡ��������Ϣ��ԭ���ǣ� " + qqUserInfoBean.getMsg());
                }
            }
        } catch (Exception e) {
        }
    }
}

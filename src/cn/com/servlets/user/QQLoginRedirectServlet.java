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
 * Time: 下午4:36
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
//                我们的网站被CSRF攻击了或者用户取消了授权
//                做一些数据统计工作
                System.out.print("没有获取到响应参数");
            } else {
                accessToken = accessTokenObj.getAccessToken();
                tokenExpireIn = accessTokenObj.getExpireIn();

                request.getSession().setAttribute("demo_access_token", accessToken);
                request.getSession().setAttribute("demo_token_expirein", String.valueOf(tokenExpireIn));

                // 利用获取到的accessToken 去获取当前用的openid -------- start
                OpenID openIDObj =  new OpenID(accessToken);
                openID = openIDObj.getUserOpenID();

                System.out.println("欢迎你，代号为 " + openID + " 的用户!");
                request.getSession().setAttribute("demo_openid", openID);
                // 利用获取到的accessToken 去获取当前用户的openid --------- end


                System.out.println("<p> start -----------------------------------利用获取到的accessToken,openid 去获取用户在Qzone的昵称等信息 ---------------------------- start </p>");
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
        			//判断该账号是否已绑定
        			UserInfoDaoImp userDaoImp = new UserInfoDaoImp();
        			cn.com.beans.UserInfoBean userInfoBean = new cn.com.beans.UserInfoBean();
        			//如果已经绑定
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
                    System.out.println("很抱歉，我们没能正确获取到您的信息，原因是： " + qqUserInfoBean.getMsg());
                }
            }
        } catch (QQConnectException | ServletException e) {
        }
    }
}

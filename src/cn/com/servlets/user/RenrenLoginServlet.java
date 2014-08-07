package cn.com.servlets.user;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import cn.com.config.AppConfig;

import com.renren.api.client.RenrenApiClient;
import com.renren.api.client.utils.HttpURLUtils;

/**
 * ���ڴ������������ʺŵ�¼��Servlet��
 * ������������OAuth 2.0��������ת����ʱ���ᵽ�����Servlet�������л���code
 */
@SuppressWarnings("serial")
public class RenrenLoginServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, java.io.IOException {
		String code = request.getParameter("code");
		if (code == null || code.length() == 0) {
			//ȱ����Ч��������ת����¼ҳȥ
			response.sendRedirect("userLogin.jsp");
			return;
		}
		//����������OAuth 2.0��token endpoint��code��ȡaccess token
		String rrOAuthTokenEndpoint = "https://graph.renren.com/oauth/token";
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("client_id", AppConfig.API_KEY);
		parameters.put("client_secret", AppConfig.APP_SECRET);
		parameters.put("redirect_uri", "http://hnustqa.duapp.com");//���redirect_uriҪ��֮ǰ����authorization endpoint��ֵһ��
		parameters.put("grant_type", "authorization_code");
		parameters.put("code", code);
		String tokenResult = HttpURLUtils.doPost(rrOAuthTokenEndpoint, parameters);
		JSONObject tokenJson = (JSONObject) JSONValue.parse(tokenResult);
		if (tokenJson != null) {
			String accessToken = (String) tokenJson.get("access_token");
			Long expiresIn = (Long) tokenJson.get("expires_in");//�������ʱ��ʱ��Σ�������
			long currentTime = System.currentTimeMillis() / 1000;
			long expiresTime = currentTime + expiresIn;//�������ڵ�ʱ��㣨������
			request.getSession().setAttribute("expiresTime", expiresTime);
			//����������API����û���Ϣ
			RenrenApiClient apiClient = new RenrenApiClient(accessToken, true);
			int rrUid = apiClient.getUserService().getLoggedInUser();
			JSONArray userInfo = apiClient.getUserService().getInfo(String.valueOf(rrUid), "name,headurl");
			if (userInfo != null && userInfo.size() > 0) {
				JSONObject currentUser = (JSONObject) userInfo.get(0);
				if (currentUser != null) {
					String name = (String) currentUser.get("name");
					String headurl = (String) currentUser.get("headurl");
					//�ж��ʺŹ���������û���ֳɵĹ���
//					String username = RenrenUserMappingDAO.getInstance().getUsername(rrUid);
//					User user;
//					if (username == null) {
//						//���ʺŹ�������û�м�¼���û��ǵ�һ������Ϊ����û�����һ��User����
//						User newUser = new User();
//						newUser.setName(name);
//						newUser.setHeadurl(headurl);
//						//�Զ�ƴװһ��username���漴����һ��password��ʵ��ʵ��ʱ������Ӧ�ñ�֤
//                                                  ƴװ������username���������ʺų�ͻ
//						username = "renren-" + rrUid;
//						String password = UUID.randomUUID().toString();
//						newUser.setUsername(username);
//						newUser.setPassword(password);
//						//���浽�û���
//						UserDAO.getInstance().addUser(newUser);
//						//���浽�ʺŹ�����
//						RenrenUserMappingDAO.getInstance().addMapping(rrUid, username);
//						user = newUser;
//					}
//					else {
//						//�û����ǵ�һ�����ˣ��Ѿ����ʺŹ�����������
//						user = UserDAO.getInstance().getUser(username);
//					}
					//���û������Ϣ�����ڻỰ��
//					request.getSession().setAttribute("user", user);
					//�ѵ�¼����ת��������ҳ
					response.sendRedirect("/profile");
					return;
				}
			}
		}
		response.sendRedirect("/login");
	}
}
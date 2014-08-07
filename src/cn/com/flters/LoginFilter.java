package cn.com.flters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.com.beans.UserInfoBean;

/**
 * @author Friday
 * @date 2014-6-19 ����10:36:01
 */
public class LoginFilter implements Filter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
	 * javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// ��������������Ҫ�õ�request,response,session����
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		HttpSession session = servletRequest.getSession();

		// ����û������URI
		String path = servletRequest.getRequestURI();
		// System.out.println(path);

		// ��session��ȡԱ��������Ϣ
		UserInfoBean userBean = (UserInfoBean) session.getAttribute("userBean");

		/*
		 * ������Constants.java������д����������˵�ҳ�� for (int i = 0; i <
		 * Constants.NoFilter_Pages.length; i++) {
		 * 
		 * if (path.indexOf(Constants.NoFilter_Pages[i]) > -1) {
		 * chain.doFilter(servletRequest, servletResponse); return; } }
		 */

		// ��½ҳ���������
		if (path.indexOf("/userLogin.jsp") > -1) {
			chain.doFilter(servletRequest, servletResponse);
			return;
		}

		if (path.indexOf("/loginServlet") > -1) {
			chain.doFilter(servletRequest, servletResponse);
			return;
		}
		// ��ҳ�������
		if(path.indexOf("/index") > -1){
			chain.doFilter(servletRequest, servletResponse);
			return;
		}
		if(path.indexOf("/") > -1){
			chain.doFilter(servletRequest, servletResponse);
			return;
		}

		// �ж����û��ȡ��Ա����Ϣ,����ת����½ҳ��
		if (userBean == null) {
			// ��ת����½ҳ��
			servletResponse.sendRedirect("userLogin.jsp");
		} else {
			// �Ѿ���½,�����˴�����
			chain.doFilter(request, response);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}

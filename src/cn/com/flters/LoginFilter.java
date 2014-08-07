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
 * @date 2014-6-19 上午10:36:01
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
		// 获得在下面代码中要用的request,response,session对象
		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		HttpSession session = servletRequest.getSession();

		// 获得用户请求的URI
		String path = servletRequest.getRequestURI();
		// System.out.println(path);

		// 从session里取员工工号信息
		UserInfoBean userBean = (UserInfoBean) session.getAttribute("userBean");

		/*
		 * 创建类Constants.java，里面写的是无需过滤的页面 for (int i = 0; i <
		 * Constants.NoFilter_Pages.length; i++) {
		 * 
		 * if (path.indexOf(Constants.NoFilter_Pages[i]) > -1) {
		 * chain.doFilter(servletRequest, servletResponse); return; } }
		 */

		// 登陆页面无需过滤
		if (path.indexOf("/userLogin.jsp") > -1) {
			chain.doFilter(servletRequest, servletResponse);
			return;
		}

		if (path.indexOf("/loginServlet") > -1) {
			chain.doFilter(servletRequest, servletResponse);
			return;
		}
		// 首页无需过滤
		if(path.indexOf("/index") > -1){
			chain.doFilter(servletRequest, servletResponse);
			return;
		}
		if(path.indexOf("/") > -1){
			chain.doFilter(servletRequest, servletResponse);
			return;
		}

		// 判断如果没有取到员工信息,就跳转到登陆页面
		if (userBean == null) {
			// 跳转到登陆页面
			servletResponse.sendRedirect("userLogin.jsp");
		} else {
			// 已经登陆,继续此次请求
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

package com.mao.web.filter;

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

import com.mao.domain.User;

/**
 * 管理员登录拦截
 */
public class AdminPermissionLoginFilter implements Filter {


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hrequest = (HttpServletRequest) request;
		HttpServletResponse hresponse = (HttpServletResponse) response;
		
		HttpSession session = hrequest.getSession();
		User user = (User)session.getAttribute("adminUser");
		if(null == user) {
			hresponse.sendRedirect(hrequest.getContextPath()+"/adminLogin.jsp");
			return;
		}
		chain.doFilter(hrequest, hresponse);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		
	}

	public void destroy() {
		
	}

}

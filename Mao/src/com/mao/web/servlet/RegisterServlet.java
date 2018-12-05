package com.mao.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.mao.domain.User;
import com.mao.service.UserService;
import com.mao.utils.CommonsUtils;

/**
 * 注册servlet
 */
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		Map<String, String[]> parameterMap = request.getParameterMap();
		User user = new User();
		try {
			BeanUtils.populate(user, parameterMap);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		user.setUid(CommonsUtils.getUid());
		user.setState(0);
		user.setCode(null);
		System.out.println("register user:"+user);
		UserService userService = new UserService();
		boolean isRegisterSuccess = userService.register(user);
		if(isRegisterSuccess) {
			//注册成功
			response.sendRedirect(request.getContextPath()+"/login.jsp");
		}else {
			//注册失败
			response.sendRedirect(request.getContextPath()+"/registerFail.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

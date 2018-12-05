package com.mao.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mao.domain.User;
import com.mao.service.AdminService;

/**
 * 管理员登录
 */
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String adminUsername = request.getParameter("adminUsername");
		String adminPassword = request.getParameter("adminPassword");
		
		AdminService adminService = new AdminService();
		User adminUser = adminService.checkAdmin(adminUsername, adminPassword);
		
		if(null == adminUser) {
			response.sendRedirect(request.getContextPath()+"/admin/login.jsp");
			return;
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("adminUser", adminUser);
		response.sendRedirect(request.getContextPath()+"/admin/adminServlet?method=findAllCategory");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

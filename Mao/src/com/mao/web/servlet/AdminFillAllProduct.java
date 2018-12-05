package com.mao.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mao.domain.PageBean;
import com.mao.service.ProductService;

/**
 * 获取所有商品
 */
public class AdminFillAllProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductService productService = new ProductService();
		//当前页
		String currentPageStr = request.getParameter("currentPage");
		if(null == currentPageStr) {
			currentPageStr = "1";
		}
		int currentPage =  Integer.parseInt(currentPageStr);
		//当前页默认显示商品数
		int currentCount = 5;
		
		PageBean pageBean = productService.findAllProduct(currentPage, currentCount);
		request.setAttribute("pageBean", pageBean);
		request.getRequestDispatcher("/admin/product.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

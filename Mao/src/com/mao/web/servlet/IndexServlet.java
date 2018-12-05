package com.mao.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mao.domain.CategoryBean;
import com.mao.domain.Product;
import com.mao.service.ProductService;

/**
 * 首页
 */
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProductService productService = new ProductService();
		
		//获取TmallMarket商品品
		List<Product> marketList = productService.findTmallMarket();
		//获取TmallHK商品
		List<Product> tmallHKList = productService.findTmallHK();
		//获取分类
		List<CategoryBean> categoryList = productService.findCategory();
		
		request.setAttribute("marketList", marketList);
		request.setAttribute("tmallHKList", tmallHKList);
		request.setAttribute("categoryList", categoryList);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

package com.mao.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mao.domain.Order;
import com.mao.service.ProductService;

/**
 * 支付
 */
public class PayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Order order = (Order) session.getAttribute("order");
		String oid = order.getOid();
		ProductService productService = new ProductService();
		productService.pay(oid);
		//移除购物车session
		session.removeAttribute("cart");
		
		response.sendRedirect(request.getContextPath()+"/paySuccess.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

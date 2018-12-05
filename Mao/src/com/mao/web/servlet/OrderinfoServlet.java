package com.mao.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.mao.domain.Order;
import com.mao.service.ProductService;

/**
 * 订单用户详情户
 */
public class OrderinfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		Order order = new Order();
		Map<String, String[]> parameterMap = request.getParameterMap();
		try {
			BeanUtils.populate(order, parameterMap);
			ProductService productService = new ProductService();
			productService.saveOrderInfo(order);
			
			response.sendRedirect(request.getContextPath()+"/permissions/pay.jsp");
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

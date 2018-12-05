package com.mao.web.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mao.domain.CartItem;
import com.mao.domain.CartProducts;
import com.mao.domain.Order;
import com.mao.domain.OrderItem;
import com.mao.domain.User;
import com.mao.service.ProductService;
import com.mao.utils.CommonsUtils;

/**
 * 提交订单
 */
public class OrderSubmitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//判断是否登陆了
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(null == user) {
			response.sendRedirect(request.getContextPath()+"/login.jsp");
			return;
		}
		//获取购物车session
		CartProducts cartProducts = (CartProducts)session.getAttribute("cart");
		
		//封装order
		Order order = new Order();
		order.setOid(CommonsUtils.getUid());
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		order.setDate(sdf.format(date));
		
		order.setTotal(cartProducts.getTotal());
		
		order.setState(0);
		
		order.setAddress(null);
		order.setName(null);
		order.setTelephone(null);
		
		order.setUser(user);
		//获取订单
		Map<Integer, CartItem> cartItems = cartProducts.getCartItem();
		for(Entry<Integer, CartItem> key:cartItems.entrySet()) {
			CartItem cartItem = key.getValue();
			OrderItem orderItem = new OrderItem();
			orderItem.setItemId(CommonsUtils.getUid());
			orderItem.setCount(cartItem.getNum());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);
			
			order.getOrderItemList().add(orderItem);
		}
		
		ProductService productService = new ProductService();
		productService.submitOrder(order);
		
		session.setAttribute("order", order);
		
		
		response.sendRedirect(request.getContextPath()+"/permissions/orderinfo.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

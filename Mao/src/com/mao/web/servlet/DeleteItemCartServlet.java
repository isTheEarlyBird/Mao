package com.mao.web.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mao.domain.CartItem;
import com.mao.domain.CartProducts;

/**
 * 删除购物车某项车
 */
public class DeleteItemCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		//获取session
		HttpSession session = request.getSession();
		CartProducts cartProducts = (CartProducts) session.getAttribute("cart");
		if(null != cartProducts) {
			Map<Integer, CartItem> cartItem = cartProducts.getCartItem();
			//更新总价
			double total = cartProducts.getTotal();
			BigDecimal bigTotal = new BigDecimal(total+"");
			double subtotal = cartItem.get(id).getSubtotal();
			bigTotal = bigTotal.subtract(new BigDecimal(subtotal+""));
			cartItem.remove(id);
			
			cartProducts.setCartItem(cartItem);
			cartProducts.setTotal(bigTotal.doubleValue());
			session.setAttribute("cart", cartProducts);
			
			response.sendRedirect(request.getContextPath()+"/permissions/cart.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

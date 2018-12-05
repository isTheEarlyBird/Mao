package com.mao.web.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mao.domain.CartItem;
import com.mao.domain.CartProducts;
import com.mao.domain.Product;
import com.mao.service.ProductService;

/**
 * 购物车
 */
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idStr = request.getParameter("id");
		//如果id为空就是点击页面顶部的购物车
		if(null != idStr) {
			int id = Integer.parseInt(idStr);
			int num = Integer.parseInt(request.getParameter("num"));
			//获取session
			HttpSession session = request.getSession();
			//判断session中是是否存在session
			CartProducts cartProducts = (CartProducts)session.getAttribute("cart");
			if(null == cartProducts) {
				cartProducts = new CartProducts();
				Map<Integer, CartItem> map = new HashMap<Integer, CartItem>();
				cartProducts.setCartItem(map);
			}
			//根据获取商品
			ProductService productService = new ProductService();
			Product product = productService.findProductById(id);
			
			//计算小计
			BigDecimal bigSubtotal = new BigDecimal(product.getMoney()+"");
			bigSubtotal = bigSubtotal.multiply(new BigDecimal(num));
			BigDecimal buytotal = bigSubtotal;
			
			
			//判断该商品是否已经在session中
			Map<Integer, CartItem> itemMap = cartProducts.getCartItem();
			if(itemMap.containsKey(id)) {
				CartItem cartItem = itemMap.get(id);
				//如果存在
				//修改数量
				System.out.println("修改前"+bigSubtotal);
				System.out.println("购物车已有"+cartItem.getSubtotal());
				int totalNum=num + cartItem.getNum();
				//修改小计
				bigSubtotal = bigSubtotal.add(new BigDecimal(cartItem.getSubtotal()+""));
				
				System.out.println("修改后"+bigSubtotal.doubleValue());
				cartItem.setNum(totalNum);
				cartItem.setSubtotal(bigSubtotal.doubleValue());
			}else {
				//封装CartItem
				CartItem cartItem = new CartItem();
				cartItem.setProduct(product);
				cartItem.setNum(num);
				
				cartItem.setSubtotal(bigSubtotal.doubleValue());
				
				itemMap.put(id, cartItem);
				
			}
			//总数为购买商品小计+之前总金额
			double total = buytotal.add(new BigDecimal(cartProducts.getTotal()+"")).doubleValue();
			cartProducts.setTotal(total);
			
			session.setAttribute("cart", cartProducts);
		}
		
		response.sendRedirect(request.getContextPath()+"/permissions/cart.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

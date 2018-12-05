package com.mao.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.mao.domain.Order;
import com.mao.domain.OrderItem;
import com.mao.domain.Product;
import com.mao.domain.User;
import com.mao.service.ProductService;

/**
 * 订单列表
 */
public class OrderlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//判断是否登陆了
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		//需要image title money num subtotal total
		//已知    order包含orderitem包含product
		ProductService productService = new ProductService();
		List<Order> orderList = productService.findOrderList(user.getUid());
		//Order里只有total，List<OrderItem> orderItemList是null
		if(null != orderList) {
			//遍历list，设置orderItemList，需要OrderItem
			//表orderitem与表product是1对1关系，可以联表查询
			for(Order order : orderList) {
				
				List<Map<String, Object>> findOrderItemByOid = productService.findOrderItemByOid(order.getOid());
				for(Map<String, Object> map:findOrderItemByOid) {
					try {
						OrderItem orderItem = new OrderItem();
						BeanUtils.populate(orderItem, map);
						Product product = new Product();
						BeanUtils.populate(product, map);
						
						orderItem.setProduct(product);
						
						order.getOrderItemList().add(orderItem);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
			request.setAttribute("orderList", orderList);
			request.getRequestDispatcher("/permissions/orderlist.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

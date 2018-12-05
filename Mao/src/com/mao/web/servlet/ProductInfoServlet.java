package com.mao.web.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mao.domain.Product;
import com.mao.service.ProductService;

/**
 * 商品信息
 */
public class ProductInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取id
		String idstr = request.getParameter("id");
		int id = Integer.parseInt(idstr);
		
		ProductService productService = new ProductService();
		//根据id获取商品信息
		Product product = productService.findProductById(id);
		
		//获取cookie
		//浏览过的商品的id以"-"为分割,   1-2-3
		String historyId = idstr;
		Cookie[] cookies = request.getCookies();
		if(null != cookies) {
			for(Cookie cookie: cookies) {
				//如果有historyId这个cookie
				if("historyId".equals(cookie.getName())) {
					String str = cookie.getValue();
					String[] arr = str.split("-");
					List<String> arrTolist = Arrays.asList(arr);
					//因为下面要用到addFirst所以
					LinkedList<String> list = new LinkedList<String>(arrTolist);
					//判断浏览的这个商品之前是否已存在cookie
					if(list.contains(idstr)) {
						list.remove(idstr);
					}
					list.addFirst(idstr);
					//再将list转回String
					StringBuilder sb = new StringBuilder();
					for(String item:list) {
						sb.append(item);
						sb.append("-");
					}
					historyId = sb.substring(0,sb.length()-1);
				}
			}
		}
		
		//添加到cookie
		Cookie cookie = new Cookie("historyId", historyId);
		response.addCookie(cookie);
		request.setAttribute("product", product);
		
		request.getRequestDispatcher("/product.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

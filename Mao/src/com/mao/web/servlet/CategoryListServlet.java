package com.mao.web.servlet;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mao.domain.PageBean;
import com.mao.domain.Product;
import com.mao.service.CategoryListService;
import com.mao.service.ProductService;

/**
 * 分类
 */
public class CategoryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取cid
		int cid = Integer.parseInt(request.getParameter("cid"));
		//当前页
		String currentPageStr = request.getParameter("currentPage");
		if(null == currentPageStr) {
			currentPageStr = "1";
		}
		int currentPage =  Integer.parseInt(currentPageStr);
		CategoryListService categoryService = new CategoryListService();
		ProductService productService = new ProductService();
		//当前页默认显示商品数
		int currentCount = 10;
		
		PageBean pageBean = categoryService.findCategory(cid, currentPage, currentCount);
		
		
		//获取cookie
		//浏览过的商品的id以"-"为分割,   1-2-3
		Cookie[] cookies = request.getCookies();
		List<Product> historyList = new LinkedList<Product>();
		if(null != cookies) {
			for(Cookie cookie: cookies) {
				//如果有historyId这个cookie
				if("historyId".equals(cookie.getName())) {
					String str = cookie.getValue();
					String[] arr = str.split("-");
					//搜索该ID下的商品
					for(String id: arr) {
						Product product = productService.findProductById(Integer.parseInt(id));
						historyList.add(product);
					}
				}
			}
		}
		request.setAttribute("pageBean", pageBean);
		request.setAttribute("cid", cid);
		request.setAttribute("historyList", historyList);
		request.getRequestDispatcher("/categoryList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

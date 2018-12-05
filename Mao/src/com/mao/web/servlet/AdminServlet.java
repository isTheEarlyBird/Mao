package com.mao.web.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.mao.domain.CategoryBean;
import com.mao.domain.Order;
import com.mao.domain.Product;
import com.mao.service.AdminService;
import com.mao.service.ProductService;

/**
 * Servlet implementation class AdminServlet
 */
public class AdminServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	
	//获取所有分类
	public void findAllCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductService productService = new ProductService();
		List<CategoryBean> categoryLsit = productService.findCategory();
		//如果是添加商品页面过来的
		String parameter = request.getParameter("return");
		if(null != parameter) {
			Gson gson = new Gson();
			String json = gson.toJson(categoryLsit);
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(json);
			return;
		}
		
		request.setAttribute("categoryLsit", categoryLsit);
		request.getRequestDispatcher("/admin/category.jsp").forward(request, response);
	}
	
	//获取所有订单
	public void findAllOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductService productService = new ProductService();
		List<Order> orderList = productService.findAllOrder(0);
		
		request.setAttribute("orderList", orderList);
		request.getRequestDispatcher("/admin/order.jsp").forward(request, response);
	}
	
	//根据oid获取订单
	public void findOrderByOid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String oid = request.getParameter("oid");
		ProductService productService = new ProductService();
		List<Map<String, Object>> list = productService.findOrderItemByOid(oid);
		
		Gson gson = new Gson();
		String json = gson.toJson(list);
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(json);
	}
	
	//添加分类
	public void saveCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		String cid = request.getParameter("cid");
		String cateName = request.getParameter("cateName");
		System.out.println("cid:"+cid+"name:"+cateName);
		AdminService adminService = new AdminService();
		adminService.saveCategory(cid, cateName);
		
		request.getRequestDispatcher("/admin/adminServlet?method=findAllCategory").forward(request, response);
	}
	
	//删除分类
	public void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cid = request.getParameter("cid");
		AdminService adminService = new AdminService();
		adminService.deleteCategory(cid);

		response.sendRedirect(request.getContextPath()+"/admin/adminServlet?method=findAllCategory");
	}
	
	//修改分类
	public void changeCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String oldCid = request.getParameter("oldCid").trim();
		String oldCateName = request.getParameter("oldCateName");
		String cid = request.getParameter("cid");
		String cateName =  request.getParameter("cateName");
		response.setContentType("text/html;charset=utf-8");
		if(oldCid.equals(cid) && oldCateName.equals(cateName)) {
			//如果没有修改但有点了确定，直接跳转页面
			response.sendRedirect(request.getContextPath()+"/admin/adminServlet?method=findAllCategory");
			return;
		}
		AdminService adminService = new AdminService();
		adminService.changeCategory(cid, cateName);

		response.sendRedirect(request.getContextPath()+"/admin/adminServlet?method=findAllCategory");
	}
	
	//修改商品
	public void changeProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("id");
		String image = request.getParameter("image");
		String title = request.getParameter("title");
		String money = request.getParameter("money");
		
		Product product = new Product();
		product.setId(id);
		product.setImage(image);
		product.setTitle(title);
		product.setMoney(Double.parseDouble(money));
		
		AdminService adminService = new AdminService();
		adminService.changePriductById(product);
		
		response.sendRedirect(request.getContextPath()+"/admin/adminFillAllProduct");
	}
	
	//删除商品
	public void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int id = Integer.parseInt(request.getParameter("id"));
		System.out.println(id);
		AdminService adminService = new AdminService();
		adminService.deleteProductById(id);
		
		String json = "{\"success\":\"success\"}";
		response.getWriter().write(json);
	}
}

package com.mao.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.mao.dao.ProductDao;
import com.mao.domain.CategoryBean;
import com.mao.domain.Order;
import com.mao.domain.PageBean;
import com.mao.domain.Product;
import com.mao.utils.DataSourceUtils;

public class ProductService {
	
	//获取TmallMarket商品
	public List<Product> findTmallMarket() {
		ProductDao productDao = new ProductDao();
		List<Product> marketList = null;
		try {
			marketList= productDao.findTmallMarket();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return marketList;
	}
	//获取TmallHK商品
	public List<Product> findTmallHK() {
		ProductDao productDao = new ProductDao();
		List<Product> tmallHK = null;
		try {
			tmallHK= productDao.findTmallHK();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tmallHK;
	}
	//获取分类列表
	public List<CategoryBean> findCategory() {
		ProductDao productDao = new ProductDao();
		List<CategoryBean> categoryList = null;
		try {
			categoryList= productDao.findCategory();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categoryList;
	}
	//根据id获取商品信息
	public Product findProductById(int id) {
		ProductDao productDao = new ProductDao();
		Product productList = null;
		try {
			productList= productDao.findProductById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productList;
	}
	
	//提交订单
	public void submitOrder(Order order) {
		try {
			// 开启事务
			DataSourceUtils.startTransaction();
			ProductDao productDao = new ProductDao();
			productDao.insetToOrders(order);
			productDao.insertToOrderItem(order);
		} catch (SQLException e) {
			try {
				DataSourceUtils.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally {
			try {
				DataSourceUtils.commitAndRelease();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//	保存订单信息	
	public void saveOrderInfo(Order order) {
		ProductDao productDao = new ProductDao();
		try {
			productDao.saveOrderInfo(order);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	//付款，改变付款状态题
	public void pay(String oid) {
		ProductDao productDao = new ProductDao();
		try {
			productDao.pay(oid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//	根据uid获取order列表	
	public List<Order> findOrderList(String uid) {
		ProductDao productDao = new ProductDao();
		List<Order> order = null;
		try {
			order = productDao.findOrderList(uid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}
	//	根据oid获取orderitem列表
	public List<Map<String, Object>> findOrderItemByOid(String oid) {
		ProductDao productDao = new ProductDao();
		List<Map<String, Object>> findOrderItemByOid = null;
		try {
			findOrderItemByOid = productDao.findOrderItemByOid(oid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return findOrderItemByOid;
	}
	//	上传商品
	public void savePeoduct(Product product) {
		ProductDao productDao = new ProductDao();
		try {
			productDao.savePeoduct(product);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// 获取10个商品信息
	public PageBean findAllProduct(int currentPage,int currentCount) {
		ProductDao productDao = new ProductDao();
		PageBean pageBean = new PageBean();
		//封装当前页显示商品数
		pageBean.setCurrentCount(currentCount);
		//封装当前页
		pageBean.setCurrentPage(currentPage);
		//封装总商品数
		int totalCount = 0;
		try {
			totalCount = productDao.findAllProductTotal();
			pageBean.setTotalCount(totalCount);
			//封装总页数
			int totalPage = 0;
			if(totalCount % currentCount == 0) {
				totalPage = (int)(totalCount / currentCount);
			}else {
				totalPage = (int)(totalCount / currentCount) + 1;
			}
			if(totalPage == 0)	{
				totalPage =1;
			}
			pageBean.setTotalPage(totalPage);
			List<Product> list = null;
			try {
				list = productDao.findAllProduct(currentCount, currentPage-1);
				pageBean.setList(list);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return pageBean;
	}
	// 获取所有订单
	public List<Order> findAllOrder(int offset) {
		ProductDao productDao = new ProductDao();
		List<Order> order = null;
		try {
			order = productDao.findAllOrder(offset);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}
	
}

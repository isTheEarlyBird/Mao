package com.mao.service;

import java.sql.SQLException;
import java.util.List;

import com.mao.dao.CategoryDao;
import com.mao.domain.PageBean;
import com.mao.domain.Product;

public class CategoryListService {
	//获取该cid下的所有商品数
	public PageBean findCategory(int cid, int currentPage, int currentCount) {
		CategoryDao category = new CategoryDao();
		PageBean pageBean = new PageBean();
		//封装当前页显示商品数
		pageBean.setCurrentCount(currentCount);
		//封装当前页
		pageBean.setCurrentPage(currentPage);
		//封装总商品数
		int totalCount = 0;
		try {
			totalCount = category.findTotalCountByCid(cid);
			pageBean.setTotalCount(totalCount);
			//封装总页数
			int totalPage = (int) Math.ceil(totalCount / currentCount);
			if(totalPage == 0)	{
				totalPage =1;
			}
			pageBean.setTotalPage(totalPage);
			//显示的商品
			List<Product>  list = null;
			try {
				list = category.findProductByCid(cid, currentPage, currentCount);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			pageBean.setList(list);
			return pageBean;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return null;
		}
	}

}

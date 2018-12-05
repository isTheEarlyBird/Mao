package com.mao.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mao.domain.Product;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class CategoryDao {
	//获取该cid下的所有商品
	public List<Product> findProductByCid(int cid, int currentPage, int currentCount) throws SQLException {
		QueryRunner runner = new QueryRunner(new ComboPooledDataSource());
		String sql = "select id, image, title, money from product where cid = ? limit ? offset ?";
		return runner.query(sql, new BeanListHandler<Product>(Product.class), cid,  currentCount, currentPage);
	}
	//获取该cid下的所有商品数
	public int findTotalCountByCid(int cid) throws SQLException {
		QueryRunner runner = new QueryRunner(new ComboPooledDataSource());
		String sql = "select count(*) from product where cid = ?";
		Long count =  (Long)runner.query(sql, new ScalarHandler(), cid);
		return count.intValue();
	}

}

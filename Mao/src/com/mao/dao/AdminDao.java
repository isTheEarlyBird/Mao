package com.mao.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.mao.domain.Product;
import com.mao.domain.User;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class AdminDao {

	public User checkAdmin(String adminUsername, String adminPassword) throws SQLException {
		QueryRunner runner = new QueryRunner(new ComboPooledDataSource());
		String sql = "select * from user where username = ? and password = ? and uid < 10";
		return runner.query(sql, new BeanHandler<User>(User.class), adminUsername, adminPassword);
	}

	public void saveCategory(String cid, String cateName) throws SQLException {
		QueryRunner runner = new QueryRunner(new ComboPooledDataSource());
		String sql = "insert into category values(?, ?)";
		runner.update(sql, cid, cateName);
	}

	public void deleteCategory(String cid) throws SQLException {
		QueryRunner runner = new QueryRunner(new ComboPooledDataSource());
		String sql = "delete from category where cid =?";
		runner.update(sql, cid);
	}

	public void changeCategory(String cid, String cateName) throws SQLException {
		QueryRunner runner = new QueryRunner(new ComboPooledDataSource());
		String sql = "update cateName = ?  from category where cid =?";
		runner.update(sql, cateName, cid);
	}

	public void changePriductById(Product product) throws SQLException {
		QueryRunner runner = new QueryRunner(new ComboPooledDataSource());
		String sql = "update product set image = ?,title = ?,money = ? where id = ?";
		runner.update(sql, product.getImage(), product.getTitle(), product.getMoney(), product.getId());
	}

	public void deleteProductById(int id) throws SQLException {
		QueryRunner runner = new QueryRunner(new ComboPooledDataSource());
		String sql = "delete from product where id = ?";
		runner.update(sql, id);
	}

}

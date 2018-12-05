package com.mao.service;

import java.sql.SQLException;

import com.mao.dao.AdminDao;
import com.mao.domain.Product;
import com.mao.domain.User;

public class AdminService {
	
	//检查是否是管理员账号
	public User checkAdmin(String adminUsername, String adminPassword) {
		AdminDao adminDao = new AdminDao();
		User user = null;
		try {
			user = adminDao.checkAdmin(adminUsername, adminPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public void saveCategory(String cid, String cateName) {
		try {
			AdminDao adminDao = new AdminDao();
			adminDao.saveCategory(cid, cateName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteCategory(String cid) {
		try {
			AdminDao adminDao = new AdminDao();
			adminDao.deleteCategory(cid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void changeCategory(String cid, String cateName) {
		try {
			AdminDao adminDao = new AdminDao();
			adminDao.changeCategory(cid, cateName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void changePriductById(Product product) {
		try {
			AdminDao adminDao = new AdminDao();
			adminDao.changePriductById(product);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteProductById(int id) {
		try {
			AdminDao adminDao = new AdminDao();
			adminDao.deleteProductById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

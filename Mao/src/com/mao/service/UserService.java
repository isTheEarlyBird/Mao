package com.mao.service;

import java.sql.SQLException;

import com.mao.dao.UserDao;
import com.mao.domain.User;

public class UserService {

	public boolean register(User user) {
		UserDao userDao = new UserDao();
		int row = 0;
		try {
			row = userDao.register(user);   // 更改数据库几行
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return row >0 ? true : false;
	}

	public boolean checkUsername(String username) {
		UserDao userDao = new UserDao();
		Long count = 0L;
		try {
			count = userDao.checkUsername(username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count > 0 ? false : true;
	}

	public User checkUser(String username, String password) {
		UserDao userDao = new UserDao();
		User user = null;
		try {
			user = userDao.checkUser(username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

}

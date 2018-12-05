package com.mao.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.mao.domain.User;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class UserDao {

	public int register(User user) throws SQLException {
		QueryRunner runner = new QueryRunner(new ComboPooledDataSource());
		String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?)";
		int row = runner.update(sql, user.getUid(), user.getUsername(), user.getPassword(), user.getName(), user.getEmail(),
				user.getTel(), user.getBirthday(), user.getGender(), user.getState(), user.getCode());
		return row;
	}

	public Long checkUsername(String username) throws SQLException {
		QueryRunner runner = new QueryRunner(new ComboPooledDataSource());
		String sql = "select count(*) from user where username = ?";
		Long count = (Long) runner.query(sql, new ScalarHandler(), username);
		return count;
	}

	public User checkUser(String username, String password) throws SQLException {
		QueryRunner runner = new QueryRunner(new ComboPooledDataSource());
//		uid, username, passeord, name, email, tel, birthday,gender
		String sql = "select * from user where username = ? and password = ?";
		return runner.query(sql, new BeanHandler<User>(User.class), username, password);
	}


}

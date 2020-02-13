package com.bizbox.dao;

import java.sql.SQLException;

import com.bizbox.vo.User;

public interface UserServiceDAO {
	int singupUser(User user) throws SQLException;
	String getPw(String email) throws SQLException;
	User login(User user) throws SQLException;
}

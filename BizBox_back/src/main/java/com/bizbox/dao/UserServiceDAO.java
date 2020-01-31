package com.bizbox.dao;

import com.bizbox.vo.User;

public interface UserServiceDAO {
	int singupUser(User user) throws Exception;
	String getPw(String email) throws Exception;
	User login(User user) throws Exception;
}

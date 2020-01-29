package com.bizbox.dao;

import com.bizbox.vo.User;

public interface UserServiceDAO {
	int singupUser(User user);
	User loginUser(User user);
}

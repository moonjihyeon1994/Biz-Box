package com.bizbox.Service;

import com.bizbox.vo.User;

public interface UserService {
	int singupUser(User user) throws Exception;
	void socialSingupUser(User user) throws Exception;
	boolean loginUser(User user) throws Exception;
	User checkUser(User user) throws Exception;
}

package com.bizbox.Service;

import org.springframework.stereotype.Service;

import com.bizbox.vo.User;

@Service
public class UserService {
	public User Singin(String name, String email) {
		return new User(name, email);
	}
	
	public String getServeInfo() {
		return "moon";
	}
}

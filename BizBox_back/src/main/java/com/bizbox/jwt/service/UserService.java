package com.bizbox.jwt.service;

import org.springframework.stereotype.Service;

import com.bizbox.vo.User;

@Service
public class UserService {
	public User signin(String email, String password) {
		if(email.equals("asdf") && password.equals("1234")) {
			return new User(email, password);
		}else {
			throw new RuntimeException("없어");
		}
	}
	public String getServerInfo() {
		return "made by bizbox";
	}
}

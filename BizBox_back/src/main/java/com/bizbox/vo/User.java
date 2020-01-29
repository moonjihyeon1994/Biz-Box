package com.bizbox.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private String name;
	private String email;
	private String pw;
	public User(String name, String email) {
		this.name = name;
		this.email = email;
	}
}

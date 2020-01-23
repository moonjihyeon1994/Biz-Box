package com.bizbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/social")
public class OAuth2Controller {
	@GetMapping("/")
	public String getAuthorizationMessage() {
		return "home";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/loginSuccess")
	public String loginSuccess() {
		return "success";
	}
	
	@GetMapping("/loginFailure")
	public String loginFailure() {
		return "fail";
	}
}

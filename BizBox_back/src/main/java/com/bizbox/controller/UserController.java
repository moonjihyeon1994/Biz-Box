package com.bizbox.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bizbox.Service.JwtService;
import com.bizbox.vo.User;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	JwtService jwtService;
	
	@PostMapping("/signup")
	public String Signup() {
		
		return "";
	}
	
	@PostMapping("/login")
	public String Login() {
		
		return "";
	}
	
	@PostMapping("/info")
	public ResponseEntity<Map<String, Object>> getInfo(HttpServletRequest req, @RequestBody User user){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			resultMap.putAll(jwtService.get(req.getHeader("jwt-auth-token")));
			resultMap.put("status", true);
			resultMap.put("request_body", user);
			status = HttpStatus.ACCEPTED;
		}catch(RuntimeException e) {
			log.error("정보조회 실패", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String,Object>>(resultMap, status);
	}
}

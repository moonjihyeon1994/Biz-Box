package com.bizbox.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bizbox.Service.JwtService;
import com.bizbox.Service.StoreService;
import com.bizbox.Service.UserService;
import com.bizbox.vo.Point;
import com.bizbox.vo.Store;
import com.bizbox.vo.User;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
//@RequestMapping("/user")
public class UserController {
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	StoreService storeService;
	
	@PostMapping("/signup")
	public ResponseEntity<Map<String, Object>> signup(@RequestBody User user, HttpServletResponse res) {
		log.info("유저 가입중 : {}", user.toString());
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			userService.singupUser(user);
			String token = jwtService.create(user);
			res.setHeader("jwt-auth-token", token);
			resultMap.put("status", true);
			resultMap.put("data", user);
			status = HttpStatus.ACCEPTED;
		}catch (Exception e) {
			log.error("회원가입 실패", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String,Object>>(resultMap, status);
	}
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> Login(@RequestBody User user, HttpServletResponse res) {
		log.info("유저 로그인중 : {}", user.toString());
		Map<String, Object>resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			if(userService.loginUser(user)) {
				User loginuser = userService.checkUser(user);
				System.out.println(loginuser.toString());
				String token = jwtService.create(loginuser);
				res.setHeader("jwt-auth-token", token);
				resultMap.put("status", true);
				resultMap.put("data", loginuser);
				status = HttpStatus.ACCEPTED;
			}else {
				resultMap.put("status", false);
				status = HttpStatus.ACCEPTED;
			}
			status = HttpStatus.ACCEPTED;
		}catch(Exception e) {
			log.error("로그인 실패", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
        return new ResponseEntity<Map<String,Object>>(resultMap, status);
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
	
	@PostMapping("/addStore")
	public ResponseEntity<Map<String, Object>> addStore(@RequestBody Store store){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			if(storeService.insertStore(store)) {
				resultMap.put("status", true);
			}else {
				resultMap.put("status", false);
			}
			status = HttpStatus.ACCEPTED;
		} catch(RuntimeException e) {
			log.error("상점 추가 실패", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String,Object>>(resultMap, status);
	}
	
	@PutMapping("/closeStore")
	public ResponseEntity<Map<String, Object>> closeStore(@RequestBody Store store){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			if(storeService.closeStore(store)) {
				resultMap.put("status", true);
			}else {
				resultMap.put("status", false);
			}
			status = HttpStatus.ACCEPTED;
		} catch(RuntimeException e) {
			log.error("상점 제거 실패", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String,Object>>(resultMap, status);
	}
	
	@GetMapping("/getStoreByEmail/{email}")
	public ResponseEntity<Map<String, Object>> getStoreByEmail(@PathVariable String email){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			List<Store> list = storeService.getStore(email);
			if(list != null) {
				resultMap.put("status", true);
				resultMap.put("data", list);
			} else {
				resultMap.put("status", false);
			}
			status = HttpStatus.ACCEPTED;
		} catch(RuntimeException e) {
			log.error("상점 찾기 실패", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String,Object>>(resultMap, status);
	}
	
	@PostMapping("/getStoreByXY")
	public ResponseEntity<Map<String, Object>> getStoreByXY(@RequestBody Point point){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			List<Store> list = storeService.getStoreByXY(point);
			if(list != null) {
				resultMap.put("status", true);
				resultMap.put("data", list);
			} else {
				resultMap.put("status", false);
			}
			status = HttpStatus.ACCEPTED;
		} catch(RuntimeException e) {
			log.error("상점 xy좌표로  찾기 실패", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String,Object>>(resultMap, status);
	}
}

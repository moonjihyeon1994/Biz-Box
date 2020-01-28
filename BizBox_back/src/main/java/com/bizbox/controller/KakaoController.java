package com.bizbox.controller;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bizbox.apis.KakaoApi;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin({ "*" })
@RequestMapping("/kakao")
public class KakaoController {
	
	@Autowired
	private KakaoApi kakao;
	
	@RequestMapping(value = "/login")
	public String login(@RequestParam("code") String code, HttpSession session) {
		//https://kauth.kakao.com/oauth/authorize?client_id=64c7963937495c25ab3d30bc9f6e65e7&redirect_uri=http://70.12.246.137:8080/kakao/login&response_type=code
		//https://kauth.kakao.com/oauth/authorize?client_id=64c7963937495c25ab3d30bc9f6e65e7&redirect_uri=http://localhost:8080/kakao/login&response_type=code
		
		System.out.println("code : " + code);
		String access_Token = kakao.getAccessToken(code);
        System.out.println("controller access_token : " + access_Token);
		HashMap<String, Object>userInfo = kakao.getUserInfo(access_Token);
		System.out.println(userInfo);
		if (userInfo.get("email") != null) {
	        session.setAttribute("userId", userInfo.get("email"));
	        session.setAttribute("access_Token", access_Token);
	    }
        return userInfo.toString();
	}
}

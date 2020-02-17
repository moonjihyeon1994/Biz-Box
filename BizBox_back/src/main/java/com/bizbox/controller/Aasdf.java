package com.bizbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bizbox.Service.JusoService;
import com.bizbox.apis.KakaoApi;

@RestController
public class Aasdf {
	
	@Autowired
	KakaoApi api;
	
	@Autowired
	JusoService service;
	
	@GetMapping("/getaddr/{x}/{y}")
	public String getaddr(@PathVariable String x, @PathVariable String y) {
		String asdf = api.kakaoCoord2regioncode(x, y);
		try {
			asdf = service.Coord2Addr(x, y);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return asdf;
	}
}

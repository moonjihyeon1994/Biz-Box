package com.bizbox.controller;

import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bizbox.apis.NaverApi;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin({ "*" })
public class SerchTrendController {
	@Autowired
	NaverApi api;
	
	@GetMapping("/searchtrend")
	public ResponseEntity<Object> getStoreNumByCount(){

		String asd = api.na();
		return new ResponseEntity<Object>(asd,HttpStatus.OK);
	}
	
	
}

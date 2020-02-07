package com.bizbox.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bizbox.apis.JusoApi;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin({ "*" })
public class StoreCountController {
	
	@Autowired
	JusoApi api;
	
	@GetMapping("/storecount/{address}/{range}")
	public ResponseEntity<Object> getStoreNumByCount(@PathVariable String address,@PathVariable String range){
		try {
			String num = api.getAddressByName(address);
			String xy = api.XYtoLatLong(num);
			JSONObject total = api.findAllStore(xy, range);
			
			return new ResponseEntity<Object>(total.toString(),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("error", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/storecountByxy/{x}/{y}/{range}")
	public ResponseEntity<Object> getEveryStore(@PathVariable String x, @PathVariable String y, @PathVariable String range){
		try {
			String xy = x+","+y;
			JSONObject total = api.findAllStore(xy, range);
			return new ResponseEntity<Object>(total.toString(),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("error", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/storecountByLarge/{address}/{range}")
	public ResponseEntity<Object> getStoreNumByLarge(@PathVariable String address,@PathVariable String range){
		try {
			String num = api.getAddressByName(address);
			String xy = api.XYtoLatLong(num);
			JSONObject total = api.findAllStoreByLarge(xy, range);
			
			return new ResponseEntity<Object>(total.toString(),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("error", HttpStatus.NOT_FOUND);
		}
	}
}

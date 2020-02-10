package com.bizbox.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bizbox.Service.JusoService;
import com.bizbox.apis.JusoApi;
import com.bizbox.vo.Category;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin({ "*" })
public class StoreCountController {
	
	@Autowired
	JusoService jusoService;
	
	@Autowired
	JusoApi jusoApi;
	
	@GetMapping("/storecount/{address}/{range}")
	public ResponseEntity<Object> getStoreNumByCount(@PathVariable String address,@PathVariable String range){
		try {
			String num = jusoService.getAddressByName(address);
			String xy = jusoApi.XYtoLatLong(num);
			JSONObject total = jusoService.findAllStore(xy, range);
			
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
			JSONObject total = jusoService.findAllStore(xy, range);
			return new ResponseEntity<Object>(total.toString(),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("error", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/storecountByLarge/{address}/{range}")
	public ResponseEntity<Object> getStoreNumByLarge(@PathVariable String address,@PathVariable String range){
		try {
			String num = jusoService.getAddressByName(address);
			String xy = jusoApi.XYtoLatLong(num);
			JSONObject total = jusoService.findAllStoreByLarge(xy, range);
			
			return new ResponseEntity<Object>(total.toString(),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("error", HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/storeDetailByCategory/{Category}")
	public ResponseEntity<Object> storeDetailByCategory(@RequestBody Category category){
		System.out.println(category.toString());
		try {
			String num = jusoService.getAddressByName(category.getAddress());
			String xy = jusoApi.XYtoLatLong(num);
			JSONObject total = jusoService.findStoreDetailByCategory(xy, category.getRange(), category.getMiddle(), category.getSmall());
			System.out.println(total);
			return new ResponseEntity<Object>(total.toString(),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("error", HttpStatus.NOT_FOUND);
		}
	}
}

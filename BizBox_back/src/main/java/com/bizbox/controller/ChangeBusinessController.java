package com.bizbox.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bizbox.Service.ChangeBusinessService;
import com.bizbox.vo.Changebusiness;

@CrossOrigin({ "*" })
@RestController
@RequestMapping("/change")
public class ChangeBusinessController {

	@Autowired
	ChangeBusinessService service;

	@GetMapping("/getHistory/{dong}")
	public ResponseEntity<Object> getHistory(@PathVariable String dong) {
		try {
			JSONObject jsonObject = service.getChangeHistorySub(dong);
			return new ResponseEntity<Object>(jsonObject, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("해당 주소가 존재하지않습니다.", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getColor")
	public ResponseEntity<Object> getColor() {
		try {
			JSONObject jsonObject = service.getColorlist();
			return new ResponseEntity<Object>(jsonObject, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("해당 주소가 존재하지않습니다.", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getHistory2/{dong}")
	public ResponseEntity<Object> getHistory2() {
		try {
			JSONObject jsonObject = service.getChangeHistorySubtoAll();
			return new ResponseEntity<Object>(jsonObject, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("해당 주소가 존재하지않습니다.", HttpStatus.NOT_FOUND);
		}
		
	}


}




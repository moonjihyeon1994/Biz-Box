package com.bizbox.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bizbox.Service.SalesService;
import com.bizbox.vo.SalesInformation;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin({ "*" })
@RequestMapping("/sales")
public class SalesController {
	
	@Autowired
	SalesService service;
	
	@GetMapping("/{address}")
	public ResponseEntity<Object> getSalesInfo(@PathVariable String address){
		List<SalesInformation> list;
		try {
			list= service.salesInfo(address);
			System.out.println(list.toString());
			return new ResponseEntity<Object>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("error", HttpStatus.NOT_FOUND);
		}
	}
}

package com.bizbox.controller;

import java.util.LinkedList;
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
import com.bizbox.utils.*;
import com.bizbox.apis.*;
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
		try {
			List<SalesInformation> list =service.salesInfosub(address);
			return new ResponseEntity<Object>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("error", HttpStatus.NOT_FOUND);
		}
	}

}

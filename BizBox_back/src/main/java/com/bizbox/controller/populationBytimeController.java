package com.bizbox.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.bizbox.Service.populationByTimeService;
import com.bizbox.vo.PopulationByTime;



@RestController
public class populationBytimeController {

	@Autowired
	populationByTimeService service;
	
	@GetMapping("/pop/{address}")
	public ResponseEntity<Object> getP(@PathVariable String address){
		
		PopulationByTime list=service.Allpopulation(address);
		//System.out.println(list.get(0).a);
		return new ResponseEntity<Object>(list,HttpStatus.OK);
	}
}

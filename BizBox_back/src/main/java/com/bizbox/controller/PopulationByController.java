package com.bizbox.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bizbox.Service.PopulationByService;
import com.bizbox.vo.PopulationByLocation;
import com.bizbox.vo.PopulationByTime;



@RestController
@RequestMapping("/population")
public class PopulationByController {

	@Autowired
	PopulationByService service;
	
	@GetMapping("/getPopulationByTime/{address}")
	public ResponseEntity<Object> getPopulationByTime(@PathVariable String address){
		PopulationByTime pbt;
		try {
			pbt = service.populationByTime(address);
			return new ResponseEntity<Object>(pbt,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("error", HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/getPopulationByLocation/{address}")
	public ResponseEntity<Object> getPopulationByLocation(@PathVariable String address){
		PopulationByLocation pbl;
		try {
			pbl = service.populationByLocation(address);
			return new ResponseEntity<Object>(pbl,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("error", HttpStatus.NOT_FOUND);
		}
	}
}

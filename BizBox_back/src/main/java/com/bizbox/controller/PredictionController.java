package com.bizbox.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bizbox.Service.PredictionService;
import com.bizbox.apis.JusoApi;

@RestController
@CrossOrigin("*")
@RequestMapping("/predict")
public class PredictionController {
	@Autowired
	JusoApi jusoapi;
	
	@Autowired
	PredictionService service;
	
	@GetMapping("/findBusiness/{cx}/{cy}")
	public ResponseEntity<Object> findBusiness(@PathVariable String cx, @PathVariable String cy){
		try {
			JSONObject data = service.findAllPrediction(cx, cy);
			return new ResponseEntity<Object>(data,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("error", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/predict_business/{cx}/{cy}/{business}")
	public ResponseEntity<Object> businessAnalysis(@PathVariable String cx, @PathVariable String cy, @PathVariable String business){
		try {
			JSONObject data = service.findBusinessAnalysis(cx, cy, business);
			return new ResponseEntity<Object>(data,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("error", HttpStatus.NOT_FOUND);
		}
	}
}

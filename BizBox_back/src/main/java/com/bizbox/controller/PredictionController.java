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
	@GetMapping("/findBusiness2/{place}")
	public ResponseEntity<Object> findBusiness2(@PathVariable String place){
		try {
			JSONObject data = service.findAllPrediction2(place);
			return new ResponseEntity<Object>(data,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("error", HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/findplace/{cx}/{cy}")
	public ResponseEntity<Object> findplace(@PathVariable String cx, @PathVariable String cy){
		try {
			JSONObject jsondata = jusoapi.findBusiness(cx, cy);
			String address = (String) jsondata.get("mainTrarNm");
			String [] addresslist = address.split(" ");
			if(addresslist[0].contains("서울")) {
				address="";
				for(int i=1 ; i< addresslist.length; i++) {
					if(i==addresslist.length-1) {address+=addresslist[i];}
					else {address+=addresslist[i]+" ";}
				}
			}
			return new ResponseEntity<Object>(address,HttpStatus.OK);
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
	
	@GetMapping("/predict_business2/{place}/{business}")
	public ResponseEntity<Object> businessAnalysis2(@PathVariable String place, @PathVariable String business){
		try {
			JSONObject data = service.findBusinessAnalysis2(place, business);
			return new ResponseEntity<Object>(data,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("error", HttpStatus.NOT_FOUND);
		}
	}
}

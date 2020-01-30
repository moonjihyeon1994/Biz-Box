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
		List<SalesInformation> list = new LinkedList<SalesInformation>();
		try {
			AddressUtil util= new AddressUtil();
			JusoApi juso= new JusoApi();
			String preaddress = util.RemoveNumber(address);
			
			List<String> adlist=juso.getAddressSetByName(preaddress);
			for (String string : adlist) {
				System.out.println(string);
			}
			
			
			list= service.salesInfo(address);
			if(list.isEmpty()) {
			for(int i=0; i<adlist.size(); i++) {
				list= service.salesInfo(adlist.get(i));
				if(!list.isEmpty())break;
			}
			}
			if(list.isEmpty()) {
				list.add(new SalesInformation
				("0","0","0",address,"0","0","0",
				 "0","0","0","0","0","0","0","0",
				 "0","0","0","0","0","0","0","0",
				 "0","0","0","0","0","0","0","0",
				 "0","0","0"));
			}
			//System.out.println(list.toString());
			return new ResponseEntity<Object>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("error", HttpStatus.NOT_FOUND);
		}
	}
}

package com.bizbox.controller;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
import com.bizbox.Service.IndustrySuggestionsService;
import com.bizbox.Service.PopulationByService;
import com.bizbox.Service.SalesService;
import com.bizbox.vo.BusinessLank;
import com.bizbox.vo.Changebusiness;
import com.bizbox.vo.PopulationByLocation;
import com.bizbox.vo.PopulationByTime;
import com.bizbox.vo.SalesInformation;
import com.bizbox.utils.*;
import net.bytebuddy.dynamic.scaffold.MethodGraph.Linked;
import com.bizbox.apis.*;

@CrossOrigin({ "*" })
@RestController
@RequestMapping("/suggestion")
public class IndustrySuggestionsController {
	@Autowired
	JusoApi api;
	@Autowired
	IndustrySuggestionsService suggestionservice;
	@Autowired
	SalesService saliesservice;
	@Autowired
	ChangeBusinessService changeservice;
	

	@GetMapping("/Industry/{address}")
	public ResponseEntity<Object> suggestionIndustry(@PathVariable String address) {
		List<BusinessLank> bllist = new LinkedList<BusinessLank>();
		List<SalesInformation> silist = new LinkedList<SalesInformation>();
		List<Changebusiness> cblist = new LinkedList<Changebusiness>();
		JSONObject jsonObject = new JSONObject();
		
		
		try {
			silist=saliesservice.salesInfosub(address);
			cblist=changeservice.getChangeHistorySubtolist(address);
			Changebusiness cb =cblist.get(5);
			
			
			String num = api.getAddressByName(address);
			String xy = api.getAddressByXY(num);
			HashMap<String, Integer> NumberOfBusinesses = api.findStoreToSpring(xy, "350");
			int[] BusinessesLank = new int[NumberOfBusinesses.size()];
			int max=0;
			int min=Integer.MAX_VALUE;
			List<String> maxstore=new LinkedList<String>();
			for (Map.Entry<String, Integer> entry : NumberOfBusinesses.entrySet()) {
				int index = 0;
				String name = entry.getKey();
				int value = entry.getValue();
				BusinessLank BL = new BusinessLank();
				BL.setName(name);BL.setValue(value);
				bllist.add(BL);
				BusinessesLank[index] = value;
				System.out.println(name + " " + value);
			
			}
			Collections.sort(bllist);
			//Arrays.sort(BusinessesLank);// 업종 수대로 순위매김
			
			for (BusinessLank bl : bllist) {
				String name=bl.getName();
				String[] sp = name.split(" ");
				for(int i=0; i<sp.length; i++) {
					for (SalesInformation si : silist) {
						if(si.getAF().contains(sp[i])){
							
						}
					}
				}
			}
			
			for (Map.Entry<String, Integer> entry : NumberOfBusinesses.entrySet()) {
				String name = entry.getKey();
				int value = entry.getValue();
				System.out.println(name + " " + value);
			}

		} catch (Exception e) {
			
		}
		
		
		BusinessLank bl = new BusinessLank();
		jsonObject.put("bl", bllist.get(0));
		
		return new  ResponseEntity<Object>(jsonObject,HttpStatus.OK);
	}

}

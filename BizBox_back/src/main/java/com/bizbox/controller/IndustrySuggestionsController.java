package com.bizbox.controller;

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
import com.bizbox.Service.JusoService;
import com.bizbox.Service.SalesService;
import com.bizbox.vo.BusinessLank;
import com.bizbox.vo.Changebusiness;
import com.bizbox.vo.SalesInformation;
import com.bizbox.apis.*;

@CrossOrigin({ "*" })
@RestController
@RequestMapping("/suggestion")
public class IndustrySuggestionsController {
	
	@Autowired
	JusoApi api;
	
	@Autowired
	JusoService jusoService;
	
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
		int maxsales=0;
		int minstore=Integer.MAX_VALUE;

		try {
			silist = saliesservice.salesInfosub(address);
			cblist = changeservice.getChangeHistorySubtolist(address);
			Changebusiness cb = cblist.get(5);

			String num = jusoService.getAddressByName(address);
			String xy = api.XYtoLatLong(num);
			HashMap<String, Integer> NumberOfBusinesses = jusoService.findStoreToSpring(xy, "350");
			
			int[] BusinessesLank = new int[NumberOfBusinesses.size()];
			int max = 0;
			int min = Integer.MAX_VALUE;
			
			List<String> maxstore = new LinkedList<String>();
			for (Map.Entry<String, Integer> entry : NumberOfBusinesses.entrySet()) {
				BusinessLank BL = new BusinessLank();
				String name = entry.getKey();
				String[] sp=name.split(" ");
				int value = entry.getValue();
				int index = 0;
				
				for(int i=0; i<sp.length; i++) {
					for (SalesInformation sl : silist) {
						if(sl.getF().contains(sp[i])) {
							BL.setSalespderstore(BL.getSalespderstore()+Integer.parseInt(sl.getG())/1000);
						}
					}
				}
				maxsales=Math.max(maxsales, BL.getSalespderstore());
				minstore=Math.min(minstore, value);
				BL.setName(name);
				BL.setValue(value);
				bllist.add(BL);
				BusinessesLank[index] = value;
			}
			Collections.sort(bllist);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		String maxss = "";
		String mincouts="";
		for (BusinessLank bl : bllist) {
			if (bl.getSalespderstore()==maxsales) {
				maxss=maxss+bl.getName()+",";
			}
			if (bl.getValue()==minstore) {
				mincouts=mincouts+bl.getName()+",";
			}
		}
		
		bllist.get(0).setName("분석이 완료 되었습니다. 우선, 경쟁업체가 가장 적은 업종은"+"\""+mincouts+"\""+"입니다. 또한 매출이 가장 좋은 업종은"+"\""+maxss+"\""+"입니다. 종합적으로 비즈박스가 추천하는 업종은 "+"\""+bllist.get(0).getName()+"\""+"입니다");
		
		jsonObject.put("bl", bllist.get(0));

		return new ResponseEntity<Object>(jsonObject, HttpStatus.OK);
	}

}

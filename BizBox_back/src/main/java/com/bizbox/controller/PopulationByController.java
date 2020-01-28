package com.bizbox.controller;

import java.util.HashMap;
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

import com.bizbox.Service.PopulationByService;
import com.bizbox.apis.JusoApi;
import com.bizbox.vo.PopulationByDong;
import com.bizbox.vo.PopulationByLocation;
import com.bizbox.vo.PopulationByTime;



@CrossOrigin({ "*" })
@RestController
@RequestMapping("/population")
public class PopulationByController {
	private final int max=11715962;
	private final int avgmax=5073881;
	private final int avg=1568200;
	private final int avgmin=783098;
	private final int min=2004;
	
	@Autowired
	PopulationByService service;
	
	@GetMapping("/getPopulationByTime/{address}")
	public ResponseEntity<Object> getPopulationByTime(@PathVariable String address){
		PopulationByTime pbt;
		int Point=0;
		try {
			JSONObject jsonObject = new JSONObject();
			pbt = service.populationByTime(address);
			if(pbt==null) {
				JusoApi api = new JusoApi();
				String predoroname=api.getAddressByName(address).split(",")[11];
				String[] string=predoroname.split(" ");
				String doroname = string[2];
				System.out.println(doroname);
				int size=doroname.length();
				for(int i=0; i<size-1; i++) {
				pbt = service.populationByTime(doroname);
				if(pbt!=null)break;
				doroname=doroname.substring(0, size-1-i);
				}
			}
			jsonObject.put("pbt", pbt);
			jsonObject.put("point", Point);
			if(pbt==null) {return new ResponseEntity<Object>("해당주소의 데이터가 존재하지 않습니다.",HttpStatus.NOT_FOUND);}
			return new ResponseEntity<Object>(jsonObject,HttpStatus.OK);
		} catch (Exception e) {
			
			e.printStackTrace();
			return new ResponseEntity<Object>("해당주소가 존재하지않습니다.", HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/getPopulationByLocation/{address}")
	public ResponseEntity<Object> getPopulationByLocation(@PathVariable String address){
		PopulationByLocation pbl;
		int Point=0;
		try {
			JSONObject jsonObject = new JSONObject();
			pbl = service.populationByLocation(address);
			if(pbl==null) {
				JusoApi api = new JusoApi();
				String predoroname=api.getAddressByName(address).split(",")[11];
				String[] string=predoroname.split(" ");
				String doroname = string[2];
				System.out.println(doroname);
				int size=doroname.length();
				for(int i=0; i<size-1; i++) {
					pbl = service.populationByLocation(doroname);
				if(pbl!=null)break;
				doroname=doroname.substring(0, size-1-i);
				}
				if(pbl!=null) {//끝까지 정보 없으면 address테이블에서 동코드 가져옴
					System.out.println("hihihi");
					predoroname=api.getAddressByName(address).split(",")[5];	
					System.out.println(predoroname);
					List<String> dongcodelist = service.getDongCodeList(predoroname);
					for (String string2 : dongcodelist) {
						System.out.println("ddd:"+string2);
					}
				}
			}
			jsonObject.put("pbl", pbl);
			jsonObject.put("point", Point);
			if(pbl==null) {return new ResponseEntity<Object>("해당주소의 데이터가 존재하지 않습니다.",HttpStatus.NOT_FOUND);}
			return new ResponseEntity<Object>(jsonObject,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("error", HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/getPopulationByDong/{address}")
	public ResponseEntity<Object> getPopulationByDong(@PathVariable String address){
		List<PopulationByDong> pbd;
		int Point=0;
		try {
			JSONObject jsonObject = new JSONObject();
			pbd = service.populationByDong(address);
			System.out.println(pbd);
			jsonObject.put("pbd", pbd);
			jsonObject.put("point", Point);
			return new ResponseEntity<Object>(jsonObject,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("error", HttpStatus.NOT_FOUND);
		}
	}
}

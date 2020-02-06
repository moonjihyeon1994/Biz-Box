package com.bizbox.controller;

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

import com.bizbox.Service.PopulationByService;
import com.bizbox.apis.JusoApi;
import com.bizbox.vo.PopulationByDong;
import com.bizbox.vo.PopulationByLocation;
import com.bizbox.vo.PopulationByTime;
import com.bizbox.vo.PopulationBytimeByDongCode;
import com.bizbox.utils.*;

@CrossOrigin({ "*" })
@RestController
@RequestMapping("/population")
public class PopulationByController {
	private final int max = 11715962;
	private final int avgmax = 5073881;
	private final int avg = 1568200;
	private final int avgmin = 783098;
	private final int min = 2004;

	@Autowired
	PopulationByService service;

	@GetMapping("/getPopulationByTime/{address}")
	public ResponseEntity<Object> getPopulationByTime(@PathVariable String address) {
		PopulationByTime pbt = null;
		List<String> adlist = new LinkedList<String>();
		int Point = 0;
		String predoroname ="";
		String realname="";
		try {

			JSONObject jsonObject = new JSONObject();
			JusoApi api = new JusoApi();
			AddressUtil util = new AddressUtil();
			
			
			
			//utll을 통해 adress를 정제. ex) 테헤란로9999111  -> 테헤란로
			System.out.println(address.substring(address.length()-1,address.length()));
			if(!address.substring(address.length()-1,address.length()).equals("동")) {
			String RemovedAddress = util.RemoveNumber(address); 
		    predoroname = api.getAddressByName(RemovedAddress).split(",")[11];// 도로명
			System.out.println(predoroname);    
			//정제된 address를 인자로 API에 요청하여 관련된 도로명리스트를 가져옵니다
			adlist = api.getAddressSetByName(RemovedAddress);
			
			pbt = service.populationByTime(address);//최초 들어온 검색으를 통해 DB탐색
			
			if (pbt == null) {//최초 검색어와 관련된 데이터가 존재하지 않는다면
				for (String string : adlist) {//전에 얻은 도로명 리스트를 반복하며 DB탐색
					pbt = service.populationByTime(string);
					
					if (pbt != null) {
						break;
					}
				}
			
			}
			}
			
			if (pbt == null) {//위 과정 후에도 데이터를 찾지 못하면
				System.out.println("테이블에 정보가 없어 address테이블에서 동코드를 가져옵니다.....!");

				List<PopulationBytimeByDongCode> list = new LinkedList<PopulationBytimeByDongCode>();
				
				if(!predoroname.equals("")) {//최초 검색어가 동이름이 아닐때만
				predoroname = api.getAddressByName(address).split(",")[5];//이번에는 최초검색어와 관련된 동이름을 가져옵니다.
				realname = predoroname;
				System.out.println(predoroname);
				}else {predoroname=address;realname=address;}
				
				String dongcode = service.getDongCodeList(predoroname);//위에서 얻은 동이름으로 동코드를 반환합니다
				while (dongcode == null && predoroname.length() > 0) {//위에서 얻은 동이름으로 코드검색이 불가 하다면, 가장 비슷한 동의 코드를 가져옵니다
					predoroname = predoroname.substring(0, predoroname.length() - 2);
					dongcode = service.getDongCodeList(predoroname);
				}
				dongcode = dongcode.substring(0, dongcode.length() - 2);//코드 뒤에 2자리를 없애 DB와 데이터 형식을 맟추고
				System.out.println(dongcode);
				list = service.getTimeByDongCode(dongcode);//위에서 얻은 동코드로 시간대별 인구를 얻어옵니다.
				double j = 0, k = 0, l = 0, m = 0, n = 0, o = 0;
				String J = null, K = null, L = null, M = null, N = null, O = null, P = null;
				for (int i = 1; i < 7; i++) {
					J = (j + Double.parseDouble(list.get(i).getD())) + "";
				}
				for (int i = 7; i < 12; i++) {
					K = (k + Double.parseDouble(list.get(i).getD())) + "";
				}
				for (int i = 12; i < 15; i++) {
					L = (l + Double.parseDouble(list.get(i).getD())) + "";
				}
				for (int i = 15; i < 18; i++) {
					M = (m + Double.parseDouble(list.get(i).getD())) + "";
				}
				for (int i = 18; i < 22; i++) {
					N = (n + Double.parseDouble(list.get(i).getD())) + "";
				}
				for (int i = 22; i < 24; i++) {
					O = (o + Double.parseDouble(list.get(i).getD())) + "";
				}
				pbt = new PopulationByTime("", "", "", "", "", realname, "", "", "", J, K, L, M, N, O, "", "", "",
						"", "", "", "");
				System.out.println(list.get(0).getA());
				jsonObject.put("pbt", pbt);
				jsonObject.put("point", Point);
				return new ResponseEntity<Object>(jsonObject, HttpStatus.OK);
			}
			jsonObject.put("pbt", pbt);
			jsonObject.put("point", Point);
			if (pbt == null) {
				return new ResponseEntity<Object>("해당주소의 데이터가 존재하지 않습니다.", HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Object>(jsonObject, HttpStatus.OK);
		} catch (Exception e) {

			e.printStackTrace();
			return new ResponseEntity<Object>("해당주소가 존재하지않습니다.", HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping("/getPopulationByLocation/{address}")
	public ResponseEntity<Object> getPopulationByLocation(@PathVariable String address) {
		PopulationByLocation pbl = new PopulationByLocation();
		List<String> adlist = new LinkedList<String>();
		int Point = 0;
		try {
			JSONObject jsonObject = new JSONObject();
			JusoApi api = new JusoApi();
			AddressUtil util = new AddressUtil();
			String RemovedAddress = util.RemoveNumber(address);
			String predoroname = api.getAddressByName(RemovedAddress).split(",")[11];// 도로명
			adlist = api.getAddressSetByName(RemovedAddress);
			pbl = service.populationByLocation(address);

			if (pbl == null) {

				for (String string : adlist) {
					pbl = service.populationByLocation(string);
					if (pbl != null) {
						break;
					}
				}
				if (pbl == null) {// 끝까지 정보 없으면 address테이블에서 동코드 가져옴
					System.out.println("테이블에 정보가 없어 address테이블에서 동코드를 가져옵니다.....!");
					predoroname = api.getAddressByName(address).split(",")[5];
					String realname = predoroname;
					System.out.println(predoroname);
					String dongcode = service.getDongCodeList(predoroname);

					while (dongcode == null && predoroname.length() > 0) {
						predoroname = predoroname.substring(0, predoroname.length() - 2);
						dongcode = service.getDongCodeList(predoroname);
					}
					dongcode = dongcode.substring(0, dongcode.length() - 2);
					System.out.println(dongcode);
					pbl = service.getByDongCode(dongcode);
					pbl.setF(realname);
					System.out.println(pbl.getA());
					jsonObject.put("pbl", pbl);
					jsonObject.put("point", Point);
					return new ResponseEntity<Object>(jsonObject, HttpStatus.OK);
				}
			}

			jsonObject.put("pbl", pbl);
			jsonObject.put("point", Point);
			return new ResponseEntity<Object>(jsonObject, HttpStatus.OK);
			
			
		} catch (Exception e) {
			System.out.println("No Data");
			return new ResponseEntity<Object>("error", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/getPopulationByDong/{address}")
	public ResponseEntity<Object> getPopulationByDong(@PathVariable String address) {
		List<PopulationByDong> pbd;
		int Point = 0;
		try {
			JSONObject jsonObject = new JSONObject();
			pbd = service.populationByDong(address);
			System.out.println(pbd);
			jsonObject.put("pbd", pbd);
			jsonObject.put("point", Point);
			return new ResponseEntity<Object>(jsonObject, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("error", HttpStatus.NOT_FOUND);
		}
	}
}

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

import com.bizbox.Service.JusoService;
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
	
	@Autowired
	JusoService jusoService;
	
	@Autowired
	JusoApi jusoApi;

	@Autowired
	AddressUtil util;
	
	@GetMapping("/getPopulationByTime/{address}")
	public ResponseEntity<Object> getPopulationByTime(@PathVariable String address) {
		PopulationByTime pbt;
		List<String> adlist = new LinkedList<String>();
		int Point = 0;
		try {

			JSONObject jsonObject = new JSONObject();
			String RemovedAddress = util.RemoveNumber(address);
			String predoroname = jusoService.getAddressByName(RemovedAddress).split(",")[11];// 도로명
			System.out.println(predoroname);
			adlist = jusoService.getAddressSetByName(RemovedAddress);
			for (String string : adlist) {
				System.out.println(string);
			}

			pbt = service.populationByTime(address);
			if (pbt == null) {
				for (String string : adlist) {
					pbt = service.populationByTime(string);
					if (pbt != null) {
						break;
					}
				}
				if (pbt == null) {// 끝까지 정보 없으면 address테이블에서 동코드 가져옴
					System.out.println("테이블에 정보가 없어 address테이블에서 동코드를 가져옵니다.....!");

					List<PopulationBytimeByDongCode> list = new LinkedList<PopulationBytimeByDongCode>();
					predoroname = jusoService.getAddressByName(address).split(",")[5];
					String realname = predoroname;
					System.out.println(predoroname);
					String dongcode = service.getDongCodeList(predoroname);
					while (dongcode == null && predoroname.length() > 0) {
						predoroname = predoroname.substring(0, predoroname.length() - 2);
						dongcode = service.getDongCodeList(predoroname);
					}
					dongcode = dongcode.substring(0, dongcode.length() - 2);
					System.out.println(dongcode);
					list = service.getTimeByDongCode(dongcode);
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
			String RemovedAddress = util.RemoveNumber(address);
			String predoroname = jusoService.getAddressByName(RemovedAddress).split(",")[11];// 도로명
			adlist = jusoService.getAddressSetByName(RemovedAddress);
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
					predoroname = jusoService.getAddressByName(address).split(",")[5];
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

package com.bizbox.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bizbox.Service.ChangeBusinessService;
import com.bizbox.vo.Changebusiness;

@CrossOrigin({ "*" })
@RestController
@RequestMapping("/change")
public class ChangeBusinessController {

	@Autowired
	ChangeBusinessService service;

	//@GetMapping("/getpoly")
//	public JSONObject getpoly() throws Exception {
//		String resultType = "json";
//		String ServiceKey = "h5CUnUDTM85ZI2cIPt4%2FIi6OA08RKDUIfE7%2BDxZ65vsXZ1tPLvGr0a4LI8bj4Ad86ISzZiLH1tu3f4n5wnb2NA%3D%3D";
//		int radius = 0;
//		JSONObject data = new JSONObject();
////		"http://apis.data.go.kr/B553077/api/open/sdsc/storeZoneInAdmi?divId=ctprvnCd
////		&key=11
////		&Servicekey=h5CUnUDTM85ZI2cIPt4%2FIi6OA08RKDUIfE7%2BDxZ65vsXZ1tPLvGr0a4LI8bj4Ad86ISzZiLH1tu3f4n5wnb2NA%3D%3D
////		&type=json"
//		while(true) {
//			String apiUrl = "http://apis.data.go.kr/B553077/api/open/sdsc/storeZoneInRadius?"
//					+ "radius=" + radius
//					+ "&ServiceKey=" + ServiceKey
//					+ "&cx=" + cx
//					+ "&cy=" + cy
//					+ "&type=" + resultType;
//
//			URL url = new URL();
//			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
//			StringBuffer sb = new StringBuffer();
//			String tempStr = null;
//			while (true) {
//				tempStr = br.readLine();
//				if (tempStr == null)
//					break;
//				sb.append(tempStr);
//			}
//			br.close();
//			try {
//				JSONParser jsonParse = new JSONParser();
//				JSONObject jsonObj = (JSONObject) jsonParse.parse(sb.toString());
//				JSONObject header = (JSONObject) jsonObj.get("header");
//				String resultCode = (String) header.get("resultCode");
//				if (resultCode.equals("03"))
//					continue;
//				JSONObject body = (JSONObject) jsonObj.get("body");
//				JSONArray itemsArray = (JSONArray) body.get("items");
//				System.out.println(itemsArray.toString());
//				data = (JSONObject) itemsArray.get(0);
//				if (resultCode.equals("00"))
//					break;
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
//		return data;
//	}
	@GetMapping("/getHistory/{dong}")
	public ResponseEntity<Object> getHistory(@PathVariable String dong) {
		try {
			JSONObject jsonObject = service.getChangeHistorySub(dong);
			return new ResponseEntity<Object>(jsonObject, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("해당 주소가 존재하지않습니다.", HttpStatus.NOT_FOUND);
		}
	}
	
	
	@GetMapping("/getColor")
	public ResponseEntity<Object> getColor() {
		try {
			JSONObject jsonObject = service.getColorlist();
			return new ResponseEntity<Object>(jsonObject, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("해당 주소가 존재하지않습니다.", HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/getHistory2/{dong}")
	public ResponseEntity<Object> getHistory2() {
		try {
			JSONObject jsonObject = service.getChangeHistorySubtoAll();
			return new ResponseEntity<Object>(jsonObject, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Object>("해당 주소가 존재하지않습니다.", HttpStatus.NOT_FOUND);
		}
		
	}


}




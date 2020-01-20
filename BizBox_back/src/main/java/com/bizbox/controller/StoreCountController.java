package com.bizbox.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

@RestController
@Slf4j
@CrossOrigin({"*"})
public class StoreCountController {
	
	@GetMapping("/StoreCount")
	@ApiOperation(value = "반경내 상가업소 조회")
	public String findAllFoods() throws IOException {
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B553077/api/open/sdsc/storeListInRadius"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=h5CUnUDTM85ZI2cIPt4%2FIi6OA08RKDUIfE7%2BDxZ65vsXZ1tPLvGr0a4LI8bj4Ad86ISzZiLH1tu3f4n5wnb2NA%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("radius","UTF-8") + "=" + URLEncoder.encode("500", "UTF-8")); /*파라미터설명*/
        urlBuilder.append("&" + URLEncoder.encode("cx","UTF-8") + "=" + URLEncoder.encode("126.847741", "UTF-8")); /*파라미터설명*/
        urlBuilder.append("&" + URLEncoder.encode("cy","UTF-8") + "=" + URLEncoder.encode("37.497257", "UTF-8")); /*파라미터설명*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*파라미터설명*/
        urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*파라미터설명*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("200", "UTF-8")); /*파라미터설명*/

        /*
         * 
         * 
         http://apis.data.go.kr/B553077/api/open/sdsc/storeListInRadius?ServiceKey=h5CUnUDTM85ZI2cIPt4%2FIi6OA08RKDUIfE7%2BDxZ65vsXZ1tPLvGr0a4LI8bj4Ad86ISzZiLH1tu3f4n5wnb2NA%3D%3D&radius=100&cx=126.847741&cy=37.497257&type=json&numOfRows=200
         * 
         * 
         */
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println("시작");
        System.out.println(sb.toString());
		return sb.toString();
	}
	
	@GetMapping("/come")
	@ApiOperation(value = "도로명주소로 입력")
	public String getAddress() throws IOException {
		// 요청변수 설정
		String currentPage = "0";
		String countPerPage = "100";
		String resultType = "json";
		String confmKey = "devU01TX0FVVEgyMDIwMDEyMDE2MjcwNjEwOTQwNzE=";
		String keyword = "경인로248-14";
		/*
		 * 
		http://www.juso.go.kr/addrlink/addrLinkApi.do?currentPage=0&countPerPage=100&keyword=경인로248-14&confmKey=devU01TX0FVVEgyMDIwMDEyMDE2MjcwNjEwOTQwNzE=&resultType=json
		
		 */
		
		String apiUrl ="http://www.juso.go.kr/addrlink/addrLinkApi.do?"+
					   "currentPage="+currentPage+
					   "&countPerPage="+countPerPage+
					   "&keyword="+URLEncoder.encode(keyword,"UTF-8")+
					   "&confmKey="+confmKey+
					   "&resultType="+resultType;
		
		URL url = new URL(apiUrl);
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
		StringBuffer sb = new StringBuffer();
		String tempStr = null;
		while(true) {
			tempStr  = br.readLine();
			if(tempStr == null) break;
			sb.append(tempStr);
		}
		br.close();
		System.out.println(sb.toString());
		
		return sb.toString();
	}	
	
}

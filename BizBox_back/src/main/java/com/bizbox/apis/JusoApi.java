package com.bizbox.apis;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jhlabs.map.proj.Projection;
import com.jhlabs.map.proj.ProjectionFactory;

public class JusoApi {
	
//	도로명주소로 입력
	public String getAddressByName(String name) throws IOException {
		// 요청변수 설정
		String currentPage = "0";
		String countPerPage = "100";
		String resultType = "json";
		String confmKey = "devU01TX0FVVEgyMDIwMDEyMDE2MjcwNjEwOTQwNzE=";
		String keyword = name;
		/*
		 * 
		 http://www.juso.go.kr/addrlink/addrLinkApi.do?currentPage=0&countPerPage=100&
		 keyword=경인로248-14&confmKey=devU01TX0FVVEgyMDIwMDEyMDE2MjcwNjEwOTQwNzE=&
		 resultType=json
		 * 
		 */

		String apiUrl = "http://www.juso.go.kr/addrlink/addrLinkApi.do?" + "currentPage=" + currentPage
				+ "&countPerPage=" + countPerPage + "&keyword=" + URLEncoder.encode(keyword, "UTF-8") + "&confmKey="
				+ confmKey + "&resultType=" + resultType;

		URL url = new URL(apiUrl);
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
		StringBuffer sb = new StringBuffer();
		String tempStr = null;
		while (true) {
			tempStr = br.readLine();
			if (tempStr == null)
				break;
			sb.append(tempStr);
		}
		br.close();
		
		/////////////json체인지
		
		Gson gson = new Gson();
		StringBuilder temp = new StringBuilder();
		try {
			JSONParser jsonParse = new JSONParser();
			JSONObject jsonObj = (JSONObject) jsonParse.parse(sb.toString());
			JSONObject jsonObj1 = (JSONObject) jsonObj.get("results");
			JSONArray jsonArray = (JSONArray) jsonObj1.get("juso");
			
			JSONObject personObject = (JSONObject) jsonArray.get(0);
			temp.append(personObject.get("admCd"));
			temp.append(",");
			temp.append(personObject.get("rnMgtSn"));
			temp.append(",");
			temp.append(personObject.get("udrtYn"));
			temp.append(",");
			temp.append(personObject.get("buldMnnm"));
			temp.append(",");
			temp.append(personObject.get("buldSlno"));
			temp.append(",");
			temp.append(personObject.get("admCd"));
			temp.append(",");
			temp.append(personObject.get("emdNm"));
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		System.out.println(temp.toString());
		return temp.toString();
	}
	
	
//	xy좌표로 입력
	public String getAddressByXY(String num) throws IOException {
		String[] nums = num.split(",");
		// 요청변수 설정
		String admCd = nums[0];
		String rnMgtSn = nums[1];
		String udrtYn = nums[2];
		String buldMnnm = nums[3];
		String buldSlno = nums[4];
		String confmKey = "devU01TX0FVVEgyMDIwMDEyMTEwMjgxNDEwOTQwOTc=";
		String resultType = "json";

		String apiUrl = "http://www.juso.go.kr/addrlink/addrCoordApi.do?" + "admCd=" + admCd + "&rnMgtSn=" + rnMgtSn
				+ "&udrtYn=" + udrtYn + "&buldMnnm=" + buldMnnm + "&buldSlno=" + buldSlno + "&confmKey=" + confmKey
				+ "&resultType=" + resultType;

		URL url = new URL(apiUrl);
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
		StringBuffer sb = new StringBuffer();
		String tempStr = null;
		while (true) {
			tempStr = br.readLine();
			if (tempStr == null)
				break;
			sb.append(tempStr);
		}
		br.close();
		
		String[] proj4_w = new String[] { "+proj=tmerc", "+lat_0=38", "+lon_0=127.5",
				"+ellps=GRS80", "+units=m", "+x_0=1000000", "+y_0=2000000", "+k=0.9996" };

		Point2D.Double srcProjec = null;
		Point2D.Double dstProjec = null;
		Projection proj = ProjectionFactory.fromPROJ4Specification(proj4_w);
//		srcProjec = new Point2D.Double(132, 37);
//		dstProjec = proj.transform(srcProjec, new Point2D.Double());
//		System.out.println("TM : +dstProjec"+dstProjec);
		// TM : Point2D.Double[644904.399587292, 400717.8948938238]
		srcProjec = new Point2D.Double(942382.1170934895 ,1944406.216968163);
		dstProjec = proj.inverseTransform(srcProjec, new Point2D.Double());
		System.out.println("WGS84 : +dstProjec"+dstProjec);
		StringBuilder temp = new StringBuilder();
		temp.append(dstProjec.x);
		temp.append(",");
		temp.append(dstProjec.y);
		return temp.toString();
	}
//	반경내 상가업소 조회
	public String findStore(String xy, String radius, String pageNo) throws IOException{
		String[] cxcy = xy.split(",");
		
		StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B553077/api/open/sdsc/storeListInRadius"); /*URL*/
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=h5CUnUDTM85ZI2cIPt4%2FIi6OA08RKDUIfE7%2BDxZ65vsXZ1tPLvGr0a4LI8bj4Ad86ISzZiLH1tu3f4n5wnb2NA%3D%3D"); /*Service Key*/
		urlBuilder.append("&" + URLEncoder.encode("radius","UTF-8") + "=" + URLEncoder.encode(radius, "UTF-8")); /*파라미터설명*/
		urlBuilder.append("&" + URLEncoder.encode("cx","UTF-8") + "=" + URLEncoder.encode(cxcy[0], "UTF-8")); /*파라미터설명*/
		urlBuilder.append("&" + URLEncoder.encode("cy","UTF-8") + "=" + URLEncoder.encode(cxcy[1], "UTF-8")); /*파라미터설명*/
		urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8")); /*파라미터설명*/
		urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*파라미터설명*/
		urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("300", "UTF-8")); /*파라미터설명*/
		
		/*
         http://apis.data.go.kr/B553077/api/open/sdsc/storeListInRadius?ServiceKey=h5CUnUDTM85ZI2cIPt4%2FIi6OA08RKDUIfE7%2BDxZ65vsXZ1tPLvGr0a4LI8bj4Ad86ISzZiLH1tu3f4n5wnb2NA%3D%3D&radius=100&cx=126.847741&cy=37.497257&type=json&numOfRows=200
		 */
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
//		System.out.println("Response code: " + conn.getResponseCode());
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
		return sb.toString();
	}
	
	public String findAllStore(String xy, String radius) throws IOException {
		int idx = 1;
		Map<String, Integer> storecount = new HashMap<>();
		while(true) {
			String str = findStore(xy, radius, String.valueOf(idx));			
			idx++;
			
			Gson gson = new Gson();
			StringBuilder temp = new StringBuilder();
			try {
				JSONParser jsonParse = new JSONParser();
				JSONObject jsonObj = (JSONObject) jsonParse.parse(str);
				
				JSONObject header = (JSONObject) jsonObj.get("header");
				String resultCode = (String) header.get("resultCode");
				if(resultCode.equals("03")) break;
				JSONObject body = (JSONObject) jsonObj.get("body");
				JSONArray itemsArray = (JSONArray) body.get("items");
				
				for (int i = 0; i < itemsArray.size(); i++) {
					JSONObject items = (JSONObject) itemsArray.get(i);
					temp.append(items.get("admCd"));
					
					
				}
				
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		return "";
	}
}

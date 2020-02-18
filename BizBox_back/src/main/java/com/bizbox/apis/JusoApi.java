package com.bizbox.apis;

import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.jhlabs.map.proj.Projection;
import com.jhlabs.map.proj.ProjectionFactory;
import com.bizbox.utils.*;

@Service
public class JusoApi {
	
	/**
	 * 도로명 주소입력하여 해당하는 동네 데이터 얻어오는 API
	 * @param name (도로명주소)
	 * @return JsonString(해당하는 동네의 데이터들 Json)
	 * @throws IOException
	 */
	@Cacheable(cacheNames = "DoroNameApi")
	public String DoroNameApi(String name) throws IOException {
		String currentPage = "0";
		String countPerPage = "100";
		String resultType = "json";
		String confmKey = "devU01TX0FVVEgyMDIwMDEyMDE2MjcwNjEwOTQwNzE=";
		String keyword = name;
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
		return sb.toString();
	}
	
	/**
	 * 동정보를 입력하여 xy를 위도 경도로 변환하는 API 
	 * @param num (동데이터 정보들:admCd,rnMgtSn,udrtYn,buldMnnm,buldMnnm)
	 * @return String (x,y) 위도,경도
	 * @throws IOException
	 */
	@Cacheable(cacheNames = "XYtoLatLong")
	public String XYtoLatLong(String num) throws IOException {
		// 요청변수 설정
		String[] nums = num.split(",");
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
		String entX = "";
		String entY = "";
		try {
			JSONParser jsonParse = new JSONParser();
			JSONObject jsonObj = (JSONObject) jsonParse.parse(sb.toString());
			JSONObject jsonObj1 = (JSONObject) jsonObj.get("results");
			JSONArray jsonArray = (JSONArray) jsonObj1.get("juso");
			JSONObject xyObject = (JSONObject) jsonArray.get(0);
			entX = (String) xyObject.get("entX");
			entY = (String) xyObject.get("entY");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] proj4_w = new String[] { "+proj=tmerc", "+lat_0=38", "+lon_0=127.5", "+ellps=GRS80", "+units=m",
				"+x_0=1000000", "+y_0=2000000", "+k=0.9996" };

		Point2D.Double srcProjec = null;
		Point2D.Double dstProjec = null;
		Projection proj = ProjectionFactory.fromPROJ4Specification(proj4_w);
		srcProjec = new Point2D.Double(Double.valueOf(entX), Double.valueOf(entY));
		dstProjec = proj.inverseTransform(srcProjec, new Point2D.Double());
		StringBuilder temp = new StringBuilder();
		temp.append(dstProjec.x);
		temp.append(",");
		temp.append(dstProjec.y);
		return temp.toString();
	}
	
	/**
	 * 반경내 상가업소 조회하여 JsonString으로 주는 API
	 * @param xy
	 * @param radius
	 * @param pageNo
	 * @return JsonString
	 * @throws IOException
	 */
	public String findStore(String xy, String radius, String pageNo) throws IOException {
		String[] cxcy = xy.split(",");

		StringBuilder urlBuilder = new StringBuilder(
				"http://apis.data.go.kr/B553077/api/open/sdsc/storeListInRadius"); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8")
				+ "=h5CUnUDTM85ZI2cIPt4%2FIi6OA08RKDUIfE7%2BDxZ65vsXZ1tPLvGr0a4LI8bj4Ad86ISzZiLH1tu3f4n5wnb2NA%3D%3D"); 
		urlBuilder.append(
				"&" + URLEncoder.encode("radius", "UTF-8") + "=" + URLEncoder.encode(radius, "UTF-8"));
		urlBuilder.append(
				"&" + URLEncoder.encode("cx", "UTF-8") + "=" + URLEncoder.encode(cxcy[0], "UTF-8"));
		urlBuilder.append(
				"&" + URLEncoder.encode("cy", "UTF-8") + "=" + URLEncoder.encode(cxcy[1], "UTF-8"));
		urlBuilder.append(
				"&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8"));
		urlBuilder.append(
				"&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));
		urlBuilder.append(
				"&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("300", "UTF-8"));

		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
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
	
	
	/**
	 * 반경내 상가업소 조회하여 JsonString으로 주는 API
	 * @param xy
	 * @param radius
	 * @param pageNo
	 * @return JsonString
	 * @throws IOException
	 */
	@Cacheable(cacheNames = "FindStore")
	public JSONArray findStoreToJson(String xy, String radius) throws IOException {
		int idx = 0;
		JSONArray list = new JSONArray();
		while(true) {
			idx++;
			String[] cxcy = xy.split(",");
			
			StringBuilder urlBuilder = new StringBuilder(
					"http://apis.data.go.kr/B553077/api/open/sdsc/storeListInRadius"); /* URL */
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8")
			+ "=h5CUnUDTM85ZI2cIPt4%2FIi6OA08RKDUIfE7%2BDxZ65vsXZ1tPLvGr0a4LI8bj4Ad86ISzZiLH1tu3f4n5wnb2NA%3D%3D"); 
			urlBuilder.append(
					"&" + URLEncoder.encode("radius", "UTF-8") + "=" + URLEncoder.encode(radius, "UTF-8"));
			urlBuilder.append(
					"&" + URLEncoder.encode("cx", "UTF-8") + "=" + URLEncoder.encode(cxcy[0], "UTF-8"));
			urlBuilder.append(
					"&" + URLEncoder.encode("cy", "UTF-8") + "=" + URLEncoder.encode(cxcy[1], "UTF-8"));
			urlBuilder.append(
					"&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(idx), "UTF-8"));
			urlBuilder.append(
					"&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));
			urlBuilder.append(
					"&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("300", "UTF-8"));
			
			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			BufferedReader rd;
			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
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
			
			try {
				JSONParser jsonParse = new JSONParser();
				JSONObject jsonObj = (JSONObject) jsonParse.parse(sb.toString());
				
				
				JSONObject header = (JSONObject) jsonObj.get("header");
				String resultCode = (String) header.get("resultCode");
				if (resultCode.equals("03"))
					break;
				JSONObject body = (JSONObject) jsonObj.get("body");
				JSONArray itemsArray = (JSONArray) body.get("items");
				
				for (int i = 0; i < itemsArray.size(); i++) {
					list.add((JSONObject) itemsArray.get(i));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return list;
	}
	
	/**
	 * 위도 경도 입력시 제일 가까운 상권정보 주기 
	 * @param cx(위도)
	 * @param cy(경도)
	 * @return 상권정보
	 * @throws IOException
	 */
	public JSONObject findBusiness(String cx,String cy) throws IOException {
		String resultType = "json";
		String ServiceKey = "h5CUnUDTM85ZI2cIPt4%2FIi6OA08RKDUIfE7%2BDxZ65vsXZ1tPLvGr0a4LI8bj4Ad86ISzZiLH1tu3f4n5wnb2NA%3D%3D";
		int radius = 50;
		
		JSONObject data = new JSONObject();
		while(true) {
			radius += radius;
			String apiUrl = "http://apis.data.go.kr/B553077/api/open/sdsc/storeZoneInRadius?"
					+ "radius=" + radius 
					+ "&ServiceKey=" + ServiceKey 
					+ "&cx=" + cx 
					+ "&cy=" + cy
					+ "&type=" + resultType;
			
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
			
			try {
				JSONParser jsonParse = new JSONParser();
				JSONObject jsonObj = (JSONObject) jsonParse.parse(sb.toString());
				JSONObject header = (JSONObject) jsonObj.get("header");
				String resultCode = (String) header.get("resultCode");
				if (resultCode.equals("03"))
					continue;
				JSONObject body = (JSONObject) jsonObj.get("body");
				JSONArray itemsArray = (JSONArray) body.get("items");
				data = (JSONObject) itemsArray.get(0);
				if (resultCode.equals("00"))
					break;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return data;
	}
}

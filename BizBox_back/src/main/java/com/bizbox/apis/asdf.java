package com.bizbox.apis;

import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class asdf {
	/**
	 * 반경내 상가업소 조회하여 JsonString으로 주는 API
	 * @param xy
	 * @param radius
	 * @param pageNo
	 * @return JsonString
	 * @throws IOException
	 */
	public JSONArray findStore(String xy, String radius) throws IOException {
		int idx = 1;
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
}

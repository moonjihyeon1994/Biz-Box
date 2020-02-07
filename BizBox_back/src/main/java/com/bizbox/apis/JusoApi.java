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

//	도로명주소로 입력
	public String getAddressByName(String name) throws IOException {
		// 요청변수 설정
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
		int size = 0;
		StringBuilder temp = new StringBuilder();
		try {
			JSONParser jsonParse = new JSONParser();
			JSONObject jsonObj = (JSONObject) jsonParse.parse(sb.toString());
			JSONObject jsonObj1 = (JSONObject) jsonObj.get("results");
			JSONArray jsonArray = (JSONArray) jsonObj1.get("juso");
			size = jsonArray.size();
			boolean isfind = false;
			for (int i = 0; i < size; i++) {
				temp = new StringBuilder();
				JSONObject personObject = (JSONObject) jsonArray.get(i);
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
				temp.append(personObject.get("emdNm"));
				temp.append(",");
				temp.append(personObject.get("emdNo"));
				temp.append(",");
				temp.append(personObject.get("liNm"));
				temp.append(",");
				temp.append(personObject.get("Rn"));
				temp.append(",");
				temp.append(personObject.get("lnbrMnnm"));
				temp.append(",");
				temp.append(personObject.get("jibunAddr"));
				temp.append(",");
				temp.append(personObject.get("roadAddrPart1"));
				if (personObject.get("roadAddrPart1").toString().contains(name)
						|| name.contains(personObject.get("roadAddrPart1").toString().substring(0,
								personObject.get("roadAddrPart1").toString().length() - 1))) {
					System.out.println("그만!!!!" + personObject.get("roadAddrPart1"));
					isfind = true;
					break;
				}
			}
			if (isfind == false) {
				for (int i = 0; i < size; i++) {
					temp = new StringBuilder();
					JSONObject personObject = (JSONObject) jsonArray.get(i);
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
					temp.append(personObject.get("emdNm"));
					temp.append(",");
					temp.append(personObject.get("emdNo"));
					temp.append(",");
					temp.append(personObject.get("liNm"));
					temp.append(",");
					temp.append(personObject.get("Rn"));
					temp.append(",");
					temp.append(personObject.get("lnbrMnnm"));
					temp.append(",");
					temp.append(personObject.get("jibunAddr"));
					temp.append(",");
					temp.append(personObject.get("roadAddrPart1"));
				
					if (personObject.get("emdNm").toString().contains(name) || name.contains(personObject.get("emdNm")
							.toString().substring(0, personObject.get("emdNm").toString().length() - 1))) {
						System.out.println("그만!!!!" + personObject.get("emdNm"));
						break;
					}
				}
			}
		} catch (Exception e) {
			System.out.println("해당주소가 존재하지않습니다.");
			e.printStackTrace();
		}
		System.out.println(size);
		return temp.toString();
	}

	public List<String> getAddressSetByName(String name) throws IOException {
		// 요청변수 설정
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
		int size = 0;
		List<String> addresslist = new LinkedList<String>();
		try {
			JSONParser jsonParse = new JSONParser();
			JSONObject jsonObj = (JSONObject) jsonParse.parse(sb.toString());
			JSONObject jsonObj1 = (JSONObject) jsonObj.get("results");
			JSONArray jsonArray = (JSONArray) jsonObj1.get("juso");
			size = jsonArray.size();

			for (int i = 0; i < size; i++) {
				JSONObject personObject = (JSONObject) jsonArray.get(i);
				String sp[] = personObject.get("roadAddrPart1").toString().split(" ");
				String ad = sp[2];
				addresslist.add(ad);
			}
		} catch (Exception e) {
			System.out.println("해당주소가 존재하지않습니다.");
		}
		Set Address = new HashSet<String>();
		AddressUtil util = new AddressUtil();
		for (int i = 0; i < addresslist.size(); i++) {
			String ad = util.RemoveNumber(addresslist.get(i));
			Address.add(ad);
		}
		List<String> setlist = new LinkedList<String>(Address);
		return setlist;
	}

	public List<String> getDongSetByName(String name) throws IOException {
		// 요청변수 설정
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
		int size = 0;
		List<String> donglist = new LinkedList<String>();
		try {
			JSONParser jsonParse = new JSONParser();
			JSONObject jsonObj = (JSONObject) jsonParse.parse(sb.toString());
			JSONObject jsonObj1 = (JSONObject) jsonObj.get("results");
			JSONArray jsonArray = (JSONArray) jsonObj1.get("juso");
			size = jsonArray.size();

			for (int i = 0; i < size; i++) {
				JSONObject personObject = (JSONObject) jsonArray.get(i);
				String sp = personObject.get("emdNm").toString();
				donglist.add(sp);
			}
		} catch (Exception e) {
			System.out.println("해당주소가 존재하지않습니다.");
		}
		Set Dong = new HashSet<String>();
		AddressUtil util = new AddressUtil();
		for (int i = 0; i < donglist.size(); i++) {
			String ad = util.RemoveDong(donglist.get(i));
			Dong.add(ad);
		}
		List<String> setlist = new LinkedList<String>(Dong);
		return setlist;
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
			System.out.println("xy좌표 찾기 오류");
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

	//	반경내 상가업소 조회
	public String findStore(String xy, String radius, String pageNo) throws IOException {
		String[] cxcy = xy.split(",");

		StringBuilder urlBuilder = new StringBuilder(
				"http://apis.data.go.kr/B553077/api/open/sdsc/storeListInRadius"); /* URL */
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8")
				+ "=h5CUnUDTM85ZI2cIPt4%2FIi6OA08RKDUIfE7%2BDxZ65vsXZ1tPLvGr0a4LI8bj4Ad86ISzZiLH1tu3f4n5wnb2NA%3D%3D"); /*
																														 * Service
																														 * Key
																														 */
		urlBuilder.append(
				"&" + URLEncoder.encode("radius", "UTF-8") + "=" + URLEncoder.encode(radius, "UTF-8")); /* 파라미터설명 */
		urlBuilder.append(
				"&" + URLEncoder.encode("cx", "UTF-8") + "=" + URLEncoder.encode(cxcy[0], "UTF-8")); /* 파라미터설명 */
		urlBuilder.append(
				"&" + URLEncoder.encode("cy", "UTF-8") + "=" + URLEncoder.encode(cxcy[1], "UTF-8")); /* 파라미터설명 */
		urlBuilder.append(
				"&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode(pageNo, "UTF-8")); /* 파라미터설명 */
		urlBuilder.append(
				"&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /* 파라미터설명 */
		urlBuilder.append(
				"&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("300", "UTF-8")); /* 파라미터설명 */

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

	// json을 parsing하여 상권 정보얻어오기!
	@Cacheable(cacheNames = "StoreCount")
	public JSONObject findAllStore(String xy, String radius) throws IOException {
		int idx = 1;
		HashMap<String, HashMap<String, Integer>> storecount = new HashMap<String, HashMap<String, Integer>>();
		HashMap<String, Integer> LNm = new HashMap<String, Integer>();
		java.util.List<String> names = new ArrayList<String>();
		while (true) {
			String str = findStore(xy, radius, String.valueOf(idx));
			idx++;
			try {
				JSONParser jsonParse = new JSONParser();
				JSONObject jsonObj = (JSONObject) jsonParse.parse(str);

				JSONObject header = (JSONObject) jsonObj.get("header");
				String resultCode = (String) header.get("resultCode");
				if (resultCode.equals("03"))
					break;
				JSONObject body = (JSONObject) jsonObj.get("body");
				JSONArray itemsArray = (JSONArray) body.get("items");

				for (int i = 0; i < itemsArray.size(); i++) {
					JSONObject items = (JSONObject) itemsArray.get(i);
					String indsLclsNm = (String) items.get("indsLclsNm"); // 대분류
					String indsMclsNm = (String) items.get("indsMclsNm"); // 중분류
					String indsSclsNm = (String) items.get("indsSclsNm"); // 소분류

					indsLclsNm = indsLclsNm.replace("/", "");
					indsMclsNm = indsMclsNm.replace("/", "");
					indsMclsNm = indsMclsNm.replace("/", "");

					if (LNm.containsKey(indsLclsNm)) { // 대분류 당 갯수
						LNm.put(indsLclsNm, LNm.get(indsLclsNm) + 1);
					} else {
						LNm.put(indsLclsNm, 1);
					}

					if (storecount.containsKey(indsMclsNm)) {
						if (storecount.get(indsMclsNm).containsKey(indsSclsNm)) {
							storecount.get(indsMclsNm).put(indsSclsNm, storecount.get(indsMclsNm).get(indsSclsNm) + 1);
						} else {
							storecount.get(indsMclsNm).put(indsSclsNm, 1);
						}
					} else {
						names.add(indsMclsNm);
						storecount.put(indsMclsNm, new HashMap<String, Integer>());
					}
				}

			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		JSONObject jsonObject = new JSONObject();
		JSONObject jsonObject1 = new JSONObject();
		JSONObject jsonObject2 = new JSONObject();
		for (int i = 0; i < storecount.size(); i++) {
			String key1 = names.get(i);
			JSONObject data = new JSONObject();
			for (Map.Entry<String, Integer> entry : storecount.get(names.get(i)).entrySet()) {
				String key2 = entry.getKey();
				int value2 = entry.getValue();
				data.put(key2, value2);
			}
			JSONArray array = new JSONArray();
			array.add(data);

			jsonObject1.put(key1, array);
		}
		jsonObject.put("small", jsonObject1);

		for (Map.Entry<String, Integer> entry : LNm.entrySet()) {
			String key = entry.getKey();
			int value = entry.getValue();
			jsonObject2.put(key, value);
		}

		jsonObject.put("large", jsonObject2);

		return jsonObject;
	}

	public HashMap<String, Integer> findStoreToSpring(String xy, String radius) throws IOException {
		
		System.out.println("상점개수 찾는중........");
		int idx = 1;
		HashMap<String, HashMap<String, Integer>> storecount = new HashMap<String, HashMap<String, Integer>>();
		HashMap<String, Integer> LNm = new HashMap<String, Integer>();
		java.util.List<String> names = new ArrayList<String>();
		while (true) {
			String str = findStore(xy, radius, String.valueOf(idx));
			idx++;
			try {
				JSONParser jsonParse = new JSONParser();
				System.out.println(str);
				JSONObject jsonObj = (JSONObject) jsonParse.parse(str);

				JSONObject header = (JSONObject) jsonObj.get("header");
				String resultCode = (String) header.get("resultCode");
				if (resultCode.equals("03"))
					break;
				JSONObject body = (JSONObject) jsonObj.get("body");
				JSONArray itemsArray = (JSONArray) body.get("items");

				for (int i = 0; i < itemsArray.size(); i++) {
					JSONObject items = (JSONObject) itemsArray.get(i);
					String indsLclsNm = (String) items.get("indsLclsNm"); // 대분류
					String indsMclsNm = (String) items.get("indsMclsNm"); // 중분류
					String indsSclsNm = (String) items.get("indsSclsNm"); // 소분류

					indsLclsNm = indsLclsNm.replace("/", " ");
					indsMclsNm = indsMclsNm.replace("/", " ");
					indsMclsNm = indsMclsNm.replace("/", " ");
					if (
						!indsMclsNm.contains("기타")  &&
						indsLclsNm.contains("음식")   ||
						indsLclsNm.contains("중식")   ||
						indsLclsNm.contains("일식")   ||
						indsLclsNm.contains("커피")   ||
						indsLclsNm.contains("치킨")   ||
						indsLclsNm.contains("양식")   || 
						indsLclsNm.contains("오락")  	 ||
						indsLclsNm.contains("스포츠")  || 
						indsLclsNm.contains("패션") 	 ||
						indsLclsNm.contains("의류") 	 ||
						indsLclsNm.contains("편의점")	 ||
						indsLclsNm.contains("운동")    
							) {
						
						if (LNm.containsKey(indsMclsNm)) { // 대분류 당 갯수
							LNm.put(indsMclsNm, LNm.get(indsMclsNm) + 1);
						} else {
							LNm.put(indsMclsNm, 1);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return LNm;
	}
	
	//큰스케일만
	public JSONObject findAllStoreByLarge(String xy, String radius) throws IOException {
		int idx = 1;
		HashMap<String, Integer> LNm = new HashMap<String, Integer>();
		java.util.List<String> names = new ArrayList<String>();
		while (true) {
			String str = findStore(xy, radius, String.valueOf(idx));
			idx++;
			try {
				JSONParser jsonParse = new JSONParser();
				JSONObject jsonObj = (JSONObject) jsonParse.parse(str);

				JSONObject header = (JSONObject) jsonObj.get("header");
				String resultCode = (String) header.get("resultCode");
				if (resultCode.equals("03"))
					break;
				JSONObject body = (JSONObject) jsonObj.get("body");
				JSONArray itemsArray = (JSONArray) body.get("items");

				for (int i = 0; i < itemsArray.size(); i++) {
					JSONObject items = (JSONObject) itemsArray.get(i);
					String indsLclsNm = (String) items.get("indsLclsNm"); // 대분류

					if (LNm.containsKey(indsLclsNm)) { // 대분류 당 갯수
						LNm.put(indsLclsNm, LNm.get(indsLclsNm) + 1);
					} else {
						LNm.put(indsLclsNm, 1);
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		JSONObject jsonObject = new JSONObject();
		JSONArray array = new JSONArray();
		for (Map.Entry<String, Integer> entry : LNm.entrySet()) {
			JSONObject jsonObject1 = new JSONObject();
			String key = entry.getKey();
			int value = entry.getValue();
			jsonObject1.put(key, value);
			array.add(jsonObject1);
		}
		jsonObject.put("large", array);
		return jsonObject;
	}
}

package com.bizbox.Service;

import java.io.IOException;
import java.sql.SQLException;
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
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizbox.apis.JusoApi;
import com.bizbox.apis.KakaoApi;
import com.bizbox.dao.StoreDAO;
import com.bizbox.utils.AddressUtil;
import com.bizbox.vo.Point;
import com.bizbox.vo.Store;

@Service
public class JusoServiceImpl implements JusoService{
	@Autowired
	JusoApi jusoapi;
	@Autowired
	AddressUtil adutil;
	@Autowired
	KakaoApi kakaoapi;
	@Autowired
	StoreDAO storedao;
	@Autowired
	StoreService storeservice;
	
	/**
	 * 도로명주소로 입력 하여 정보를 String으로 받음
	 * @param name (도로명 주소)
	 * @return String (동네 정보 : admCd, rnMgtSn, udrtYn, buldMnnm, buldSlno, emdNm, emdNo, liNm, Rn, lnbrMnnm, jibunAddr, roadAddrPart1)
	 * @throws IOException
	 */
	public String getAddressByName(String name) throws IOException {
		String sb = jusoapi.DoroNameApi(name);
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
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp.toString();
	}
	
	public List<String> getAddressSetByName(String name) throws IOException {
		String sb = jusoapi.DoroNameApi(name);
		
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
			e.printStackTrace();
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
		String sb = jusoapi.DoroNameApi(name);
		
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
			e.printStackTrace();
		}
		Set Dong = new HashSet<String>();
		for (int i = 0; i < donglist.size(); i++) {
			String ad = adutil.RemoveDong(donglist.get(i));
			Dong.add(ad);
		}
		List<String> setlist = new LinkedList<String>(Dong);
		return setlist;
	}

	/**
	 * 위도 경도 좌표로 상권정보 받기
	 * @param xy
	 * @param radius
	 * @return Json(대분류 개수, 중분류에 해당하는 각각의 소분류 개수)
	 * @throws IOException
	 */
	public JSONObject findAllStore(String xy, String radius) throws IOException {
		int idx = 1;
		HashMap<String, HashMap<String, Integer>> storecount = new HashMap<String, HashMap<String, Integer>>();
		HashMap<String, Integer> LNm = new HashMap<String, Integer>();
		java.util.List<String> names = new ArrayList<String>();
		while (true) {
			String str = jusoapi.findStore(xy, radius, String.valueOf(idx));
			
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
		int idx = 1;
		HashMap<String, HashMap<String, Integer>> storecount = new HashMap<String, HashMap<String, Integer>>();
		HashMap<String, Integer> LNm = new HashMap<String, Integer>();
		java.util.List<String> names = new ArrayList<String>();
		while (true) {
			String str = jusoapi.findStore(xy, radius, String.valueOf(idx));
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
	
	/**
	 * 위도,경도, 반경 입력하여 대분류 갯수 받기
	 * @param xy (String 위도,경도)
	 * @param radius (m, 미터)
	 * @return Json(대분류당 개수)
	 * @throws IOException
	 */
	public JSONObject findAllStoreByLarge(String xy, String radius) throws IOException {
		JSONArray itemsArray = jusoapi.findStoreToJson(xy, radius);
		HashMap<String, Integer> LNm = new HashMap<String, Integer>();
		for (int i = 0; i < itemsArray.size(); i++) {
			JSONObject items = (JSONObject) itemsArray.get(i);
			String indsLclsNm = (String) items.get("indsLclsNm"); // 대분류
			
			if (LNm.containsKey(indsLclsNm)) { // 대분류 당 갯수
				LNm.put(indsLclsNm, LNm.get(indsLclsNm) + 1);
			} else {
				LNm.put(indsLclsNm, 1);
			}
		}
		
		JSONObject jsonObject = new JSONObject();
		for (Map.Entry<String, Integer> entry : LNm.entrySet()) {
			String key = entry.getKey();
			int value = entry.getValue();
			if(key.contains("학문")) {
				key = "학문교육";
			}else if(key.contains("관광")) {
				key = "관광여가오락";
			}
			jsonObject.put(key, value);
		}
		return jsonObject;
	}
	
	/**
	 * 위도,경도, 반경 입력하여 대분류 갯수 받기
	 * @param xy (String 위도,경도)
	 * @param radius (m, 미터)
	 * @return Json(대분류당 객체로 리턴 개수)
	 * @throws IOException
	 */
	public JSONObject findAllStoreByLargeJson(String xy, String radius) throws IOException {
		String[] latlot = xy.split(",");
		String lat = latlot[0];
		String lot = latlot[1];
		if(Double.parseDouble(latlot[0])>Double.parseDouble(latlot[1])) {
			lat = latlot[1];
			lot = latlot[0];
		}
		
		String distance = String.valueOf(radius);
		Point point = new Point(lat, lot, distance);
		
		List<Store> list = storeservice.getAllStoreByXY(point);
		
		HashMap<String, Integer> LNm = new HashMap<String, Integer>();
		for (int i = 0; i < list.size(); i++) {
			String indsLclsNm = list.get(i).getCategory_large();
			if (LNm.containsKey(indsLclsNm)) { // 대분류 당 갯수
				LNm.put(indsLclsNm, LNm.get(indsLclsNm) + 1);
			} else {
				LNm.put(indsLclsNm, 1);
			}
		}
		
		JSONArray itemsArray = jusoapi.findStoreToJson(xy, radius);
		for (int i = 0; i < itemsArray.size(); i++) {
			JSONObject items = (JSONObject) itemsArray.get(i);
			String indsLclsNm = (String) items.get("indsLclsNm"); // 대분류
			
			if (LNm.containsKey(indsLclsNm)) { // 대분류 당 갯수
				LNm.put(indsLclsNm, LNm.get(indsLclsNm) + 1);
			} else {
				LNm.put(indsLclsNm, 1);
			}
		}
		
		JSONObject jsonObject = new JSONObject();
		for (Map.Entry<String, Integer> entry : LNm.entrySet()) {
			String key = entry.getKey();
			if(key.contains("학문")) {
				key = "학문교육";
			}else if(key.contains("관광")) {
				key = "관광여가오락";
			}
			int value = entry.getValue();
			jsonObject.put(key, value);
		}
		return jsonObject;
	}
	
	/**
	 * 소분류를 선택하면 범위에 해당하는 상점 이름과 주소 리턴
	 * @param xy(String 위도,경도)
	 * @param range(String m)
	 * @param middle(중분류)
	 * @param small(소분류)
	 * @return Json(상점 이름과 주소)
	 * @throws IOException
	 */
	@Override
	public JSONObject findStoreDetailByCategory(String xy, String range, String middle, String small) throws IOException {
		String cxcy = xy.split(",")[1] + "," + xy.split(",")[0];
		JSONArray itemsArray = jusoapi.findStoreToJson(cxcy, range);
		JSONObject object = new JSONObject();
		JSONArray array = new JSONArray();
		
		String[] latlot = xy.split(",");
		String lat = latlot[0];
		String lot = latlot[1];
		if(Double.parseDouble(latlot[0])>Double.parseDouble(latlot[1])) {
			lat = latlot[1];
			lot = latlot[0];
		}
		
		Point point = new Point(lat, lot, range);

		List<Store> list = storeservice.getAllStoreByXY(point);
		
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getCategory_middle().equals(middle) && list.get(i).getCategory_small().equals(small)) {
				JSONObject data = new JSONObject();
				String value = list.get(i).getStore_name();
				data.put("name", value);
				
				value = "해당하는 위치의 주소가 없습니다";
				try {
					value = Coord2Addr(list.get(i).getLat(), list.get(i).getLot());
				} catch (Exception e) {
					e.printStackTrace();
				}
				data.put("addr", value);
				array.add(data);
			}
		}
		
		if(small.equals("전체")) { //소분류 전체를 보여줄때
			for (int i = 0; i < itemsArray.size(); i++) {
				JSONObject items = (JSONObject) itemsArray.get(i);
				String indsMclsNm = (String) items.get("indsMclsNm"); // 중분류
				
//				rdnmAdr : 주소, bizesNm : 이름
				if(indsMclsNm.equals(middle)) {	// 중분류 전체
					JSONObject data = new JSONObject();
					String key = "name";
					String value = (String) items.get("bizesNm");
					data.put(key, value);
					key = "addr";
					value = (String) items.get("rdnmAdr");
					data.put(key, value);
					array.add(data);
				}
			}
		}else {
			for (int i = 0; i < itemsArray.size(); i++) {
				JSONObject items = (JSONObject) itemsArray.get(i);
				String indsSclsNm = (String) items.get("indsSclsNm"); // 소분류
				
//				rdnmAdr : 주소, bizesNm : 이름
				if(indsSclsNm.equals(small)) {	// 중분류 전체
					JSONObject data = new JSONObject();
					String key = "name";
					String value = (String) items.get("bizesNm");
					data.put(key, value);
					key = "addr";
					value = (String) items.get("rdnmAdr");
					data.put(key, value);
					array.add(data);
				}
			}
		}
		object.put("detail", array);
		return object;
	}

	@Override
	public String Coord2Addr(String x, String y) throws Exception {
		JSONParser jsonParse = new JSONParser();
		JSONObject jsonObj = (JSONObject) jsonParse.parse(kakaoapi.kakaoCoord2regioncode(x, y));
		JSONArray itemsArray = (JSONArray) jsonObj.get("documents");
		JSONObject document = (JSONObject) itemsArray.get(0);
		JSONObject address = (JSONObject) document.get("address");
		String address_name = (String) address.get("address_name");
		return address_name;
	}
}

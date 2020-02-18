package com.bizbox.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;

public interface JusoService {
	String getAddressByName(String name) throws IOException;
	List<String> getAddressSetByName(String name) throws IOException;
	List<String> getDongSetByName(String name) throws IOException;
	JSONObject findAllStore(String xy, String radius) throws IOException;
	HashMap<String, Integer> findStoreToSpring(String xy, String radius) throws IOException;
	JSONObject findAllStoreByLarge(String xy, String radius) throws IOException;
	JSONObject findAllStoreByLargeJson(String xy, String radius) throws IOException;
	JSONObject findStoreDetailByCategory(String xy, String range, String middle, String small) throws IOException;
	String Coord2Addr(String x, String y) throws Exception;
}

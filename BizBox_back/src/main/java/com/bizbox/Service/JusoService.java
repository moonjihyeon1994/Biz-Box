package com.bizbox.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONObject;

public interface JusoService {
	public String getAddressByName(String name) throws IOException;
	public List<String> getAddressSetByName(String name) throws IOException;
	public List<String> getDongSetByName(String name) throws IOException;
	public JSONObject findAllStore(String xy, String radius) throws IOException;
	public HashMap<String, Integer> findStoreToSpring(String xy, String radius) throws IOException;
	public JSONObject findAllStoreByLarge(String xy, String radius) throws IOException;
	public JSONObject findStoreDetailByCategory(String xy, String range, String middle, String small) throws IOException;
}

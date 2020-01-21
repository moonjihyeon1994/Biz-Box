package com.bizbox.controller;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bizbox.apis.JusoApi;
import com.google.gson.JsonParser;
import com.jhlabs.map.proj.Projection;
import com.jhlabs.map.proj.ProjectionFactory;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;

@RestController
@Slf4j
@CrossOrigin({ "*" })
public class StoreCountController {

	JusoApi api = new JusoApi();
	
	@GetMapping("/storecount")
	public String countasdfasd() throws IOException {
		String num = api.getAddressByName("경인로248-14");
		String xy = api.getAddressByXY(num);
		JSONObject total = api.findAllStore(xy, "500");
		System.out.println(1);
		return total.toString();
	}
	
}

package com.bizbox.apis;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.extern.slf4j.Slf4j;

@Service
@CrossOrigin({ "*" })
@Slf4j
public class KakaoApi {
	/**
	 * front에서 받아오는 refresh_token으로 access_token발급받기
	 * @param refresh_token
	 * @return access_token
	 */
	public String getAccessToken(String refresh_token) {
		String access_Token = "";
		String reqURL = "https://kauth.kakao.com/oauth/token";

		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=refresh_token");
			sb.append("&client_id=");
			sb.append(client_id);
			sb.append("&refresh_token=" + refresh_token);
			bw.write(sb.toString());
			bw.flush();

			int responseCode = conn.getResponseCode();
			log.info("카카오 refeshtoken 발급 : {}", responseCode);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}

			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);

			access_Token = element.getAsJsonObject().get("access_token").getAsString();
			log.info("카카오 access토큰 : {}", access_Token);

			br.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
			log.error("카카오 토큰 받기 실패 : {}", e);
		}
		
		return access_Token;
	}
	
	/**
	 * access_token으로 유저정보 얻기
	 * @param access_Token
	 * @return 유저정보
	 */
	public HashMap<String, Object> getUserInfo(String access_Token) {
		HashMap<String, Object> userInfo = new HashMap<>();
		String reqURL = "https://kapi.kakao.com/v2/user/me";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");

			conn.setRequestProperty("Authorization", "Bearer " + access_Token);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}

			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);

			JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
			JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

			String nickname = properties.getAsJsonObject().get("nickname").getAsString();
			String email = kakao_account.getAsJsonObject().get("email").getAsString();

			userInfo.put("nickname", nickname);
			userInfo.put("email", email);

		} catch (IOException e) {
			e.printStackTrace();
			log.error("카카오 유저정보 얻기 실패 : {}", e);
		}

		return userInfo;
	}
	
	/**
	 * 카카오 로그아웃
	 * @param access_Token
	 */
	public void kakaoLogout(String access_Token) {
		String reqURL = "https://kapi.kakao.com/v1/user/logout";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "Bearer " + access_Token);

			int responseCode = conn.getResponseCode();

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String result = "";
			String line = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 카카오 좌표로 지도얻기
	 * @param access_Token
	 */
	public String kakaoCoord2regioncode(String x, String y) {
		String result = "";
		String reqURL = "https://dapi.kakao.com/v2/local/geo/coord2address.json?"
				+"x=" + y
				+"&y=" + x
				+"&input_coord=WGS84";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Authorization", "KakaoAK " + client_id);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			
			String line = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}

package com.bizbox.Service;

import java.io.IOException;
import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizbox.apis.JusoApi;
import com.bizbox.dao.PredictionDAO;
import com.bizbox.vo.PopulationByLocation;
import com.bizbox.vo.Prediction;
@Service
public class PredictionServiceImpl implements PredictionService {

	@Autowired
	PredictionDAO dao;

	@Autowired
	JusoApi api;

	@Override
	public JSONObject findAllPrediction(String cx, String cy) throws Exception {
		JSONObject jsondata = api.findBusiness(cx, cy);
		String address = (String) jsondata.get("mainTrarNm");

		System.out.println(address);
		
		JSONObject data = new JSONObject();
		
		List<Prediction> predict_2016 = dao.find2016Prediction(address);
		List<Prediction> predict_2017 = dao.find2017Prediction(address);
		List<Prediction> predict_2018 = dao.find2018Prediction(address);

		data.put("2016", predict_2016);
		data.put("2017", predict_2017);
		data.put("2018", predict_2018);

		return data;
	}

	@Override
	public JSONObject findBusinessAnalysis(String cx, String cy, String business) throws IOException {
		JSONObject jsondata = api.findBusiness(cx, cy);
		String address = (String) jsondata.get("mainTrarNm");// 현재 좌표에서 가장 가까운 상권의 이름입니다.
		
		JSONObject data = new JSONObject();
		
		int[] population_age = new int[6];
		//해당 상권이름으로 가장 높은 유동인구 가져옵니다.
//		PopulationByLocation pbl =dao.getpopulation(address);
//		population_age[0]=Integer.parseInt(pbl.getJ());
//		population_age[1]=Integer.parseInt(pbl.getK());
//		population_age[2]=Integer.parseInt(pbl.getL());
//		population_age[3]=Integer.parseInt(pbl.getM());
//		population_age[4]=Integer.parseInt(pbl.getN());
//		population_age[5]=Integer.parseInt(pbl.getO());
//		int MaxAge=0;
//		for (int i = 0; i < population_age.length-1; i++) {
//			if(population_age[i]<population_age[i+1]) {
//				MaxAge=i+2;// 유동인구가 가장 큰 나이대
//			}
//		}
		//경쟁사 수
		//해당 상권의 매출점수를 증감률을 가져옵니다
		//세일즈 인포에서 점포수 증감률 가져와서 생존률 계산
		List<Prediction> prediclist =dao.predict_business(address,business);
		//data.put("maxpopulation", MaxAge);
		data.put("prediclist", prediclist);
		return data;
	}

}

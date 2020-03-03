package com.bizbox.Service;

import java.io.IOException;
import java.sql.SQLException;
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
		String[] addresslist = address.split(" ");
		if (addresslist[0].contains("서울")) {
			address = "";
			for (int i = 1; i < addresslist.length; i++) {
				if (i == addresslist.length - 1) {
					address += addresslist[i];
				} else {
					address += addresslist[i] + " ";
				}
			}
		}

		JSONObject data = new JSONObject();
//		String predoroname = jusoService.getAddressByName(RemovedAddress).split(",")[11];// 도로명
		List<Prediction> predict_2016 = dao.find2016Prediction(address);
		List<Prediction> predict_2017 = dao.find2017Prediction(address);
		List<Prediction> predict_2018 = dao.find2018Prediction(address);
		if(predict_2016.size()==0) {System.out.println("NoData");}
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

		List<Prediction> prediclist = dao.predict_business(address, business);
		data.put("prediclist", prediclist);
		return data;
	}

	@Override
	public JSONObject findAllPrediction2(String place) throws SQLException {
		JSONObject data = new JSONObject();

		List<Prediction> predict_2016 = dao.find2016Prediction(place);
		List<Prediction> predict_2017 = dao.find2017Prediction(place);
		List<Prediction> predict_2018 = dao.find2018Prediction(place);
		data.put("2016", predict_2016);
		data.put("2017", predict_2017);
		data.put("2018", predict_2018);
		return data;
	}

	@Override
	public JSONObject findBusinessAnalysis2(String place, String business) {
		JSONObject data = new JSONObject();

		int[] population_age = new int[6];

		List<Prediction> prediclist = dao.predict_business(place, business);
		data.put("prediclist", prediclist);
		return data;

	}

}

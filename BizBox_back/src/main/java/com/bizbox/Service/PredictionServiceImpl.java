package com.bizbox.Service;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizbox.apis.JusoApi;
import com.bizbox.dao.PredictionDAO;
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

}

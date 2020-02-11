package com.bizbox.Service;

import java.sql.SQLException;

import org.json.simple.JSONObject;

public interface PredictionService {
	JSONObject findAllPrediction(String cx, String cy) throws Exception;
}

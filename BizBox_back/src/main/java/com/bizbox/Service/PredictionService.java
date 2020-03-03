package com.bizbox.Service;

import java.io.IOException;
import java.sql.SQLException;

import org.json.simple.JSONObject;

public interface PredictionService {
	JSONObject findAllPrediction(String cx, String cy) throws Exception;
	JSONObject findBusinessAnalysis(String cx, String cy, String business) throws IOException;
	JSONObject findAllPrediction2(String place) throws SQLException;
	JSONObject findBusinessAnalysis2(String place, String business);
}

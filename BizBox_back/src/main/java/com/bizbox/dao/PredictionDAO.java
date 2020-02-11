package com.bizbox.dao;

import java.sql.SQLException;
import java.util.List;

import com.bizbox.vo.Prediction;

public interface PredictionDAO {
	List<Prediction> find2018Prediction(String address) throws SQLException;
	List<Prediction> find2017Prediction(String address) throws SQLException;
	List<Prediction> find2016Prediction(String address) throws SQLException;
}

package com.bizbox.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bizbox.vo.PopulationByLocation;
import com.bizbox.vo.Prediction;
@Repository
public class PredictionDAOImpl implements PredictionDAO {
	
	private final String namespace ="com.bizbox.mapper.predictionMapper.";
	
	@Autowired
	SqlSession session;

	@Override
	public List<Prediction> find2018Prediction(String address) throws SQLException {
		return session.selectList(namespace+"find2018Prediction", address);
	}

	@Override
	public List<Prediction> find2017Prediction(String address) throws SQLException {
		return session.selectList(namespace+"find2017Prediction", address);
	}

	@Override
	public List<Prediction> find2016Prediction(String address) throws SQLException {
		return session.selectList(namespace+"find2016Prediction", address);
	}

	@Override
	public List<Prediction> predict_business(String address ,String business) {
		List<Prediction> list = new LinkedList<Prediction>();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("address", address);
		map.put("business", business);
		
		Prediction Prediction_2016 = session.selectOne(namespace + "predict_business_2016",map);
		Prediction Prediction_2017 = session.selectOne(namespace + "predict_business_2017",map);
		Prediction Prediction_2018 = session.selectOne(namespace + "predict_business_2018",map);
		list.add(Prediction_2016);list.add(Prediction_2017);list.add(Prediction_2018);
		return list;
	}

	@Override
	public PopulationByLocation getpopulation(String address) {
		return  session.selectOne(namespace + "getpopulation",address);
	}

}

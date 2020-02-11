package com.bizbox.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

}

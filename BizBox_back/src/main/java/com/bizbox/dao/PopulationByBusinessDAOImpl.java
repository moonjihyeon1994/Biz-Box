package com.bizbox.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bizbox.vo.PopulationByBusiness;
@Repository
public class PopulationByBusinessDAOImpl implements PopulationByBusinessDAO {
	
	private final String namespace ="com.bizbox.mapper.populationByBusinessMapper.";
	
	@Autowired
	SqlSession session;
	
	@Override
	public List<PopulationByBusiness> getPopulation(String trdar_cd) throws SQLException {
		return session.selectList(namespace+"getPopulation", trdar_cd);
	}

}

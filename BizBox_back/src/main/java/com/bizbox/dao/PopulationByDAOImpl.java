package com.bizbox.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bizbox.vo.PopulationByDong;
import com.bizbox.vo.PopulationByLocation;
import com.bizbox.vo.PopulationByTime;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class PopulationByDAOImpl implements PopulationByDAO {

	private final String name ="com.bizbox.mapper.populationByMapper.";
	
	@Autowired
	SqlSession session;

	@Override
	public PopulationByTime populationByTime(String address) throws Exception{
		return session.selectOne(name+"selectByTimeAdress",address);
	}
	@Override
	public PopulationByLocation populationByLocation(String address) throws Exception {
		return session.selectOne(name+"populationByLocation",address);
	}
	@Override
	public List<PopulationByDong> populationByDong(String address) throws Exception {
		return session.selectList(name+"populationByDong", address);
	}
}

package com.bizbox.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bizbox.vo.PopulationByDong;
import com.bizbox.vo.PopulationByLocation;
import com.bizbox.vo.PopulationByTime;
import com.bizbox.vo.PopulationBytimeByDongCode;

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
	@Override
	public String getDongCodeList(String predoroname) {
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("predoroname", predoroname);
		return session.selectOne(name+"getDongCodeList",predoroname);
	}
	@Override
	public PopulationByLocation getByDongCode(String dongcode) {
		// TODO Auto-generated method stub
		return session.selectOne(name+"getByDongCode",dongcode);
	}
	@Override
	public List<PopulationBytimeByDongCode> populationByTimeByDongCode(String dongcode) {
		// TODO Auto-generated method stub
		return session.selectList(name+"getTimeByDongCode",dongcode);
	}
}

package com.bizbox.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bizbox.vo.PopulationByTime;

import lombok.extern.slf4j.Slf4j;

@Slf4j

@Repository
public class populationBtDAOImpl implements populationBtDAO {

	private final String name ="com.bizbox.mapper.populationByTimeMapper.";
	
	@Autowired
	SqlSession session;

	@Override
	public PopulationByTime findAllpopulation(String address) {
		
		System.out.println("asdfasdf");
		PopulationByTime pop =  session.selectOne(name+"selectByAdress",address);
		System.out.println(pop.getF());
		return session.selectOne(name+"selectByAdress",address);
	}
}

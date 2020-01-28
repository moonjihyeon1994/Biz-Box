package com.bizbox.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizbox.dao.PopulationByDAO;
import com.bizbox.vo.PopulationByDong;
import com.bizbox.vo.PopulationByLocation;
import com.bizbox.vo.PopulationByTime;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PopulationByServiceImpl implements PopulationByService{

	@Autowired
	PopulationByDAO dao;
	
	@Override
	public PopulationByTime populationByTime(String address) throws Exception {
		log.trace("address",address);
		return dao.populationByTime(address);
	}

	@Override
	public PopulationByLocation populationByLocation(String address)  throws Exception{
		log.trace("address",address);
		return dao.populationByLocation(address);
	}

	@Override
	public List<PopulationByDong> populationByDong(String address) throws Exception {
		log.trace("address",address);
		return dao.populationByDong(address);
	}

	@Override
	public List<String> getDongCodeList(String predoroname) {
		// TODO Auto-generated method stub
		return dao.getDongCodeList(predoroname);
	}

}

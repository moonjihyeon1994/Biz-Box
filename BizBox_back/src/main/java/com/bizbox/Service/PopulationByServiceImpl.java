package com.bizbox.Service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizbox.dao.PopulationByBusinessDAO;
import com.bizbox.dao.PopulationByDAO;
import com.bizbox.vo.PopulationByBusiness;
import com.bizbox.vo.PopulationByDong;
import com.bizbox.vo.PopulationByLocation;
import com.bizbox.vo.PopulationByTime;
import com.bizbox.vo.PopulationBytimeByDongCode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PopulationByServiceImpl implements PopulationByService{

	@Autowired
	PopulationByDAO dao;
	
	@Autowired
	PopulationByBusinessDAO bzdao;
	
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
	public String getDongCodeList(String predoroname) {
		log.trace("address",predoroname);
		return dao.getDongCodeList(predoroname);
	}

	@Override
	public PopulationByLocation getByDongCode(String dongcode) {
		return dao.getByDongCode(dongcode);
	}

	@Override
	public List<PopulationBytimeByDongCode> getTimeByDongCode(String dongcode) {
		return dao.populationByTimeByDongCode(dongcode);
	}

	@Override
	public List<PopulationByBusiness> getPopulation(String trdar_cd) {
		List<PopulationByBusiness> list = null;		
		try {
			list = bzdao.getPopulation(trdar_cd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}

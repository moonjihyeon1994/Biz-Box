package com.bizbox.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizbox.dao.populationBtDAO;
import com.bizbox.vo.PopulationByTime;

import lombok.extern.slf4j.Slf4j;
@Slf4j

@Service
public class populationByTimeServiceImpl implements populationByTimeService{

	@Autowired
	populationBtDAO dao;
	
	@Override
	public PopulationByTime Allpopulation(String address) {
			log.trace("address",address);
		return dao.findAllpopulation(address);
	}

}

package com.bizbox.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizbox.dao.PopulationByDAO;
import com.bizbox.dao.SalesDAO;
import com.bizbox.vo.PopulationByDong;
import com.bizbox.vo.PopulationByLocation;
import com.bizbox.vo.PopulationByTime;
import com.bizbox.vo.SalesInformation;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SalesServiceImpl implements SalesService{

	@Autowired
	SalesDAO dao;

	@Override
	public List<SalesInformation> salesInfo(String address) throws Exception {
		return dao.salesInfo(address);
	}
}

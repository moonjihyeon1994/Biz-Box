package com.bizbox.dao;

import java.util.List;

import com.bizbox.vo.PopulationByDong;
import com.bizbox.vo.PopulationByLocation;
import com.bizbox.vo.PopulationByTime;

public interface PopulationByDAO {
	
	PopulationByTime populationByTime(String address) throws Exception;
	PopulationByLocation populationByLocation(String address) throws Exception;
	List<PopulationByDong> populationByDong(String address) throws Exception;
	List<String> getDongCodeList(String predoroname);
}

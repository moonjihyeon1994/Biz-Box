package com.bizbox.dao;

import com.bizbox.vo.PopulationByLocation;
import com.bizbox.vo.PopulationByTime;

public interface PopulationByDAO {
	
	PopulationByTime populationByTime(String address) throws Exception;
	PopulationByLocation populationByLocation(String address) throws Exception;
	
}

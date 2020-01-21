package com.bizbox.Service;

import com.bizbox.vo.PopulationByLocation;
import com.bizbox.vo.PopulationByTime;

public interface PopulationByService {

	public PopulationByTime populationByTime(String address) throws Exception;
	public PopulationByLocation populationByLocation(String address) throws Exception;
}

package com.bizbox.Service;

import java.util.List;

import com.bizbox.vo.PopulationByBusiness;
import com.bizbox.vo.PopulationByDong;
import com.bizbox.vo.PopulationByLocation;
import com.bizbox.vo.PopulationByTime;
import com.bizbox.vo.PopulationBytimeByDongCode;

public interface PopulationByService {

	public PopulationByTime populationByTime(String address) throws Exception;
	public PopulationByLocation populationByLocation(String address) throws Exception;
	public List<PopulationByDong> populationByDong(String address) throws Exception;
	public String getDongCodeList(String predoroname);
	public PopulationByLocation getByDongCode(String dongcode);
	public List<PopulationBytimeByDongCode> getTimeByDongCode(String dongcode);
	List<PopulationByBusiness> getPopulation(String trdar_cd);
}

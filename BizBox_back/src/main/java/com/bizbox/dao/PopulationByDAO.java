package com.bizbox.dao;

import java.util.List;

import com.bizbox.vo.PopulationByDong;
import com.bizbox.vo.PopulationByLocation;
import com.bizbox.vo.PopulationByTime;
import com.bizbox.vo.PopulationBytimeByDongCode;

public interface PopulationByDAO {
	PopulationByTime populationByTime(String address) throws Exception;
	PopulationByLocation populationByLocation(String address) throws Exception;
	List<PopulationByDong> populationByDong(String address) throws Exception;
	String getDongCodeList(String predoroname);
	PopulationByLocation getByDongCode(String dongcode);
	List<PopulationBytimeByDongCode> populationByTimeByDongCode(String dongcode);
}

package com.bizbox.dao;

import java.sql.SQLException;
import java.util.List;

import com.bizbox.vo.PopulationByBusiness;

public interface PopulationByBusinessDAO {
	List<PopulationByBusiness> getPopulation(String trdar_cd) throws SQLException;
}

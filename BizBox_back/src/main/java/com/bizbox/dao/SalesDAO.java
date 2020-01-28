package com.bizbox.dao;

import java.util.List;

import com.bizbox.vo.SalesInformation;

public interface SalesDAO {
	
	List<SalesInformation> salesInfo(String address) throws Exception;
}

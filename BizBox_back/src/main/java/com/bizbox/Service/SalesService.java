package com.bizbox.Service;

import java.util.List;

import com.bizbox.vo.SalesInformation;

public interface SalesService {

	public List<SalesInformation> salesInfo(String address) throws Exception;
}

package com.bizbox.Service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizbox.dao.OpenCloseDAO;
import com.bizbox.vo.AddressCategory;
import com.bizbox.vo.OpenClose;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OpenCloseSeviceImpl implements OpenCloseService{
	
	@Autowired
	OpenCloseDAO dao;
	
	@Override
	public OpenClose getOpenClose(AddressCategory ac) throws SQLException {
		log.trace("OpenCloseRate : ", ac);
		return dao.getOpenClose(ac);
	}

	@Override
	public int insertOpenCloseData(OpenClose oc) throws SQLException {
		return dao.insertOpenCloseData(oc);
	}

}

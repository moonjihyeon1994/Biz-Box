package com.bizbox.dao;

import java.sql.SQLException;

import com.bizbox.vo.AddressCategory;
import com.bizbox.vo.OpenClose;

public interface OpenCloseDAO {
	OpenClose getOpenClose(AddressCategory ac) throws SQLException;
	int insertOpenCloseData(OpenClose oc) throws SQLException;
}

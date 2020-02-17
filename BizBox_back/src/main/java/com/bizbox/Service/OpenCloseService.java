package com.bizbox.Service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.bizbox.vo.AddressCategory;
import com.bizbox.vo.OpenClose;

@Service
public interface OpenCloseService {
	public OpenClose getOpenClose(AddressCategory ac) throws SQLException;
	public int insertOpenCloseData(OpenClose oc) throws SQLException;
}

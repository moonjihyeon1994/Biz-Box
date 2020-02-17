package com.bizbox.dao;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bizbox.vo.AddressCategory;
import com.bizbox.vo.OpenClose;

@Repository
public class OpenCloseDAOImpl implements OpenCloseDAO{
	
private final String namespace ="com.bizbox.mapper.OpenCloseMapper.";
	
	@Autowired
	SqlSession session;
	
	@Override
	public OpenClose getOpenClose(AddressCategory ac) throws SQLException {
		return session.selectOne(namespace+"getOpenClose", ac);
	}

	@Override
	public int insertOpenCloseData(OpenClose oc) throws SQLException {
		return session.insert(namespace+"putOpenCloseData", oc);
	}
	
}

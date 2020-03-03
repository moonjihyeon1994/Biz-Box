package com.bizbox.dao;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

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
		List<OpenClose> list = new LinkedList<>();
		list = session.selectList(namespace+"getOpenClose", ac);
		System.out.println(list.size());
		return list.get(0);
	}

	@Override
	public int insertOpenCloseData(OpenClose oc) throws SQLException {
		return session.insert(namespace+"putOpenCloseData", oc);
	}
	
}

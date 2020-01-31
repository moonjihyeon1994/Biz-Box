package com.bizbox.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bizbox.vo.SalesInformation;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class SalesDAOImpl implements SalesDAO {

	private final String name ="com.bizbox.mapper.salesMapper.";
	
	@Autowired
	SqlSession session;
	
	@Override
	public List<SalesInformation> salesInfo(String address) throws Exception {
		return session.selectList(name+"salesInfo", address);
	}

}

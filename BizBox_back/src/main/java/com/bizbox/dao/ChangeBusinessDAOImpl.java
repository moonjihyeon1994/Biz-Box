package com.bizbox.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bizbox.vo.Changebusiness;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class ChangeBusinessDAOImpl implements ChangeBusinessDAO {

private final String name ="com.bizbox.mapper.changebusinessMapper.";
	
	@Autowired
	SqlSession session;
	
	
	@Override
	public List<Changebusiness> getChangeHistory(String dongcode) {
		
		return session.selectList("findByDong", dongcode);
	}

}

package com.bizbox.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bizbox.vo.Password;

@Repository
public class SaltFindDAOImpl implements SaltFindDAO {
	
	private final String name ="com.bizbox.mapper.passMapper.";
	
	@Autowired
	SqlSession session;
	
	@Override
	public int insertSalt(Password pass) throws Exception {
		return session.insert(name+"insertSalt", pass);
	}

	@Override
	public String findSalt(String email) throws Exception {
		return session.selectOne(name+"findSalt", email);
	}

}

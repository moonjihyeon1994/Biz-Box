package com.bizbox.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bizbox.vo.User;

import lombok.extern.slf4j.Slf4j;
@Repository
@Slf4j 
public class UserServiceDAOImpl implements UserServiceDAO {

	private final String name ="com.bizbox.mapper.userServiceMapper.";
	
	@Autowired
	SqlSession session;
	
	@Override
	public int singupUser(User user) {
		return session.insert(name+"singupUser", user);
	}

	@Override
	public User loginUser(User user) {
		return null;
	}
}

package com.bizbox.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bizbox.vo.User;

@Repository
public class UserServiceDAOImpl implements UserServiceDAO {

	private final String name ="com.bizbox.mapper.userServiceMapper.";
	
	@Autowired
	SqlSession session;
	
	@Override
	public int singupUser(User user) throws Exception{
		return session.insert(name+"singupUser", user);
	}

	@Override
	public String getPw(String email) throws Exception{
		return session.selectOne(name+"getPw", email);
	}

	@Override
	public User login(User user) throws Exception {
		return session.selectOne(name+"login",user);
	}
}

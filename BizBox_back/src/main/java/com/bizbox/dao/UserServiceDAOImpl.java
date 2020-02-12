package com.bizbox.dao;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bizbox.vo.User;

@Repository
public class UserServiceDAOImpl implements UserServiceDAO {

	private final String namespace ="com.bizbox.mapper.userServiceMapper.";
	
	@Autowired
	SqlSession session;
	
	@Override
	public int singupUser(User user) throws SQLException{
		return session.insert(namespace+"singupUser", user);
	}

	@Override
	public String getPw(String email) throws SQLException{
		return session.selectOne(namespace+"getPw", email);
	}

	@Override
	public User login(User user) throws SQLException {
		return session.selectOne(namespace+"login",user);
	}
}

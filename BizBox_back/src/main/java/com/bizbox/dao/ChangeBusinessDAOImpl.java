package com.bizbox.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bizbox.vo.Changebusiness;
import com.bizbox.vo.color;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class ChangeBusinessDAOImpl implements ChangeBusinessDAO {

private final String name ="com.bizbox.mapper.changebusinessMapper.";
	
	@Autowired
	SqlSession session;
	
	
	@Override
	public List<Changebusiness> getChangeHistory(String dongcode) {
		
		return session.selectList(name+"findByDong", dongcode);
	}


	@Override
	public void makenewtable(String color, String nname) {
		System.out.println(color);
		System.out.println(nname);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("color", color);
		map.put("name", nname);
		session.selectList(name+"insert", map);
	}


	@Override
	public List<color> getColor() {
		// TODO Auto-generated method stub
		return session.selectList(name+"getcolor");
	}

}

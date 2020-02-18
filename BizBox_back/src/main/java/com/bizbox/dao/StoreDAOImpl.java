package com.bizbox.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bizbox.vo.Point;
import com.bizbox.vo.Store;
@Repository
public class StoreDAOImpl implements StoreDAO {
	
	private final String namespace ="com.bizbox.mapper.storeMapper.";
	
	@Autowired
	SqlSession session;
	
	@Override
	public int insertStore(Store store) {
		return session.insert(namespace+"insertStore", store);
	}

	@Override
	public int closeStore(Store store) throws SQLException {
		return session.update(namespace+"closeStore", store);
	}

	@Override
	public List<Store> getStore(String email) throws SQLException {
		return session.selectList(namespace+"getStore", email);
	}

	@Override
	public List<Store> getStoreByXY(Point point) throws SQLException {
		return session.selectList(namespace+"getStoreByXY", point);
	}

	@Override
	public List<Store> getAllStore() throws SQLException {
		return session.selectList(namespace+"getAllStore");
	}

	@Override
	public String getDistanceByXY(Point point) throws SQLException {
		return session.selectOne(namespace+"getDistanceByXY", point);
	}
}

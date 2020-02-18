package com.bizbox.dao;

import java.sql.SQLException;
import java.util.List;

import com.bizbox.vo.Point;
import com.bizbox.vo.Store;

public interface StoreDAO {
	int insertStore(Store store) throws SQLException;
	int closeStore(Store store) throws SQLException;
	List<Store> getStore(String email) throws SQLException;
	List<Store> getStoreByXY(Point point) throws SQLException;
	List<Store> getAllStore() throws SQLException;
	String getDistanceByXY(Point point) throws SQLException;
}

package com.bizbox.Service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bizbox.dao.StoreDAO;
import com.bizbox.vo.Point;
import com.bizbox.vo.Store;
@Service
public class StoreServiceImpl implements StoreService {

	@Autowired
	StoreDAO dao;

	@Override
	@Transactional
	public boolean insertStore(Store store) {
		try {
			dao.insertStore(store);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public boolean closeStore(Store store) {
		try {
			dao.closeStore(store);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	@Transactional
	public List<Store> getStore(String email) {
		List<Store> list = null;
		try {
			list = dao.getStore(email);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	@Transactional
	public List<Store> getStoreByXY(Point point) {
		List<Store> list = null;
		try {
			list = dao.getStoreByXY(point);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}

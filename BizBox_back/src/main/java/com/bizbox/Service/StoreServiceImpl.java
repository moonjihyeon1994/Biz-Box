package com.bizbox.Service;

import java.sql.SQLException;
import java.util.ArrayList;
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

	@Override
	public List<Store> getAllStoreByXY(Point point) {
		List<Store> data = new ArrayList<>();
		try {
			List<Store> list = dao.getAllStore();
			for (int i = 0; i < list.size(); i++) {
				Point temp = new Point(point.getLat(), point.getLot(), list.get(i).getLat(), list.get(i).getLot(), point.getDistance());
				int dist = (int)(Double.parseDouble(dao.getDistanceByXY(temp))*1000);
				if(dist < Integer.parseInt(point.getDistance())) {
					data.add(list.get(i));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return data;
	}
}

package com.bizbox.Service;

import java.util.List;

import com.bizbox.vo.Point;
import com.bizbox.vo.Store;

public interface StoreService {
	boolean insertStore(Store store);
	boolean closeStore(Store store);
	List<Store> getStore(String email);
	List<Store> getStoreByXY(Point point);
	List<Store> getAllStoreByXY(Point point);
}
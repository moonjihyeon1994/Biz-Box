package com.bizbox.dao;

import java.util.List;

import com.bizbox.vo.Changebusiness;
import com.bizbox.vo.color;

public interface ChangeBusinessDAO {
	List<Changebusiness>  getChangeHistory(String dongcode);

	void makenewtable(String color, String name);

	List<color> getColor();
}

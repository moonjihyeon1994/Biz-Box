package com.bizbox.dao;

import java.util.List;

import com.bizbox.vo.Changebusiness;

public interface ChangeBusinessDAO {
	List<Changebusiness>  getChangeHistory(String dongcode);
}

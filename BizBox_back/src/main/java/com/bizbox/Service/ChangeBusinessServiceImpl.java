package com.bizbox.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bizbox.dao.ChangeBusinessDAO;
import com.bizbox.vo.Changebusiness;



@Service
public class ChangeBusinessServiceImpl implements ChangeBusinessService {

	@Autowired
	ChangeBusinessDAO dao;
	
	@Override
	public List<Changebusiness> getChangeHistory(String dongcode) {
		
		return dao.getChangeHistory(dongcode);
	}

}

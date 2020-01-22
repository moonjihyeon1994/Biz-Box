package com.bizbox.Service;

import java.util.List;

import com.bizbox.vo.Changebusiness;

public interface ChangeBusinessService {

	List<Changebusiness> getChangeHistory(String dongcode);
	
}

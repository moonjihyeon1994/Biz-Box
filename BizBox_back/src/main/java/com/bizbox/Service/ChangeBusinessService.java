package com.bizbox.Service;

import java.util.List;

import org.json.simple.JSONObject;

import com.bizbox.vo.Changebusiness;

public interface ChangeBusinessService {
	List<Changebusiness> getChangeHistory(String dongcode);
	JSONObject getChangeHistorySub(String dong) throws Exception;
	List<Changebusiness> getChangeHistorySubtolist(String dong) throws Exception;
	JSONObject getChangeHistorySubtoAll();
	JSONObject getColorlist();
	
}

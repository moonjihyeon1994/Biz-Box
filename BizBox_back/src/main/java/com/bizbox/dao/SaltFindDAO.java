package com.bizbox.dao;

import com.bizbox.vo.Password;

public interface SaltFindDAO {
	int insertSalt(Password pass) throws Exception;
	String findSalt(String email) throws Exception;
}

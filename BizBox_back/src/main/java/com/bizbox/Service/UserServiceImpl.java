package com.bizbox.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bizbox.dao.SaltFindDAO;
import com.bizbox.dao.UserServiceDAO;
import com.bizbox.utils.SHA256Util;
import com.bizbox.vo.Password;
import com.bizbox.vo.User;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserServiceDAO userDao;
	
	@Autowired
	SHA256Util shaUtil;
	
	@Autowired
	SaltFindDAO saltDao;
	
	@Override
	@Transactional
	public int singupUser(User user) throws Exception{
		String salt = shaUtil.generateSalt();	//salt생성
		String pw = user.getPw();
		pw = shaUtil.getEncrypt(pw, salt);	//생성된 salt를 pw와 조합해서 암호화
		
		Password pass = new Password(user.getEmail(), salt);
		saltDao.insertSalt(pass);
		user.setPw(pw);
		return userDao.singupUser(user);
	}

	@Override
	@Transactional
	public boolean loginUser(User user) throws Exception{
		String salt = saltDao.findSalt(user.getEmail());
		String targetPw = shaUtil.getEncrypt(user.getPw(), salt);
		String originalPw = userDao.getPw(user.getEmail());
		//비교할 두개의 패스워드
		if(targetPw.equals(originalPw)) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	@Transactional
	public User checkUser(User user) throws Exception {
		return userDao.login(user);
	}

	@Override
	@Transactional
	public void socialSingupUser(User user) throws Exception {
		userDao.singupUser(user);
	}
}

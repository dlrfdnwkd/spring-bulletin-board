package kr.dlrfdnwkd.bulletinboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.dlrfdnwkd.bulletinboard.dao.UserDAO;

@Service
public class UserService {

	private UserDAO userDAO;
	
	@Autowired
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
}

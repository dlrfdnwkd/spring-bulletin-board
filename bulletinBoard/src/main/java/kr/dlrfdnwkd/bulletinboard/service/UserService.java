package kr.dlrfdnwkd.bulletinboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.dlrfdnwkd.bulletinboard.dao.UserDAO;
import kr.dlrfdnwkd.bulletinboard.model.User;

@Service
public class UserService {

	private UserDAO userDAO;
	
	@Autowired
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public User login(String id,String pw) {
		User user = userDAO.login(id);
		if(user == null) 
			return user;
		else {
			if(user.getPw().equals(pw)) 
				return user;
			else {
				user.setPw(null);
				return user;
			}
		}	
	}
	public boolean userIdCheck(String id) {
		return userDAO.userIdCheck(id);
	}
	public int updateUserInfo(User user) {
		return userDAO.updateUserInfo(user);
	}
}

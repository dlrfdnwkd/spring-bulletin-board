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
	public boolean userEmailCheck(String email) {
		return userDAO.userEmailCheck(email);
	}
	public int updateUserInfo(User user) {
		return userDAO.updateUserInfo(user);
	}
	public int deleteUserInfo(User user) {
		return userDAO.deleteUserInfo(user);
	}
	public int signupUser(String email, String id, String pw) {
		return userDAO.insertUserInfo(email, id, pw);
	}
	public int signupUser(String email, String id, String pw, String name) {
		return userDAO.insertUserInfo(email, id, pw, name);
	}
}

package kr.dlrfndwkd.bulletinboard.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.dlrfdnwkd.bulletinboard.model.User;
import kr.dlrfdnwkd.bulletinboard.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	public UserService userService;
	
	@Autowired
	public void setService(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value = "/userIdCheck")
	@ResponseBody
	public String userIdCheck(@RequestParam("userId") String id) {
		if(userService.userIdCheck(id)) {
			return "none";
		}else {
			return "exist";
		}
	}
	@RequestMapping(value = "/updateUserInfo")
	@ResponseBody
	public String updateUserInfo(@RequestParam("userId") String id, @RequestParam("userName") String name,
			@RequestParam("userPw") String pw, HttpSession session) {
		User user = (User)session.getAttribute("user");
		if(id.isEmpty())
			id = user.getId();
		else
			user.setId(id);
		if(name.isEmpty())
			name = user.getName();
		else
			user.setName(name);
		if(pw.isEmpty())
			pw = user.getPw();
		else
			user.setPw(pw);
		if(userService.updateUserInfo(user) == 0) {
			return "fail";
		}
		return "success";
	}
}

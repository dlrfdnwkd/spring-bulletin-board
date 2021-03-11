package kr.dlrfndwkd.bulletinboard.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.dlrfdnwkd.bulletinboard.model.User;
import kr.dlrfdnwkd.bulletinboard.service.UserService;

@Controller
@RequestMapping(value = "/session")
public class SessionController {
	
	public UserService userService;
	
	@Autowired
	public void setService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value="/login", method = RequestMethod.POST)
	@ResponseBody
	public String login(@RequestParam("userId") String id,@RequestParam("userPw") String pw,Model model,HttpSession session) {
		User user = userService.login(id,pw);
		if(user == null) {
			return "none";
		}
		else {
			if(user.getPw() == null) {
				return "fail";
			}else {
				session.setAttribute("user", user);
				session.setAttribute("name",user.getName());
				return "success";
			}
		}
	}
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}

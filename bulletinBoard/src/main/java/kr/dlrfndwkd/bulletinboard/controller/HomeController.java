package kr.dlrfndwkd.bulletinboard.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.dlrfdnwkd.bulletinboard.model.User;
import kr.dlrfdnwkd.bulletinboard.service.UserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private UserService userService;
	
	@Autowired
	public void setService(UserService userService) {
		this.userService = userService;
	}
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	@RequestMapping(value= "/loginPage")
	public String loginPage() {
		return "loginPage";
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
}

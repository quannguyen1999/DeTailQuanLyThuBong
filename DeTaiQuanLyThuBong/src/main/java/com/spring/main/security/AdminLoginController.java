package com.spring.main.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("admin-panel")
public class AdminLoginController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String index() {
		
		return "redirect:/admin-panel/login";
		
	}
	
	@RequestMapping(value="login",method=RequestMethod.GET)
	
	public String login(@RequestParam(value = "error",required = false) String error ,
			@RequestParam(value = "logout",required = false) String logout,
			ModelMap model
			,HttpServletRequest request) {
		
		HttpSession session=request.getSession();
		
		session.invalidate();
		
		if(error!=null) {
			
			model.put("msg", "mật khẩu không chính xác");
			
		}
		return "admin/login";
	}
	
	@RequestMapping(value = "process-logout",method=RequestMethod.GET)
	public String logout(HttpServletRequest reqeust) {
		
		HttpSession session=reqeust.getSession();
		
		session.invalidate();
		
		return "redirect:/admin-panel/login?logout";
		
	}
	
	@RequestMapping(value = "accessDenied",method=RequestMethod.GET)
	public String accessDenied(Authentication authentication,ModelMap model) {
		
		if(authentication!=null) {
			
			model.put("msg", "hi"+authentication.getName()+"you do not have permission");
			
		}
		else {
			
			model.put("msg", "you do not have petmmision");
			
		}
		
		return "admin/accessDenied";
	}
	
	@RequestMapping(value = "welcome",method=RequestMethod.GET)
	public String welcome() {
		
		return "admin/welcome";
		
	}
	
	
	
}

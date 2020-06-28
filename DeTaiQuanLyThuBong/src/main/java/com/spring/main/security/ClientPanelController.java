package com.spring.main.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("client-panel")
public class ClientPanelController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String index() {
		
		return "redirect:/client-panel/login";
		
	}
	
	@RequestMapping(value="login",method=RequestMethod.GET)
	public String login(@RequestParam(value = "error",required = false) String error ,
			@RequestParam(value = "logout",required = false) String logout,
			ModelMap model
			) {
		
		if(error!=null) {
			
			model.put("msg", "mật khẩu không chính xác");
			
		}
		
		return "client/login";
	}
	

	@RequestMapping(value = "logout",method=RequestMethod.GET)
	public String logout(HttpServletRequest reqeust) {
		
		HttpSession session=reqeust.getSession();
		
		session.invalidate();
		
		return "redirect:/client-panel/login?logout";
	}
	
}

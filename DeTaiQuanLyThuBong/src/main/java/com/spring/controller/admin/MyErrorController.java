package com.spring.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyErrorController{
	
	@RequestMapping("/")
	
	public String handleRequest(@CookieValue(value = "username") String userName) {
		
		if(userName.isEmpty()==false) {
			
			return "admin/errorPage/errorPage";
			
		}
		return "error";
	}
	
}

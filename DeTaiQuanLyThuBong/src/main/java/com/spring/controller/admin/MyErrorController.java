package com.spring.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MyErrorController{
	@RequestMapping("/")
	public String handleRequest(@CookieValue(value = "username") String userName) {
		System.out.println(userName);
		System.out.println("abc:"+userName);
		if(userName.isEmpty()==false) {
			return "admin/errorPage/errorPage";
		}
		return "error";
	}
	
}

package com.spring.controller.admin;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.spring.entities.Account;
import com.spring.services.Impl.AccountLmpl;
import com.spring.services.dao.AccountDAO;
@Controller
@RequestMapping("admin")
public class AdminController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String welcome() {
		
		return "admin/welcome";
		
	}
	
	@RequestMapping(value = "suaAccount",method=RequestMethod.GET)
	public String suaAccount() {
		
		return "admin/edit";
		
	}
	
	@RequestMapping(value = "suaAccount",method=RequestMethod.POST)
	public String processSuaAccount(@RequestParam("userName")String userName,@RequestParam("passwordOld") String passwordOld
			,@RequestParam("passwordNew") String passwordNew,ModelMap model) {
		
		AccountDAO accDAO=new AccountLmpl();
		
		Account account=accDAO.timKiemUsername(userName);
		
		boolean checkPasswordOld=true;
		
		boolean checkPasswordNew=true;
		
		if(passwordOld.isEmpty()) {
			
			checkPasswordOld=false;
			
			model.put("errorPasswordOld","password old can't empty" );
		}
		
		if(checkPasswordOld==true) {
			
		}
		
		if(passwordOld.isEmpty()) {
			
			checkPasswordNew=false;
			
			model.put("errorPasswordNew","password new can't empty" );
			
		}
		
		if(checkPasswordNew==true) {
			
		}
		
		if(checkPasswordNew==true && checkPasswordOld==true) {
			
			if(BCrypt.checkpw(passwordOld,account.getPassword())==false) {
				
				model.put("errorPasswordOld", "mật khẩu cũ không đúng");
				
				checkPasswordOld=false;
				
			}
		}
		
		if(checkPasswordNew==false || checkPasswordOld==false) {
			
			return "admin/edit";
			
		}
		
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
		
		String encodedPassword = passwordEncoder.encode(passwordNew);
		
		if(accDAO.capNhapAccount(new Account(userName, encodedPassword))==true){
			
			model.put("errorPasswordOld","Cập nhập thành công" );
		
		};
		
		return "admin/edit";
	}
}

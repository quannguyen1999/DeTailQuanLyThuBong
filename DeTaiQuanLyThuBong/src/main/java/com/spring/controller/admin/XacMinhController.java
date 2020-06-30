package com.spring.controller.admin;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.spring.entities.Account;
import com.spring.services.EmailService;
import com.spring.services.Impl.AccountLmpl;
import com.spring.services.dao.AccountDAO;

@Controller
@RequestMapping("admin2")
public class XacMinhController {
	@Autowired
	private  EmailService emailService;

	@RequestMapping(value = "xacMinh",method=RequestMethod.GET)
	public String xacMinh(HttpServletRequest request) {

		HttpSession session=request.getSession();
		
		int random = (int)(Math.random() * 10000 + 1);
		
		try {
			
			emailService.sendAsync("nguyendanganhquan99@gmail.com",random);
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return "admin/trangThaiGuiEmail";
		}
		
		System.out.println(random);
		
		session.setAttribute("maXM",String.valueOf(random));

		return "admin/xacMinhTaiKhoan";
	}

	@RequestMapping(value = "process-xacMinh",method=RequestMethod.POST)
	public String processXacMinh(@RequestParam("password") String password,HttpServletRequest request,ModelMap model) {
		
		HttpSession session=request.getSession();
		
		String idRandDom=(String) session.getAttribute("maXM");
		
		System.out.println("process-xac minh");
		
		System.out.println(idRandDom);
		
		if(idRandDom.equals(password)) {
			
			session.setAttribute("username", "admin");
			
			return "admin/doiMatKhau";
			
		}else {
			
			model.put("maXMMatKhau", "Mã không hợp lệ");
			
			return "admin/xacMinhTaiKhoan";
		}
	}
	
	@RequestMapping(value = "process-doiMK",method=RequestMethod.POST)
	public String doiMK(@RequestParam("passOLD") String passOLD,@RequestParam("passNEW") String passNEW,HttpServletRequest request,ModelMap model) {
		
		boolean checkPassOLD=true;
		
		boolean checkPassNEW=true;
		
		if(passOLD.isEmpty()==true) {
			
			model.put("errorPassOLD","Bạn chưa nhập mật khẩu");
			
			checkPassOLD=false;
			
		}
		if(passNEW.isEmpty()==true) {
			
			model.put("errorPassNEW","Bạn chưa nhập mật khẩu");
			
			checkPassNEW=false;
			
		}
		if(checkPassNEW==true  && checkPassOLD==true) {
			
			if(passOLD.equals(passNEW)) {
				
				AccountDAO account=new AccountLmpl();
				
				PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
				
				String encodedPassword = passwordEncoder.encode(passNEW);
				
				Account acc=new Account("admin", encodedPassword);
				
				account.capNhapAccount(acc);
				
				return "admin/trangThai";
				
			}
		}
		return "admin/doiMatKhau";
		
	}

	@RequestMapping(value = "trangThai",method=RequestMethod.GET)
	public String trangThai() {
		return "admin/trangThai";
	}


}

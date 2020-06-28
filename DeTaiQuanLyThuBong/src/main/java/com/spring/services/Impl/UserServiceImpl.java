package com.spring.services.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.entities.Account;
import com.spring.services.UserService;
import com.spring.services.dao.AccountDAO;


@Service("userService")
public class UserServiceImpl implements UserService{
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		AccountDAO listACC=new AccountLmpl();
		
		Account acc=listACC.timKiemUsername(username);
		
		if(acc==null) {
			
			throw new UsernameNotFoundException("username not found for"+username);
			
		}
		
		List<GrantedAuthority> granted=new ArrayList<GrantedAuthority>();
		
		granted.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		
		return new org.springframework.security.core.userdetails.User(acc.getUserName(), acc.getPassword(), granted);
	}
	

}

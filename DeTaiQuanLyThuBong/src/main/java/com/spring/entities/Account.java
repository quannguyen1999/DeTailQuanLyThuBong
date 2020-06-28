package com.spring.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "Account")
public class Account implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private String userName;
	
	@JoinColumn(name = "password")
	private String password;
	
	
	public Account(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public Account() {
		super();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Account [userName=" + userName + ", password=" + password + "]";
	}
	
	
}

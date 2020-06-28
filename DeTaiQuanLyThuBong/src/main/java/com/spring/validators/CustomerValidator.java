package com.spring.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.spring.entities.Customers;

public class CustomerValidator implements Validator{
	
	public boolean supports(Class clazz) {
		
		return Customers.class.equals(clazz);
		
	}

	public void validate(Object target, Errors errors) {
		
	}
}

package com.spring.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.spring.entities.Categories;
import com.spring.entities.Customers;
import com.spring.entities.Orders;

public class OrderValidator implements Validator{
	
	public boolean supports(Class clazz) {
		
		return Orders.class.equals(clazz);
		
	}

	public void validate(Object target, Errors errors) {
		
	}
}

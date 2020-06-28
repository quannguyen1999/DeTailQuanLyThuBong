package com.spring.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.spring.entities.Categories;
import com.spring.entities.Customers;
import com.spring.entities.OrderDetails;
import com.spring.entities.Orders;

public class OrderdetailValidator implements Validator{
	
	public boolean supports(Class clazz) {
		
		return OrderDetails.class.equals(clazz);
		
	}

	public void validate(Object target, Errors errors) {
		
	}
}

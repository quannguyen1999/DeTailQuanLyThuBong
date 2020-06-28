package com.spring.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.spring.entities.Categories;
import com.spring.entities.Customers;
import com.spring.entities.Products;
import com.spring.entities.Suppliers;

public class SupplierValidator implements Validator{
	public boolean supports(Class clazz) {
		return Suppliers.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
	}
}

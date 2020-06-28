package com.spring.entities.util;

import com.spring.entities.OrderDetails;
import com.spring.entities.Products;

public class OrderDetailsProduct {
	
	private OrderDetails orderDetails;
	
	private Products products;
	
	public OrderDetailsProduct(OrderDetails orderDetails, Products products) {
		
		super();
		
		this.orderDetails = orderDetails;
		
		this.products = products;
		
	}
	public OrderDetailsProduct() {
		
		super();
		
	}
	public OrderDetails getOrderDetails() {
		
		return orderDetails;
		
	}
	public void setOrderDetails(OrderDetails orderDetails) {
		
		this.orderDetails = orderDetails;
		
	}
	public Products getProducts() {
		
		return products;
		
	}
	public void setProducts(Products products) {
		
		this.products = products;
		
	}
	@Override
	public String toString() {
		
		return "OrderDetailsProduct [orderDetails=" + orderDetails + ", products=" + products + "]";
		
	}
	
}

package com.spring.entities.util;



import javax.validation.Valid;

import com.spring.entities.OrderDetails;
import com.spring.entities.Orders;

public class TwoForm {
	
	@Valid
	private Orders orders;
	
	
	@Valid
	private OrderDetails orderDetails;
	

	public TwoForm(Orders orders, OrderDetails orderDetails) {
		
		super();
		
		this.orders = orders;
		
		this.orderDetails = orderDetails;
	}

	public TwoForm() {
		super();
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public OrderDetails getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(OrderDetails orderDetails) {
		this.orderDetails = orderDetails;
	}
	
	
}

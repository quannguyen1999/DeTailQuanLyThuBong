package com.spring.entities.util;

import java.util.List;

import com.spring.entities.Orders;

public class LichSuOrder {
	
	private Orders orders;
	
	private List<OrderDetailsProduct> OrderDetailsProduct;
	
	private int tongChiPhi;
	
	public LichSuOrder(Orders orders, List<com.spring.entities.util.OrderDetailsProduct> orderDetailsProduct, int tongChiPhi) {
		
		super();
		
		this.orders = orders;
		
		OrderDetailsProduct = orderDetailsProduct;
		
		this.tongChiPhi = tongChiPhi;
		
	}
	public LichSuOrder() {
		
		super();
		
	}
	public Orders getOrders() {
		
		return orders;
		
	}
	public void setOrders(Orders orders) {
		
		this.orders = orders;
		
	}
	public List<OrderDetailsProduct> getOrderDetailsProduct() {
		
		return OrderDetailsProduct;
		
	}
	public void setOrderDetailsProduct(List<OrderDetailsProduct> orderDetailsProduct) {
		
		OrderDetailsProduct = orderDetailsProduct;
		
	}
	public int getTongChiPhi() {
		
		return tongChiPhi;
		
	}
	public void setTongChiPhi(int tongChiPhi) {
		
		this.tongChiPhi = tongChiPhi;
		
	}
	@Override
	public String toString() {
		
		return "LichSuOrder [orders=" + orders + ", OrderDetailsProduct=" + OrderDetailsProduct + ", tongChiPhi="
				+ tongChiPhi + "]";
		
	}
	
	
}

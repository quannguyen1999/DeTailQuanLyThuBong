package com.spring.entities.util;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Orders_Products implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "OrderID")
	private String OrderID;
	
	@Column(name = "productID")
	private String productID;

	public Orders_Products(String orderID, String productID) {
		
		super();
		
		OrderID = orderID;
		
		this.productID = productID;
		
	}

	public Orders_Products() {
		
		super();
		
	}
	
	

	@Override
	public int hashCode() {
		
		final int prime = 31;
		
		int result = 1;
		
		result = prime * result + ((OrderID == null) ? 0 : OrderID.hashCode());
		
		result = prime * result + ((productID == null) ? 0 : productID.hashCode());
		
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orders_Products other = (Orders_Products) obj;
		if (OrderID == null) {
			if (other.OrderID != null)
				return false;
		} else if (!OrderID.equals(other.OrderID))
			return false;
		if (productID == null) {
			if (other.productID != null)
				return false;
		} else if (!productID.equals(other.productID))
			return false;
		return true;
	}

	public String getOrderID() {
		return OrderID;
	}

	public void setOrderID(String orderID) {
		OrderID = orderID;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	@Override
	public String toString() {
		return "Orders_Products [OrderID=" + OrderID + ", productID=" + productID + "]";
	}
	
	
	
}

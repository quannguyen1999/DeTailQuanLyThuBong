package com.spring.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Orders")
public class Orders implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message = "mã khách hàng bị lỗi")
	@ManyToOne
	@JoinColumn(name = "CustomerID")
	private Customers CustomerID;
	
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	@JoinColumn
	private Date OrderDate;
	
	@NotNull(message = "Mã hàng chưa nhập")
	@Id
	private String OrderID;
	
	@NotNull(message = "thành phố chưa nhập")
	@JoinColumn
	private String shipCity;
	
	@NotNull(message = "Ngày ship chưa nhập")
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	@JoinColumn
	private Date shippedDate;
	
	@NotNull(message = "Khu vực ship chưa nhập")
	@JoinColumn
	private String ShipRegion;

	public Orders(Customers customerID, Date orderDate, String orderID, String shipCity, Date shippedDate,
			String shipRegion) {
		super();
		CustomerID = customerID;
		OrderDate = orderDate;
		OrderID = orderID;
		this.shipCity = shipCity;
		this.shippedDate = shippedDate;
		ShipRegion = shipRegion;
	}

	public Orders() {
		super();
	}

	public Customers getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(Customers customerID) {
		CustomerID = customerID;
	}

	public Date getOrderDate() {
		return OrderDate;
	}

	public void setOrderDate(Date orderDate) {
		OrderDate = orderDate;
	}

	public String getOrderID() {
		return OrderID;
	}

	public void setOrderID(String orderID) {
		OrderID = orderID;
	}

	public String getShipCity() {
		return shipCity;
	}

	public void setShipCity(String shipCity) {
		this.shipCity = shipCity;
	}

	public Date getShippedDate() {
		return shippedDate;
	}

	public void setShippedDate(Date shippedDate) {
		this.shippedDate = shippedDate;
	}

	public String getShipRegion() {
		return ShipRegion;
	}

	public void setShipRegion(String shipRegion) {
		ShipRegion = shipRegion;
	}

	@Override
	public String toString() {
		return "Orders [CustomerID=" + CustomerID + ", OrderDate=" + OrderDate + ", OrderID=" + OrderID + ", shipCity="
				+ shipCity + ", shippedDate=" + shippedDate + ", ShipRegion=" + ShipRegion + "]";
	}
	

	
	
	
	
	
	
}

package com.spring.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Suppliers")
public class Suppliers implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Id
	private String supplierID;
	
	@NotEmpty(message = "số điện thoại không được bỏ trống")
	@JoinColumn
	private String phone;
	
	@NotEmpty(message = "điện thoại không được bỏ trống")
	@JoinColumn
	private String address;
	
	@NotEmpty(message = "tên công ty không được bỏ trống")
	@JoinColumn
	private String companyName;

	public Suppliers(String supplierID, String phone, String address, String companyName) {
		super();
		this.supplierID = supplierID;
		this.phone = phone;
		this.address = address;
		this.companyName = companyName;
	}

	public Suppliers() {
		super();
	}

	public String getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(String supplierID) {
		this.supplierID = supplierID;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		return "Suppliers [supplierID=" + supplierID + ", phone=" + phone + ", address=" + address + ", companyName="
				+ companyName + "]";
	}
	
	
}

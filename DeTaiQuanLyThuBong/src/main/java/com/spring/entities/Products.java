package com.spring.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "Products")
public class Products implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotEmpty(message = "bạn chưa nhập mã sản phẩm")
	@Id
	private String productID;
	
	@ManyToOne
	@JoinColumn(name = "supplierID")
	private Suppliers supplierID;
	
	@NotNull
	@JoinColumn
	private boolean Discontinuted;
	
	@JoinColumn
	private String moTa;
	
	@NotEmpty(message = "tên sản phẩm không được bỏ trống")
	@JoinColumn
	private String productName;
	
	@NotNull(message = "số lượng không được bỏ trống") 
	@Min(0)
	@JoinColumn
	private int QuatityInStock;
	
	@ManyToOne
	@JoinColumn(name = "categoryID")
	private Categories categoryID;
	
	@NotNull
	@Min(0)
	@JoinColumn
	private double UnitPrrice;
	
	private String picture;

	public Products(@NotEmpty(message = "bạn chưa nhập mã sản phẩm") String productID, Suppliers supplierID,
			@NotNull boolean discontinuted, String moTa, @NotEmpty String productName,
			@NotNull @Min(0) int quatityInStock, Categories categoryID, @NotNull @Min(0) double unitPrrice,
			String picture) {
		super();
		this.productID = productID;
		this.supplierID = supplierID;
		Discontinuted = discontinuted;
		this.moTa = moTa;
		this.productName = productName;
		QuatityInStock = quatityInStock;
		this.categoryID = categoryID;
		UnitPrrice = unitPrrice;
		this.picture = picture;
	}

	public Products() {
		super();
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public Suppliers getSupplierID() {
		return supplierID;
	}

	public void setSupplierID(Suppliers supplierID) {
		this.supplierID = supplierID;
	}

	public boolean isDiscontinuted() {
		return Discontinuted;
	}

	public void setDiscontinuted(boolean discontinuted) {
		Discontinuted = discontinuted;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuatityInStock() {
		return QuatityInStock;
	}

	public void setQuatityInStock(int quatityInStock) {
		QuatityInStock = quatityInStock;
	}

	public Categories getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Categories categoryID) {
		this.categoryID = categoryID;
	}

	public double getUnitPrrice() {
		return UnitPrrice;
	}

	public void setUnitPrrice(double unitPrrice) {
		UnitPrrice = unitPrrice;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return "Products [productID=" + productID + ", supplierID=" + supplierID + ", Discontinuted=" + Discontinuted
				+ ", moTa=" + moTa + ", productName=" + productName + ", QuatityInStock=" + QuatityInStock
				+ ", categoryID=" + categoryID + ", UnitPrrice=" + UnitPrrice + ", picture=" + picture + "]";
	}

	
	
}

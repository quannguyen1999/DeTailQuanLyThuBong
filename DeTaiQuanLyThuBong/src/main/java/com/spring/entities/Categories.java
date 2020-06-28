package com.spring.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Categories")
public class Categories implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Id
	private String categoryID;
	
	@NotEmpty(message = "tên mặt hàng không được bỏ trống")
	@JoinColumn
	private String categoryName;
	
	@NotEmpty(message = "mô tả không được bỏ trống")
	@JoinColumn
	private String Description;
	
	@NotEmpty(message = "hình ảnh không được bỏ trống")
	@JoinColumn
	private String picture;

	public Categories(String categoryID, String categoryName, String description, String picture) {
		super();
		this.categoryID = categoryID;
		this.categoryName = categoryName;
		Description = description;
		this.picture = picture;
	}

	public Categories() {
		super();
	}

	public String getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return "Categories [categoryID=" + categoryID + ", categoryName=" + categoryName + ", Description="
				+ Description + ", picture=" + picture + "]";
	}
	
	
	
}

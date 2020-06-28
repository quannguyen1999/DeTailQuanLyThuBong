package com.spring.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "Customers")
public class Customers implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "địa chỉ không được bỏ trống")
	@JoinColumn
	private String address;

	@NotEmpty(message = "thành phố không được bỏ trống")
	@JoinColumn
	private String city;
	
	@Id
	private String CustomerID;
	
	@NotEmpty(message = "email không được bỏ trống")
	@Email(message = "email không hợp lệ")
	@JoinColumn
	private String email;
	
	@NotEmpty(message = "firstName không được bỏ trống")
	@Length(min = 3,max = 20,message = "chiều dài lastname phải từ 3 - 20")
	@JoinColumn
	private String firstName;
	
	@NotEmpty(message = "lastName không được bỏ trống")
	@Length(min = 3,max = 20,message = "chiều dài lastname phải từ 3 - 20")
	@JoinColumn
	private String lastName;
	
	@OneToOne(targetEntity =Account.class,cascade = CascadeType.ALL)
	@JoinColumn(name = "userName")
	private Account userName;
	
	@NotEmpty(message = "Điện thoại không được bỏ trống")
	@JoinColumn
	private String phone;

	public Customers(@NotEmpty String address, @NotEmpty String city, String customerID, @NotEmpty @Email String email,
			@NotEmpty @Length(min = 3, max = 20) String firstName, @NotEmpty @Length(min = 3, max = 20) String lastName,
			Account userName, @NotEmpty String phone) {
		super();
		this.address = address;
		this.city = city;
		CustomerID = customerID;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.phone = phone;
	}

	public Customers() {
		super();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCustomerID() {
		return CustomerID;
	}

	public void setCustomerID(String customerID) {
		CustomerID = customerID;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Account getUserName() {
		return userName;
	}

	public void setUserName(Account userName) {
		this.userName = userName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Customers [address=" + address + ", city=" + city + ", CustomerID=" + CustomerID + ", email=" + email
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", userName=" + userName + ", phone=" + phone
				+ "]";
	}
	
	


	
	
	
}

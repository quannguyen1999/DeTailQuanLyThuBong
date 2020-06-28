package com.spring.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.jboss.logging.Message;

import com.spring.entities.util.Orders_Products;

@Entity
@Table(name = "OrderDetails")
public class OrderDetails implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	Orders_Products id;
	
	@NotNull(message = "giảm giá không được bỏ trống")
	@JoinColumn
	private float Discount;
	
	@NotNull(message = "số lượng không được bỏ trống")
	@JoinColumn
	private int Quatity;
	
	@NotNull
	@JoinColumn
	private int totalAmmount;

	public OrderDetails(Orders_Products id, float discount, int quatity, int totalAmmount) {
		super();
		this.id = id;
		Discount = discount;
		Quatity = quatity;
		this.totalAmmount = totalAmmount;
	}

	public OrderDetails() {
		super();
	}

	public Orders_Products getId() {
		return id;
	}

	public void setId(Orders_Products id) {
		this.id = id;
	}

	public float getDiscount() {
		return Discount;
	}

	public void setDiscount(float discount) {
		Discount = discount;
	}

	public int getQuatity() {
		return Quatity;
	}

	public void setQuatity(int quatity) {
		Quatity = quatity;
	}

	public int getTotalAmmount() {
		return totalAmmount;
	}

	public void setTotalAmmount(int totalAmmount) {
		this.totalAmmount = totalAmmount;
	}

	@Override
	public String toString() {
		return "OrderDetails [id=" + id + ", Discount=" + Discount + ", Quatity=" + Quatity + ", totalAmmount="
				+ totalAmmount + "]";
	}

	
	
	
	
}

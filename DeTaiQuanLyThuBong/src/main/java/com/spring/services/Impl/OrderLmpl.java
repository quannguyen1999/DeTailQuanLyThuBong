package com.spring.services.Impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.spring.entities.Customers;
import com.spring.entities.OrderDetails;
import com.spring.entities.Orders;
import com.spring.entities.Products;
import com.spring.services.dao.MyEntityManager;
import com.spring.services.dao.OrderDAO;

public class OrderLmpl implements OrderDAO{
	
	private static EntityManager em;
	

	public OrderLmpl(){
		
		em = MyEntityManager.getInstance().getEntityManager();
		
	}
	public boolean themOrder(Orders nv) {
		
		EntityTransaction tr = em.getTransaction();
		
		try {
			
			tr.begin();
			
			em.persist(nv);
			
			tr.commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			tr.rollback();
			
			return false;
			
		}
		return true;
	}
	
	public  boolean themOrderDetails(OrderDetails nv)  {
		
		EntityTransaction tr = em.getTransaction();
		
		try {
			
			tr.begin();
			
			em.persist(nv);
			
			tr.commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			tr.rollback();
			
			return false;
			
		}
		return true;
	}

	
	public boolean xoaOrder(String ma) {
		
		EntityTransaction tr = em.getTransaction();
		
		try {
			
			tr.begin();
			
			em.remove(em.find(Orders.class, ma));
			
			tr.commit();
			
		} catch (Exception e) {
			
			
			e.printStackTrace();
			
			tr.rollback();
			
			return false;
		}
		return true;
	}

	
	public List<Orders> layDanhSachOrders() {
		
		return em.createNativeQuery("{}",Orders.class).getResultList();
		
	}
	
	public Orders timKiemId(String ma) {
		
		Orders acc=null;
		
		try {
			
			acc=(Orders) em.createNativeQuery("{_id:'" + ma + "'}",Orders.class).getSingleResult();;
			
		} catch (Exception e) {
			
			return null;
			
			// TODO: handle exception
		}
		return acc;
	}
	
	
	public List<Orders> timKiemMaKH(String ma) {
		
		// TODO Auto-generated method stub
		return em.createNativeQuery("{CustomerID:'" + ma + "'}",Orders.class).getResultList();
		
	}
	
	public List<OrderDetails> timKiemMaOrderDetails(String ma) {
		
		// TODO Auto-generated method stub
		return em.createNativeQuery("{'_id.OrderID':'" + ma + "'}",OrderDetails.class).getResultList();
		
	}
	
	public boolean xoaOrderDetails(String ma) {
		
		EntityTransaction tr = em.getTransaction();
		
		try {
			
			tr.begin();
			
			em.remove(em.createNativeQuery("{'_id.OrderID':'" + ma + "'}",OrderDetails.class).getSingleResult());
			
			tr.commit();
			
		} catch (Exception e) {
			
			
			e.printStackTrace();
			
			tr.rollback();
			
			return false;
			
		}
		return true;
		
	}
	
	public List<OrderDetails> timKiemTheoProductID(String ma) {
		
		ArrayList<OrderDetails> oddt=new ArrayList<OrderDetails>();
		
		try {
			
			oddt= (ArrayList<OrderDetails>) em.createNativeQuery("{'_id.productID':'" + ma + "'}",OrderDetails.class).getResultList();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return oddt;
	}
	
	public boolean xoaOrderByProductID(String ma) {
		
		EntityTransaction tr = em.getTransaction();
		
		try {
			
			tr.begin();
			
			em.remove(em.createNativeQuery("{'_id.productID':'" + ma + "'}",OrderDetails.class).getSingleResult());
			
			tr.commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			tr.rollback();
			
			return false;
			
		}
		return true;
		
	}
	
	public OrderDetails timKiemOrderDetails(String ma) {
		
		return (OrderDetails) em.createNativeQuery("{'_id.OrderID':'" + ma + "'}",OrderDetails.class).getSingleResult();
		
	}
	
	public boolean capNhapOrder(Orders od) {
		
		EntityTransaction tr = em.getTransaction();
		
		try {
			
			tr.begin();
			
			em.merge(od);
			
			tr.commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			tr.rollback();
			
			return false;
			
		}
		return true;
	}
	
	public boolean capNhapOrderDetail(OrderDetails od) {
		
		EntityTransaction tr = em.getTransaction();
		
		try {
			
			tr.begin();
			
			em.merge(od);
			
			tr.commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			tr.rollback();
			
			return false;
			
		}
		
		return true;
	}

	
	

}

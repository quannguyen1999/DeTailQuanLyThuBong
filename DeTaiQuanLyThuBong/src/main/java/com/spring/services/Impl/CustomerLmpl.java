package com.spring.services.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.spring.entities.Categories;
import com.spring.entities.Customers;
import com.spring.services.dao.CustomerDAO;
import com.spring.services.dao.MyEntityManager;

public class CustomerLmpl implements CustomerDAO{
	
	private static EntityManager em;
	

	public CustomerLmpl(){
		
		em = MyEntityManager.getInstance().getEntityManager();
		
	}
	public boolean themCustomer(Customers nv) {
		
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

	public boolean xoaCustomer(String manv) {
		
		EntityTransaction tr = em.getTransaction();
		
		try {
			
			tr.begin();
			
			em.remove(em.find(Customers.class, manv));
			
			tr.commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			tr.rollback();
			
			return false;
			
		}
		
		return true;
	}

	public List<Customers> layDanhSachCustomer() {
		
		return em.createNativeQuery("{}",Customers.class).getResultList();
		
	}
	public Customers timKiemId(String ma) {
		
		Customers acc=new Customers();
		
		try {
			
			acc=(Customers) em.createNativeQuery("{_id:'" + ma + "'}",Customers.class).getSingleResult();;
			
		} catch (Exception e) {
			
			return null;
			
		}
		return acc;
	}
	public Customers timKiemEmail(String email) {
		
		Customers acc=null;
		
		try {
			
			acc=(Customers) em.createNativeQuery("{'email':'" + email + "'}",Customers.class).getSingleResult();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			return null;
			
		}
		return acc;
	}
	public boolean capNhapCustomer(Customers nv) {
		
		EntityTransaction tr = em.getTransaction();
		
		try {
			
			tr.begin();
			
			em.merge(nv);
			
			tr.commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			tr.rollback();
			
			return false;
			
		}
		return true;
		
	}
	@Override
	public Customers timKiemUserName(String userName) {
		
		Customers acc=null;
		
		try {
			
			acc=(Customers) em.createNativeQuery("{userName:'" + userName + "'}",Customers.class).getSingleResult();;
			
		} catch (Exception e) {
			
			return null;
			
		}
		
		return acc;
		
	}
	@Override
	public Customers timKiemSoDienThoai(String sdt) {
		
		Customers acc=null;
		
		try {
			
			acc=(Customers) em.createNativeQuery("{phone:'" + sdt + "'}",Customers.class).getSingleResult();;
			
		} catch (Exception e) {
			
			return null;
			
		}
		
		return acc;
		
	}

}

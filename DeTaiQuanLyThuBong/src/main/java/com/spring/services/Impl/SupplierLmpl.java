package com.spring.services.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.spring.entities.Categories;
import com.spring.entities.Customers;
import com.spring.entities.Products;
import com.spring.entities.Suppliers;
import com.spring.services.dao.MyEntityManager;
import com.spring.services.dao.SupplierDAO;

public class SupplierLmpl implements SupplierDAO{
	
	private static EntityManager em;

	public SupplierLmpl(){
		
		em = MyEntityManager.getInstance().getEntityManager();
		
	}
	
	public boolean themSupplier(Suppliers sl) {
		
		EntityTransaction tr = em.getTransaction();
		
		try {
			
			
			tr.begin();
			
			em.persist(sl);
			
			tr.commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			tr.rollback();
			
			return false;
		}
		return true;
	}

	
	public boolean xoaSupplier(String masl) {
		
		EntityTransaction tr = em.getTransaction();
		
		try {
			
			tr.begin();
			
			em.remove(em.find(Suppliers.class, masl));
			
			tr.commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			tr.rollback();
			
			return false;
			
		}
		
		return true;
	}

	
	public List<Suppliers> layDanhSachSupplier() {
		
		return em.createNativeQuery("{}",Suppliers.class).getResultList();
		
	}
	
	public Suppliers timKiemId(String idSs) {
		
		Suppliers acc=null;
		
		try {
			
			acc=(Suppliers) em.createNativeQuery("{_id:'" + idSs + "'}",Suppliers.class).getSingleResult();;
			
		} catch (Exception e) {
			
			return null;
			
		}
		return acc;
	}
	
	public boolean capNhapSupplier(Suppliers sl) {
		
		EntityTransaction tr = em.getTransaction();
		
		try {
			
			tr.begin();
			
			em.merge(sl);
			
			tr.commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			tr.rollback();
			
			return false;
		}
		return true;
	}

}

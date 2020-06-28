package com.spring.services.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.spring.entities.Categories;
import com.spring.entities.Orders;
import com.spring.entities.Products;
import com.spring.entities.Suppliers;
import com.spring.services.dao.MyEntityManager;
import com.spring.services.dao.ProductDAO;

public class ProductLmpl implements ProductDAO{
	
	private static EntityManager em;
	

	public ProductLmpl(){
		
		em = MyEntityManager.getInstance().getEntityManager();
		
	}

	public boolean themProduct(Products ct) {
		
		EntityTransaction tr = em.getTransaction();
		
		try {
			
			tr.begin();
			
			em.persist(ct);
			
			tr.commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			tr.rollback();
			
			return false;
			
		}
		return true;
	}


	public boolean xoaProduct(String ma) {
		
		EntityTransaction tr = em.getTransaction();
		
		try {
			
			tr.begin();
			
			em.remove(em.find(Products.class, ma));
			
			tr.commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			tr.rollback();
			
			return false;
			
		}
		return true;
	}


	public List<Products> layDanhSachProduct() {
		
		List<Products> listPro=new ArrayList<Products>();
		
		listPro=em.createNativeQuery("{}",Products.class).getResultList();
		
		return listPro;
	}

	public Products timKiemId(String ma) {
		
		Products acc=null;
		
		try {
			
			acc=(Products) em.createNativeQuery("{_id:'" + ma + "'}",Products.class).getSingleResult();;
			
		} catch (Exception e) {
			
			return null;
			// TODO: handle exception
		}
		return acc;
	}

	public List<Products> timKiemTheoMaCategory(String ma) {
		// TODO Auto-generated method stub
		return  em.createNativeQuery("{categoryID:'" + ma + "'}",Products.class).getResultList();
		
	}

	public List<Products> timKiemTheoMaSupplier(String ma) {
		// TODO Auto-generated method stub
		return  em.createNativeQuery("{supplierID:'" + ma + "'}",Products.class).getResultList();
		
	}

	public boolean capNhapProduct(Products ct) {
		
		EntityTransaction tr = em.getTransaction();
		
		try {
			
			tr.begin();
			
			em.merge(ct);
			
			tr.commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			tr.rollback();
			
			return false;
		}
		
		return true;
	}

	List<String> search=null;
	@Override
	public List<String> timKiemTenProduct(String maTim) {
		
		String ma=maTim.trim();
		
		search=new ArrayList<String>();
		
		List<Products> listPro=em.createNativeQuery("{}",Products.class).getResultList();
		
		listPro.forEach(t->{
			
			if(t.getProductName().toLowerCase().contains(ma.toLowerCase())) {
				
				search.add(t.getProductName());
				
			}
		});
		
		return  search;

	}

	@Override
	public List<Products> listTimKiemTenProduct(String ma) {
		
		return  em.createNativeQuery("{productName:'" + ma + "'}",Products.class).getResultList();
		
	}
}

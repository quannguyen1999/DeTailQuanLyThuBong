package com.spring.services.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.spring.entities.Account;
import com.spring.entities.Categories;
import com.spring.services.dao.CategoriesDAO;
import com.spring.services.dao.MyEntityManager;


public class CategoriesLmpl implements CategoriesDAO{
	
	private static EntityManager em;
	

	public CategoriesLmpl(){
		
		em = MyEntityManager.getInstance().getEntityManager();
		
	}
	public boolean themCategory(Categories ct) {
		
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

	public boolean xoaCategory(String ma) {
		
		EntityTransaction tr = em.getTransaction();
		
		try {
			
			tr.begin();
			
			em.remove(em.find(Categories.class, ma));
			
			tr.commit();
			
		} catch (Exception e) {
			
			return false;
			
		}
		return true;
	}

	public List<Categories> layDanhSachCategory() {
		
		return em.createNativeQuery("{}",Categories.class).getResultList();
		
	}
	public Categories timKiemId(String idcate) {
		
		Categories acc=null;
		
		try {
			
			acc=(Categories) em.createNativeQuery("{_id:'" + idcate + "'}",Categories.class).getSingleResult();
			
		} catch (Exception e) {
			
			return null;
			
		}
		return acc;
	}
	public boolean capNhapCategories(Categories cate) {
		
		EntityTransaction tr = em.getTransaction();
		
		try {
			
			tr.begin();
			
			em.merge(cate);
			
			tr.commit();
			
			return true;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			tr.rollback();
			
		}
		return false;
	}
	public boolean capNhapCategory(Categories ct) {
		
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

}

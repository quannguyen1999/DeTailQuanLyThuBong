package com.spring.services.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.spring.entities.Account;
import com.spring.entities.Customers;
import com.spring.services.dao.AccountDAO;
import com.spring.services.dao.MyEntityManager;



public class AccountLmpl implements AccountDAO{
	/**
	 * 
	 */
	private static EntityManager em;

	public AccountLmpl(){
		em = MyEntityManager.getInstance().getEntityManager();
	}

	public  boolean themAccount(Account nv) {
		
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
	
	public boolean xoaAccount(String manv) {
		
		EntityTransaction tr = em.getTransaction();
		
		try {
			
			tr.begin();
			
			em.remove(em.find(Account.class, manv));
			
			tr.commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			tr.rollback();
			
			return false;
		}
		return true;
		
	}
	
	public boolean xoaCustomerByAccount(String maCus) {
		
		EntityTransaction tr = em.getTransaction();
		
		try {
			
			tr.begin();
			
			em.remove(em.find(Customers.class, maCus));
			
			tr.commit();
			
		} catch (Exception e) {
			
			return false;
			
		}
		return true;
		
	}
	
	public Account timKiemUsername(String userName) {
		
		Account acc=null;
		
		try {
			
			acc=(Account) em.createNativeQuery("{_id:'" + userName + "'}",Account.class).getSingleResult();;
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}
		return acc;
	}
	
	public Customers TimKiemCustomerByID(String maCus) {
		
		try {
			
			Customers cus=(Customers) em.createNativeQuery("{userName:'" + maCus + "'}", Customers.class).getSingleResult();

			return cus;
			
		} catch (Exception e) {
			
			return null;
			
		}
	}

	
	public List<Account> layDanhSachAccount() {
		
		return em.createNativeQuery("{}",Account.class).getResultList();
		
	}

	public boolean capNhapAccount(Account nv) {
		
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
}

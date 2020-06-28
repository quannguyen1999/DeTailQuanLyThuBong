package com.spring.services.dao;

import java.util.List;

import com.spring.entities.Customers;
import com.spring.entities.Orders;
import com.spring.entities.Products;

public interface ProductDAO {
	public boolean themProduct(Products ct) ;
	public boolean capNhapProduct(Products ct) ;
	public boolean xoaProduct(String ma) ;
	public List<Products> layDanhSachProduct();
	public Products timKiemId(String ma);
	public List<String> timKiemTenProduct(String ma);
	public List<Products> listTimKiemTenProduct(String ma);
	public List<Products> timKiemTheoMaCategory(String ma);
	public List<Products> timKiemTheoMaSupplier(String ma);
}

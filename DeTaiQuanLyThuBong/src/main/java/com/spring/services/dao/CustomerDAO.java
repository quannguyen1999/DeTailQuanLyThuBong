package com.spring.services.dao;

import java.util.List;

import com.spring.entities.Customers;
import com.spring.entities.Suppliers;

public interface CustomerDAO {
	public boolean themCustomer(Customers nv) ;
	public boolean capNhapCustomer(Customers nv) ;
	public boolean xoaCustomer(String manv) ;
	public List<Customers> layDanhSachCustomer();
	public Customers timKiemId(String ma);
	public Customers timKiemEmail(String email);
	public Customers timKiemUserName(String userName);
	public Customers timKiemSoDienThoai(String sdt);
}

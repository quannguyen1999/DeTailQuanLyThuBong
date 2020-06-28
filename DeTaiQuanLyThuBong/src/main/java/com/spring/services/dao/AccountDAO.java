package com.spring.services.dao;
import java.util.List;

import com.spring.entities.Account;
import com.spring.entities.Customers;

public interface AccountDAO {
	public boolean themAccount(Account nv) ;
	public boolean capNhapAccount(Account nv) ;
	public boolean xoaAccount(String manv) ;
	public boolean xoaCustomerByAccount(String maCus) ;
	public Customers TimKiemCustomerByID(String maCus) ;
	public List<Account> layDanhSachAccount();
	public Account timKiemUsername(String userName);
}

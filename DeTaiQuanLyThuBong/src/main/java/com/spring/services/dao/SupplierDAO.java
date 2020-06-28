package com.spring.services.dao;

import java.util.List;

import com.spring.entities.Categories;
import com.spring.entities.Suppliers;

public interface SupplierDAO {
	public boolean themSupplier(Suppliers sl) ;
	public boolean capNhapSupplier(Suppliers sl) ;
	public boolean xoaSupplier(String masl) ;
	public List<Suppliers> layDanhSachSupplier();
	public Suppliers timKiemId(String idSs);
}

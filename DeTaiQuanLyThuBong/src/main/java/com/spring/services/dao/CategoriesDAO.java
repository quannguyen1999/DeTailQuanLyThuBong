package com.spring.services.dao;

import java.util.List;

import org.springframework.validation.BindingResult;

import com.spring.entities.Account;
import com.spring.entities.Categories;

public interface CategoriesDAO {
	public boolean themCategory(Categories ct) ;
	public boolean capNhapCategory(Categories ct) ;
	public boolean xoaCategory(String ma) ;
	public List<Categories> layDanhSachCategory();
	public Categories timKiemId(String idcate);
	public boolean capNhapCategories(Categories cate);
}

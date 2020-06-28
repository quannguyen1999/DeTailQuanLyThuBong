package com.spring.controller.admin;

import static com.sun.org.apache.xerces.internal.impl.dv.util.Base64.encode;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.spring.entities.Categories;
import com.spring.entities.OrderDetails;
import com.spring.entities.Products;
import com.spring.services.Impl.AccountLmpl;
import com.spring.services.Impl.CategoriesLmpl;
import com.spring.services.Impl.CustomerLmpl;
import com.spring.services.Impl.OrderLmpl;
import com.spring.services.Impl.ProductLmpl;
import com.spring.services.Impl.SupplierLmpl;
import com.spring.services.dao.CategoriesDAO;
import com.spring.services.dao.CustomerDAO;
import com.spring.services.dao.OrderDAO;
import com.spring.services.dao.ProductDAO;
import com.spring.services.dao.SupplierDAO;
import com.spring.validators.CustomerValidator;
import com.spring.validators.ProductValidator;

@Controller
@RequestMapping("/admin/sanPham")
@ControllerAdvice
public class ProductController {
	
	@RequestMapping(value = "danhSach",method=RequestMethod.GET)
	public String danhSach(ModelMap model) {
		
		ProductDAO listAcc=new ProductLmpl();
		
		model.put("listProduct",listAcc.layDanhSachProduct());
		
		return "/admin/sanPham/danhSach";
	}
	@RequestMapping(value = "them",method=RequestMethod.GET)
	public String them(ModelMap model) {
		
		ProductDAO proDAO=new ProductLmpl();
		
		
		boolean result=false;
		
		String idRandom="";
		
		int random=0;
		
		while(result==false) {
			
			random = (int)(Math.random() * 10000 + 1);
			
			idRandom="CT"+random;
			
			if(proDAO.timKiemId(idRandom)==null) {
				
				result=true;
				
			};
		}
		
		model.addAttribute("maProduct",idRandom);
		
		CategoriesDAO cateDAO=new CategoriesLmpl();
		
		SupplierDAO supliDAO=new SupplierLmpl();
	
		model.put("product",new Products());
		
		model.put("listSupplier", supliDAO.layDanhSachSupplier());
		
		model.put("listCate",cateDAO.layDanhSachCategory());
		
		return "/admin/sanPham/them";
	}
	@RequestMapping(value = "edit/{id}",method=RequestMethod.GET)
	public String sua(@PathVariable String id,ModelMap model) {
		
		ProductDAO proDAO=new ProductLmpl();
		
		CategoriesDAO cateDAO=new CategoriesLmpl();
		
		SupplierDAO supliDAO=new SupplierLmpl();
		
		model.put("product",proDAO.timKiemId(id));
		
		model.put("listSupplier", supliDAO.layDanhSachSupplier());
		
		model.put("listCate",cateDAO.layDanhSachCategory());
		
		return "/admin/sanPham/edit";
	}
	
	@RequestMapping(value = "process-them",method=RequestMethod.POST)
	public String processThem( @ModelAttribute("product") @Valid Products product,
			BindingResult result,
			Model model,@RequestParam("file") MultipartFile file) throws IOException {
		
		CategoriesDAO cateDAO=new CategoriesLmpl();
		
		SupplierDAO supliDAO=new SupplierLmpl();
		
		model.addAttribute("listSupplier", supliDAO.layDanhSachSupplier());
		
		model.addAttribute("listCate",cateDAO.layDanhSachCategory());
		
		boolean checkSupplierID=true;
		
		boolean checkCategoryID=true;
		
		if(product.getSupplierID().getSupplierID().isEmpty()==true) {
			
			model.addAttribute("errorMaNCC", "Bạn chưa chọn nhà cung cấp");
			
			checkSupplierID=false;
			
		}
		if(product.getCategoryID().getCategoryID().isEmpty()==true) {
			
			model.addAttribute("errorMaMH", "Bạn chưa chọn mặt hàng");
			
			checkCategoryID=false;
			
		}
		if(checkCategoryID==false || checkSupplierID==false) {
			
			return "/admin/sanPham/them";
			
		}
		
		ProductValidator cusvalidator=new ProductValidator();
		
		cusvalidator.validate(product,result);
		
		if(result.hasErrors()) {
			
			return "/admin/sanPham/them";
			
		}
		
		if(file.isEmpty()) {
			
			model.addAttribute("errorHinh", "picture can't empty");
			
			return "/admin/matHang/them";
			
		}
		byte[] bytes = file.getBytes();
		
		@SuppressWarnings("restriction")
		
		String encodedImage = encode(bytes); 
		
		
		Categories cate=cateDAO.timKiemId(product.getCategoryID().getCategoryID());
		
		product.setMoTa(cate.getDescription());
		
		product.setCategoryID(cate);
		
		product.setPicture(encodedImage);
		
		ProductDAO proDAO=new ProductLmpl();
		
		if(proDAO.themProduct(product)==true) {
			
			return "/admin/sanPham/themThanhCong";
		};
		
		model.addAttribute("errorMaNCC", "Thêm không thành công");
		
		return "/admin/sanPham/them";
	}
	@RequestMapping(value = "process-sua",method=RequestMethod.POST)
	public String processSua( @ModelAttribute("product") @Valid Products    product,
			BindingResult result,
			Model model,@RequestParam("file") MultipartFile file) throws IOException {
	
		ProductDAO proDus=new ProductLmpl();
		
		ProductValidator proValidator=new ProductValidator();
		
		proValidator.validate(product,result);
		
		if(result.hasErrors()) {
			
			CategoriesDAO cateDAO=new CategoriesLmpl();
			
			SupplierDAO supliDAO=new SupplierLmpl();
			
			model.addAttribute("listSupplier", supliDAO.layDanhSachSupplier());
			
			model.addAttribute("listCate",cateDAO.layDanhSachCategory());
			
			return "/admin/sanPham/edit";
		}
		if(file.isEmpty()) {
			
			Products cateOLD=proDus.timKiemId(product.getProductID());
			
			product.setPicture(cateOLD.getPicture());// .setPicture(cateOLD.getPicture());
			
		}else {
			byte[] bytes = file.getBytes();
			
			@SuppressWarnings("restriction")
			
			String encodedImage = encode(bytes); 
			
			product.setPicture(encodedImage);
		}
		proDus.capNhapProduct(product);
		
		return "redirect:/admin/sanPham/danhSach";
	}
	@RequestMapping(value = "remove/{id}",method=RequestMethod.GET)
	public String remove(@PathVariable("id") String id,ModelMap model) {
		
		ProductDAO proDAO=new ProductLmpl();
		
		OrderDAO odDAO=new OrderLmpl();
		
		List<OrderDetails> listOrdersDetails=odDAO.timKiemTheoProductID(id);
		
		for(int i=0;i<listOrdersDetails.size();i++) {
			
			odDAO.xoaOrderDetails(listOrdersDetails.get(i).getId().getOrderID());
			
			odDAO.xoaOrder(listOrdersDetails.get(i).getId().getOrderID());
			
		}
		
		proDAO.xoaProduct(id);
		
		return  "redirect:/admin/sanPham/danhSach";
	}

}

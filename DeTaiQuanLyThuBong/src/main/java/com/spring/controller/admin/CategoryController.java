package com.spring.controller.admin;

import static com.sun.org.apache.xerces.internal.impl.dv.util.Base64.encode;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
import com.spring.entities.Customers;
import com.spring.entities.OrderDetails;
import com.spring.entities.Orders;
import com.spring.entities.Products;
import com.spring.services.Impl.AccountLmpl;
import com.spring.services.Impl.CategoriesLmpl;
import com.spring.services.Impl.CustomerLmpl;
import com.spring.services.Impl.OrderLmpl;
import com.spring.services.Impl.ProductLmpl;
import com.spring.services.dao.AccountDAO;
import com.spring.services.dao.CategoriesDAO;
import com.spring.services.dao.CustomerDAO;
import com.spring.services.dao.OrderDAO;
import com.spring.services.dao.ProductDAO;
import com.spring.validators.CategoryValidator;
import com.spring.validators.CustomerValidator;

@Controller
@RequestMapping("/admin/matHang")
@ControllerAdvice
public class CategoryController {
	
	@RequestMapping(value = "danhSach",method=RequestMethod.GET)
	
	public String danhSach(ModelMap model) {
		
		CategoriesDAO listCate=new CategoriesLmpl();
		
		model.put("listCate",listCate.layDanhSachCategory());
		
		return "/admin/matHang/danhSach";
		
	}

	@RequestMapping(value = "them",method=RequestMethod.GET)
	
	public String them(ModelMap model) {
		
		CategoriesDAO cateDAO=new CategoriesLmpl();
		
		boolean result=false;
		
		String idRandom="";
		
		int random=0;
		
		while(result==false) {
			
			random = (int)(Math.random() * 10000 + 1);
			
			idRandom="CT"+random;
			
			if(cateDAO.timKiemId(idRandom)==null) {
				
				result=true;
				
			};
		}
		
		model.addAttribute("MaCate",idRandom);
		
		model.put("category",new Categories());
		
		return "/admin/matHang/them";
	}
	
	@RequestMapping(value = "edit/{id}",method=RequestMethod.GET)
	public String sua(@PathVariable String id,ModelMap model,HttpServletRequest request) {
		
		CategoriesDAO cusDAO=new CategoriesLmpl();
		
		model.put("category",cusDAO.timKiemId(id));
		
		ProductDAO proDAO=new ProductLmpl();
		
		List<Products> listProductLienQuan=proDAO.timKiemTheoMaCategory(id);
		
		model.put("listProductLienQuan",listProductLienQuan);
		
		HttpSession session=request.getSession();
		
		session.setAttribute("trangAdmin","/admin/matHang/edit/"+id);
		
		return "/admin/matHang/edit";
	}
	
	@RequestMapping(value = "remove/products/{id}",method=RequestMethod.GET)
	public String removeProduct(@PathVariable("id") String id,ModelMap model,HttpServletRequest request) {
		
		ProductDAO proDAO=new ProductLmpl();
		
		OrderDAO odDAO=new OrderLmpl();
		
		List<OrderDetails> listOrdersDetails=odDAO.timKiemTheoProductID(id);
		
		for(int i=0;i<listOrdersDetails.size();i++) {
			
			odDAO.xoaOrderDetails(listOrdersDetails.get(i).getId().getOrderID());
			
			odDAO.xoaOrder(listOrdersDetails.get(i).getId().getOrderID());
			
		}
		
		HttpSession session=request.getSession();
		
		String currentPage=(String) session.getAttribute("trangAdmin");
		
		proDAO.xoaProduct(id);
		
		return  "redirect:"+currentPage;
	}
	
	int tongSoSP=0;
	@RequestMapping(value = "process-sua",method=RequestMethod.POST)
	public String processSua( @ModelAttribute("category") @Valid Categories categories,
			BindingResult result,@RequestParam("file") MultipartFile file,
			Model model) throws IOException {
		
		CategoriesDAO cateDAO=new CategoriesLmpl();
		
		CategoryValidator categoryValidator=new CategoryValidator();
		
		categoryValidator.validate(categories,result);
		
		if(result.hasErrors()) {
			
			return "/admin/matHang/edit";
			
		}
		if(file.isEmpty()) {
			
			Categories cateOLD=cateDAO.timKiemId(categories.getCategoryID());
			
			categories.setPicture(cateOLD.getPicture());
			
		}else {
			
			byte[] bytes = file.getBytes();
			
			@SuppressWarnings("restriction")
			
			String encodedImage = encode(bytes); 
			
			categories.setPicture(encodedImage);
			
		}
		cateDAO.capNhapCategories(categories);
		
		return "redirect:/admin/matHang/danhSach";
	}
	
	@RequestMapping(value = "remove/{id}",method=RequestMethod.GET)
	public String remove(@PathVariable("id") String id,ModelMap model) {
		
		CategoriesDAO suppli=new CategoriesLmpl();
		
		ProductDAO proDAO=new ProductLmpl();
		
		OrderDAO odDAO=new OrderLmpl();
		
		Categories su=suppli.timKiemId(id);
		
		List<Products> listProduct=proDAO.timKiemTheoMaCategory(su.getCategoryID());
		
		if(listProduct.size()>0) {
			
			for(int i=0;i<listProduct.size();i++) {
				
				List<OrderDetails> listOrderDetail=odDAO.timKiemTheoProductID(listProduct.get(i).getProductID());
				
				if(listOrderDetail.size()>0) {
					
					for(int j=0;j<listOrderDetail.size();j++) {
						
						odDAO.xoaOrderDetails(listOrderDetail.get(j).getId().getOrderID());
						
						odDAO.xoaOrder(listOrderDetail.get(j).getId().getOrderID());
						
					}
				}
				
				proDAO.xoaProduct(listProduct.get(i).getProductID());
				
			}
		}
		
		suppli.xoaCategory(id);
		
		return "redirect:/admin/matHang/danhSach";
		
	}

	@RequestMapping(value = "process-them",method=RequestMethod.POST)
	public String processThem( @ModelAttribute("category") @Valid Categories category,
			BindingResult result,@RequestParam("file") MultipartFile file,
			Model model) throws IOException {
		
		CategoriesDAO cateDAO=new CategoriesLmpl();
		
		CategoryValidator catevalidator=new CategoryValidator();
		
		catevalidator.validate(category,result);
		
		if(result.hasErrors()) {
			
			return "/admin/matHang/them";
			
		}
		if(file.isEmpty()) {
			
			model.addAttribute("errorHinh", "picture can't empty");
			
			return "/admin/matHang/them";
			
		}
		
		byte[] bytes = file.getBytes();
		
		@SuppressWarnings("restriction")
		
		String encodedImage = encode(bytes); 
		
		System.out.println(encodedImage);
		
		category.setPicture(encodedImage);
		
		if(cateDAO.themCategory(category)==true) {
			
			model.addAttribute("errorMa", "Thêm không thành công");
			
			return "/admin/matHang/themThanhCong";
			
		}else{
			
			return "/admin/matHang/them";
			
		}
	}


}

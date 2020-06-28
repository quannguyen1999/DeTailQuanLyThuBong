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
import com.spring.entities.Customers;
import com.spring.entities.OrderDetails;
import com.spring.entities.Orders;
import com.spring.entities.Products;
import com.spring.entities.Suppliers;
import com.spring.services.Impl.AccountLmpl;
import com.spring.services.Impl.CategoriesLmpl;
import com.spring.services.Impl.CustomerLmpl;
import com.spring.services.Impl.OrderLmpl;
import com.spring.services.Impl.ProductLmpl;
import com.spring.services.Impl.SupplierLmpl;
import com.spring.services.dao.AccountDAO;
import com.spring.services.dao.CategoriesDAO;
import com.spring.services.dao.CustomerDAO;
import com.spring.services.dao.OrderDAO;
import com.spring.services.dao.ProductDAO;
import com.spring.services.dao.SupplierDAO;
import com.spring.validators.CategoryValidator;
import com.spring.validators.CustomerValidator;
import com.spring.validators.SupplierValidator;

@Controller
@RequestMapping("/admin/nhaCungCap")
@ControllerAdvice
public class SupplierController {
	
	@RequestMapping(value = "danhSach",method=RequestMethod.GET)
	public String danhSach(ModelMap model) {
		
		SupplierDAO supliDAO=new SupplierLmpl();
		
		model.put("listSupli",supliDAO.layDanhSachSupplier());
		
		return "/admin/nhaCungCap/danhSach";
		
	}

	@RequestMapping(value = "them",method=RequestMethod.GET)
	public String them(ModelMap model) {
		
		SupplierDAO supplierDAO=new SupplierLmpl();
		
		
		model.put("supplier",new Suppliers());
		
		boolean result=false;
		
		String idRandom="";
		
		int random=0;
		
		while(result==false) {
			
			random = (int)(Math.random() * 10000 + 1);
			
			
			idRandom="CT"+random;
			
			if(supplierDAO.timKiemId(idRandom)==null) {
				
				result=true;
				
			};
		}
		model.addAttribute("maSupplier",idRandom);
		
		return "/admin/nhaCungCap/them";
	}

	@RequestMapping(value = "edit/{id}",method=RequestMethod.GET)
	public String sua(@PathVariable String id,ModelMap model) {
		
		SupplierDAO supliDAO=new SupplierLmpl();
		
		model.put("supplier",supliDAO.timKiemId(id));
		
		return "/admin/nhaCungCap/edit";
	}

	@RequestMapping(value = "process-sua",method=RequestMethod.POST)
	public String processSua( @ModelAttribute("supplier") @Valid Suppliers suppliers,
			BindingResult result,
			Model model) throws IOException {
		
		SupplierDAO supliDAO=new SupplierLmpl();
		
		SupplierValidator supplierValidator=new SupplierValidator();
		
		supplierValidator.validate(suppliers,result);
		
		if(result.hasErrors()) {
			
			
			return "/admin/nhaCungCap/edit";
			
		}
		supliDAO.capNhapSupplier(suppliers);
		return "redirect:/admin/nhaCungCap/danhSach";
	}

	@RequestMapping(value = "remove/{id}",method=RequestMethod.GET)
	public String remove(@PathVariable("id") String idCate,ModelMap model) {
		
		ProductDAO proDAO=new ProductLmpl();
		
		SupplierDAO supliDAO=new SupplierLmpl();
		
		Suppliers suppliers=supliDAO.timKiemId(idCate);
		
		List<Products> listPro=proDAO.timKiemTheoMaSupplier(suppliers.getSupplierID());
		
		for(int k=0;k<listPro.size();k++) {
			
			OrderDAO odDAO=new OrderLmpl();
			
			List<OrderDetails> listOrdersDetails=odDAO.timKiemTheoProductID(listPro.get(k).getProductID());
			
			if(listOrdersDetails.size()>0) {
				
				for(int i=0;i<listOrdersDetails.size();i++) {
					
					odDAO.xoaOrderDetails(listOrdersDetails.get(i).getId().getOrderID());
					
					odDAO.xoaOrder(listOrdersDetails.get(i).getId().getOrderID());
					
				}
			}
			
			proDAO.xoaProduct(listPro.get(k).getProductID());
			
		}
		supliDAO.xoaSupplier(idCate);
		
		return "redirect:/admin/nhaCungCap/danhSach";
	}

	@RequestMapping(value = "process-them",method=RequestMethod.POST)
	public String processThem( @ModelAttribute("supplier") @Valid Suppliers suppliers,
			BindingResult result,
			Model model) throws IOException {
		
		SupplierDAO supliDAO=new SupplierLmpl();
		
		SupplierValidator supplierValidator=new SupplierValidator();
		
		supplierValidator.validate(suppliers,result);
		
		if(result.hasErrors()) {
			
			return "/admin/nhaCungCap/them";
			
		}
		
		if(supliDAO.themSupplier(suppliers)==true) {
			
			return "/admin/nhaCungCap/themThanhCong";
			
		}else{
			
			model.addAttribute("errorMa","Thêm không thành công");
			
			return "admin/nhaCungCap/them";
		}
	}


}

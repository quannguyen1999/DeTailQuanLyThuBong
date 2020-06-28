package com.spring.controller.admin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.entities.Customers;
import com.spring.entities.OrderDetails;
import com.spring.entities.Orders;
import com.spring.entities.Products;
import com.spring.entities.util.Orders_Products;
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
import com.spring.validators.CustomerValidator;

@Controller
@RequestMapping("/admin/khachHang")
@ControllerAdvice
public class CustomerController {

	@RequestMapping(value = "taoOrder/{id}",method=RequestMethod.GET)
	public String taoOrder(@PathVariable("id") String id,ModelMap model,HttpServletRequest request) {
		
		CustomerDAO cusDAO=new CustomerLmpl();
		
		Customers customer=cusDAO.timKiemId(id);
		
		model.put("customer", customer);
		
		HttpSession session=request.getSession();
		
		String luuID=(String) session.getAttribute("luuID");
		
		if(luuID==null) {
			

		}else if(luuID.equals(id)) {
			

		}else {
			
			session.setAttribute("trangOrder", null);
			
		}
		
		session.setAttribute("trangOrder", "/admin/khachHang/taoOrder/"+id);
		
		session.setAttribute("luuID",id);
		
		ProductDAO listAcc=new ProductLmpl();
		
		List<Products> listProduct=listAcc.layDanhSachProduct();
		
		model.put("listProduct",listProduct);

		return "/admin/khachHang/taoOrder";
	}

	@RequestMapping(value = "sanPhams/remove/{id}",method=RequestMethod.GET)
	public String xoaSanPhamOrder(@PathVariable("id") String id,HttpServletRequest request,ModelMap model) {
		
		HttpSession session=request.getSession();
		
		String trangAdmin=(String) session.getAttribute("trangOrder");
		
		List<Products> listSpChon=(List<Products>) session.getAttribute("listSPChon");
		
		int tong=(int) session.getAttribute("tongSP");
		
		System.out.println("remove");
		
		System.out.println(tong);
		
		for(int i=0;i<listSpChon.size();i++) {
			
			if(listSpChon.get(i).getProductID().equals(id)) {
				
				tong-=listSpChon.get(i).getUnitPrrice();
				
				listSpChon.remove(listSpChon.get(i));
				
				break;
			}
		}
		
		session.setAttribute("tongSP", tong);
		
		session.setAttribute("listSPChon",listSpChon);
		
		return "redirect:"+trangAdmin;
	}
	
	@RequestMapping(value = "sanPhams/bot/{id}",method=RequestMethod.GET)
	public String botSanPhamOrder(@PathVariable("id") String id,HttpServletRequest request,ModelMap model) {
		
		HttpSession session=request.getSession();
		
		String trangAdmin=(String) session.getAttribute("trangOrder");
		
		List<Products> listSpChon=(List<Products>) session.getAttribute("listSPChon");
		
		int tong=(int) session.getAttribute("tongSP");
		
		
		for(int i=0;i<listSpChon.size();i++) {
			
			if(listSpChon.get(i).getProductID().equals(id)) {
				
				if(listSpChon.get(i).getQuatityInStock()<=0) {
					
					tong-=(listSpChon.get(i).getUnitPrrice());
					
					listSpChon.remove(listSpChon.get(i));
					
				}else {
					
					tong-=(listSpChon.get(i).getUnitPrrice()/listSpChon.get(i).getQuatityInStock());
					
					listSpChon.get(i).setUnitPrrice(listSpChon.get(i).getUnitPrrice()-listSpChon.get(i).getUnitPrrice()/listSpChon.get(i).getQuatityInStock());
					
					listSpChon.get(i).setQuatityInStock(listSpChon.get(i).getQuatityInStock()-1);
				}
				
				break;
			}
		}
		
		System.out.println(tong);
		
		session.setAttribute("tongSP", tong);
		
		session.setAttribute("listSPChon",listSpChon);
		
		return "redirect:"+trangAdmin;
	}
	
	

	@RequestMapping(value = "sanPhams/xacNhanOrder/{id}",method=RequestMethod.GET)
	public String xacNhanOrder(@PathVariable("id") String id,HttpServletRequest request,ModelMap model) throws ParseException {
		
		HttpSession session=request.getSession();
		
		if(session.getAttribute("tongSP")==null || Integer.parseInt(session.getAttribute("tongSP").toString())==0) {
			
			model.addAttribute("error","( Chưa có sản phẩm nào )");
			
			CustomerDAO cusDAO=new CustomerLmpl();
			
			Customers customer=cusDAO.timKiemId(id);
			
			model.put("customer", customer);
			
			String luuID=(String) session.getAttribute("luuID");
			
			if(luuID==null) {

			}else if(luuID.equals(id)) {

			}else {
				
				session.setAttribute("trangOrder", null);
				
			}
			
			session.setAttribute("trangOrder", "/admin/khachHang/taoOrder/"+id);
			
			session.setAttribute("luuID",id);
			
			ProductDAO listAcc=new ProductLmpl();
			
			List<Products> listProduct=listAcc.layDanhSachProduct();
			
			model.put("listProduct",listProduct);
			
			return "/admin/khachHang/taoOrder";
			
		}
		
		OrderDAO odDAO=new OrderLmpl();
		
		boolean result=false;
		
		String idRandom="";
		
		int random=0;
		
		while(result==false) {
			
			random = (int)(Math.random() * 10000 + 1);
			
			idRandom="CT"+random;
			
			if(odDAO.timKiemId(idRandom)==null) {
				
				result=true;
				
			};
		}
		ProductDAO proDAO=new ProductLmpl();
		
		CustomerDAO cusDAO=new CustomerLmpl();
		
		Customers customers=cusDAO.timKiemId(id);
		
		LocalDate dateNow=LocalDate.now();
		
		String valueDateNow=dateNow.getDayOfMonth()+"/"+dateNow.getMonthValue()+"/"+dateNow.getYear();
		
		SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
		
		Date dateStr=formatter.parse(valueDateNow);
		
		Orders od=new Orders(customers,dateStr, idRandom, customers.getCity(), dateStr, customers.getAddress());
		
		List<Products> listProduct=null;
		
		if(odDAO.themOrder(od)==true) {
			
			 listProduct=(List<Products>) session.getAttribute("listSPChon");
			 
			for(int i=0;i<listProduct.size();i++) {
				
				Products product=new Products();
				

				product=proDAO.timKiemId(listProduct.get(i).getProductID());
				
				product.setQuatityInStock(product.getQuatityInStock()-listProduct.get(i).getQuatityInStock());
				

				proDAO.capNhapProduct(product);

				Orders_Products odp=new Orders_Products();
				
				odp.setOrderID(idRandom);
				
				odp.setProductID(listProduct.get(i).getProductID());
				
				int totalAmount=(int) listProduct.get(i).getUnitPrrice()*listProduct.get(i).getQuatityInStock();
				
				OrderDetails oddt=new OrderDetails(odp, 0, listProduct.get(i).getQuatityInStock(), totalAmount);
				
				odDAO.themOrderDetails(oddt);
			}
			session.setAttribute("customer",null);
		};
		session.setAttribute("idOrder", idRandom);
		
		session.setAttribute("maKH", customers.getCustomerID());
		
		session.setAttribute("tenKH",customers.getFirstName()+" "+customers.getLastName());
		
		session.setAttribute("sdt", customers.getPhone());
		
		return "redirect:/admin/khachHang/datHangThanhCong";
	}




	@RequestMapping(value = "sanPhams/chon/{id}",method=RequestMethod.GET)
	public String chonSanPham(@PathVariable("id") String id,HttpServletRequest request,ModelMap model) {
		
		boolean check=false;

		HttpSession session=request.getSession();
		
		int tong=0;
		
		String trangAdmin=(String) session.getAttribute("trangOrder");
		
		List<Products> listSpChon=(List<Products>) session.getAttribute("listSPChon");
		
		ProductDAO proDAP=new ProductLmpl();
		
		Products proC=proDAP.timKiemId(id);
		
		Products pro=new Products();
		
		pro.setProductID(proC.getProductID());
		
		pro.setCategoryID(proC.getCategoryID());
		
		pro.setDiscontinuted(proC.isDiscontinuted());
		
		pro.setMoTa(proC.getMoTa());
		
		pro.setPicture(proC.getPicture());
		
		pro.setProductName(proC.getProductName());
		
		pro.setQuatityInStock(proC.getQuatityInStock());
		
		pro.setSupplierID(proC.getSupplierID());
		
		pro.setUnitPrrice(proC.getUnitPrrice());
		
		if(pro.getQuatityInStock()==0) {
			
			session.setAttribute("error", "( sản phẩm tên:"+pro.getProductName()+" hết hàng)");
			
		}else {
			
			session.setAttribute("error", "");
			
			if(listSpChon==null) {
				
				listSpChon=new ArrayList<Products>();
				
			}
			for(int i=0;i<=listSpChon.size()-1;i++) {
				
				if(listSpChon.get(i).getProductID().equals(pro.getProductID())) {
					
					check=true;
					
					listSpChon.get(i).setQuatityInStock(listSpChon.get(i).getQuatityInStock()+1);
					
					listSpChon.get(i).setUnitPrrice(listSpChon.get(i).getUnitPrrice()+pro.getUnitPrrice());
					
				}
				tong+=listSpChon.get(i).getUnitPrrice();
				
			}
			if(check==false) {
				
				pro.setQuatityInStock(1);
				
				listSpChon.add(pro);
				
				tong=(int) (tong+pro.getUnitPrrice());
				
			}
		}
		session.setAttribute("tongSP", tong);
		
		session.setAttribute("listSPChon",listSpChon);
		
		return "redirect:"+trangAdmin;
	}

	@RequestMapping(value = "danhSach",method=RequestMethod.GET)
	
	public String danhSach(ModelMap model) {
		
		CustomerDAO listAcc=new CustomerLmpl();
		
		model.put("listCustomer",listAcc.layDanhSachCustomer());
		
		return "/admin/khachHang/danhSach";
		
	}

	@RequestMapping(value = "datHangThanhCong",method=RequestMethod.GET)
	public String datHangThanhCong(ModelMap model) {
		
		return "/admin/khachHang/datHangThanhCong";
		
	}

	@RequestMapping(value = "them",method=RequestMethod.GET)
	public String them(ModelMap model) {
		
		CustomerDAO cateDAO=new CustomerLmpl();
		
		boolean result=false;
		
		String idRandom="";
		
		int random=0;
		
		while(result==false) {
			
			System.out.println("load thêm");
			
			random = (int)(Math.random() * 10000 + 1);
			
			idRandom="CT"+random;
			
			if(cateDAO.timKiemId(idRandom)==null) {
				
				result=true;
				
			};
		}
		model.addAttribute("maKH",idRandom);
		
		model.put("customer",new Customers());
		
		return "/admin/khachHang/them";
	}

	@RequestMapping(value = "edit/orders/{id}",method=RequestMethod.GET)
	public String xoaOrder(@PathVariable String id,ModelMap model,HttpServletRequest request) {
		
		OrderDAO odDAO=new OrderLmpl();

		Orders order=odDAO.timKiemId(id);
		
		System.out.println(order);
		
		if(order!=null) {
			
			odDAO.timKiemMaOrderDetails(order.getOrderID()).forEach(x->{
				
				odDAO.xoaOrderDetails(x.getId().getOrderID());
				
			});

		}
		
		System.out.println(odDAO.xoaOrder(order.getOrderID()));
		
		HttpSession session=request.getSession();
		
		String adminPage=(String) session.getAttribute("adminPage");
		
		return "redirect:"+adminPage;
	}


	@RequestMapping(value = "edit/{id}",method=RequestMethod.GET)
	public String sua(@PathVariable String id,ModelMap model,HttpServletRequest request) {
		
		int tong=0;
		
		CustomerDAO cusDAO=new CustomerLmpl();
		
		model.put("customer",cusDAO.timKiemId(id));
		
		OrderDAO odDAO=new OrderLmpl();
		
		List<Orders> listSPOrder=odDAO.timKiemMaKH(id);
		
		model.put("listSPOrder", listSPOrder);
		
		for(int k=0;k<listSPOrder.size();k++) {
			
			List<OrderDetails> listOrderDetails=odDAO.timKiemMaOrderDetails(listSPOrder.get(k).getOrderID());
			
			for(int i=0;i<listOrderDetails.size();i++) {
				
				tong+=listOrderDetails.get(i).getTotalAmmount();
				
			}
		}
		
		HttpSession session=request.getSession();
		
		session.setAttribute("adminPage","/admin/khachHang/edit/"+id);
		
		model.put("tongOrder", tong);
		
		return "/admin/khachHang/edit";
	}
	@RequestMapping(value = "process-them",method=RequestMethod.POST)
	public String processThem( @ModelAttribute("customer") @Valid Customers    customer,
			BindingResult result,
			Model model) {
		
		CustomerValidator cusvalidator=new CustomerValidator();
		
		cusvalidator.validate(customer,result);

		AccountDAO daoAcc=new AccountLmpl();
		
		CustomerDAO daoCus=new CustomerLmpl();

		boolean checkUserName=true;
		
		boolean checkPassword=true;

		String userName=customer.getUserName().getUserName();
		
		String password=customer.getUserName().getPassword();

		if(userName.isEmpty()) {
			
			checkUserName=false;
			
			model.addAttribute("errorUserName","username không được bỏ trống");
			
		}
		if(checkUserName==true) {
			
			if(daoAcc.timKiemUsername(userName)!=null) {
				
				checkUserName=false;
				
				model.addAttribute("errorUserName","username đã có tài khoản");
				
			}
		}
		if(password.isEmpty()) {
			
			checkPassword=false;
			
			model.addAttribute("errorPassword","mật khẩu không được bỏ trống");
			
		}
		
		if(result.hasErrors()) {
			
			return "/admin/khachHang/them";
			
		}
		if(checkUserName==false || checkPassword==false) {
			
			return "/admin/khachHang/them";
			
		}
		
		Customers customerDT=daoCus.timKiemSoDienThoai(customer.getPhone());
		
		if(customerDT!=null) {
			
			model.addAttribute("errorPhone","Số điện thoại đã tồn tại");
			
			return "/admin/khachHang/them";
			
		}
		if(customer.getPhone().matches("^[0-9]{10}")==false) {
			
			model.addAttribute("errorPhone","Số điện thoại không hợp lệ");
			
			return "/admin/khachHang/them";
			
		}
		Customers customerEmail=daoCus.timKiemEmail(customer.getEmail());
		
		if(customerEmail!=null) {
			
			model.addAttribute("errorEmail","Email đã tồn tại");
			
			return "/admin/khachHang/them";
			
		}
		if(checkPassword==true) {
			
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
			
			String encodedPassword = passwordEncoder.encode(password);
			
			customer.getUserName().setPassword(encodedPassword);
			
		}

		if(daoCus.themCustomer(customer)==true){
			
			return "/admin/khachHang/themThanhCong";
			
		}else{
			
			model.addAttribute("errorUserName","Thêm không thành công");
			
			return "/admin/khachHang/them";
		}
	}

	@RequestMapping(value = "process-sua",method=RequestMethod.POST)
	public String processSua( @ModelAttribute("customer") @Valid Customers    customer,
			BindingResult result,
			Model model) {
		
		CustomerDAO daoCus=new CustomerLmpl();
		
		CustomerValidator cusvalidator=new CustomerValidator();
		
		cusvalidator.validate(customer,result);
		
		if(result.hasErrors()) {
			
			return "/admin/khachHang/edit";
			
		}
		Customers customerOld=daoCus.timKiemId(customer.getCustomerID());
		
		Customers customerDT=daoCus.timKiemSoDienThoai(customer.getPhone());
		
		if(customerOld.getPhone().equals(customer.getPhone())==false) {
			
			if(customerDT!=null) {
				
				model.addAttribute("errorPhone","Số điện thoại đã tồn tại");
				
				return "/admin/khachHang/edit";
				
			}
			if(customer.getPhone().matches("^[0-9]{10}")==false) {
				
				model.addAttribute("errorPhone","Số điện thoại không hợp lệ");
				
				return "/admin/khachHang/edit";
				
			}
		}
		if(customerOld.getEmail().equals(customer.getEmail())==false) {
			
			Customers customerEmail=daoCus.timKiemEmail(customer.getEmail());
			
			if(customerEmail!=null) {
				
				model.addAttribute("errorEmail","Email đã tồn tại");
				
				return "/admin/khachHang/edit";
			}
		}
		daoCus.capNhapCustomer(customer);
		
		return "redirect:/admin/khachHang/danhSach";
	}

	@RequestMapping(value = "remove/{id}",method=RequestMethod.GET)
	public String remove(@PathVariable("id") String id,ModelMap model) {
		
		CustomerDAO cusDAO=new CustomerLmpl();
		
		OrderDAO odDAO=new OrderLmpl();
		
		List<Orders> listOrder=odDAO.timKiemMaKH(id);
		
		if(listOrder.size()>0) {
			
			for(int i=0;i<listOrder.size();i++) {
				
				odDAO.xoaOrderDetails(listOrder.get(i).getOrderID());
				
				odDAO.xoaOrder(listOrder.get(i).getOrderID());
				
			}
		}
		if(cusDAO.xoaCustomer(id)==true) {
			
			return "redirect:/admin/khachHang/danhSach";
		}else{
			
			return "redirect:/admin/badRequest";
		}
	}



}

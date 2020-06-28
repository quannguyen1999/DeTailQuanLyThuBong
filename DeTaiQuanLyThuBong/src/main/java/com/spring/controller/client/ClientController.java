package com.spring.controller.client;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCrypt;
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
import org.springframework.web.bind.annotation.ResponseBody;
import com.spring.entities.Account;
import com.spring.entities.Customers;
import com.spring.entities.OrderDetails;
import com.spring.entities.Orders;
import com.spring.entities.Products;
import com.spring.entities.util.LichSuOrder;
import com.spring.entities.util.OrderDetailsProduct;
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
@RequestMapping("/client")
@ControllerAdvice
public class ClientController {

	@Autowired
	private JavaMailSender sender;

	@RequestMapping(method=RequestMethod.GET)
	public String welcome(ModelMap model,HttpServletRequest request) {
		
		CategoriesDAO categoryDAO=new CategoriesLmpl();
		
		ProductDAO proDAO=new ProductLmpl();
		
		model.put("listMatHang", categoryDAO.layDanhSachCategory());
		
		model.put("listProduct",proDAO.layDanhSachProduct() );
		
		HttpSession session=request.getSession();
		
		session.setAttribute("trangCu", "/client");
		
		return "client/welcome";
		
	}

	@RequestMapping(value="thongTinCaNhan/{id}",method=RequestMethod.GET)
	public String thongTinCaNhan(@PathVariable("id") String idCustomer,ModelMap model,HttpServletRequest request) {
		
		HttpSession session=request.getSession();
		
		Customers customersFind=(Customers) session.getAttribute("customer"); 
		
		session.setAttribute("trangCu", "/client/thongTinCaNhan/"+idCustomer);
		
		if(customersFind==null) {
			
			return "redirect:/client/login";
			
		}
		CategoriesDAO categoryDAO=new CategoriesLmpl();
		
		ProductDAO proDAO=new ProductLmpl();
		
		model.put("listMatHang", categoryDAO.layDanhSachCategory());
		
		model.put("listProduct",proDAO.layDanhSachProduct() );
		
		CustomerDAO cusDAO=new CustomerLmpl();
		
		Customers customers=cusDAO.timKiemId(idCustomer);
		
		model.put("customer", customers);
		
		return "client/thongTinCaNhan";
	}

	@RequestMapping(value = "thongTinCaNhan/sua",method=RequestMethod.POST)
	public String suaThongTinCaNhan(@ModelAttribute("customer") @Valid Customers    customer,
			BindingResult result,
			ModelMap model,HttpServletRequest request) {
		
		HttpSession session=request.getSession();
		
		Customers customersFind=(Customers) session.getAttribute("customer"); 
		
		if(customersFind==null) {
			
			return "redirect:/client/login";
			
		}
		
		CategoriesDAO categoryDAO=new CategoriesLmpl();
		
		ProductDAO proDAO=new ProductLmpl();
		
		model.put("listMatHang", categoryDAO.layDanhSachCategory());
		
		model.put("listProduct",proDAO.layDanhSachProduct() );
		
		CustomerDAO cusDAO=new CustomerLmpl();
		
		Customers customerOld=cusDAO.timKiemId(customer.getCustomerID());
		
		CustomerValidator cusvalidator=new CustomerValidator();
		
		cusvalidator.validate(customer,result);
		
		if(result.hasErrors()) {
			
			return "/client/thongTinCaNhan";
			
		}
		customerOld.setFirstName(customer.getFirstName());
		
		customerOld.setLastName(customer.getLastName());
		
		customerOld.setPhone(customer.getPhone());
		
		customerOld.setAddress(customer.getAddress());
		
		customerOld.setCity(customer.getCity());
		
		model.put("customer", customerOld);
		
		session.setAttribute("customer",customerOld); 
		
		return "/client/thongTinCaNhan";
	}

	@RequestMapping(value = "thongTinCaNhan/suaMK",method=RequestMethod.POST)
	public String suaThongTinCaNhan(@RequestParam("passOLD")String passOLD,
			@RequestParam("passNEW")String passNEW,
			@RequestParam("passNEWAgain")String passNEWAgain,
			ModelMap model,HttpServletRequest request) {
		
		HttpSession session=request.getSession();
		
		Customers customersFind=(Customers) session.getAttribute("customer"); 
		
		if(customersFind==null) {
			
			return "redirect:/client/login";
			
		}
		boolean checkPassOld=true;
		
		boolean checkPassNew=true;
		
		boolean checkPassNewAgain=true;
		
		AccountDAO accDAO=new AccountLmpl();
		
		Customers customer=(Customers) session.getAttribute("customer");
		
		ProductDAO proDAO=new ProductLmpl();
		
		CategoriesDAO categoryDAO=new CategoriesLmpl();
		
		model.put("listMatHang", categoryDAO.layDanhSachCategory());
		
		model.put("listProduct",proDAO.layDanhSachProduct());
		
		if(passOLD.isEmpty()==true) {
			
			model.put("errorPassOld","Bạn chưa nhập mật khẩu");
			
			checkPassOld=false;
			
		}
		
		if(passNEW.isEmpty()==true) {
			
			model.put("errorPassNew","Bạn chưa nhập mật khẩu mới");
			
			checkPassNew=false;
			
		}
		if(passNEWAgain.isEmpty()==true) {
			
			model.put("errorPassNewAgain","Bạn chưa nhập mật khẩu mới");
			
			checkPassNewAgain=false;
			
		}
		if(checkPassNew==true && checkPassNewAgain==true) {
			
			if(passNEW.equals(passNEWAgain)==false) {
				
				model.put("errorPassNewAgain", "mật khẩu không trùng khớp");
				
				checkPassNewAgain=false;
				
			}
		}

		if(checkPassNew==true && checkPassOld==true && checkPassNewAgain==true) {
			
			if(BCrypt.checkpw(passOLD,customer.getUserName().getPassword())==false) {
				
				model.put("errorPassOld", "mật khẩu cũ không đúng");
				
				checkPassOld=false;
				
			}
			if(checkPassOld==true) {
				
				PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
				
				String encodedPassword = passwordEncoder.encode(passNEWAgain);
				
				Account accout=new Account(customer.getUserName().getUserName(),encodedPassword);
				
				if(accDAO.capNhapAccount(accout)==true){
					
					model.put("errorPassOld","Cập nhập thành công");
					
				};
			}
		}
		model.put("customer", customer);
		
		session.setAttribute("customer",customer); 
		
		return "/client/thongTinCaNhan";
	}



	@RequestMapping(value="lienHe",method=RequestMethod.GET)
	public String lienHe(ModelMap model) {
		
		CategoriesDAO categoryDAO=new CategoriesLmpl();
		
		ProductDAO proDAO=new ProductLmpl();
		
		model.put("listMatHang", categoryDAO.layDanhSachCategory());
		
		model.put("listProduct",proDAO.layDanhSachProduct() );
		
		return "client/lienHe";
	}

	@RequestMapping(value="quenMK",method=RequestMethod.GET)
	public String quenMK(ModelMap model) {
		
		CategoriesDAO categoryDAO=new CategoriesLmpl();
		
		ProductDAO proDAO=new ProductLmpl();
		
		model.put("listMatHang", categoryDAO.layDanhSachCategory());
		
		model.put("listProduct",proDAO.layDanhSachProduct() );
		
		return "client/quenMatKhau";
	}

	@RequestMapping(value="xacMinhEmail",method=RequestMethod.POST)
	public String xacMinh(ModelMap model,@RequestParam("email") String email,HttpServletRequest request) {
		
		HttpSession session=request.getSession();
		
		boolean checkEmail=true;
		
		Customers cus=null;
		
		CustomerDAO cusDAO=new CustomerLmpl();
		
		CategoriesDAO categoryDAO=new CategoriesLmpl();
		
		ProductDAO proDAO=new ProductLmpl();
		
		model.put("listMatHang", categoryDAO.layDanhSachCategory());
		
		model.put("listProduct",proDAO.layDanhSachProduct() );
		
		if(email.isEmpty()==true) {
			
			model.put("errorEmail", "Bạn chưa nhập email");
			
			checkEmail=false;
			
		}
		if(checkEmail==true) {
			
			cus=cusDAO.timKiemEmail(email);
			
			if(cus==null) {
				
				model.put("errorEmail", "email không tồn tại");
				
				checkEmail=false;
				
			}
		}
		if(checkEmail==true) {
			
			session.setAttribute("userName", cus.getUserName().getUserName());
			
			try {
				
				int idRandDom=sendEmail(email);
				
				session.setAttribute("MaXM", String.valueOf(idRandDom));
				
			} catch (Exception e) {
				
				e.printStackTrace();
				
				return "admin/trangThaiGuiEmail";
			}
			
			model.put("errorEmail", "Email gửi thành công");
			
		}
		
		model.put("email",email);

		return "client/quenMatKhau";
	}
	private int sendEmail(String email) throws Exception{
		
		MimeMessage message = sender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(message);
		
		helper.setSubject("Xác nhận tài khoản từ cửa hàng BearToy");
		
		int random = (int)(Math.random() * 10000 + 1);
		
		helper.setText("Mã:"+random);
		
		helper.setTo(email);
		
		sender.send(message);
		
		return random;
	}

	@RequestMapping(value="xacMinhMa",method=RequestMethod.POST)
	public String processXacMinhMa(ModelMap model,@RequestParam("maXM") String maXM,HttpServletRequest request) {
		
		CategoriesDAO categoryDAO=new CategoriesLmpl();
		
		ProductDAO proDAO=new ProductLmpl();
		
		model.put("listMatHang", categoryDAO.layDanhSachCategory());
		
		model.put("listProduct",proDAO.layDanhSachProduct() );
		
		HttpSession session=request.getSession();
		
		String idRanDom=(String) session.getAttribute("MaXM");
		
		if(maXM.equals(idRanDom)) {
			
			String userName=(String) session.getAttribute("userName");
			
			model.put("userName", userName);
			
			return "client/doiMatKhau";
			
		}else {
			
			model.put("errorMAXM", "mã không trùng khớp");
			
			return "redirect:/client/xacMinhEmail";
			
		}
	}


	@RequestMapping(value="process-doiMK",method=RequestMethod.POST)
	public String processDoiMK(ModelMap model,@RequestParam("passNEW") String passOLD,@RequestParam("passNewAgain") String passNEW,HttpServletRequest request) {
		
		HttpSession session=request.getSession();
		
		String userName=(String) session.getAttribute("userName");
		
		CategoriesDAO categoryDAO=new CategoriesLmpl();
		
		ProductDAO proDAO=new ProductLmpl();
		
		model.put("listMatHang", categoryDAO.layDanhSachCategory());
		
		model.put("listProduct",proDAO.layDanhSachProduct() );
		
		boolean checkPassOld=true;
		
		boolean checkPassNew=true;
		
		if(passOLD.isEmpty()) {
			
			model.put("errorPassOld","Bạn chưa nhập mật khẩu");
			
			checkPassOld=false;
			
		}
		if(passNEW.isEmpty()) {
			
			model.put("errorPassNew","Bạn chưa nhập mật khẩu");
			
			checkPassOld=false;
			
		}
		if(checkPassNew==true && checkPassOld==true) {
			
			AccountDAO account=new AccountLmpl();
			
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
			
			String encodedPassword = passwordEncoder.encode(passNEW);
			
			Account acc=new Account(userName, encodedPassword);
			
			account.capNhapAccount(acc);
			
			return "client/trangThaiDoiMatKhau";
			
		}else {
			return "client/doiMatKhau";
			
		}
	}

	@RequestMapping(value="chiTietSanPham/{id}",method=RequestMethod.GET)
	public String chiTietSanPham(@PathVariable("id") String id,ModelMap model,HttpServletRequest request) {
		
		ProductDAO proDAO=new ProductLmpl();
		
		CategoriesDAO categoryDAO=new CategoriesLmpl();
		
		Products product=proDAO.timKiemId(id);
		
		List<Products> listProSanPhamLQ=proDAO.timKiemTheoMaCategory(product.getCategoryID().getCategoryID());
		
		if(listProSanPhamLQ.contains(product)) {
			
			listProSanPhamLQ.remove(product);
			
		}
		model.put("product",product);
		
		model.put("listMatHang", categoryDAO.layDanhSachCategory());
		
		model.put("listProSanPhamLQ", listProSanPhamLQ);
		
		HttpSession session=request.getSession();
		
		session.setAttribute("trangCu", "/client/chiTietSanPham/"+id);
		
		return "client/shopDetails";
	}

	@RequestMapping(value = "shop",method=RequestMethod.GET)
	public String shop(ModelMap model,HttpServletRequest request) {
		
		HttpSession session=request.getSession();
		
		session.setAttribute("trangCu", "/client/shop/all");
		
		ProductDAO proDAO=new ProductLmpl();
		
		CategoriesDAO cateDAO=new CategoriesLmpl();
		
		List<Products> listPro=proDAO.layDanhSachProduct();
		
		request.setAttribute("sortNameType","Mặc định");
		
		model.put("listProduct", hangConVaHangKhongCon(listPro));
		
		model.put("listMatHang", cateDAO.layDanhSachCategory());
		
		return "client/shop";
	}

	public static List<Products>  HightToLow ( List<Products> array) { 
		
		Collections.sort(array, new Comparator<Products>() {
			
			@Override
			public int compare(Products arg0, Products arg1) {
				
				return arg0.getUnitPrrice() > arg1.getUnitPrrice() ? -1 : (arg0.getUnitPrrice() < arg1.getUnitPrrice()) ? 1 : 0;
				
			}
		});
		
		return array;
	} 

	public static List<Products>  lowToHigh ( List<Products> array) { 
		
		Collections.sort(array, new Comparator<Products>() {
			
			@Override
			public int compare(Products arg0, Products arg1) {
				
				return arg0.getUnitPrrice() < arg1.getUnitPrrice() ? -1 : (arg0.getUnitPrrice() > arg1.getUnitPrrice()) ? 1 : 0;
				
			}
		});
		
		return array;
	} 

	public List<Products> hangCon(List<Products> hangCon){
		
		List<Products> listProducts=new ArrayList<Products>();
		
		hangCon.forEach(t->{
			
			if(t.getQuatityInStock()!=0) {
				
				listProducts.add(t);
				
			}
		});
		
		return listProducts;
	} 

	public List<Products> hangConVaHangKhongCon(List<Products> hangConVaHangKhongCon){
		
		List<Products> listProducts=new ArrayList<Products>();
		
		hangConVaHangKhongCon.forEach(t->{
			
			if(t.getQuatityInStock()!=0) {
				
				listProducts.add(t);
				
			}
		});

		hangConVaHangKhongCon.forEach(t->{
			
			if(t.getQuatityInStock()==0) {
				
				listProducts.add(t);
				
			}
		});

		return listProducts;
	}
	@RequestMapping(value="sort",method=RequestMethod.POST)
	public String sort(@RequestParam("sortId")String id,ModelMap model,HttpServletRequest request) {
		
		CategoriesDAO cateDAO=new CategoriesLmpl();
		
		ProductDAO proDAO=new ProductLmpl();
		
		if(id.equals("Mặc định")) {
			
			request.setAttribute("sortNameType", "Mặc định");
			
			return "redirect:/client/shop";
			
		}else if(id.equals("Giá cao → thấp")) {
			
			request.setAttribute("sortNameType", "Giá cao → thấp");
			
			model.put("listProduct", HightToLow(proDAO.layDanhSachProduct()));
			
		}else if(id.equals("Hàng còn")) {
			
			request.setAttribute("sortNameType", "Hàng còn");
			
			model.put("listProduct", hangCon(proDAO.layDanhSachProduct()));
			
		}else {
			request.setAttribute("sortNameType","Giá thấp → Giá cao");
			
			model.put("listProduct", lowToHigh(proDAO.layDanhSachProduct()));
			
		}
		model.put("listMatHang", cateDAO.layDanhSachCategory());
		
		HttpSession session=request.getSession();
		
		session.setAttribute("trangCu", "/client/shop/"+id);
		
		return "client/shop";
	}

	@RequestMapping(value="searchResult",method=RequestMethod.POST)
	public String searchProduct(@RequestParam("search")String id,ModelMap model,HttpServletRequest request) {

		CategoriesDAO cateDAO=new CategoriesLmpl();
		
		ProductDAO proDAO=new ProductLmpl();
		
		request.setAttribute("sortNameType","Mặc định");
		
		model.put("listProduct", proDAO.listTimKiemTenProduct(id));
		
		model.put("listMatHang", cateDAO.layDanhSachCategory());
		
		HttpSession session=request.getSession();
		
		session.setAttribute("trangCu", "/client/shop/"+id);
		
		return "client/shop";
	}

	@RequestMapping(value = "search",method=RequestMethod.GET)
	@ResponseBody
	public List<String> search(HttpServletRequest request) {
		
		ProductDAO proDAP=new ProductLmpl();
		
		return proDAP.timKiemTenProduct(request.getParameter("term"));
		
	}

	@RequestMapping(value = "shop/{id}",method=RequestMethod.GET)
	public String findItemInshop(@PathVariable("id") String id,ModelMap model,HttpServletRequest request) {
		
		ProductDAO proDAO=new ProductLmpl();
		
		CategoriesDAO cateDAO=new CategoriesLmpl();
		
		if(id.equals("all")) {
			
			model.put("listProduct", proDAO.layDanhSachProduct());
			
		}else {
			
			model.put("listProduct", proDAO.timKiemTheoMaCategory(id));
			
		}
		request.setAttribute("sortNameType","Mặc định");
		
		model.put("listMatHang", cateDAO.layDanhSachCategory());
		
		HttpSession session=request.getSession();
		
		session.setAttribute("trangCu", "/client/shop/"+id);
		
		HttpServletRequest requestX = (HttpServletRequest) request;
		
		String path = requestX.getRequestURI().substring(requestX.getContextPath().length());
		
		return "client/shop";
	}

	public List<Products> listProductTheoMucGia(int tuGia,int denGia,boolean type){
		
		ProductDAO proDAO=new ProductLmpl();
		
		List<Products> listP=new ArrayList<Products>();
		
		proDAO.layDanhSachProduct().forEach(t->{
			
			if(type==true) {
				
				if(tuGia<=t.getUnitPrrice() && t.getUnitPrrice()<=denGia) {
					
					listP.add(t);
					
				}
			}else{
				
				if(denGia<=t.getUnitPrrice()) {
					
					listP.add(t);
					
				}
			}

		});
		return listP;
	}

	@RequestMapping(value = "shop/MucGia/{id}",method=RequestMethod.GET)
	public String findMucGia(@PathVariable("id") String id,ModelMap model,HttpServletRequest request) {
		
		CategoriesDAO cateDAO=new CategoriesLmpl();
		
		switch (id) {
		
		case "1":
			
			model.put("listProduct",listProductTheoMucGia(0,300000, true));
			
			break;
			
		case "2":
			
			model.put("listProduct",listProductTheoMucGia(300000,500000, true));
			
			break;
			
		case "3":
			
			model.put("listProduct",listProductTheoMucGia(500000,1000000, true));
			
			break;

		case "4":
			
			model.put("listProduct",listProductTheoMucGia(1000000,1500000, true));
			
			break;

		case "5":
			
			model.put("listProduct",listProductTheoMucGia(0,2000000, false));
			
			break;
			
		default:
			
			break;
			
		}
		
		request.setAttribute("sortNameType","Mặc định");
		
		model.put("listMatHang", cateDAO.layDanhSachCategory());
		
		HttpSession session=request.getSession();
		
		session.setAttribute("trangCu", "/client/shop/"+id);
		
		return "client/shop";
	}

	@RequestMapping(value = "login",method=RequestMethod.GET)
	public String login(Model model,HttpServletRequest request,ModelMap modal) {
		
		CategoriesDAO categoryDAO=new CategoriesLmpl();
		
		ProductDAO proDAO=new ProductLmpl();
		
		modal.put("listMatHang", categoryDAO.layDanhSachCategory());
		
		modal.put("listProduct",proDAO.layDanhSachProduct() );
		
		HttpSession session=request.getSession();
		
		Customers customers=(Customers) session.getAttribute("customer"); 
		
		if(customers!=null) {
			
			return "redirect:/client/shop";
			
		}
		
		model.addAttribute("account",new Account());
		
		return "client/login";
	}

	@RequestMapping(value = "process-login",method=RequestMethod.POST)
	public String processLogin(@ModelAttribute("account") Account account,Model model,HttpServletRequest request,HttpSession sessionCart) {
		
		String trangCu=null;
		
		try {
			trangCu=(String) sessionCart.getAttribute("trangCu");
		} catch (Exception e) {
		}
		
		boolean checkUserName=true;
		
		boolean checkPassword=true;
		
		AccountDAO accDAo=new AccountLmpl();
		
		if(account.getUserName().isEmpty()==true) {
			
			checkUserName=false;
			
			model.addAttribute("errorUserName", "user name chưa nhập");
			
		}
		if(account.getPassword().isEmpty()==true) {
			
			checkPassword=false;
			
			model.addAttribute("erroPass", "password chưa nhập");
			
		}
		
		Account acc=accDAo.timKiemUsername(account.getUserName());
		
		if(checkUserName==true) {
			
			if(acc==null) {
				
				checkUserName=false;
				
				model.addAttribute("errorUserName", "user name không tồn tại");
				
			}else {
				
				if(BCrypt.checkpw(account.getPassword(),acc.getPassword())==false) {
					
					model.addAttribute("erroPass", "pass word không đúng");
					
					checkUserName=false;
					
				}
			}
		}

		if(checkUserName==true) {
			
			if(account.getUserName().equals("admin")) {
				
				checkUserName=false;
				
				model.addAttribute("errorUserName", "user name không hợp lệ");
				
			}
		}

		if(checkPassword==true) {

		}
		if(checkUserName==true && checkPassword==true) {	
			
			CustomerDAO cusDAO=new CustomerLmpl();
			
			HttpSession session=request.getSession(true);
			
			Customers customer=cusDAO.timKiemUserName(acc.getUserName());
			
			session.setAttribute("customer",customer);
			
			if(trangCu!=null) {
				
				return "redirect:"+trangCu;
				
			}
			
			return "/client/login";
			
		}else {
			
			return "/client/login";
			
		}
	}

	int tongR=0;
	@RequestMapping(value="deleteMyCart/{id}",method=RequestMethod.GET)
	public String removeOneItemInMyCart(@PathVariable("id") String idProduct,HttpServletRequest request,HttpSession sessionCart) {
		
		String trangCu=(String) sessionCart.getAttribute("trangCu");
		
		List<Products> list = (List<Products>) sessionCart.getAttribute("listPro");
		
		tongR= (int) sessionCart.getAttribute("tong");
		
		ProductDAO proDAO=new ProductLmpl();
		
		Products product=proDAO.timKiemId(idProduct);

		for(int i=0;i<list.size();i++) {
			
			if(list.get(i).getProductID().equalsIgnoreCase(idProduct)) {
				
				list.remove(list.get(i));
				
				tongR-=product.getUnitPrrice();
				
			}
		}

		sessionCart.setAttribute("listPro", list);
		
		sessionCart.setAttribute("countCart",list.size());
		
		sessionCart.setAttribute("tong", tongR);
		
		if(trangCu!=null) {
			
			return "redirect:"+trangCu;
			
		}
		return "redirect:/client/shop";
	}
	
	@RequestMapping(value="lowMyCart/{id}",method=RequestMethod.GET)
	public String lowMyCart(@PathVariable("id") String idProduct,HttpServletRequest request,HttpSession sessionCart) {
		
		String trangCu=(String) sessionCart.getAttribute("trangCu");
		
		List<Products> list = (List<Products>) sessionCart.getAttribute("listPro");
		
		tongR= (int) sessionCart.getAttribute("tong");
		
		ProductDAO proDAO=new ProductLmpl();
		
		Products product=proDAO.timKiemId(idProduct);
		

		for(int i=0;i<list.size();i++) {
			
			if(list.get(i).getProductID().equalsIgnoreCase(idProduct)) {
				
				list.get(i).setQuatityInStock(list.get(i).getQuatityInStock()-1);
				
				if(list.get(i).getQuatityInStock()<=0) {
					
					list.remove(list.get(i));
					
					tongR-=product.getUnitPrrice();
					
				}else {
					
					tongR-=product.getUnitPrrice();
					
				}
				
			}
		}

		sessionCart.setAttribute("listPro", list);
		
		sessionCart.setAttribute("countCart",list.size());
		
		sessionCart.setAttribute("tong", tongR);
		
		if(trangCu!=null) {
			
			return "redirect:"+trangCu;
			
		}
		return "redirect:/client/shop";
	}
	
	
	

	

	@RequestMapping(value = "addCart/{id}",method=RequestMethod.GET)
	public String addCart(@PathVariable("id") String idProduct,HttpServletRequest request,HttpSession sessionCart) {
		
		String trangCu=(String) sessionCart.getAttribute("trangCu");
		
		int tong=0;
		
		ProductDAO proDAP=new ProductLmpl();
		
		List<Products> listProduct=null;
		
		if(sessionCart.getAttribute("listPro")==null) {
			
			listProduct=new ArrayList<Products>();
			
			Products pro=proDAP.timKiemId(idProduct);
			
			Products productDTO=new Products(pro.getProductID(), pro.getSupplierID(), pro.isDiscontinuted(), pro.getMoTa(), pro.getProductName(), 1, pro.getCategoryID(), pro.getUnitPrrice(), pro.getPicture());
			
			listProduct.add(productDTO);
			
			tong+=listProduct.get(0).getUnitPrrice();
			
		}else {
			List<Products> list = (List<Products>) sessionCart.getAttribute("listPro");
			
			int tongR= (int) sessionCart.getAttribute("tong");
			
			listProduct=list;
			
			boolean result=true;
			
			for(int i=0;i<listProduct.size();i++) {
				
				if(listProduct.get(i).getProductID().equals(idProduct)) {
					
					listProduct.get(i).setQuatityInStock(listProduct.get(i).getQuatityInStock()+1);
					
					tongR+=listProduct.get(i).getUnitPrrice();
					
					result=false;
					
					break;
					
				}
			}
			if(result==true) {
				
				Products pro=proDAP.timKiemId(idProduct);
				
				Products productDTO2=new Products(pro.getProductID(), pro.getSupplierID(), pro.isDiscontinuted(), pro.getMoTa(), pro.getProductName(), 1, pro.getCategoryID(), pro.getUnitPrrice(), pro.getPicture());
				
				listProduct.add(productDTO2);
				
				tongR+=productDTO2.getUnitPrrice();
			}
			
			tong=tongR;
			
		}
		sessionCart.setAttribute("listPro", listProduct);
		
		sessionCart.setAttribute("countCart",listProduct.size());
		
		sessionCart.setAttribute("tong", tong);
		
		if(trangCu!=null) {
			
			return "redirect:"+trangCu;
			
		}
		return "redirect:/client/shop";
	}

	@RequestMapping(value = "dangKy",method=RequestMethod.GET)
	public String dangKy(ModelMap model) {
		
		CategoriesDAO cateDAO=new CategoriesLmpl();
		
		model.put("listMatHang", cateDAO.layDanhSachCategory());
		
		model.put("customer", new Customers());
		
		return "/client/dangKy";
	}

	@RequestMapping(value = "thanhToan",method=RequestMethod.GET)
	public String thanhToan(HttpServletRequest request,ModelMap model) {
		
		HttpSession session=request.getSession();
		
		Customers customer=(Customers) session.getAttribute("customer");
		
		session.setAttribute("trangCu","/client/thanhToan");
		
		if(customer==null) {
			
			model.addAttribute("account", new Account());
			
			model.addAttribute("errorUserName", "vui lòng đăng nhập trước");
			
			return "/client/login";
		}
		
		CategoriesDAO categoryDAO=new CategoriesLmpl();
		
		ProductDAO proDAO=new ProductLmpl();
		
		model.put("listMatHang", categoryDAO.layDanhSachCategory());
		
		model.put("listProduct",proDAO.layDanhSachProduct() );
		
		session.setAttribute("trangCu", "/client/thanhToan");
		
		return "/client/thanhToan2";
	}

	@RequestMapping(value = "process-dangKy",method=RequestMethod.POST)
	public String processDangKy( @ModelAttribute("customer") @Valid Customers    customer,
			BindingResult result,
			Model model,HttpServletRequest request) {
		
		CustomerDAO cateDAO=new CustomerLmpl();
		
		boolean result1=false;
		
		String idRandom="";
		
		int random=0;
		
		while(result1==false) {
			
			random = (int)(Math.random() * 10000 + 1);
			
			idRandom="CT"+random;
			
			if(cateDAO.timKiemId(idRandom)==null) {
				
				result1=true;
				
			};
		}
		
		customer.setCustomerID(idRandom);


		CustomerValidator cusvalidator=new CustomerValidator();
		
		cusvalidator.validate(customer,result);

		CategoriesDAO categoriDAO=new CategoriesLmpl();
		
		AccountDAO daoAcc=new AccountLmpl();
		
		CustomerDAO daoCus=new CustomerLmpl();
		
		boolean checkUserName=true;
		
		boolean checkPassword=true;

		String userName=customer.getUserName().getUserName();
		
		String password=customer.getUserName().getPassword();

		if(userName.isEmpty()) {
			
			checkUserName=false;
			
			model.addAttribute("errorUserName","username can't empty");
			
		}
		if(checkUserName==true) {
			
			if(daoAcc.timKiemUsername(userName)!=null) {
				
				checkUserName=false;
				
				model.addAttribute("errorUserName","username had account");
				
			}
		}
		if(password.isEmpty()) {
			
			checkPassword=false;
			
			model.addAttribute("errorPassword","password can't empty");
			
		}
		if(checkPassword==true) {
			
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
			
			String encodedPassword = passwordEncoder.encode(password);
			
			customer.getUserName().setPassword(encodedPassword);
			
		}
		
		model.addAttribute("listMatHang", categoriDAO.layDanhSachCategory());
		
		if(result.hasErrors()) {
			
			
			return "/client/dangKy";
			
		}
		if(checkUserName==false || checkPassword==false) {
			
			
			return "/client/dangKy";
			
		}

		daoCus.themCustomer(customer);

		HttpSession session=request.getSession();
		
		session.setAttribute("customer", customer);

		return "redirect:/client/shop";
	}


	@RequestMapping(value = "huyMua",method=RequestMethod.GET)
	public String huyMua(HttpServletRequest request,HttpSession sessionCart) {
		
		String trangCu=(String) sessionCart.getAttribute("trangCu");
		
		HttpSession session=request.getSession();
		
		sessionCart.setAttribute("listPro", null);
		
		sessionCart.setAttribute("countCart",null);
		
		sessionCart.setAttribute("tong", null);
		
		return "redirect:/client";
	}

	@RequestMapping(value = "datHang",method=RequestMethod.GET)
	public String datHang(HttpServletRequest request,ModelMap model) throws ParseException {
		
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
		
		HttpSession session=request.getSession();
		
		Customers customers=(Customers) session.getAttribute("customer"); 
		
		LocalDate dateNow=LocalDate.now();
		
		String valueDateNow=dateNow.getDayOfMonth()+"/"+dateNow.getMonthValue()+"/"+dateNow.getYear();
		
		SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy");
		
		Date dateStr=formatter.parse(valueDateNow);
		
		Orders od=new Orders(customers,dateStr, idRandom, customers.getCity(), dateStr, customers.getAddress());
		
		if(odDAO.themOrder(od)==true) {
			
			List<Products> listProduct=(List<Products>) session.getAttribute("listPro");
			
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
			
			session.setAttribute("listPro", null);
			
			session.setAttribute("countCart",null);
			
			session.setAttribute("tong", null);
			
			CategoriesDAO categoryDAO=new CategoriesLmpl();
			
			model.put("listMatHang", categoryDAO.layDanhSachCategory());
			
			model.put("listProduct",proDAO.layDanhSachProduct() );
			
			return "/client/datHangThanhCong";
		};

		return "";
	}

	@RequestMapping(value = "lichSuGiaoDich",method=RequestMethod.GET)
	public String lichSuGiaoDich(HttpServletRequest request,HttpSession sessionCart,ModelMap model) {
		
		HttpSession session=request.getSession();
		
		Customers customer=(Customers) session.getAttribute("customer");
		
		session.setAttribute("trangCu","/client/lichSuGiaoDich");
		
		if(customer==null) {
			
			return "redirect:/client/login";
			
		}
		
		CategoriesDAO categoryDAO=new CategoriesLmpl();
		
		ProductDAO proDAO=new ProductLmpl();
		
		OrderDAO odDAO=new OrderLmpl();
		
		List<Orders> listOrders=odDAO.timKiemMaKH(customer.getCustomerID());
		
		List<LichSuOrder> listLichSuOrder=new ArrayList<LichSuOrder>();
		
		int tongChiPhi=0;
		
		for(int i=0;i<listOrders.size();i++) {
			
			List<OrderDetails> listOrderDetails=odDAO.timKiemMaOrderDetails(listOrders.get(i).getOrderID());
			
			List<OrderDetailsProduct> listOrderDetailsProduct=new ArrayList<OrderDetailsProduct>();
			
			tongChiPhi=0;
			
			for(int j=0;j<listOrderDetails.size();j++) {
				
				Products pro=proDAO.timKiemId(listOrderDetails.get(j).getId().getProductID());
				
				OrderDetailsProduct orderDetailsProduct=new OrderDetailsProduct(listOrderDetails.get(j),pro);
				
				listOrderDetailsProduct.add(orderDetailsProduct);
				
				tongChiPhi+=listOrderDetails.get(j).getTotalAmmount();
				
			}
			LichSuOrder lichSuOrder=new LichSuOrder(listOrders.get(i), listOrderDetailsProduct,tongChiPhi);
			
			listLichSuOrder.add(lichSuOrder);
			
		}
		model.put("listLichSuOrder",listLichSuOrder);
		
		model.put("listMatHang", categoryDAO.layDanhSachCategory());
		
		model.put("listProduct",proDAO.layDanhSachProduct() );

		return "/client/lichSuGiaoDich";
	}


	@RequestMapping(value = "dangXuat",method=RequestMethod.GET)
	public String dangXuat(HttpServletRequest request,HttpSession sessionCart) {
		
		String trangCu=(String) sessionCart.getAttribute("trangCu");
		
		HttpSession session=request.getSession();
		
		session.setAttribute("customer", null);
		
		sessionCart.setAttribute("listPro", null);
		
		sessionCart.setAttribute("countCart",null);
		
		sessionCart.setAttribute("tong", null);
		
		if(trangCu!=null) {
			
			return "redirect:"+trangCu;
			
		}
		
		return "redirect:/client/shop";
	}
}

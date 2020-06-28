package com.spring.controller.admin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.entities.OrderDetails;
import com.spring.entities.Orders;
import com.spring.entities.Products;
import com.spring.entities.util.LichSuOrder;
import com.spring.entities.util.OrderDetailsProduct;
import com.spring.entities.util.TwoForm;
import com.spring.services.Impl.CategoriesLmpl;
import com.spring.services.Impl.OrderLmpl;
import com.spring.services.Impl.ProductLmpl;
import com.spring.services.Impl.SupplierLmpl;
import com.spring.services.dao.CategoriesDAO;
import com.spring.services.dao.OrderDAO;
import com.spring.services.dao.ProductDAO;
import com.spring.services.dao.SupplierDAO;

@Controller
@RequestMapping("/admin/datHang")
@ControllerAdvice
public class OrderController {
	
	@RequestMapping(value = "danhSach",method=RequestMethod.GET)
	public String danhSach(ModelMap model) {
		
		ProductDAO proDAO=new ProductLmpl();
		
		OrderDAO odDAO=new OrderLmpl();
		
		List<Orders> listOrders=odDAO.layDanhSachOrders();
		
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
		
		model.put("listOrder",listLichSuOrder);
		
		return "/admin/datHang/danhSach";
	}
	@RequestMapping(value = "edit/{id}",method=RequestMethod.GET)
	public String sua(@PathVariable("id") String id,ModelMap model) {
		
		ProductDAO proDAO=new ProductLmpl();
		
		OrderDAO odDAO=new OrderLmpl();
		
		Orders od=odDAO.timKiemId(id);
		
		if(od!=null) {
			
			OrderDetails oddt=odDAO.timKiemOrderDetails(od.getOrderID());
			
			TwoForm twoForm=new TwoForm();
			
			twoForm.setOrderDetails(oddt);
			
			twoForm.setOrders(od);
			
			model.addAttribute("twoForm", twoForm);
			
			model.addAttribute("listProduct", proDAO.layDanhSachProduct());
			
		}
		return "/admin/datHang/edit";
	}
	
	@RequestMapping(value = "remove/{id}",method=RequestMethod.GET)
	public String xoaOrder(@PathVariable("id") String idCate,HttpServletRequest request,ModelMap model) {
		
		OrderDAO odDAO=new OrderLmpl();
		
		Orders order=odDAO.timKiemId(idCate);
		
		if(order!=null) {
			
				odDAO.timKiemMaOrderDetails(order.getOrderID()).forEach(x->{
					
					odDAO.xoaOrderDetails(x.getId().getOrderID());
					
				});
				
		}
		
		odDAO.xoaOrder(order.getOrderID());
		
		return "redirect:/admin/datHang/danhSach";
	}
	
	
}

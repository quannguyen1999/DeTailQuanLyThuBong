package com.spring.services.dao;

import java.util.List;

import com.spring.entities.OrderDetails;
import com.spring.entities.Orders;
import com.spring.entities.Suppliers;

public interface OrderDAO {
	public boolean themOrder(Orders od) ;
	public boolean capNhapOrder(Orders od) ;
	public boolean capNhapOrderDetail(OrderDetails od) ;
	public boolean xoaOrder(String ma) ;
	public boolean xoaOrderDetails(String ma);
	public boolean xoaOrderByProductID(String ma);
	public List<Orders> layDanhSachOrders();
	public Orders timKiemId(String ma);
	public List<Orders> timKiemMaKH(String ma);
	public List<OrderDetails> timKiemMaOrderDetails(String ma);
	public OrderDetails timKiemOrderDetails(String ma);
	public List<OrderDetails> timKiemTheoProductID(String ma);
	public  boolean themOrderDetails(OrderDetails nv);
}

<%@page import="com.spring.services.Impl.ProductLmpl"%>
<%@page import="com.spring.services.dao.ProductDAO"%>
<%@page import="com.spring.entities.Products"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<!-- Mobile Metas -->
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Site Metas -->
<title>Toy shop - Ecommerce Bootstrap 4 HTML Template</title>
<meta name="keywords" content="">
<meta name="description" content="">
<meta name="author" content="">

<!-- Site Icons -->
<link rel="shortcut icon"
	href="${pageContext.request.contextPath }/resources/client/images/logo1.png"
	type="image/x-icon">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/client/css/bootstrap.min.css">
<!-- Site CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/client/css/style.css">
<!-- Responsive CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/client/css/responsive.css">
<!-- Custom CSS -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/client/css/custom.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/client/search/jquery-ui.css">

<script
	src="${pageContext.request.contextPath }/resources/client/search/jquery-1.12.4.js"></script>
<script
	src="${pageContext.request.contextPath }/resources/client/search/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath }/resources/toastr/toastr.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/toastr/toastr.min.css">
<script src="${pageContext.request.contextPath }/resources/client/js/ajax/ajaxClient.js"></script>
<script
	src="${pageContext.request.contextPath }/resources/client/js/ajax/ajaxClient.js"></script>
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<style>
.saleEdit {
	color: #ffffff;
	padding: 2px 10px;
	font-weight: 700;
	text-transform: uppercase;
}
.imageOut{
	filter: brightness(50%);
}
</style>
</head>
<body>
	<!-- Start Main Top -->
	<div class="main-top">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">

					<div class="right-phone-box">
						<p>
							Hotline :- <a href="#"> +0708821227</a>
						</p>
					</div>
					<div class="our-link">
						<c:if test="${empty customer.firstName}">
							<ul>
								<li><a
									href="${pageContext.request.contextPath}/client/login"><i
										class="fa fa-user s_color"></i> Đăng nhập</a></li>
							</ul>
						</c:if>
						<c:if test="${!empty customer.firstName}">
							<ul>
								<li><a
									href="${pageContext.request.contextPath}/client/thongTinCaNhan/${customer.customerID}"><i
										class="fa fa-user s_color"></i> Account: ${customer.firstName}
										${customer.lastName}</a></li>
								<li><a
									href="${pageContext.request.contextPath }/client/lichSuGiaoDich"><i
										class="fas fa-headset"></i>Lịch sử giao dịch</a></li>
							</ul>
						</c:if>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
					<div class="login-box">
						<c:if test="${empty customer.firstName}">
							<a href="${pageContext.request.contextPath}/client/dangKy"
								style="color: white;">Đăng ký</a>
						</c:if>
						<c:if test="${!empty customer.firstName}">
							<a href="${pageContext.request.contextPath}/client/dangXuat"
								style="color: white;">Đăng xuất</a>
						</c:if>
					</div>
					<div class="text-slid-box">
						<div id="offer-box" class="carouselTicker">
							<ul class="offer-box">
								<li><i class="fab fa-opencart"></i>Cửa hàng thú bông</li>
								<li><i class="fab fa-opencart"></i>Hàng ngàn sản phẩm đa
									dạng</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End Main Top -->

	<!-- Start Main Top -->
	<header class="main-header">
		<!-- Start Navigation -->
		<nav
			class="navbar navbar-expand-lg navbar-light bg-light navbar-default bootsnav">
			<div class="container">
				<!-- Start Header Navigation -->
				<div class="navbar-header">
					<button class="navbar-toggler" type="button" data-toggle="collapse"
						data-target="#navbar-menu" aria-controls="navbars-rs-food"
						aria-expanded="false" aria-label="Toggle navigation">
						<i class="fa fa-bars"></i>
					</button>
					<a class="navbar-brand"
						href="${pageContext.request.contextPath}/client"><img
						src="${pageContext.request.contextPath }/resources/client/images/logo.png"
						width="100px;" class="logo" alt=""></a>
				</div>
				<!-- End Header Navigation -->

				<!-- Collect the nav links, forms, and other content for toggling -->
				<div class="collapse navbar-collapse" id="navbar-menu">
					<ul class="nav navbar-nav ml-auto" data-in="fadeInDown"
						data-out="fadeOutUp">
						<li class="nav-item active"><a class="nav-link"
							href="${pageContext.request.contextPath}/client">Trang chủ</a></li>
						<li class="dropdown "><a style="color: #9932CC;"
							href="${pageContext.request.contextPath}/client/shop"
							class="nav-link dropdown-toggle" data-toggle="dropdown">Cửa
								hàng</a>
							<ul class="dropdown-menu">
								<c:forEach var="category" items="${listMatHang}">
									<li><a style="color: white;"
										href="${pageContext.request.contextPath}/client/shop/${category.categoryID}">${category.categoryName }</a></li>
								</c:forEach>
							</ul></li>
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/client/lienHe">Liên
								hệ</a></li>
					</ul>
				</div>
				<!-- /.navbar-collapse -->

				<!-- Start Atribute Navigation -->
				<div class="attr-nav">
					<ul>
						<li class="search"><a href="#"><i class="fa fa-search"></i></a></li>
						<li class="side-menu"><a href="#" onclick="clickMyCart()">
								<i class="fa fa-shopping-bag"></i> <span class="badge">
							</span>
								<p>Xem giỏ hàng</p>
						</a></li>
					</ul>
				</div>
				<!-- End Atribute Navigation -->
			</div>
			<!-- Start Side Menu -->
			<div class="side" id="startSide">
				<a href="#" class="close-side"><i class="fa fa-times"></i></a>
				<li class="cart-box">
					<ul class="cart-list"  id="ajaxGetUserServletResponse" >
						<c:forEach var="productX" items="${listPro}">
							<li><a href="#" class="photo"> <img
									src="data:image/png;base64,${productX.picture }"
									class="cart-thumb" alt="Image">
							</a>
								<h6>
									<p>${productX.productName}</p>
									<br> <a style="color: black;"
										href="${pageContext.request.contextPath}/client/chiTietSanPham/${productX.productID}">Chi
										tiết</a> |
									<button class="cart btn hvr-hover"
										style="margin-top: -10px; color: white;"
										onclick="myFunction(this.value)" value="${productX.productID}">+
									</button>
									|
									<button class="cart btn hvr-hover"
										style="margin-top: -10px; color: white;"
										onclick="lowMyCartFunction(this.value,this.value)"
										value="${productX.productID}">-</button>
									<button class="cart btn"
										style="background-color: red; width: 100%; margin-top: 10px; color: white;"
										onclick="deleteMyCartFunction(this.value,this.value)"
										value="${productX.productID}">Xóa</button>
								</h6>
								<p>
									${productX.quatityInStock }x - <span class="price"><fmt:formatNumber
											type="number" maxFractionDigits="3"
											value="${productX.unitPrrice }" /> đ</span>
								</p></li>
						</c:forEach>
						<c:if test="${empty listPro}">
							<li>Chưa có sản phẩm nào</li>
						</c:if>
						<li class="total"><c:if test="${!empty countCart}">
								<a href="${pageContext.request.contextPath }/client/thanhToan"
									class="btn btn-default hvr-hover btn-cart">Thanh toán</a>
								<span class="float-right"><strong>Tổng</strong>:<fmt:formatNumber
										type="number" maxFractionDigits="3" value="${tong}" /> đ</span>
							</c:if></li>

					</ul>
				</li>
			</div>
			<!-- End Side Menu -->
		</nav>
		<!-- End Navigation -->
	</header>
	<!-- End Main Top -->

	<!-- Start Top Search -->
	<div class="top-search">
		<div class="container">
			<form class="login100-form validate-form" method="post"
				action="${pageContext.request.contextPath }/client/searchResult">
				<div class="input-group" id="demo" class="collapse">
					<span class="input-group-addon"><i class="fa fa-search"></i></span>
					<input type="text" id="productName" class="form-control"
						placeholder="Search" name="search"> <span
						class="input-group-addon close-search"><i
						class="fa fa-times"></i></span>
				</div>
			</form>
		</div>
	</div>
	<!-- End Top Search -->

	<!-- Start All Title Box -->
	<div class="all-title-box">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<h2>Cửa hàng</h2>
					<ul class="breadcrumb">
						<li class="breadcrumb-item"><a
							href="${pageContext.request.contextPath }/client/shop">Trang
								chủ</a></li>
						<li class="breadcrumb-item active">Cửa hàng</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!-- End All Title Box -->

	<!-- Start Shop Page  -->
	<div class="shop-box-inner" style="padding-top: 10px;">
		<div class="container">
			<div class="row">
				<div
					class="col-xl-9 col-lg-9 col-sm-12 col-xs-12 shop-content-right">
					<div class="right-product-box">
						<div class="product-item-filter row">
							<div class="col-12 col-sm-8 text-center text-sm-left">
								<div class="toolbar-sorter-right">
									<form method="post"
										action="${pageContext.request.contextPath}/client/sort">
										<input style="height: 41px;" type="submit" class="cart"
											value="sắp xếp"> <select id="basic"
											class="selectpicker show-tick form-control" name="sortId"
											data-placeholder="$ USD">
											<%
												String macDinh = "Mặc định";
											%>
											<%
												String giaCaoThap = "Giá cao → thấp";
											%>
											<%
												String giaThapCao = "Giá thấp → Giá cao";
											%>
											<%
												String hangCon = "Hàng còn";
											%>
											<%
												List<String> listDanhSach = new ArrayList<String>();
											%>
											<%
												listDanhSach.add(macDinh);
											%>
											<%
												listDanhSach.add(giaCaoThap);
											%>
											<%
												listDanhSach.add(giaThapCao);
											%>
											<%
												listDanhSach.add(hangCon);
											%>
											<%
												String chonKieuSapXep = (String) request.getAttribute("sortNameType");
											%>
											<option data-display="Select"><%=chonKieuSapXep%></option>
											<%
												for (int i = 0; i < listDanhSach.size(); i++) {
											%>
											<%
												if (listDanhSach.get(i).equals(chonKieuSapXep) == false) {
											%>
											<option value="<%=listDanhSach.get(i)%>"><%=listDanhSach.get(i)%></option>
											<%
												}
											%>
											<%
												}
											%>
										</select>
									</form>
								</div>
								<p>Có ${fn:length(listProduct)} sản phẩm</p>
							</div>

							<div class="col-12 col-sm-4 text-center text-sm-right">
								<ul class="nav nav-tabs ml-auto">
									<li><a class="nav-link active" href="#grid-view"
										data-toggle="tab"> <i class="fa fa-th"></i>
									</a></li>
									<li><a class="nav-link" href="#list-view"
										data-toggle="tab"> <i class="fa fa-list-ul"></i>
									</a></li>
								</ul>
							</div>
						</div>

						<div class="product-categorie-box">
							<div class="tab-content">
								<div role="tabpanel" class="tab-pane fade show active"
									id="grid-view">
									<div class="row">
										<%
											List<Products> listProduct = (ArrayList<Products>) request.getAttribute("listProduct");
										%>
										<%
											if (listProduct.size() == 0) {
										%>
										<div class="col-sm-12 col-md-12 col-lg-12 col-xl-12">
											<h3 style="text-align: center;"><%="không còn sản phẩm nào"%></h3>
										</div>
										<%
											}
										%>
										<c:forEach var="product" items="${listProduct}">
											<div class="col-sm-6 col-md-6 col-lg-4 col-xl-4">
												<div class="products-single fix">
													<div class="box-img-hover" style="height: 250px;">
														<div class="type-lb">
															<c:if test="${product.quatityInStock==0}">
															<p class="saleEdit"
																style="-ms-transform: rotate(20deg);; font-size: 30px; padding-top: 75px; padding-right: 60px;; text-align: center; color: white; background-color: transparent;">Hết
																hàng</p>
															</c:if>
															<c:if test="${product.quatityInStock!=0}">
																  <p class="sale" style="background-color:black;">New</p>
															</c:if>
															

														</div>
														<c:if test="${product.quatityInStock==0}">
															<img src="data:image/png;base64,${product.picture }"
															class="img-fluid imageOut" alt="Image">
														</c:if>
														<c:if test="${product.quatityInStock!=0}">
															<img src="data:image/png;base64,${product.picture }"
															class="img-fluid" alt="Image">
														</c:if>
														<div class="mask-icon">
															<ul>
																<li><a
																	href="${pageContext.request.contextPath }/client/chiTietSanPham/${product.productID}"
																	data-toggle="tooltip" data-placement="right"
																	title="Chi tiết"><i class="fas fa-eye"></i></a></li>
																<li><a 
																href="#"
																 data-toggle="tooltip"
																	data-placement="right" title="So sánh"><i
																		class="fas fa-sync-alt"></i></a></li>
																<li><a href="#" data-toggle="tooltip"
																	data-placement="right" title="yêu thích"><i
																		class="far fa-heart"></i></a></li>
															</ul>
															<c:if test="${product.quatityInStock!=0}">
																<button class="cart btn hvr-hover"
																	id="fuck" onclick="myFunction(this.value)" style="color:white;margin-top:150px;"
																	value="${product.productID}"
																	>Mua
																	ngay</button>
															</c:if>
														</div>
													</div>
													<div class="why-text">
														<h4>${product.productName }</h4>

														<h5>
															<fmt:formatNumber type="number" maxFractionDigits="3"
																value="${product.unitPrrice }" />
															đ
														</h5>
													</div>
												</div>
											</div>
										</c:forEach>
									</div>
								</div>
								<div role="tabpanel" class="tab-pane fade" id="list-view">
									<%
										if (listProduct.size() == 0) {
									%>
									<div class="col-sm-12 col-md-12 col-lg-12 col-xl-12">
										<h3 style="text-align: center;"><%="không còn sản phẩm nào"%></h3>
									</div>
									<%
										}
									%>
									<c:forEach var="product" items="${listProduct}">
										<div class="list-view-box">
											<div class="row">
												<div class="col-sm-6 col-md-6 col-lg-4 col-xl-4">
													<div class="products-single fix">
														<div class="box-img-hover">
															<div class="type-lb">
															<c:if test="${product.quatityInStock==0}">
															<p class="saleEdit"
																style="-ms-transform: rotate(20deg);; font-size: 30px; padding-top: 75px; padding-right: 60px;; text-align: center; color: white; background-color: transparent;">Hết
																hàng</p>
															</c:if>
															<c:if test="${product.quatityInStock!=0}">
																  <p class="sale" style="background-color:black;">New</p>
															</c:if>
															</div>
															<c:if test="${product.quatityInStock==0}">
															<img src="data:image/png;base64,${product.picture }"
																class="img-fluid imageOut" alt="Image">
														</c:if>
														<c:if test="${product.quatityInStock!=0}">
															<img src="data:image/png;base64,${product.picture }"
																class="img-fluid" alt="Image">
														</c:if>
															
															<div class="mask-icon">
																<ul>
																	<li><a
																		href="${pageContext.request.contextPath }/client/chiTietSanPham/${product.productID}"
																		data-toggle="tooltip" data-placement="right"
																		title="Chi tiết"><i class="fas fa-eye"></i></a></li>
																	<li><a href="#" data-toggle="tooltip"
																		data-placement="right" title="So sánh"><i
																			class="fas fa-sync-alt"></i></a></li>
																	<li><a href="#" data-toggle="tooltip"
																		data-placement="right" title="yêu thích"><i
																			class="far fa-heart"></i></a></li>
																</ul>

															</div>
														</div>
													</div>
												</div>
												<div class="col-sm-6 col-md-6 col-lg-8 col-xl-8">
													<div class="why-text full-width">
														<h4>${product.productName}</h4>
														<h5 style="color: white;">
															<fmt:formatNumber type="number" maxFractionDigits="3"
																value="${product.unitPrrice }" />
															đ

														</h5>
														<p>${product.moTa}.</p>
														<c:if test="${product.quatityInStock!=0}">
															<button class="cart btn hvr-hover"
																	id="fuck" onclick="myFunction(this.value)" style="color:white;margin-top:150px;"
																	value="${product.productID}"
																	>Mua
																	ngay</button>
														</c:if>
														<c:if test="${product.quatityInStock==0}">
															<span style="color: red; font-size: 40px;">Hết
																hàng</span>
														</c:if>
													</div>
												</div>
											</div>
										</div>
									</c:forEach>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-xl-3 col-lg-3 col-sm-12 col-xs-12 sidebar-shop-left">
					<div class="product-categori">
						<div class="search-product">
							<form class="login100-form validate-form" method="post"
								action="${pageContext.request.contextPath }/client/searchResult">

								<input id="productNameBottom" name="search" class="form-control"
									placeholder="Tìm kiếm..." type="text">
								<button type="submit">
									<i class="fa fa-search"></i>
								</button>
							</form>
						</div>
						<div class="filter-sidebar-left">
							<div class="title-left">
								<h3>Danh sách các mặt hàng</h3>
							</div>
							<div
								class="list-group list-group-collapse list-group-sm list-group-tree"
								id="list-group-men" data-children=".sub-men">
								<div class="list-group-collapse sub-men">
									<a class="list-group-item list-group-item-action"
										href="${pageContext.request.contextPath}/client/shop/all"
										aria-controls="sub-men1">Các loại gấu<small
										class="text-muted"></small>
									</a>
									<div class="collapse show" id="sub-men1"
										data-parent="#list-group-men">
										<div class="list-group">
											<c:forEach var="category" items="${listMatHang}">
												<a
													href="${pageContext.request.contextPath}/client/shop/${category.categoryID}"
													class="list-group-item list-group-item-action">
													${category.categoryName} <small class="text-muted"></small>
												</a>
											</c:forEach>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="filter-price-left">
							<div class="title-left">
								<h3>Mức giá</h3>
							</div>
							<div
								class="list-group list-group-collapse list-group-sm list-group-tree"
								id="list-group-men" data-children=".sub-men">
								<div class="list-group-collapse sub-men">
									<a class="list-group-item list-group-item-action"
										href="#"
										aria-controls="sub-men1">Chọn loại<small
										class="text-muted"></small>
									</a>
									<div class="collapse show" id="sub-men1"
										data-parent="#list-group-men">
										<div class="list-group">
												<a
													href="${pageContext.request.contextPath}/client/shop/MucGia/1"
													class="list-group-item list-group-item-action">
													Dưới 300,000 đ<small class="text-muted"></small>
												</a>
												<a
													href="${pageContext.request.contextPath}/client/shop/MucGia/2"
													class="list-group-item list-group-item-action">
													Từ 300,000 đ đến 500,000 đ<small class="text-muted"></small>
												</a>
												<a
													href="${pageContext.request.contextPath}/client/shop/MucGia/3"
													class="list-group-item list-group-item-action">
													Từ 500,000 đ đến 1,000,000 đ<small class="text-muted"></small>
												</a>
												<a
													href="${pageContext.request.contextPath}/client/shop/MucGia/4"
													class="list-group-item list-group-item-action">
													Từ 1,000,000 đ đến 1,500,000 đ<small class="text-muted"></small>
												</a>
												<a
													href="${pageContext.request.contextPath}/client/shop/MucGia/5"
													class="list-group-item list-group-item-action">
													Trên 2,000,000 đ<small class="text-muted"></small>
												</a>
												
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End Shop Page -->
	<!-- Start Instagram Feed  -->
	<div class="instagram-box">
		<div class="main-instagram owl-carousel owl-theme">
			<div class="item">
				<div class="ins-inner-box">
					<img
						src="${pageContext.request.contextPath }/resources/client/images/f1.jpg"
						alt="" />
					<div class="hov-in">
						<a href="#"><i class="fab fa-instagram"></i></a>
					</div>
				</div>
			</div>
			<div class="item">
				<div class="ins-inner-box">
					<img
						src="${pageContext.request.contextPath }/resources/client/images/a1.jpg"
						alt="" />
					<div class="hov-in">
						<a href="#"><i class="fab fa-instagram"></i></a>
					</div>
				</div>
			</div>
			<div class="item">
				<div class="ins-inner-box">
					<img
						src="${pageContext.request.contextPath }/resources/client/images/f2.jpg"
						alt="" />
					<div class="hov-in">
						<a href="#"><i class="fab fa-instagram"></i></a>
					</div>
				</div>
			</div>
			<div class="item">
				<div class="ins-inner-box">
					<img
						src="${pageContext.request.contextPath }/resources/client/images/a2.jpg"
						alt="" />
					<div class="hov-in">
						<a href="#"><i class="fab fa-instagram"></i></a>
					</div>
				</div>
			</div>
			<div class="item">
				<div class="ins-inner-box">
					<img
						src="${pageContext.request.contextPath }/resources/client/images/f3.jpg"
						alt="" />
					<div class="hov-in">
						<a href="#"><i class="fab fa-instagram"></i></a>
					</div>
				</div>
			</div>


			<div class="item">
				<div class="ins-inner-box">
					<img
						src="${pageContext.request.contextPath }/resources/client/images/a3.jpg"
						alt="" />
					<div class="hov-in">
						<a href="#"><i class="fab fa-instagram"></i></a>
					</div>
				</div>
			</div>

		</div>
	</div>
	<!-- End Instagram Feed  -->


	<!-- Start Footer  -->
	<footer>
		<div class="footer-main">
			<div class="container">
				<div class="row">
					<div class="col-lg-4 col-md-12 col-sm-12">
						<div class="footer-top-box">
							<h3>Giờ hành chính</h3>
							<ul class="list-time">
								<li>Thứ 2 - Thứ 6: 08.00am to 05.00pm</li>
								<li>Thứ bảy: 10.00am to 08.00pm</li>
								<li>Chủ nhật: <span>mở cà ngày</span></li>
							</ul>
						</div>
					</div>

					<div class="col-lg-4 col-md-12 col-sm-12">
						<div class="footer-top-box">
							<h3>Liên hệ</h3>
							<ul>
								<li><a href="#"><i class="fab fa-facebook"
										aria-hidden="true"></i></a></li>
							</ul>
						</div>
					</div>
				</div>
				<hr>
				<div class="row">
					<div class="col-lg-4 col-md-12 col-sm-12">
						<div class="footer-widget">
							<h4>Về cửa hàng chúng tôi</h4>
							<p>✓Chuyên Gấu Bông Nhập</p>
							<p>✓ Đổi trả 3 ngày</p>
							<p>✓ Giao hàng tận nơi.</p>
							<p>✓ 100% Gòn Trắng</p>
							<p>✓ Bảo hành Vĩnh Viễn</p>
						</div>
					</div>
					<div class="col-lg-4 col-md-12 col-sm-12">
						<div class="footer-widget">
							<h4>Thông tin</h4>
							<ul>
								<li><a style="color: white;" href="#">Về chế độ bảo
										hành</a></li>
								<li><a style="color: white;" href="#">Địa chỉ</a></li>
								<li><a style="color: white;" href="#">Chính sách giao
										hàng</a></li>
							</ul>
						</div>
					</div>
					<div class="col-lg-4 col-md-12 col-sm-12">
						<div class="footer-link-contact">
							<h4>Địa chỉ</h4>
							<ul>
								<li>
									<p>
										<i class="fas fa-map-marker-alt"></i>Địa chỉ: Michael I. Days
										3756 <br>Huỳnh văn nghệ,<br> KS 67213
									</p>
								</li>
								<li>
									<p>
										<i class="fas fa-phone-square"></i>Số điện thoại: <a
											href="tel:+1-888705770">+1-888 705 770</a>
									</p>
								</li>
								<li>
									<p>
										<i class="fas fa-envelope"></i>Email: <a
											href="mailto:contactinfo@gmail.com">hopTacTeddyShop@gmail.com</a>
									</p>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</footer>
	<!-- End Footer  -->



	<a href="#" id="back-to-top" title="Back to top" style="display: none;">&uarr;</a>
	<a href="#" id="back-to-top" title="Back to top" style="display: none;">&uarr;</a>
	<!-- End copyright  -->

	<a href="#" id="back-to-top" title="Back to top" style="display: none;">&uarr;</a>

	<script
		src="${pageContext.request.contextPath }/resources/client/js/popper.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/client/js/bootstrap.min.js"></script>
	<!-- ALL PLUGINS -->
	<script
		src="${pageContext.request.contextPath }/resources/client/js/jquery.superslides.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/client/js/bootstrap-select.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/client/js/inewsticker.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/client/js/bootsnav.js."></script>
	<script
		src="${pageContext.request.contextPath }/resources/client/js/images-loded.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/client/js/isotope.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/client/js/owl.carousel.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/client/js/baguetteBox.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/client/js/form-validator.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/client/js/contact-form-script.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/client/js/custom.js"></script>
</body>
</html>
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
<script type="text/javascript">
	$(document).ready(function() {
		$('#productName').autocomplete({
			source : '${pageContext.request.contextPath}/client/search'
		});
	});
</script>
<script src="${pageContext.request.contextPath }/resources/toastr/toastr.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/toastr/toastr.min.css">
<script>
	function myFunction(x) {
		toastr.options = {
				  "closeButton": true,
				  "debug": true,
				  "newestOnTop": true,
				  "progressBar": true,
				  "positionClass": "toast-top-right",
				  "preventDuplicates": false,
				  "showDuration": "1000",
				  "hideDuration": "1000",
				  "timeOut": "3000",
				  "extendedTimeOut": "1000",
				  "showEasing": "swing",
				  "hideEasing": "linear",
				  "showMethod": "show",
				  "hideMethod": "hide"
				}
		$.ajax({
			url : 'http://localhost:9596/client/addCartJson/'+x,
			success : function(responseText) {
					toastr["success"]("Thêm thành công!");
					$('#ajaxGetUserServletResponse').html(responseText);
			},
			statusCode: {
			    400: function() {
			    	toastr["error"]("Không đủ hàng");
			    }
			}
		});
	} 
</script>
<style>
.toast {
    top: 150px;
}
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
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
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
								<li><a href="${pageContext.request.contextPath}/client/thongTinCaNhan/${customer.customerID}"><i class="fa fa-user s_color"></i>
										Account: ${customer.firstName} ${customer.lastName}</a></li>
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
						<li class="nav-item active"><a style="color: #9932CC;"
							class="nav-link" href="${pageContext.request.contextPath}/client">Trang
								chủ</a></li>
						<li class="dropdown "><a
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
					<ul class="cart-list"  id="ajaxGetUserServletResponse">
						<c:forEach var="productX" items="${listPro}">
							<li><a href="#" class="photo"> <img
									src="data:image/png;base64,${productX.picture }"
									class="cart-thumb" alt="Image">
							</a>
								<h6>
									<p>${productX.productName}</p> 
									<br>
									 <a style="color:black;"
										href="${pageContext.request.contextPath}/client/chiTietSanPham/${productX.productID}">Chi tiết</a>
									 |
									 <a style="color:red;"
										href="${pageContext.request.contextPath}/client/deleteMyCart/${productX.productID}">Xóa</a>
									
									 | <a style="color:blue;"
										href="${pageContext.request.contextPath}/client/addCart/${productX.productID}">Thêm</a>
										 | <a style="color:green;"
										href="${pageContext.request.contextPath}/client/lowMyCart/${productX.productID}">Bớt</a>
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
								<a href="${pageContext.request.contextPath}/client/thanhToan"
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

	<!-- Start Slider -->
	<div id="slides-shop" class="cover-slides">
		<ul class="slides-container">
			<li class="text-center"><img
				src="${pageContext.request.contextPath }/resources/client/images/b1.jpg"
				alt="">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<h1 class="m-b-20">
								<strong>Chào mừng đến <br> Toy shop
								</strong>
							</h1>
							<p class="m-b-40">Lựa chọn sản phẩm tốt nhất cho quà tặng</p>
							<p>
								<a class="btn hvr-hover"
									href="${pageContext.request.contextPath}/client/shop">Mua
									ngay</a>
							</p>
						</div>
					</div>
				</div></li>
			<li class="text-center"><img
				src="${pageContext.request.contextPath }/resources/client/images/b2.jpg"
				alt="">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<h1 class="m-b-20">
								<strong>Chào mừng đến <br> Toy shop
								</strong>
							</h1>
							<p class="m-b-40">bảo hành vĩnh viễn</p>
							<p>
								<a class="btn hvr-hover"
									href="${pageContext.request.contextPath}/client/shop">Mua
									ngay</a>
							</p>
						</div>
					</div>
				</div></li>
			<li class="text-center"><img
				src="${pageContext.request.contextPath }/resources/client/images/b3.jpg"
				alt="">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<h1 class="m-b-20">
								<strong>Chào mừng đến <br> Toy shop
								</strong>
							</h1>
							<p class="m-b-40">Nhiểu loại mẫu mã</p>
							<p>
								<a class="btn hvr-hover"
									href="${pageContext.request.contextPath}/client/shop">Mua
									ngay</a>
							</p>
						</div>
					</div>
				</div></li>
		</ul>
		<div class="slides-navigation">
			<a href="#" class="next"><i class="fa fa-angle-right"
				aria-hidden="true"></i></a> <a href="#" class="prev"><i
				class="fa fa-angle-left" aria-hidden="true"></i></a>
		</div>
	</div>
	<!-- End Slider -->

	<!-- Start Categories  -->
	<div class="categories-shop">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12"
					style="margin-top: -75px; margin-bottom: 10px;">
					<img
						src="${pageContext.request.contextPath}/resources/client/images/danhMuc.PNG"
						class="img-fluid">
				</div>
				<br>
				<c:forEach var="category" items="${listMatHang }">
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
						<div class="shop-cat-box" style="height: 280px;">
							<img src="data:image/png;base64,${category.picture}"
								width="100px;" height="50px;" class="img-fluid"> <a
								class="btn hvr-hover"
								href="${pageContext.request.contextPath}/client/shop/${category.categoryID}">${category.categoryName }</a>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<!-- End Categories -->

	<div class="box-add-products">
		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-md-6 col-sm-12">
					<div class="offer-box-products">
						<img class="img-fluid" style="width: 400px; height: 200px;"
							src="${pageContext.request.contextPath }/resources/client/images/freeShip2.png"
							alt="" />
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12">
					<div class="offer-box-products">
						<img class="img-fluid" style="width: 400px; height: 200px;"
							src="${pageContext.request.contextPath }/resources/client/images/goiQua.png"
							alt="" />
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Start Products  -->
	<div class="products-box">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="title-all text-center">
						<h1>Các loại sản phẩm</h1>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-12">
					<div class="special-menu text-center">
						<div class="button-group filter-button-group">
							<button class="active" data-filter="*">All</button>
							<c:forEach var="category" items="${listMatHang}">
								<button data-filter=".${category.categoryID}">${category.categoryName}</button>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>

			<div class="row special-list">
				<c:forEach var="product" items="${listProduct}">
					<div
						class="col-lg-3 col-md-6 special-grid  ${product.categoryID.categoryID}">
						<div class="products-single fix">
							<div class="box-img-hover" style="height: 280px;">
								<div class="type-lb">
									
									<p class="sale">${product.productName}</p>
									<c:if test="${product.quatityInStock==0}">
															<p class="saleEdit"
																style="-ms-transform: rotate(20deg);; font-size: 30px; padding-top: 75px; padding-right: 60px;; text-align: center; color: white; background-color: transparent;">Hết
																hàng</p>
															</c:if>
								</div>
								<c:if test="${product.quatityInStock!=0}">
								<img src="data:image/png;base64,${product.picture}"
									class="img-fluid" alt="Image"
									style="height: 280px; width: 400px;">
								</c:if>
								<c:if test="${product.quatityInStock==0}">
								<img src="data:image/png;base64,${product.picture}"
									class="img-fluid imageOut" alt="Image"
									style="height: 280px; width: 400px;">
								</c:if>
								<div class="mask-icon">
									<ul>
										<li><a
											href="${pageContext.request.contextPath }/client/chiTietSanPham/${product.productID}"
											data-toggle="tooltip" data-placement="right" title="Chi tiết"><i
												class="fas fa-eye"></i></a></li>
										<li><a href="#" data-toggle="tooltip"
											data-placement="right" title="So sánh"><i
												class="fas fa-sync-alt"></i></a></li>
										<li><a href="#" data-toggle="tooltip"
											data-placement="right" title="*"><i class="far fa-heart"></i></a></li>
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
	</div>
	<!-- End Products  -->

	<!-- Start Blog  -->
	<!--div class="latest-blog">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="title-all text-center">
                        <h1>latest blog</h1>
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sit amet lacus enim.</p>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6 col-lg-4 col-xl-4">
                    <div class="blog-box">
                        <div class="blog-img">
                            <img class="img-fluid" src="images/blog-img.jpg" alt="" />
                        </div>
                        <div class="blog-content">
                            <div class="title-blog">
                                <h3>Fusce in augue non nisi fringilla</h3>
                                <p>Nulla ut urna egestas, porta libero id, suscipit orci. Quisque in lectus sit amet urna dignissim feugiat. Mauris molestie egestas pharetra. Ut finibus cursus nunc sed mollis. Praesent laoreet lacinia elit id lobortis.</p>
                            </div>
                            <ul class="option-blog">
                                <li><a href="#"><i class="far fa-heart"></i></a></li>
                                <li><a href="#"><i class="fas fa-eye"></i></a></li>
                                <li><a href="#"><i class="far fa-comments"></i></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-4 col-xl-4">
                    <div class="blog-box">
                        <div class="blog-img">
                            <img class="img-fluid" src="images/blog-img-01.jpg" alt="" />
                        </div>
                        <div class="blog-content">
                            <div class="title-blog">
                                <h3>Fusce in augue non nisi fringilla</h3>
                                <p>Nulla ut urna egestas, porta libero id, suscipit orci. Quisque in lectus sit amet urna dignissim feugiat. Mauris molestie egestas pharetra. Ut finibus cursus nunc sed mollis. Praesent laoreet lacinia elit id lobortis.</p>
                            </div>
                            <ul class="option-blog">
                                <li><a href="#"><i class="far fa-heart"></i></a></li>
                                <li><a href="#"><i class="fas fa-eye"></i></a></li>
                                <li><a href="#"><i class="far fa-comments"></i></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-md-6 col-lg-4 col-xl-4">
                    <div class="blog-box">
                        <div class="blog-img">
                            <img class="img-fluid" src="images/blog-img-02.jpg" alt="" />
                        </div>
                        <div class="blog-content">
                            <div class="title-blog">
                                <h3>Fusce in augue non nisi fringilla</h3>
                                <p>Nulla ut urna egestas, porta libero id, suscipit orci. Quisque in lectus sit amet urna dignissim feugiat. Mauris molestie egestas pharetra. Ut finibus cursus nunc sed mollis. Praesent laoreet lacinia elit id lobortis.</p>
                            </div>
                            <ul class="option-blog">
                                <li><a href="#"><i class="far fa-heart"></i></a></li>
                                <li><a href="#"><i class="fas fa-eye"></i></a></li>
                                <li><a href="#"><i class="far fa-comments"></i></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div-->
	<!-- End Blog  -->


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
						<div class="footer-link-contact">
							<h4>Chi nhánh 1</h4>
							<ul>
								<li>
									<p>
										<i class="fas fa-map-marker-alt"></i>Địa chỉ: 219 Huỳnh Văn Bánh,P.12,Q.Phú nhuận,HCM
									</p>
								</li>
								<li>
									<p>
										<i class="fas fa-phone-square"></i>Số điện thoại: <a
											href="tel:+1-888705770">0708821227</a>
									</p>
								</li>
								<li>
									<p>
										<i class="fas fa-envelope"></i>Email: <a
											href="mailto:contactinfo@gmail.com">hopTacTeddyShopCN1@gmail.com</a>
									</p>
								</li>
							</ul>
						</div>
					</div>
					<div class="col-lg-4 col-md-12 col-sm-12">
						<div class="footer-link-contact">
							<h4>Chi nhánh 2</h4>
							<ul>
								<li>
									<p>
										<i class="fas fa-map-marker-alt"></i>Địa chỉ: 219 Huỳnh Văn Nghệ,P.12,Q.Gò vấp,HCM
									</p>
								</li>
								<li>
									<p>
										<i class="fas fa-phone-square"></i>Số điện thoại: <a
											href="tel:+1-888705770">0123421227</a>
									</p>
								</li>
								<li>
									<p>
										<i class="fas fa-envelope"></i>Email: <a
											href="mailto:contactinfo@gmail.com">hopTacTeddyShopCN2@gmail.com</a>
									</p>
								</li>
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
								<li><a style="color: white;" href="#">Về chế độ bảo hành</a></li>
								<li><a style="color: white;" href="#">Liên hệ</a></li>
								<li><a style="color: white;" href="#">Chính sách giao hàng</a></li>
							</ul>
						</div>
					</div>
					<div class="col-lg-4 col-md-12 col-sm-12">
						<div class="footer-top-box">
							<h3>Liên hệ</h3>
							<ul>
								<li><a href="https://www.facebook.com/Toy-Bear-105014534189202/"><i class="fab fa-facebook"
										aria-hidden="true"></i></a></li>
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
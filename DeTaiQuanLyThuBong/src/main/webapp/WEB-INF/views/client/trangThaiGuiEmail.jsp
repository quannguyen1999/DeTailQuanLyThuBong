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
<script
	src="${pageContext.request.contextPath }/resources/client/js/ajax/ajaxClient.js"></script>

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
								<li><a href="#"><i class="fa fa-user s_color"></i>
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
						<li class="nav-item active"><a class="nav-link"
							href="${pageContext.request.contextPath}/client">Trang chủ</a></li>
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
						<li class="side-menu">
							<a href="#" onclick="clickMyCart()">
								<i class="fa fa-shopping-bag"></i> <span class="badge" id="showSoLuong">
									<c:if
										test="${empty countCart}">
                            		0
                            	</c:if> ${countCart}
									
								 </span>
								<p>Xem giỏ hàng</p>
							</a>
						</li>
					</ul>
				</div>
				<!-- End Atribute Navigation -->
			</div>
			<!-- Start Side Menu -->
			<div class="side" id="startSide">
				<a href="#" class="close-side"><i class="fa fa-times"></i></a>
				<li class="cart-box">
					<ul class="cart-list">
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
			<div class="input-group">
				<span class="input-group-addon"><i class="fa fa-search"></i></span>
				<input type="text" class="form-control" placeholder="Search">
				<span class="input-group-addon close-search"><i
					class="fa fa-times"></i></span>
			</div>
		</div>
	</div>
	<!-- End Top Search -->

	<!-- Start All Title Box -->
	<div class="all-title-box">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<h2>Đăng nhập</h2>
					<ul class="breadcrumb">
						<li class="breadcrumb-item"><a
							href="${pageContext.request.contextPath }/client/shop">Trang
								chủ</a></li>
						<li class="breadcrumb-item active">Đăng nhập</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	<!-- End All Title Box -->

	<!-- Start Contact Us  -->
	<div class="contact-box-main">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 col-sm-12">
					<div class="contact-form-right">
						<h2>Email gửi không thành công</h2>
						<p></p>
						<div class="row">
							<div class="col-md-12">

								<div class="submit-button text-center">
									<a style="color: White;"
										href="${pageContext.request.contextPath}/client/login"
										class="btn hvr-hover" id="submit" type="submit">Quay lại
										trang đăng nhập</a>
									<div id="msgSubmit" class="h3 text-center hidden"></div>
									<div class="clearfix"></div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-4 col-sm-12">
					<div class="contact-info-left">
						<h2>CONTACT INFO</h2>
						<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
							Praesent urna diam, maximus ut ullamcorper quis, placerat id
							eros. Duis semper justo sed condimentum rutrum. Nunc tristique
							purus turpis. Maecenas vulputate.</p>
						<ul>
							<li>
								<p>
									<i class="fas fa-map-marker-alt"></i>Address: 33/16 <br>Vu
									han,<br> KS 87213
								</p>
							</li>
							<li>
								<p>
									<i class="fas fa-phone-square"></i>Phone: <a href="tel:+1-113">+1-888
										705 770</a>
								</p>
							</li>
							<li>
								<p>
									<i class="fas fa-envelope"></i>Email: <a
										href="mailto:contactinfo@gmail.com">contactinfo@gmail.com</a>
								</p>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End Cart -->

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
		src="${pageContext.request.contextPath }/resources/client/js/jquery-3.2.1.min.js"></script>
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
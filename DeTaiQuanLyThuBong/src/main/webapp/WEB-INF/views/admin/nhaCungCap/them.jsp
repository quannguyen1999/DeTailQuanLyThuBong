<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta http-equiv="Content-Language" content="en">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Form Controls - Wide selection of forms controls, using
	the Bootstrap 4 code base, but built with React.</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, shrink-to-fit=no" />
<meta name="description"
	content="Wide selection of forms controls, using the Bootstrap 4 code base, but built with React.">
<meta name="msapplication-tap-highlight" content="no">
<link
	href="${pageContext.request.contextPath }/resources/assets/css/main.css"
	rel="stylesheet">
<style>
.error {
	color: red;
}
</style>
</head>
<body>
	<div
		class="app-container app-theme-white body-tabs-shadow fixed-sidebar fixed-header bg-deep-blue sidebar-text-dark">
		<div class="app-header header-shadow  bg-deep-blue sidebar-text-dark">
			<div class="app-header__logo">
				<div class="">
					<h3>administer</h3>
				</div>
				<div class="header__pane ml-auto">
					<div>
						<button type="button"
							class="hamburger close-sidebar-btn hamburger--elastic"
							data-class="closed-sidebar">
							<span class="hamburger-box"> <span class="hamburger-inner"></span>
							</span>
						</button>
					</div>
				</div>
			</div>
			<div class="app-header__mobile-menu">
				<div>
					<button type="button"
						class="hamburger hamburger--elastic mobile-toggle-nav">
						<span class="hamburger-box"> <span class="hamburger-inner"></span>
						</span>
					</button>
				</div>
			</div>
			<div class="app-header__menu">
				<span>
					<button type="button"
						class="btn-icon btn-icon-only btn btn-primary btn-sm mobile-toggle-header-nav">
						<span class="btn-icon-wrapper"> <i
							class="fa fa-ellipsis-v fa-w-6"></i>
						</span>
					</button>
				</span>
			</div>
			<div class="app-header__content">
				<div class="app-header-right">
					<div class="header-btn-lg pr-0">
						<div class="widget-content p-0">
							<div class="widget-content-wrapper">
								<div class="widget-content-left">
									<div class="btn-group">
										<a data-toggle="dropdown" aria-haspopup="true"
											aria-expanded="false" class="p-0 btn"> <img width="40"
											height="40" class="rounded-circle"
											src="${pageContext.request.contextPath }/resources/client/images/logo.png"
											alt=""> <i class="fa fa-angle-down ml-2 opacity-8"></i>
										</a>
										<div tabindex="-1" role="menu" aria-hidden="true"
											class="dropdown-menu dropdown-menu-right">
											<a href="${pageContext.request.contextPath }/admin/suaAccount" type="button" tabindex="0" class="dropdown-item">Sửa tài khoản</a>
											
											<a href="${pageContext.request.contextPath }/admin-panel/process-logout" type="button" tabindex="0" class="dropdown-item">Đăng
												xuất</a>
										</div>
									</div>
								</div>
								<div class="widget-content-left  ml-3 header-user-info">
									<div class="widget-heading">hello</div>
									<div class="widget-subheading">${pageContext.request.userPrincipal.name}</div>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!--div class="ui-theme-settings">
            <button type="button" id="TooltipDemo" class="btn-open-options btn btn-warning">
                <i class="fa fa-cog fa-w-16 fa-spin fa-2x"></i>
            </button>
            <div class="theme-settings__inner">
                <div class="scrollbar-container">
                    <div class="theme-settings__options-wrapper">
                        <h3 class="themeoptions-heading">Layout Options
                        </h3>
                        <div class="p-3">
                            <ul class="list-group">
                                <li class="list-group-item">
                                    <div class="widget-content p-0">
                                        <div class="widget-content-wrapper">
                                            <div class="widget-content-left mr-3">
                                                <div class="switch has-switch switch-container-class" data-class="fixed-header">
                                                    <div class="switch-animate switch-on">
                                                        <input type="checkbox" checked data-toggle="toggle" data-onstyle="success">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="widget-content-left">
                                                <div class="widget-heading">Fixed Header
                                                </div>
                                                <div class="widget-subheading">Makes the header top fixed, always visible!
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li class="list-group-item">
                                    <div class="widget-content p-0">
                                        <div class="widget-content-wrapper">
                                            <div class="widget-content-left mr-3">
                                                <div class="switch has-switch switch-container-class" data-class="fixed-sidebar">
                                                    <div class="switch-animate switch-on">
                                                        <input type="checkbox" checked data-toggle="toggle" data-onstyle="success">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="widget-content-left">
                                                <div class="widget-heading">Fixed Sidebar
                                                </div>
                                                <div class="widget-subheading">Makes the sidebar left fixed, always visible!
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li class="list-group-item">
                                    <div class="widget-content p-0">
                                        <div class="widget-content-wrapper">
                                            <div class="widget-content-left mr-3">
                                                <div class="switch has-switch switch-container-class" data-class="fixed-footer">
                                                    <div class="switch-animate switch-off">
                                                        <input type="checkbox" data-toggle="toggle" data-onstyle="success">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="widget-content-left">
                                                <div class="widget-heading">Fixed Footer
                                                </div>
                                                <div class="widget-subheading">Makes the app footer bottom fixed, always visible!
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                        <h3 class="themeoptions-heading">
                            <div>
                                Header Options
                            </div>
                            <button type="button" class="btn-pill btn-shadow btn-wide ml-auto btn btn-focus btn-sm switch-header-cs-class" data-class="">
                                Restore Default
                            </button>
                        </h3>
                        <div class="p-3">
                            <ul class="list-group">
                                <li class="list-group-item">
                                    <h5 class="pb-2">Choose Color Scheme
                                    </h5>
                                    <div class="theme-settings-swatches">
                                        <div class="swatch-holder bg-primary switch-header-cs-class" data-class="bg-primary header-text-light">
                                        </div>
                                        <div class="swatch-holder bg-secondary switch-header-cs-class" data-class="bg-secondary header-text-light">
                                        </div>
                                        <div class="swatch-holder bg-success switch-header-cs-class" data-class="bg-success header-text-dark">
                                        </div>
                                        <div class="swatch-holder bg-info switch-header-cs-class" data-class="bg-info header-text-dark">
                                        </div>
                                        <div class="swatch-holder bg-warning switch-header-cs-class" data-class="bg-warning header-text-dark">
                                        </div>
                                        <div class="swatch-holder bg-danger switch-header-cs-class" data-class="bg-danger header-text-light">
                                        </div>
                                        <div class="swatch-holder bg-light switch-header-cs-class" data-class="bg-light header-text-dark">
                                        </div>
                                        <div class="swatch-holder bg-dark switch-header-cs-class" data-class="bg-dark header-text-light">
                                        </div>
                                        <div class="swatch-holder bg-focus switch-header-cs-class" data-class="bg-focus header-text-light">
                                        </div>
                                        <div class="swatch-holder bg-alternate switch-header-cs-class" data-class="bg-alternate header-text-light">
                                        </div>
                                        <div class="divider">
                                        </div>
                                        <div class="swatch-holder bg-vicious-stance switch-header-cs-class" data-class="bg-vicious-stance header-text-light">
                                        </div>
                                        <div class="swatch-holder bg-midnight-bloom switch-header-cs-class" data-class="bg-midnight-bloom header-text-light">
                                        </div>
                                        <div class="swatch-holder bg-night-sky switch-header-cs-class" data-class="bg-night-sky header-text-light">
                                        </div>
                                        <div class="swatch-holder bg-slick-carbon switch-header-cs-class" data-class="bg-slick-carbon header-text-light">
                                        </div>
                                        <div class="swatch-holder bg-asteroid switch-header-cs-class" data-class="bg-asteroid header-text-light">
                                        </div>
                                        <div class="swatch-holder bg-royal switch-header-cs-class" data-class="bg-royal header-text-light">
                                        </div>
                                        <div class="swatch-holder bg-warm-flame switch-header-cs-class" data-class="bg-warm-flame header-text-dark">
                                        </div>
                                        <div class="swatch-holder bg-night-fade switch-header-cs-class" data-class="bg-night-fade header-text-dark">
                                        </div>
                                        <div class="swatch-holder bg-sunny-morning switch-header-cs-class" data-class="bg-sunny-morning header-text-dark">
                                        </div>
                                        <div class="swatch-holder bg-tempting-azure switch-header-cs-class" data-class="bg-tempting-azure header-text-dark">
                                        </div>
                                        <div class="swatch-holder bg-amy-crisp switch-header-cs-class" data-class="bg-amy-crisp header-text-dark">
                                        </div>
                                        <div class="swatch-holder bg-heavy-rain switch-header-cs-class" data-class="bg-heavy-rain header-text-dark">
                                        </div>
                                        <div class="swatch-holder bg-mean-fruit switch-header-cs-class" data-class="bg-mean-fruit header-text-dark">
                                        </div>
                                        <div class="swatch-holder bg-malibu-beach switch-header-cs-class" data-class="bg-malibu-beach header-text-light">
                                        </div>
                                        <div class="swatch-holder bg-deep-blue switch-header-cs-class" data-class="bg-deep-blue header-text-dark">
                                        </div>
                                        <div class="swatch-holder bg-ripe-malin switch-header-cs-class" data-class="bg-ripe-malin header-text-light">
                                        </div>
                                        <div class="swatch-holder bg-arielle-smile switch-header-cs-class" data-class="bg-arielle-smile header-text-light">
                                        </div>
                                        <div class="swatch-holder bg-plum-plate switch-header-cs-class" data-class="bg-plum-plate header-text-light">
                                        </div>
                                        <div class="swatch-holder bg-happy-fisher switch-header-cs-class" data-class="bg-happy-fisher header-text-dark">
                                        </div>
                                        <div class="swatch-holder bg-happy-itmeo switch-header-cs-class" data-class="bg-happy-itmeo header-text-light">
                                        </div>
                                        <div class="swatch-holder bg-mixed-hopes switch-header-cs-class" data-class="bg-mixed-hopes header-text-light">
                                        </div>
                                        <div class="swatch-holder bg-strong-bliss switch-header-cs-class" data-class="bg-strong-bliss header-text-light">
                                        </div>
                                        <div class="swatch-holder bg-grow-early switch-header-cs-class" data-class="bg-grow-early header-text-light">
                                        </div>
                                        <div class="swatch-holder bg-love-kiss switch-header-cs-class" data-class="bg-love-kiss header-text-light">
                                        </div>
                                        <div class="swatch-holder bg-premium-dark switch-header-cs-class" data-class="bg-premium-dark header-text-light">
                                        </div>
                                        <div class="swatch-holder bg-happy-green switch-header-cs-class" data-class="bg-happy-green header-text-light">
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                        <h3 class="themeoptions-heading">
                            <div>Sidebar Options</div>
                            <button type="button" class="btn-pill btn-shadow btn-wide ml-auto btn btn-focus btn-sm switch-sidebar-cs-class" data-class="">
                                Restore Default
                            </button>
                        </h3>
                        <div class="p-3">
                            <ul class="list-group">
                                <li class="list-group-item">
                                    <h5 class="pb-2">Choose Color Scheme
                                    </h5>
                                    <div class="theme-settings-swatches">
                                        <div class="swatch-holder bg-primary switch-sidebar-cs-class" data-class="bg-primary sidebar-text-light">
                                        </div>
                                        <div class="swatch-holder bg-secondary switch-sidebar-cs-class" data-class="bg-secondary sidebar-text-light">
                                        </div>
                                        <div class="swatch-holder bg-success switch-sidebar-cs-class" data-class="bg-success sidebar-text-dark">
                                        </div>
                                        <div class="swatch-holder bg-info switch-sidebar-cs-class" data-class="bg-info sidebar-text-dark">
                                        </div>
                                        <div class="swatch-holder bg-warning switch-sidebar-cs-class" data-class="bg-warning sidebar-text-dark">
                                        </div>
                                        <div class="swatch-holder bg-danger switch-sidebar-cs-class" data-class="bg-danger sidebar-text-light">
                                        </div>
                                        <div class="swatch-holder bg-light switch-sidebar-cs-class" data-class="bg-light sidebar-text-dark">
                                        </div>
                                        <div class="swatch-holder bg-dark switch-sidebar-cs-class" data-class="bg-dark sidebar-text-light">
                                        </div>
                                        <div class="swatch-holder bg-focus switch-sidebar-cs-class" data-class="bg-focus sidebar-text-light">
                                        </div>
                                        <div class="swatch-holder bg-alternate switch-sidebar-cs-class" data-class="bg-alternate sidebar-text-light">
                                        </div>
                                        <div class="divider">
                                        </div>
                                        <div class="swatch-holder bg-vicious-stance switch-sidebar-cs-class" data-class="bg-vicious-stance sidebar-text-light">
                                        </div>
                                        <div class="swatch-holder bg-midnight-bloom switch-sidebar-cs-class" data-class="bg-midnight-bloom sidebar-text-light">
                                        </div>
                                        <div class="swatch-holder bg-night-sky switch-sidebar-cs-class" data-class="bg-night-sky sidebar-text-light">
                                        </div>
                                        <div class="swatch-holder bg-slick-carbon switch-sidebar-cs-class" data-class="bg-slick-carbon sidebar-text-light">
                                        </div>
                                        <div class="swatch-holder bg-asteroid switch-sidebar-cs-class" data-class="bg-asteroid sidebar-text-light">
                                        </div>
                                        <div class="swatch-holder bg-royal switch-sidebar-cs-class" data-class="bg-royal sidebar-text-light">
                                        </div>
                                        <div class="swatch-holder bg-warm-flame switch-sidebar-cs-class" data-class="bg-warm-flame sidebar-text-dark">
                                        </div>
                                        <div class="swatch-holder bg-night-fade switch-sidebar-cs-class" data-class="bg-night-fade sidebar-text-dark">
                                        </div>
                                        <div class="swatch-holder bg-sunny-morning switch-sidebar-cs-class" data-class="bg-sunny-morning sidebar-text-dark">
                                        </div>
                                        <div class="swatch-holder bg-tempting-azure switch-sidebar-cs-class" data-class="bg-tempting-azure sidebar-text-dark">
                                        </div>
                                        <div class="swatch-holder bg-amy-crisp switch-sidebar-cs-class" data-class="bg-amy-crisp sidebar-text-dark">
                                        </div>
                                        <div class="swatch-holder bg-heavy-rain switch-sidebar-cs-class" data-class="bg-heavy-rain sidebar-text-dark">
                                        </div>
                                        <div class="swatch-holder bg-mean-fruit switch-sidebar-cs-class" data-class="bg-mean-fruit sidebar-text-dark">
                                        </div>
                                        <div class="swatch-holder bg-malibu-beach switch-sidebar-cs-class" data-class="bg-malibu-beach sidebar-text-light">
                                        </div>
                                        <div class="swatch-holder bg-deep-blue switch-sidebar-cs-class" data-class="bg-deep-blue sidebar-text-dark">
                                        </div>
                                        <div class="swatch-holder bg-ripe-malin switch-sidebar-cs-class" data-class="bg-ripe-malin sidebar-text-light">
                                        </div>
                                        <div class="swatch-holder bg-arielle-smile switch-sidebar-cs-class" data-class="bg-arielle-smile sidebar-text-light">
                                        </div>
                                        <div class="swatch-holder bg-plum-plate switch-sidebar-cs-class" data-class="bg-plum-plate sidebar-text-light">
                                        </div>
                                        <div class="swatch-holder bg-happy-fisher switch-sidebar-cs-class" data-class="bg-happy-fisher sidebar-text-dark">
                                        </div>
                                        <div class="swatch-holder bg-happy-itmeo switch-sidebar-cs-class" data-class="bg-happy-itmeo sidebar-text-light">
                                        </div>
                                        <div class="swatch-holder bg-mixed-hopes switch-sidebar-cs-class" data-class="bg-mixed-hopes sidebar-text-light">
                                        </div>
                                        <div class="swatch-holder bg-strong-bliss switch-sidebar-cs-class" data-class="bg-strong-bliss sidebar-text-light">
                                        </div>
                                        <div class="swatch-holder bg-grow-early switch-sidebar-cs-class" data-class="bg-grow-early sidebar-text-light">
                                        </div>
                                        <div class="swatch-holder bg-love-kiss switch-sidebar-cs-class" data-class="bg-love-kiss sidebar-text-light">
                                        </div>
                                        <div class="swatch-holder bg-premium-dark switch-sidebar-cs-class" data-class="bg-premium-dark sidebar-text-light">
                                        </div>
                                        <div class="swatch-holder bg-happy-green switch-sidebar-cs-class" data-class="bg-happy-green sidebar-text-light">
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                        <h3 class="themeoptions-heading">
                            <div>Main Content Options</div>
                            <button type="button" class="btn-pill btn-shadow btn-wide ml-auto active btn btn-focus btn-sm">Restore Default
                            </button>
                        </h3>
                        <div class="p-3">
                            <ul class="list-group">
                                <li class="list-group-item">
                                    <h5 class="pb-2">Page Section Tabs
                                    </h5>
                                    <div class="theme-settings-swatches">
                                        <div role="group" class="mt-2 btn-group">
                                            <button type="button" class="btn-wide btn-shadow btn-primary btn btn-secondary switch-theme-class" data-class="body-tabs-line">
                                                Line
                                            </button>
                                            <button type="button" class="btn-wide btn-shadow btn-primary active btn btn-secondary switch-theme-class" data-class="body-tabs-shadow">
                                                Shadow
                                            </button>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div-->
		<div class="app-main">
			<div class="app-sidebar sidebar-shadow bg-sunny-morning sidebar-text-dark">
				<div class="app-header__logo">
					<div class="logo-src"></div>
					<div class="header__pane ml-auto">
						<div>
							<button type="button"
								class="hamburger close-sidebar-btn hamburger--elastic"
								data-class="closed-sidebar">
								<span class="hamburger-box"> <span
									class="hamburger-inner"></span>
								</span>
							</button>
						</div>
					</div>
				</div>
				<div class="app-header__mobile-menu">
					<div>
						<button type="button"
							class="hamburger hamburger--elastic mobile-toggle-nav">
							<span class="hamburger-box"> <span class="hamburger-inner"></span>
							</span>
						</button>
					</div>
				</div>
				<div class="app-header__menu">
					<span>
						<button type="button"
							class="btn-icon btn-icon-only btn btn-primary btn-sm mobile-toggle-header-nav">
							<span class="btn-icon-wrapper"> <i
								class="fa fa-ellipsis-v fa-w-6"></i>
							</span>
						</button>
					</span>
				</div>
				<div class="scrollbar-sidebar bg-plum-plate switch-sidebar-cs-class">
					<div class="app-sidebar__inner">
						<ul class="vertical-nav-menu ">
							<li class="app-sidebar__heading" style="color: White;">Danh
								mục</li>
							<li style="color: White;"><a href="${pageContext.request.contextPath }/admin/khachHang/danhSach"> <i
									class="metismenu-icon pe-7s-users" style="color: White;"></i>
									<span style="color: White;">Quản lý khách hàng<span></a></li>
							<li><a href="${pageContext.request.contextPath }/admin/matHang/danhSach"> <i
									class="metismenu-icon pe-7s-display2" style="color: White;"></i>
									<span style="color: White;">Quản lý mặt hàng<span></a></li>
							<li><a href="${pageContext.request.contextPath }/admin/datHang/danhSach"> <i
									class="metismenu-icon pe-7s-cart" style="color: White;"></i>
									<span style="color: White;">Quản lý đơn hàng<span></a></li>
							<li><a href="${pageContext.request.contextPath }/admin/sanPham/danhSach"> <i
									class="metismenu-icon pe-7s-box2" style="color: White;"></i>
									<span style="color: White;">Quản lý sản phẩm<span></a></li>
							<li style="color: White; background-color: rgba(0, 0, 0, 0.6);"><a href="${pageContext.request.contextPath }/admin/nhaCungCap/danhSach"> <i
									class="metismenu-icon pe-7s-display2" style="color: White;"></i>
									<span style="color: White;">Quản lý nhà cung cấp<span></a></li>
							<li class="app-sidebar__heading" style="color: White;">Tùy
								chọn khác</li>
							<li>
												<a href="${pageContext.request.contextPath }/admin-panel/process-logout"> <i
									class="metismenu-icon pe-7s-power" style="color: White;"></i>
									<span style="color: White;">Đăng xuất<span></a>
												
												</li>
						</ul>
					</div>
				</div>
			</div>
			<div class="app-main__outer">

				<div class="app-main__inner">
					<ul
						class="body-tabs body-tabs-layout tabs-animated body-tabs-animated nav">

					</ul>
					<div class="tab-content">
						<div class="tab-pane tabs-animation fade show active"
							id="tab-content-0" role="tabpanel">
							<div class="row">

								<div class="col-md-12">
									<div class="main-card mb-3 card">
										<div class="card-body">
											<s:form method="post"
												action="${pageContext.request.contextPath}/admin/nhaCungCap/process-them"
												modelAttribute="supplier" enctype="multipart/form-data">
												<h5 class="card-title">Tạo thông tin nhà cung cấp</h5>
												<div class="position-relative form-group">
													<label for="exampleEmail" class=""> Mã </label>
													<c:if test="${!empty errorMa }">
														<span style="color:red;">${errorMa }</span>
													</c:if>
													<s:errors path="supplierID" cssClass="error" />
													<s:input readonly="true" path="supplierID"
														placeholder="Nhập id" type="text" class="form-control" value="${ maSupplier}" />
												</div>
												<div class="position-relative form-group">
													<label for="exampleEmail" class=""> tên công ty</label>
													<s:errors path="companyName" cssClass="error" />
													<s:input id="firstName" path="companyName"
														placeholder="Nhập tên công ty" type="text"
														class="form-control" />
												</div>
												<div class="position-relative form-group">
													<label for="exampleEmail" class=""> điện thoại</label>
													<s:errors path="phone" cssClass="error" />
													<s:input id="" path="phone"
														placeholder="Nhập phone" type="text"
														class="form-control" />
												</div>
												<div class="position-relative form-group">
													<label for="exampleEmail" class=""> địa chỉ</label>
													<s:errors path="address" cssClass="error" />
													<s:input id="" path="address"
														placeholder="Nhập địa chỉ" type="text"
														class="form-control" />
												</div>
												<button class="mt-1 btn btn-primary">Xác nhận</button>
												<a
													href="${pageContext.request.contextPath }/admin/nhaCungCap/danhSach"
													class="mt-1 btn btn-primary">Hủy</a>
											</s:form>

										</div>
									</div>

								</div>


							</div>
						</div>
						<div class="tab-pane tabs-animation fade" id="tab-content-1"
							role="tabpanel">
							<div class="row">
								<div class="col-md-6">
									<div class="main-card mb-3 card">
										<div class="card-body">
											<h5 class="card-title">Input Groups</h5>
											<div>
												<div class="input-group">
													<div class="input-group-prepend">
														<span class="input-group-text">@</span>
													</div>
													<input placeholder="username" type="text"
														class="form-control">
												</div>
												<br>
												<div class="input-group">
													<div class="input-group-prepend">
														<span class="input-group-text"><input
															aria-label="Checkbox for following text input"
															type="checkbox" class=""></span>
													</div>
													<input placeholder="Check it out" type="text"
														class="form-control">
												</div>
												<br>
												<div class="input-group">
													<input placeholder="username" type="text"
														class="form-control">
													<div class="input-group-append">
														<span class="input-group-text">@example.com</span>
													</div>
												</div>
												<br>
												<div class="input-group">
													<div class="input-group-prepend">
														<span class="input-group-text">$</span><span
															class="input-group-text">$</span>
													</div>
													<input placeholder="Dolla dolla billz yo!" type="text"
														class="form-control">
													<div class="input-group-append">
														<span class="input-group-text">$</span><span
															class="input-group-text">$</span>
													</div>
												</div>
												<br>
												<div class="input-group">
													<div class="input-group-prepend">
														<span class="input-group-text">$</span>
													</div>
													<input placeholder="Amount" step="1" type="number"
														class="form-control">
													<div class="input-group-append">
														<span class="input-group-text">.00</span>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="main-card mb-3 card">
										<div class="card-body">
											<h5 class="card-title">Input Group Button Dropdown</h5>
											<div class="input-group">
												<div class="input-group-prepend">
													<button type="button" data-toggle="dropdown"
														aria-haspopup="true" aria-expanded="false"
														class="dropdown-toggle btn btn-secondary">Button
														Dropdown</button>
													<div tabindex="-1" role="menu" aria-hidden="true"
														class="dropdown-menu">
														<h6 tabindex="-1" class="dropdown-header">Header</h6>
														<button type="button" disabled="" tabindex="-1"
															class="disabled dropdown-item">Action</button>
														<button type="button" tabindex="0" class="dropdown-item">Another
															Action</button>
														<div tabindex="-1" class="dropdown-divider"></div>
														<button type="button" tabindex="0" class="dropdown-item">Another
															Action</button>
													</div>
												</div>
												<input type="text" class="form-control">
											</div>
										</div>
									</div>
									<div class="main-card mb-3 card">
										<div class="card-body">
											<h5 class="card-title">Input Group Button Shorthand</h5>
											<div>
												<div class="input-group">
													<div class="input-group-prepend">
														<button class="btn btn-secondary">To the Left!</button>
													</div>
													<input type="text" class="form-control">
												</div>
												<br>
												<div class="input-group">
													<input type="text" class="form-control">
													<div class="input-group-append">
														<button class="btn btn-secondary">To the Right!</button>
													</div>
												</div>
												<br>
												<div class="input-group">
													<div class="input-group-prepend">
														<button class="btn btn-danger">To the Left!</button>
													</div>
													<input placeholder="and..." type="text"
														class="form-control">
													<div class="input-group-append">
														<button class="btn btn-success">To the Right!</button>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="main-card mb-3 card">
										<div class="card-body">
											<h5 class="card-title">Input Group Sizing</h5>
											<div>
												<div class="input-group input-group-lg">
													<div class="input-group-prepend">
														<span class="input-group-text">@lg</span>
													</div>
													<input type="text" class="form-control">
												</div>
												<br>
												<div class="input-group">
													<div class="input-group-prepend">
														<span class="input-group-text">@normal</span>
													</div>
													<input type="text" class="form-control">
												</div>
												<br>
												<div class="input-group input-group-sm">
													<div class="input-group-prepend">
														<span class="input-group-text">@sm</span>
													</div>
													<input type="text" class="form-control">
												</div>
											</div>
										</div>
									</div>
									<div class="main-card mb-3 card">
										<div class="card-body">
											<h5 class="card-title">Input Group Addon</h5>
											<div>
												<div class="input-group">
													<div class="input-group-prepend">
														<span class="input-group-text">To the Left!</span>
													</div>
													<input type="text" class="form-control">
												</div>
												<br>
												<div class="input-group">
													<input type="text" class="form-control">
													<div class="input-group-append">
														<span class="input-group-text">To the Right!</span>
													</div>
												</div>
												<br>
												<div class="input-group">
													<div class="input-group-prepend">
														<span class="input-group-text">To the Left!</span>
													</div>
													<input placeholder="and..." type="text"
														class="form-control">
													<div class="input-group-append">
														<span class="input-group-text">To the Right!</span>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="main-card mb-3 card">
										<div class="card-body">
											<h5 class="card-title">Input Group Button</h5>
											<div>
												<div class="input-group">
													<div class="input-group-prepend">
														<button class="btn btn-secondary">I'm a button</button>
													</div>
													<input type="text" class="form-control">
												</div>
												<br>
												<div class="input-group">
													<input type="text" class="form-control">
													<div class="input-group-append">
														<button type="button" data-toggle="dropdown"
															aria-haspopup="true" aria-expanded="false"
															class="dropdown-toggle btn btn-secondary">Button
															Dropdown</button>
														<div tabindex="-1" role="menu" aria-hidden="true"
															class="dropdown-menu">
															<h6 tabindex="-1" class="dropdown-header">Header</h6>
															<button type="button" disabled="" tabindex="-1"
																class="disabled dropdown-item">Action</button>
															<button type="button" tabindex="0" class="dropdown-item">Another
																Action</button>
															<div tabindex="-1" class="dropdown-divider"></div>
															<button type="button" tabindex="0" class="dropdown-item">Another
																Action</button>
														</div>
													</div>
												</div>
												<br>
												<div class="input-group">
													<div class="input-group-prepend">
														<button class="btn btn-outline-secondary">Split
															Button</button>
														<button type="button" data-toggle="dropdown"
															aria-haspopup="true" aria-expanded="false"
															class="dropdown-toggle dropdown-toggle-split btn btn-outline-secondary">
															<span class="sr-only">Toggle Dropdown</span>
														</button>
														<div tabindex="-1" role="menu" aria-hidden="true"
															class="dropdown-menu">
															<h6 tabindex="-1" class="dropdown-header">Header</h6>
															<button type="button" disabled="" tabindex="-1"
																class="disabled dropdown-item">Action</button>
															<button type="button" tabindex="0" class="dropdown-item">Another
																Action</button>
															<div tabindex="-1" class="dropdown-divider"></div>
															<button type="button" tabindex="0" class="dropdown-item">Another
																Action</button>
														</div>
													</div>
													<input placeholder="and..." type="text"
														class="form-control">
													<div class="input-group-append">
														<button class="btn btn-secondary">I'm a button</button>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="tab-pane tabs-animation fade" id="tab-content-2"
							role="tabpanel">
							<form class="">
								<div class="row">
									<div class="col-md-6">
										<div class="main-card mb-3 card">
											<div class="card-body">
												<h5 class="card-title">Checkboxes</h5>
												<div class="position-relative form-group">
													<div>
														<div class="custom-checkbox custom-control">
															<input type="checkbox" id="exampleCustomCheckbox"
																class="custom-control-input"><label
																class="custom-control-label" for="exampleCustomCheckbox">Check
																this custom checkbox</label>
														</div>
														<div class="custom-checkbox custom-control">
															<input type="checkbox" id="exampleCustomCheckbox2"
																class="custom-control-input"><label
																class="custom-control-label"
																for="exampleCustomCheckbox2">Or this one</label>
														</div>
														<div class="custom-checkbox custom-control">
															<input type="checkbox" id="exampleCustomCheckbox3"
																disabled="" class="custom-control-input"><label
																class="custom-control-label"
																for="exampleCustomCheckbox3">But not this
																disabled one</label>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="main-card mb-3 card">
											<div class="card-body">
												<h5 class="card-title">Inline</h5>
												<div class="position-relative form-group">
													<div>
														<div
															class="custom-checkbox custom-control custom-control-inline">
															<input type="checkbox" id="exampleCustomInline"
																class="custom-control-input"><label
																class="custom-control-label" for="exampleCustomInline">An
																inline custom input</label>
														</div>
														<div
															class="custom-checkbox custom-control custom-control-inline">
															<input type="checkbox" id="exampleCustomInline2"
																class="custom-control-input"><label
																class="custom-control-label" for="exampleCustomInline2">and
																another one</label>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="col-md-6">
										<div class="main-card mb-3 card">
											<div class="card-body">
												<h5 class="card-title">Radios</h5>
												<div class="position-relative form-group">
													<div>
														<div class="custom-radio custom-control">
															<input type="radio" id="exampleCustomRadio"
																name="customRadio" class="custom-control-input"><label
																class="custom-control-label" for="exampleCustomRadio">Select
																this custom radio</label>
														</div>
														<div class="custom-radio custom-control">
															<input type="radio" id="exampleCustomRadio2"
																name="customRadio" class="custom-control-input"><label
																class="custom-control-label" for="exampleCustomRadio2">Or
																this one</label>
														</div>
														<div class="custom-radio custom-control">
															<input type="radio" id="exampleCustomRadio3" disabled=""
																class="custom-control-input"><label
																class="custom-control-label" for="exampleCustomRadio3">But
																not this disabled one</label>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="main-card mb-3 card">
											<div class="card-body">
												<h5 class="card-title">Form Select</h5>
												<div class="row">
													<div class="col-md-6">
														<div class="position-relative form-group">
															<label for="exampleCustomSelect" class="">Custom
																Select</label><select type="select" id="exampleCustomSelect"
																name="customSelect" class="custom-select">
																<option value="">Select</option>
																<option>Value 1</option>
																<option>Value 2</option>
																<option>Value 3</option>
																<option>Value 4</option>
																<option>Value 5</option>
															</select>
														</div>
														<div class="position-relative form-group">
															<label for="exampleCustomMutlipleSelect" class="">Custom
																Multiple Select</label><select multiple="" type="select"
																id="exampleCustomMutlipleSelect" name="customSelect"
																class="custom-select">
																<option value="">Select</option>
																<option>Value 1</option>
																<option>Value 2</option>
																<option>Value 3</option>
																<option>Value 4</option>
																<option>Value 5</option>
															</select>
														</div>
													</div>
													<div class="col-md-6">
														<div class="position-relative form-group">
															<label for="exampleCustomSelectDisabled" class="">Custom
																Select Disabled</label><select type="select"
																id="exampleCustomSelectDisabled" name="customSelect"
																disabled="" class="custom-select">
																<option value="">Select</option>
																<option>Value 1</option>
																<option>Value 2</option>
																<option>Value 3</option>
																<option>Value 4</option>
																<option>Value 5</option>
															</select>
														</div>
														<div class="position-relative form-group">
															<label for="exampleCustomMutlipleSelectDisabled" class="">Custom
																Multiple Select Disabled</label><select multiple=""
																type="select" id="exampleCustomMutlipleSelectDisabled"
																name="customSelect" disabled="" class="custom-select">
																<option value="">Select</option>
																<option>Value 1</option>
																<option>Value 2</option>
																<option>Value 3</option>
																<option>Value 4</option>
																<option>Value 5</option>
															</select>
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal -->
	<div class="modal fade" id="myModal" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<s:form
					action="${pageContext.request.contextPath }/admin/process-suaAccount"
					method="post">
					<div class="modal-header">
						<p class="">Đổi mật khẩu</p>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label for="email">userName:</label> <input type="text"
								class="form-control"
								name="userName" value="${pageContext.request.userPrincipal.name}" readonly="true">
						</div>
						<div class="form-group">
							<label for="pwd">Password:</label> <input type="password"
								class="form-control" id="passwordOld" placeholder="nhập mật khẩu cũ"
								name="passwordOld">
						</div>
						<div class="form-group">
							<label for="pwd">Password:</label> <input type="password"
								class="form-control" id="passwordNew" placeholder="nhập mật khẩu mới"
								name="passwordNew">
						</div>
					</div>
					<div class="modal-footer">
						<input type="submit"/>
						<a class="btn btn-default" data-dismiss="modal">Close</a>
					</div>
				</s:form>

			</div>

		</div>
	</div>
	<script type="text/javascript"
		src="${pageContext.request.contextPath }/resources/assets/scripts/main.js"></script>
</body>
</html>
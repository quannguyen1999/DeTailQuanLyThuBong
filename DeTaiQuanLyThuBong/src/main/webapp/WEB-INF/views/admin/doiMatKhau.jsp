<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>Login V1</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png"  href="${pageContext.request.contextPath }/resources/admin/images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/admin/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/admin/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/admin/vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/admin/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/admin/vendor/select2/select2.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/admin/css/util.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/resources/admin/css/main.css">
<!--===============================================================================================-->
</head>
<body>
	
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<div class="login100-pic js-tilt" data-tilt>
					<img src="${pageContext.request.contextPath }/resources/admin/images/img-01.png" alt="IMG">
					
				</div>

				<form class="login100-form validate-form" method="post" action="${pageContext.request.contextPath }/admin2/process-doiMK">
					<span class="login100-form-title">
						
						Đổi mật khẩu<br>
						
						<c:if test="${!empty msg}"><span style="color:red;">error:${msg}</span></c:if>
					</span>
					<p style="padding-left:10px;">Mật khẩu mới
						<br>
						<c:if test="${!empty errorPassOLD}">
							<span style="color:red;">
								${ errorPassOLD }
							</span>
						</c:if>
					</p>
					<div class="wrap-input100 validate-input" data-validate = "Valid email is required: ex@abc.xyz">
						<input class="input100" type="password" name="passOLD">
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<i class="fa fa-lock" aria-hidden="true"></i>
						</span>
					</div>

					<p style="padding-left:10px;">Nhập lại mật khẩu mới 
						<br>
						<c:if test="${!empty errorPassNEW}">
							<span style="color:red;">
								${ errorPassNEW }
							</span>
						</c:if>	
					</p>
					<div class="wrap-input100 validate-input" data-validate = "Password is required">
						<input class="input100" type="password" name="passNEW">
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<i class="fa fa-lock" aria-hidden="true"></i>
						</span>
					</div>
					
					<div class="container-login100-form-btn">
						<button class="login100-form-btn">
							Xác minh
						</button>
					</div>
					<br>
					<div class="container-login100-form-btn">
						<a href="${pageContext.request.contextPath }/client" class="btn btn-danger">
							Hủy
						</a>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	

	
<!--===============================================================================================-->	
	<script src="${pageContext.request.contextPath }/resources/admin/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="${pageContext.request.contextPath }/resources/admin/vendor/bootstrap/js/popper.js"></script>
	<script src="${pageContext.request.contextPath }/resources/admin/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="${pageContext.request.contextPath }/resources/admin/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="${pageContext.request.contextPath }/resources/admin/vendor/tilt/tilt.jquery.min.js"></script>
	<script >
		$('.js-tilt').tilt({
			scale: 1.1
		})
	</script>
<!--===============================================================================================-->
	<script src="${pageContext.request.contextPath }/resources/admin/js/main.js"></script>

</body>
</html>
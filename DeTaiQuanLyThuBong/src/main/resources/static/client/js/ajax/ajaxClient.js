toastr.options = {
		  "closeButton": true,
		  "debug": true,
		  "newestOnTop": true,
		  "progressBar": true,
		  "positionClass": "toast-bottom-right",
		  "preventDuplicates": false,
		  "showDuration": "300",
		  "hideDuration": "1000",
		  "timeOut": "5000",
		  "extendedTimeOut": "1000",
		  "showEasing": "swing",
		  "hideEasing": "linear",
		  "showMethod": "show",
		  "hideMethod": "hide"
		}
		function lowMyCartFunction(x){
			$.ajax({
				url : 'http://localhost:9596/client/lowMyCartJson/'+x,
				success : function(responseText) {
						$('#ajaxGetUserServletResponse').html(responseText);
						$.ajax({
							url : 'http://localhost:9596/client/showSoLuongGioHang',
							success : function(responseText) {
									$('#showSoLuong').html(responseText);
							}
						});
				}
			});
		}

		function deleteMyCartFunction(x,y) {
			$.ajax({
				url : 'http://localhost:9596/client/deleteMyCartJson/'+x,
				success : function(responseText) {
						$('#ajaxGetUserServletResponse').html(responseText);
						$.ajax({
							url : 'http://localhost:9596/client/showSoLuongGioHang',
							success : function(responseText) {
									$('#showSoLuong').html(responseText);
							}
						});
				}
			});
		} 
			function myFunction(x) {
				$.ajax({
					url : 'http://localhost:9596/client/addCartJson/'+x,
					success : function(responseText) {
							toastr["success"]("Thêm thành công!");
							$('#ajaxGetUserServletResponse').html(responseText);
							$.ajax({
								url : 'http://localhost:9596/client/showSoLuongGioHang',
								success : function(responseText) {
										$('#showSoLuong').html(responseText);
								}
							});
					},
					statusCode: {
					    400: function() {
					    	toastr["error"]("Không đủ hàng");
					    }
					}
				});
			} 
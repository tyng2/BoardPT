<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>SignUp / Potal &mdash; Web Community</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<%@ include file="/WEB-INF/views/inc/commonCSS.jsp" %>

<script src="js/jquery-3.3.1.min.js"></script>

<script>
$(function() {
	$('input[name=id]').on('keyup', function(event) {
		var id = $(this).val();
		console.log('id 변수 타입 : ' + typeof id);
		console.log('id : ' + id);
		
		$.ajax({
			url: 'joinIdCheckJson.do', // 'joinIdCheckJson.do?userid=id입력값' - data속성 사용하지 않을 시
			data: {userID: id}, // userid = id입력값
			success: function(isDup) {
				console.log('result 변수 타입 : ' + typeof(isDup));
	   			console.log('result : ' + isDup);
				
				if (isDup) {
					$('span#dupCheck').html('이미 존재하는 아이디입니다.').css('color', '#FF0000');
				} else {
					$('span#dupCheck').html('사용 가능한 아이디입니다.').css('color', '#309050');
				}
				
			}
		});
	});
});
</script>
</head>
<body>
<jsp:include page="/WEB-INF/views/inc/top.jsp"></jsp:include>

<div class="site-blocks-cover inner-page-cover overlay"
	style="background-image: url(images/hero_1.jpg);" data-aos="fade"
	data-stellar-background-ratio="0.5">
	<div class="container">
		<div
			class="row align-items-center justify-content-center text-center">

			<div class="col-md-12" data-aos="fade-up" data-aos-delay="400">

				<div class="row justify-content-center mb-4">
					<div class="col-md-8 text-center">
						<h1>Sign Up</h1>
						<p class="lead mb-5"></p>
					</div>
				</div>

			</div>
		</div>
	</div>
</div>



<div class="site-section bg-light">
<div class="container" id="f">
	<div class="row" style="margin: 0 auto; max-width: 600px;">
		<div style="width: 100%;">

			<form action="join.do" class="p-5 bg-white" method="POST">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

				<h2 class="h4 text-black mb-5">Sign Up</h2>

				<div class="row form-group">
					<div class="col-md-12">
						<label class="text-black" for="id">ID</label><span id="dupCheck" style="margin-left: 50px;"></span>
						<input type="text" name="id" class="form-control" required>
					</div>
				</div>

				<div class="row form-group">
					<div class="col-md-12">
						<label class="text-black" for="password">Password</label>
						<input type="password" name="pw" class="form-control" required>
					</div>
				</div>
				
				<div class="row form-group">
					<div class="col-md-12">
						<label class="text-black" for="email">E-Mail</label>
						<input type="email" name="email" class="form-control" required>
					</div>
				</div>
				
				<div class="row form-group">
					<div class="col-md-12">
						<label class="text-black" for="name">Name</label>
						<input type="text" name="name" class="form-control" required>
					</div>
				</div>

				<div class="row form-group">
					<div class="col-md-12">
						<label class="text-black" for="address">Address</label>
						<input type="text" name="address" class="form-control" required>
					</div>
				</div>	

<!-- 				<div class="row form-group"> -->
<!-- 					<div class="col-md-4 col-sm-4 col-xs-12 form-group"> -->
<!-- 						<label class="text-black">Gender</label><br> -->
<!-- 						<div class="form-check-inline"> -->
<!-- 							<label class="customradio"><span class="radiotextsty">M</span> -->
<!-- 							<input type="radio" checked="checked" name="gender" value="M"> -->
<!-- 							<span class="checkmark"></span></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
<!-- 							<label class="customradio"><span class="radiotextsty">W</span> -->
<!-- 							<input type="radio" name="gender" value="W"> -->
<!-- 							<span class="checkmark"></span></label> -->
<!-- 						</div> -->
<!-- 					</div> -->
<!-- 				</div> -->

				<div class="row form-group">
					<div class="col-md-12"><br>
					<button class="btn btn-custom btn-md">가입</button>
					<a href="#" onclick="history.back();" class="btn btn-custom btn-md" style="float: right;">돌아가기</a>
					</div>
				</div>

			</form>
			
		</div>
	</div>
</div>
</div>

<jsp:include page="/WEB-INF/views/inc/bottom.jsp"></jsp:include>

<%@ include file="/WEB-INF/views/inc/commonJS.jsp" %>

</body>
</html>
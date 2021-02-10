<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.board.dto.UserDto"%>
<!DOCTYPE html>
<html lang="en"><!--  boardpage -->
<head>
<title>Admin menu / Potal &mdash; Web Community</title>
<meta charset="utf-8">
<meta name="viewport"content="width=device-width, initial-scale=1, shrink-to-fit=no">

<%@ include file="/WEB-INF/views/inc/commonCSS.jsp" %>

<!-- bootstrapBoard -->

</head>
<body>
<jsp:include page="/WEB-INF/views/inc/top.jsp"></jsp:include>
<c:if test="${sessionID != 'admin' }">
<script>
alert("이 페이지는 관리자만 접근할 수 있습니다.");
location.href="main.do";
</script>
</c:if>

<div class="site-blocks-cover inner-page-cover overlay"
	style="background-image: url(images/hero_1.jpg);" data-aos="fade"
	data-stellar-background-ratio="0.5">
	<div class="container">
		<div
			class="row align-items-center justify-content-center text-center">

			<div class="col-md-12" data-aos="fade-up" data-aos-delay="400">

				<div class="row justify-content-center mb-4">
					<div class="col-md-8 text-center">
						<h1>Administrator Menu</h1>
						<p class="lead mb-5">Modify your contents</p>
					</div>
				</div>

			</div>
		</div>
	</div>
</div>

<!-- content -->
<%
UserDto m = (UserDto) request.getAttribute("member");
%>
<section class="site-section bg-light">
<div class="container">
	<div class="row">
	
		<div style="width: 100%">
			<form action="memberModifyProcess.do" class="p-5 bg-white" method="POST">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				
				<h2 class="h2 font-weight-bold mb-5">${member.id } - 정보 수정</h2>
				<input type="hidden" name="id" value="${member.id }">
				<input type="hidden" name="pageNum" value="${param.pageNum }">
				<div class="row form-group">
					<div class="col-md-6 mb-3 mb-md-0">
						<label class="text-black" for="fname">Password</label><br>
						<input type="text" name="pw" class="form-control" value="${member.password }">
					</div>
<!-- 					<div class="col-md-6"> -->
<!-- 						<label class="text-black" for="title">Gender</label><br> -->
<!-- 						<div class="form-check-inline"> -->
<!-- 							<label class="customradio"><span class="radiotextsty">M</span> -->
<%-- 							<input type="radio" name="gender" value="M" <% if ("M".equals(m.getGender())) %>checked<% %>> --%>
<!-- 							<span class="checkmark"></span></label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; -->
<!-- 							<label class="customradio"><span class="radiotextsty">W</span> -->
<%-- 							<input type="radio" name="gender" value="W" <% if ("W".equals(m.getGender())) %>checked<% %>> --%>
<!-- 							<span class="checkmark"></span></label> -->
<!-- 						</div> -->
<!-- 					</div> -->
				</div>
				
				<div class="row form-group">
					<div class="col-md-12">
						<label class="text-black" for="title">Name</label>
						<input type="text" class="form-control" name="name" value="${member.name}" required>
					</div>
				</div>

				<div class="row form-group">
					<div class="col-md-12">
						<label class="text-black" for="title">E-mail</label>
						<input type="text" class="form-control" name="email" value="${member.email}" required>
					</div>
				</div>

				<div class="row form-group">
					<div class="col-md-12">
						<label class="text-black" for="title">Address</label>
						<input type="text" class="form-control" name="address" value="${member.address}" required>
					</div>
				</div>
				<br>
				<div class="row form-group">
					<div class="col-md-12">
						<input type="submit" value="확인" class="btn btn-custom btn-md">
						<a href="memberList.do?pageNum=${param.pageNum }" class="btn btn-custom btn-md" style="float: right;">회원 목록</a>
					</div>
				</div>

			</form>
		</div>
	
	</div>
</div>
</section>

<jsp:include page="/WEB-INF/views/inc/bottom.jsp"></jsp:include>

<script src="js/jquery-3.3.1.min.js"></script>
<%@ include file="/WEB-INF/views/inc/commonJS.jsp" %>

</body>
</html>
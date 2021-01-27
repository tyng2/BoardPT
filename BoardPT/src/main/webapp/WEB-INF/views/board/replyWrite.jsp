<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en"><!--  boardpage -->
<head>
<title>Board / Potal &mdash; Web Community</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<%@ include file="/WEB-INF/views/inc/commonCSS.jsp" %>

<!-- bootstrapBoard -->

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
						<h1>Reply Write</h1>
						<p class="lead mb-5">Write your contents</p>
					</div>
				</div>

			</div>
		</div>
	</div>
</div>

<!-- content -->

<section class="site-section bg-light">
<div class="container">
	<div class="row">
	
		<div style="width: 100%">
			<form action="replyProcess.do" class="p-5 bg-white">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				
				<h2 class="h4 text-black mb-5">답글 작성</h2>
				<input type="hidden" name="pageNum" value="${param.pageNum }">
				<input type="hidden" name="re_ref" value="${param.re_ref }">
				<input type="hidden" name="re_lev" value="${param.re_lev }">
				<input type="hidden" name="re_seq" value="${param.re_seq }">
				<input type="hidden" name="category" value="${param.pcategory }">
				<div class="row form-group">
					<div class="col-md-6 mb-3 mb-md-0">
						<label class="text-black" for="fname">Category</label><br>
						<select class="form-control" name="categy">
						<option selected>${param.category }</option>
						</select>
						<!-- <input type="text" id="fname" class="form-control"> -->
					</div>
					<div class="col-md-6">
						<!-- <label class="text-black" for="lname">Last Name</label> <input
							type="text" id="lname" class="form-control"> -->
					</div>
				</div>
				
				<div class="row form-group">
					<div class="col-md-12">
						<label class="text-black" for="title">Title</label>
						<input type="text" id="title" class="form-control" name="title" required>
					</div>
				</div>

				<div class="row form-group">
					<div class="col-md-12">
						<label class="text-black" for="content">Content</label>
						<textarea name="content" id="content" cols="30" rows="8"
							class="form-control"
							placeholder="Write your contents here..."></textarea>
					</div>
				</div>

				<div class="row form-group">
					<div class="col-md-12">
						<input type="submit" value="확인" class="btn btn-custom btn-md">
						<c:choose>
						<c:when test="${param.pcategory == null }">
						<a href="board.do?pageNum=${param.pageNum }" class="btn btn-custom btn-md" style="float: right;">목록</a>
						</c:when>
						<c:otherwise>
						<a href="boardCategory.do?category=${param.pcategory }&pageNum=${param.pageNum }" class="btn btn-custom btn-md" style="float: right;">목록</a>
						</c:otherwise>
						</c:choose>
						<!-- <a href="board.do" class="btn btn-custom btn-md" style="float: right;">목록</a> -->
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
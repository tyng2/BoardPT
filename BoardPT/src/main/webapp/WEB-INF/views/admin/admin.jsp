<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en"><!--  boardpage -->
<head>
<title>Admin menu / Potal &mdash; Web Community</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

 <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
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
		<div class="row align-items-center justify-content-center text-center">

			<div class="col-md-12" data-aos="fade-up" data-aos-delay="400">

				<div class="row justify-content-center mb-4">
					<div class="col-md-8 text-center">
						<h1>Administrator Menu</h1>
						<p class="lead mb-5">관리자 메뉴</p>
					</div>
				</div>

			</div>
		</div>
	</div>
</div>

<section class="site-section">
<!-- <h2 class="text-black site-section-heading text-center" id="lst">Select</h2> -->
	<div class="container">
		<div class="row">
			<div style="width: 100%;">
			<!-- bootstrapBoard -->
				<div class="panel-body">
				<table class="table table-striped table-bordered table-list" style="float: none;">
					<tr>
					<th width="50%">Member List</th>
					<th width="50%">Admin Chart</th>
					<!-- <th width="16px"></th> -->
					</tr>
					<tr>
					<td><a href="memberList.do"><img src="images/admin.png" style="height: 200px;"></a></td>
					<td><a href="adminChartForm.do"><img src="images/chart.png" style="height: 200px;"></a></td>
					</tr>
				</table>

				</div>

				<!-- bootstrapBoard -->
				
				<!--  boardpage -->
				
				
				<!--  boardpage -->
			</div>

		</div>
	</div>
</section>

<jsp:include page="/WEB-INF/views/inc/bottom.jsp"></jsp:include>

<%@ include file="/WEB-INF/views/inc/commonJS.jsp" %>
<script src="/js/jquery-3.3.1.min.js"></script>

<script>
$(document).ready(function() {
	$('#chAll').click(function() {
		if ($('#chAll').is(':checked')){
			$('input:checkbox[name=chBoxId]').prop('checked', true);
		} else {
			$('input:checkbox[name=chBoxId]').prop('checked', false);
		}
	});
});
</script>
</body>
</html>
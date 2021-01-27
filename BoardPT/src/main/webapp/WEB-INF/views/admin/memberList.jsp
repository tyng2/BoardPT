<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en"><!--  boardpage -->
<head>
<title>Admin menu / Potal &mdash; Web Community</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<%@ include file="/WEB-INF/views/inc/commonCSS.jsp" %>

 <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
<!-- bootstrapBoard -->
<script>
/*
function delMember(id){
	if (id == "") {
		alert('탈퇴할 회원을 먼저 선택해 주세요.');
	} else{
		var del = confirm(id + ' 회원을 강제 탈퇴 시키겠습니까?');
		if (del) {
			location.href="memberDelProcess.do?id=" + id;
		}			
	}
}
*/
</script>
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
						<p class="lead mb-5">회원 목록</p>
					</div>
				</div>

			</div>
		</div>
	</div>
</div>

<section class="site-section">
<h2 class="text-black site-section-heading text-center" id="lst">Member List</h2>
	<div class="container">
		<div class="row">
			<div style="width: 100%;">
			<!-- bootstrapBoard -->
			<form action="memberDelProcess.do" method="POST">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
			
				<div class="panel-body">
				<table class="table table-striped table-bordered table-list">
				<thead>
					<tr>
					<th width="16px"><input type="checkbox" id="chAll"></th>
					<th width="10%">ID</th>
					<th>PW</th>
					<th>NAME</th>
					<th>E-MAIL</th>
					<th width="30px">GENDER</th>
					<th width="10%">ADDRESS</th>
					<th width="170px">REG_DATE</th>
					<!-- <th width="16px"></th> -->
					</tr>
				</thead>
				<tbody>
				<c:choose>
				<c:when test="${empty list }">
				<tr><td colspan="8" style="text-align: center;">가입한 멤버가 없습니다.</td></tr>
				</c:when>
				<c:otherwise>
				<c:forEach items="${list }" var="v">
					<tr>
					<%-- <td><a href="javascript:delMember('${v.id }')"><i class="material-icons">clear</i></a></td> --%>
					<td><input type="checkbox" name="chBoxId" value="${v.id }" ></td>
					<td class="title1"><a href="memberModifyForm.do?id=${v.id }&pageNum=${pageInfoMap.pageNum }">${v.id }</a></td>
					<td>${v.password }</td>
					<td>${v.name }</td>
					<td>${v.email }</td>
					<td>${v.gender }</td>
					<td>${v.address }</td>
					<td><fmt:formatDate value="${v.reg_date }" pattern="yyyy.MM.dd HH:mm:ss"/></td>
					<%-- <td><a href="javascript:delMember('${v.id }')"><i class="material-icons">clear</i></a></td> --%>
					</tr>
				</c:forEach>
				</c:otherwise>
				</c:choose>
				</tbody>
				</table>

				</div>

				<!-- bootstrapBoard -->
				
				<!--  boardpage -->
				
				<div class="row">
				<div class="col-12">
				<div class="custom-pagination text-center">
					<c:if test="${pageInfoMap.allRowCount > 0 }">

					<c:if test="${pageInfoMap.startPage > pageInfoMap.pageBlockSize }">
					<a href="memberList.do?pageNum=1&search=${search }&#lst">1</a><span class="more-page">...</span>
					<a href="memberList.do?pageNum=${pageInfoMap.startPage - 1 }&search=${search }&#lst"><span class="pt"><img src="images/left-arrow.png" width="18px" height="18px"></span></a>
					</c:if>

					<c:forEach begin="${pageInfoMap.startPage }" end="${pageInfoMap.endPage }" step="1" varStatus="s">
					
					<c:choose>
					<c:when test="${s.current == pageInfoMap.pageNum }">
					<span>${s.current }</span>
					</c:when>
					<c:otherwise>
						<a href="memberList.do?pageNum=${s.current }&search=${search }&#lst">${s.current }</a>
					</c:otherwise>
					</c:choose>
					</c:forEach>

					<c:if test="${pageInfoMap.endPage < pageInfoMap.maxPage }">
					<a href="memberList.do?pageNum=${pageInfoMap.endPage + 1 }&search=${search }&#lst"><span class="pt"><img src="images/right-arrow.png" width="18px" height="18px"></span></a>
					<span class="more-page">...</span>
					<a href="memberList.do?&pageNum=${pageInfoMap.maxPage }&search=${search }&#lst">${pageInfoMap.maxPage }</a>
					</c:if>

					</c:if>
				</div>
				</div>
				</div>
				
				<p style="text-align: right;" class="mt-4">
					<button class="btn btn-custom btn-md">강제 탈퇴</button>
					<%-- <a href="javascript:delMember('${v.id }')" class="btn btn-custom btn-md">강제 탈퇴</a> --%>
				</p>
				</form>
				
				<h3 class="h5 text-black mb-3">Search</h3>
				<form action="memberList.do" method="get">
					<%-- <input type="hidden" name="category" value="${param.category }"> --%>
					<div class="form-group d-flex">
						<input type="text" class="form-control" name="search"
							placeholder="ID로 검색" value="${search }">
					</div>
				</form>
				
				<!--  boardpage -->
			</div>

		</div>
	</div>
</section>

<jsp:include page="/WEB-INF/views/inc/bottom.jsp"></jsp:include>

<script src="js/jquery-3.3.1.min.js"></script>
<%@ include file="/WEB-INF/views/inc/commonJS.jsp" %>

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
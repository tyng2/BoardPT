<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
						<h1>Board</h1>
						<p class="lead mb-5">Free board for everyone</p>
					</div>
				</div>

			</div>
		</div>
	</div>
</div>

<section class="site-section">
<h2 class="text-black site-section-heading text-center" id="board">전체글보기</h2>

<div class="container">
<div class="row">
	<div class="col-md-8">
	<!-- bootstrapBoard -->

		<div class="panel-body">
		<table class="table table-striped table-bordered table-list">
		<thead>
			<tr>
			<!-- <th width="10%">게시판</th> -->
			<th colspan="2">제목</th>
			<th width="20%">작성자</th>
			<th width="10%">작성일</th>
			<th width="60px">조회</th>
			</tr>
		</thead>
		<tbody>
		<c:choose>
		<c:when test="${empty list }">
		<tr><td colspan="5" style="text-align: center;">작성된 게시글이 없습니다.</td></tr>
		</c:when>
		<c:otherwise>
		<c:forEach items="${list }" var="v">
			<tr>
			<td width="10%" class="category"><a href="board.do?bord_catg=${v.bord_catg }">${v.bord_catg }</a></td>
			<td class="title1 left">
			<c:if test="${v.bord_levl > 0 }">
				<c:forEach begin="1" end="${v.bord_levl }">
					&nbsp;
				</c:forEach>
				<span id="reply">└</span>
			</c:if>
			<a href="boardView.do?bord_numb=${v.bord_numb }&pageNum=${pageInfoMap.pageNum }&#view">
					${v.bord_titl }</a>
			<c:if test="${v.file_cont > 0 }">
			<img src="images/disk.png" style="width: 14px;">
			</c:if>
			<c:if test="${v.comm_cont > 0 }">
			<b style="color: red;">[${v.comm_cont }]</b>
			</c:if>
			</td>
			<td>${v.user_id }</td>
			<td>
			<jsp:useBean id="today" class="java.util.Date"></jsp:useBean>
			<fmt:parseNumber value="${today.time / (1000 * 60 * 60 * 24)}" var="nowDays" integerOnly="true" />
			<fmt:parseNumber value="${v.bord_date.time / (1000 * 60 * 60 * 24)}" var="regDays" integerOnly="true" />
			<c:set value="${nowDays - regDays }" var="dayDiff" />
			<c:choose>
			<c:when test="${dayDiff == 0 }">
			<fmt:formatDate value="${v.bord_date }" pattern="HH:mm:ss"/>
			</c:when>
			<c:otherwise>
			<fmt:formatDate value="${v.bord_date }" pattern="yyyy.MM.dd"/>
			</c:otherwise>
			</c:choose>
			</td>
			<td>${v.bord_hitc }</td>
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
			<a href="board.do?pageNum=1&search=${search }&#board">1</a><span class="more-page">...</span>
			<a href="board.do?pageNum=${pageInfoMap.startPage - 1 }&search=${search }&#board"><span class="pt"><img src="images/left-arrow.png" width="18px" height="18px"></span></a>
			</c:if>

			<c:forEach begin="${pageInfoMap.startPage }" end="${pageInfoMap.endPage }" step="1" varStatus="s">
			
			<c:choose>
			<c:when test="${s.current == pageInfoMap.pageNum }">
			<span>${s.current }</span>
			</c:when>
			<c:otherwise>
				<a href="board.do?pageNum=${s.current }&search=${search }&#board">${s.current }</a>
			</c:otherwise>
			</c:choose>
			</c:forEach>

			<c:if test="${pageInfoMap.endPage < pageInfoMap.maxPage }">
			<a href="board.do?pageNum=${pageInfoMap.endPage + 1 }&search=${search }&#board"><span class="pt"><img src="images/right-arrow.png" width="18px" height="18px"></span></a>
			<span class="more-page">...</span>
			<a href="board.do?pageNum=${pageInfoMap.maxPage }&search=${search }&#board">${pageInfoMap.maxPage }</a>
			</c:if>

			</c:if>
		</div>
		</div>
		</div>
		
		<p style="text-align: right;" class="mt-4">
			<c:if test="${sessionID != null }">
				<a href="boardWrite.do" class="btn btn-custom btn-md">글쓰기</a>
			</c:if>
		</p>
		
		<!--  boardpage -->
	</div>


	<div class="col-md-3 ml-auto" ><!-- style="max-width: 25%;" -->
		<div class="mb-5">
			<h3 class="h5 text-black mb-3">Search</h3>
			<form action="board.do" method="get">
				<div class="form-group d-flex">
					<input type="text" class="form-control" name="search"
						placeholder="Search keyword and hit enter..." value="${search }">
				</div>
			</form>
		</div>

		<div class="mb-5">
			<h3 class="h5 text-black mb-3">Popular Posts</h3>
			<ul class="list-unstyled">
				<c:forEach items="${hitList }" var="hl">
					<li class="mb-2"><a href="boardView.do?bord_numb=${hl.bord_numb }&#view">${hl.bord_titl }</a></li>
				</c:forEach>
			</ul>
		</div>

		<div class="mb-5">
			<h3 class="h5 text-black mb-3">Recent Comments</h3>
			<ul class="list-unstyled">
				<c:forEach items="${commentList }" var="cl">
					<li class="mb-2"><a href="boardView.do?bord_numb=${cl.bord_numb }&#CommentAn">${cl.comm_cont }</a></li>
				</c:forEach>
				<!-- <li class="mb-2"><a href="#">Joefrey</a> <em>in</em> <a
					href="#">Lorem ipsum dolor sit amet</a></li>
				<li class="mb-2"><a href="#">Joefrey</a> <em>in</em> <a
					href="#">Quaerat rerum voluptatibus veritatis</a></li>
				<li class="mb-2"><a href="#">Joefrey</a> <em>in</em> <a
					href="#">Maiores sapiente veritatis reprehenderit</a></li>
				<li class="mb-2"><a href="#">Joefrey</a> <em>in</em> <a
					href="#">Natus eligendi nobis</a></li> -->
			</ul>
		</div>

	</div>

</div>
</div>

</section>

<jsp:include page="/WEB-INF/views/inc/bottom.jsp"></jsp:include>
<%@ include file="/WEB-INF/views/inc/commonJS.jsp" %>


</body>
</html>
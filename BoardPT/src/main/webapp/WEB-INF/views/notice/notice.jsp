<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en"><!--  boardpage -->
<head>
<title>Notice / Potal &mdash; Web Community</title>
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
						<h1>Notice</h1>
						<p class="lead mb-5">공지사항</p>
					</div>
				</div>

			</div>
		</div>
	</div>
</div>

<section class="site-section bg-light" id="board">
<!-- <h2 class="text-black site-section-heading text-center" id="board">공지사항</h2> -->
<div class="container">
	<div class="row">
		<div style="width: 100%;">
		<!-- bootstrapBoard -->
		<c:forEach items="${noticeList }" var="n">
			<div class="panel-body">
			<div class="p-4 mb-3 bg-white" style="margin: 0;">
				<p class="mb-0 font-weight-bold h2">${n.title }
				<span style="float: right; font-size: 15px;">No. ${n.num }</span></p>
				<p class="mb-4" style="margin: 15px 0;"><span style="float: right;"><fmt:formatDate value="${n.reg_date }" pattern="yyyy.MM.dd HH:mm:ss"/></span></p>
			</div>
				
			<div class="p-4 mb-3 bg-white">
				<p><pre style="font-family: 'Quicksand'; font-size: 15px; margin: 20px 0; min-height: 160px;">${n.content }</pre></p>
				
			</div>
			</div><br><br><br>
		</c:forEach>
			<!-- bootstrapBoard -->
			
			<!--  boardpage -->
			<div class="row">
			<div class="col-12">
			<div class="custom-pagination text-center">
				<c:if test="${pageInfoMap.allRowCount > 0 }">

				<c:if test="${pageInfoMap.startPage > pageInfoMap.pageBlockSize }">
				<a href="notice.do?pageNum=1&search=${search }&#board">1</a><span class="more-page">...</span>
				<a href="notice.do?pageNum=${pageInfoMap.startPage - 1 }&search=${search }&#board"><span class="pt"><img src="images/left-arrow.png" width="18px" height="18px"></span></a>
				</c:if>

				<c:forEach begin="${pageInfoMap.startPage }" end="${pageInfoMap.endPage }" step="1" varStatus="s">
				
				<c:choose>
				<c:when test="${s.current == pageInfoMap.pageNum }">
				<span>${s.current }</span>
				</c:when>
				<c:otherwise>
					<a href="notice.do?pageNum=${s.current }&#board">${s.current }</a>
				</c:otherwise>
				</c:choose>
				</c:forEach>

				<c:if test="${pageInfoMap.endPage < pageInfoMap.maxPage }">
				<a href="notice.do?pageNum=${pageInfoMap.endPage + 1 }&#board"><span class="pt"><img src="images/right-arrow.png" width="18px" height="18px"></span></a>
				<span class="more-page">...</span>
				<a href="notice.do?pageNum=${pageInfoMap.maxPage }&#board">${pageInfoMap.maxPage }</a>
				</c:if>

				</c:if>
			</div>
			</div>
			</div>
			
			<p style="text-align: right;" class="mt-4">
				<c:if test="${sessionID.equals('admin') }">
				<a href="noticeWrite.do" class="btn btn-custom btn-md">글쓰기</a>
				</c:if>
			</p>
			
			<!--  boardpage -->
		</div>

	</div>
</div>
</section>

<jsp:include page="/WEB-INF/views/inc/bottom.jsp"></jsp:include>
<%@ include file="/WEB-INF/views/inc/commonJS.jsp" %>


</body>
</html>
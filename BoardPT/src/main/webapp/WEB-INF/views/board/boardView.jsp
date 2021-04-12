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

</head>
<body>
<jsp:include page="/WEB-INF/views/inc/top.jsp"></jsp:include>
<div id="sessionID" style="display: none;">${sessionID }</div>
<div class="site-blocks-cover inner-page-cover overlay"
	style="background-image: url(images/hero_1.jpg);" data-aos="fade"
	data-stellar-background-ratio="0.5">
	<div class="container">
	<div class="row align-items-center justify-content-center text-center">

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



<!-- content -->
<section class="site-section bg-light">
<div id="view" class="container">
	<div class="row">
	<div style="min-width: 100%;">
		<div class="p-4 mb-3 bg-white" style="margin: 0;">
			<p class="mb-0 font-weight-bold h2">${board.bord_titl } <span style="font-size: 18px"><span style="color: gray;">|</span> ${board.bord_catg }</span>
			<span style="float: right; font-size: 15px;">No. ${board.bord_numb }</span></p>
			<p class="mb-0" style="margin: 15px 0;"><b>${board.user_id }</b><span style="float: right;"><fmt:formatDate value="${board.bord_date }" pattern="yyyy.MM.dd HH:mm:ss"/></span></p>
		</div>
		
		<c:if test="${files.size() != 0 }">
			<div class="p-4 mb-3 bg-white"><p class="mb-0"><br />
			<c:forEach items="${files }" var="item" >
				<a href="boardFileProcess.do?fileId=${item.file_numb }" target="_blank"><button class="file themeBtn4">${item.file_olnm }</button></a><br /><br />
			</c:forEach>
			</p></div>
		</c:if>
					
		<div class="p-4 mb-3 bg-white">
			<p><pre style="font-family: 'Quicksand'; font-size: 15px; margin: 20px 0; min-height: 160px;">${board.bord_cont }</pre></p>
			<p class="mb-4" style="text-align: right;">조회 : ${board.bord_hitc }</p>
		</div>
		<div id="CommentAn"></div>
		<div class="p-4 mb-3 bg-white">
			<div id="comment"></div>
			
			<form action="boardComment.do" method="POST">
				<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				
				<input type="hidden" name="num" id="num" value="${board.bord_numb }">
				<input type="hidden" name="pageNum" id="pageNum" value="${param.pageNum }">
				<input type="hidden" name="category" id="category" value="${param.bord_catg }">
		
				<c:if test="${sessionID != null }">
				<textarea name="content" id="content" cols="30" rows="4"
					class="form-control" 
					placeholder="Write your comment here..."></textarea><br>
				<button type="button" id="insertComment" class="btn btn-custom btn-md" style="float: right;">등록</button>
				</c:if>
			</form><br><br>
		</div>

		<div class="p-4 mb-3">
			<p style="text-align: right;" class="mb-0">
			<c:if test="${sessionID != null }">
				<c:if test="${sessionID.equals('admin') && !sessionID.equals(board.user_id) }">
					<a href="boardDeleteProcess.do?num=${board.bord_numb }&pageNum=${param.pageNum }&category=${param.bord_catg }" class="btn btn-custom btn-md">삭제</a>
				</c:if>
				<c:if test="${sessionID.equals(board.user_id) }">
					<a href="boardModify.do?num=${board.bord_numb }&pageNum=${param.pageNum }&category=${param.bord_catg }" class="btn btn-custom btn-md">수정</a>
					<a href="boardDeleteProcess.do?num=${board.bord_numb }&pageNum=${param.pageNum }&category=${param.bord_catg }" class="btn btn-custom btn-md">삭제</a>
				</c:if>
				<a href="reply.do?re_ref=${board.bord_refr }&re_lev=${board.bord_levl }&re_seq=${board.bord_seqn }&category=${board.bord_catg }&pageNum=${param.pageNum }&pcategory=${param.bord_catg }" class="btn btn-custom btn-md">답글</a>
			</c:if>
			<c:choose>
			<c:when test="${param.bord_catg == null }">
				<a href="board.do?pageNum=${param.pageNum }&#board" class="btn btn-custom btn-md">목록</a>
			</c:when>
			<c:otherwise>
				<a href="board.do?category=${board.bord_catg }&pageNum=${param.pageNum }&#board" class="btn btn-custom btn-md">목록</a>
			</c:otherwise>
			</c:choose>
				
			</p>
		</div>
	</div>
	</div>
</div>
</section>
<jsp:include page="/WEB-INF/views/inc/bottom.jsp"></jsp:include>

<%@ include file="/WEB-INF/views/inc/commonJS.jsp" %>
<script src="/js/boardPT/board/boardView.js"></script>
</body>
</html>
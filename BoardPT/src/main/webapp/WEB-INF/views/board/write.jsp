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
						<h1>Write</h1>
						<p class="lead mb-5">Write your contents</p>
					</div>
				</div>

			</div>
		</div>
	</div>
</div>

<section class="site-section bg-light">

<!-- content -->
<%
String category = (String) request.getParameter("bord_catg");
%>
<div class="container">
<div class="row">

	<div style="width: 100%">
	<form action="boardWriteProcess.do" class="p-5 bg-white" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		
		<h2 class="h4 text-black mb-5">게시글 작성</h2>

		<input type="hidden" name="category" value="${param.category }">
		<div class="row form-group">
			<div class="col-md-6 mb-3 mb-md-0">
				<label class="text-black" for="fname">Category</label><br>
				<select class="form-control" name="categy">
				<option <% if ("C".equals(category)) {%>selected<%} %>>C</option>
				<option <% if ("Java".equals(category)) {%>selected<%} %>>Java</option>
				<option <% if ("Python".equals(category)) {%>selected<%} %>>Python</option>
				<option <% if ("SQL".equals(category)) {%>selected<%} %>>SQL</option>
				<option <% if ("Web".equals(category)) {%>selected<%} %>>Web</option>
				<option <% if ("기타".equals(category)) {%>selected<%} %>>기타</option>
				<option <% if ("잡담".equals(category) || category == null) {%>selected<%} %>>잡담</option>
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
				<label class="text-black" for="title">File</label>
				<input type="hidden" id="file_cnt" name="file_cnt" value="1">
				<button type="button" class="btn btn-none" onclick="file_add(50, 'class=input_write');"><img src="images/add.png" width="22px" height="22px"></button>
				<button type="button" class="btn btn-none" onclick="file_delete();"><img src="images/minus.png" width="22px" height="22px"></button>
				<div>
				
				</div>
				<div id="file_add_form" >
					<input type="file" name='bbs_file' class="form-control" >
<!-- 					<input type="file" name='bbs_file1' class="form-control" > -->
				</div>
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
				<input type="submit" value="작성" class="btn btn-custom btn-md">
				<a href="board.do" class="btn btn-custom btn-md" style="float: right;">목록</a>
			</div>
		</div>

	</form>
	</div>

</div>
</div>
</section>

<jsp:include page="/WEB-INF/views/inc/bottom.jsp"></jsp:include>
<%@ include file="/WEB-INF/views/inc/commonJS.jsp" %>
<script>
var uf = "";
function file_add(size, ext) {
	var filecountTemp = parseInt(document.getElementById("file_cnt").value);
	var parents = document.getElementById("file_add_form");
	var br = document.createElement("br");
	br.setAttribute("id", "br" + (filecountTemp + 1));
	parents.appendChild(br);
	if (filecountTemp == 30) {
		alert("파일 업로드는 최대 30개까지 가능합니다.");
		return;
	} else {
		var obj = document.createElement("input");
		obj.setAttribute("type", "file");
		//obj.setAttribute("size", size);
		obj.setAttribute("name", "bbs_file");
		obj.setAttribute("class", "form-control");
		obj.setAttribute("id", "file" + (filecountTemp + 1));
		parents.appendChild(obj);
	}
	document.getElementById("file_cnt").value = filecountTemp + 1;
}

function file_delete() {
	var filecountTemp = parseInt(document.getElementById("file_cnt").value);
	var parents = document.getElementById("file_add_form");
	var obj = document.getElementById("file" + filecountTemp);
	var br = document.getElementById('br' + filecountTemp);
	parents.removeChild(obj);
	parents.removeChild(br);
	document.getElementById("file_cnt").value = filecountTemp - 1;
}
</script>

</body>
</html>
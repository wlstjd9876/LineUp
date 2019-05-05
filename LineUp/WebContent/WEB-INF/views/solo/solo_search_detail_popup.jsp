<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap-theme.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/solotab/solo.js"></script>
<script type="text/javascript">
function setAddress(){
	window.opener.document.getElementById("loc_name").value=document.getElementById("loc_name").value;
	window.opener.document.getElementById("loc_num").value=document.getElementById("loc_num").value;
	window.close();
}
</script>
<div class="page-main-style  align-center">
	<div class="form-header">
		<h1>${loc.loc_name} 경기장의 정보</h1>
	</div> 
	<div class="row">
		<div class="col-sm-6 col-md-6 col-md-offset-3">
			<div class="thumbnail">
				<img src="${pageContext.request.contextPath}/upload/${loc.loc_photo2}" class="thumb-image">
			</div>
		</div>
	</div>
	<div class="form-panel1">
		<div class="form-content">
		<div class="form-group align-center js-style">
				<input type="hidden" class="form-control" id="loc_name" name="loc_name" value="${loc.loc_name }">
			</div>
			<div class="form-group align-center js-style">
				<input type="hidden" class="form-control" id="loc_num" name="loc_num" value="${loc.loc_num }">
			</div>
			<div class="form-group align-center js-style">
				<label for="loc_address">Location ADDRESS</label>
				<input type="hidden" class="form-control" id="loc_address" name="loc_address" value="${loc.loc_address }"><p class="js-style">${loc.loc_address }</p>
			</div>
			<div class="form-group align-center js-style">
				<label for="loc_phone">Location PHONE</label>
				<input type="hidden" class="form-control" id="loc_phone" name="loc_phone" value="${loc.loc_phone}"><p class="js-style">${loc.loc_phone }</p>
			</div>
			<div class="form-group align-center js-style">
				<label for="loc_content">Location CONTENT</label>
				<input type="hidden" class="form-control" id="loc_content" name="loc_content" value="${loc.loc_content }"><p class="js-style">${loc.loc_content }</p>
			</div>
			<div class="form-group">
				<button type="button" onclick="setAddress()" class="js">경기장 사용</button>	
				<button type="button" class="js" onclick="location.href='${pageContext.request.contextPath}/solo/search.do'">목록으로</button>
			</div>
		</div>
	</div>
</div>
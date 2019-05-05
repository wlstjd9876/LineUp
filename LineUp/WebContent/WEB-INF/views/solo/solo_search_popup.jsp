<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css"
	type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap/bootstrap-theme.css"
	type="text/css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/solotab/solo.js"></script>
<div class="page-main-style">
	<h2 class="align-center">경기장목록</h2>
	<form action="search.do" method="get" id="search_form">
		<ul class="search js-center">
			<li class="sel"><select class="selectBox01" name="keyfield"
				id="keyfield">
					<option value="loc_name">경기장이름</option>
					<option value="loc_address">경기장주소</option>
			</select></li>
			<li><span class="green_window"> <input type="search"
					class="input_text" name="keyword" id="keyword" maxlength="33" />
			</span>
				<button type="submit" class="sch_smit">검색</button></li>
		</ul>
	</form>
	<br>
	<c:if test="${count==0}">
		<div class="align-center">
			<h2>등록된 경기장이 없습니다.</h2>
		</div>
	</c:if>
	<c:if test="${count > 0}">
		<c:forEach var="loc" items="${list}">
			<div class="list-group">
				<a href="search_detail.do?loc_num=${loc.loc_num}"
					class="js_loc_pop list-group-item">
					<h4 class="list-group-item-heading">${loc.loc_name }</h4>
					<p class="list-group-item-text">${loc.loc_address }</p>
				</a>
			</div>
		</c:forEach>
		<div class="align-center">${pagingHtml}</div>
	</c:if>
	<br>
	<br>
	<div class="list-space js-center">
		<div class="form-group">
			<button type="button"
				onclick="javascript:opener.location.href='${pageContext.request.contextPath}/location/writeForm.do';self.close();"
				class="js">경기장 등록</button>
			<button type="button" class="js"
				onclick="location.href='search_list.do'">목록으로</button>
			<button type="button" class="js"
				onclick="location.href=''${pageContext.request.contextPath}/main/main.do'">홈으로</button>
		</div>
	</div>
</div>
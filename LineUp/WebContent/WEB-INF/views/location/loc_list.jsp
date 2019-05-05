<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/locationtab/location.js"></script>
<div class="page-main-style">
	<h2 class="align-center">경기장목록</h2>
	<form action="list.do" method="get" id="search_form">
		<ul class="search js-center">
			<li class="sel">
				<select class="selectBox01" name="keyfield" id="keyfield">
						<option value="loc_name">경기장이름</option>
						<option value="loc_address">경기장주소</option>
				</select>
			</li>
			<li><span class="green_window">
				<input type="search" class="input_text" name="keyword" id="keyword" maxlength="33" />
				</span>
				<button type="submit" class="sch_smit">검색</button>
			</li> 
		</ul>
	</form>
	<br>
	<c:if test="${count==0}">
		<div class="align-center">
				<h2>등록된 경기장이 없습니다.</h2>
		</div>
	</c:if>
	<c:if test="${count > 0}">
			<div class="row">
				<c:forEach var="loc" items="${list}">
				<div class="col-sm-6 col-md-4 align-center">
					<div class="thumbnail">
						<c:if test="${!empty loc.loc_photo2}">
							<img
								src="${pageContext.request.contextPath}/upload/${loc.loc_photo2}"
								class="thumb-image">
						</c:if>
						<div class="caption">
							<h3>${loc.loc_name }</h3>
							<p class="js-loc_address">${loc.loc_address }</p>
							<p>
								<a href="detail.do?loc_num=${loc.loc_num}" class="btn btn-primary">자세히 보기</a>
							</p>
						</div>
					</div>
				</div>
				</c:forEach>
			</div>
		<div class="align-center">${pagingHtml}</div>
	</c:if>
	<br><br>
	<div class="list-space js-center">
		<div class="form-group">
			<button type="button" class="js" onclick="location.href='writeForm.do'">경기장 등록</button>
			<button type="button" class="js" onclick="location.href='list.do'">목록으로</button>
			<button type="button" class="js" onclick="location.href=''${pageContext.request.contextPath}/main/main.do'">홈으로</button>
		</div>
	</div>
</div>
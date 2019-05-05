<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/table/table.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/teamtab/team.js"></script>
<div class="page-main-style">
	<h2 class="align-center">경기 목록</h2>
	<br>
	<c:if test="${count==0}">
		<div class="align-center">
				<h2>등록된 경기가 없습니다.</h2>
		</div>
	</c:if>
	<c:if test="${count > 0}">
		<div class="team-tb-wid js-center">
			<div class="tbrow header blue">
				<div class="cell">연령대</div>
				<div class="cell">경기일자</div>
				<div class="cell">마감일자</div>
				<div class="cell">모집인원</div>
				<div class="cell">신청인원</div>
				<div class="cell">신청</div>
			</div>
			<c:forEach var="team" items="${list}">
				<div class="tbrow">
					<div class="cell">${team.team_age}</div>
					<div class="cell">${team.team_date}</div>
					<div class="cell">${team.team_end_date}</div>
					<div class="cell">${team.team_member}</div>
					<div class="cell">${team.team_now_member}</div>
					<div class="cell"><button type="button" class="btn btn-success btn-sm" onclick="location.href='applyForm.do?team_num=${team.team_num}'">신청</button></div>
				</div>
			</c:forEach>
		</div>
		<div class="align-center">${pagingHtml}</div>
	</c:if>
	
	<br><br>
	<div class="list-space js-center">
		<div class="form-group align-center">
			<c:if test="${user_auth >1}">
				<button type="button" class="js" id="team_lineup_write" onclick="location.href='${pageContext.request.contextPath}/team/writeform.do'">라인업 등록</button>
			</c:if>
			<button type="button" class="js" onclick="location.href='hirelist.do'">목록으로</button>
			<button type="button" class="js" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">홈으로</button>
		</div>
	</div>
</div>
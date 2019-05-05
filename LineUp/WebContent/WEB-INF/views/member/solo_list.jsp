<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/table/table.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/teamtab/team.js"></script>
<div class="page-main-style">
	<c:if test="${count > 0}">
		<h2 class="align-center">등록한 경기 목록</h2>
		<br>
		<div class="write-tb-wid-solo js-center align-center">
			<div class="tbrow header blue">
				<div class="cell">글번호</div>
				<div class="cell">연령대</div>
				<div class="cell">성별</div>
				<div class="cell">경기장</div>
				<div class="cell">경기일자</div>
				<div class="cell">마감일자</div>
				<div class="cell">모집인원</div>
				<div class="cell">최소인원</div>
				<div class="cell">신청인원</div>
				<div class="cell">작성글확인</div>
			</div>
			<c:forEach var="solo" items="${list}">
				<div class="tbrow">
					<div class="cell">${solo.solo_num}</div>
					<div class="cell">${solo.solo_age }</div>
					<div class="cell">${solo.solo_gen }</div>
					<div class="cell">${solo.loc.loc_name}</div>
					<div class="cell">${solo.solo_date}</div>
					<div class="cell">${solo.solo_end_date}</div>
					<div class="cell">${solo.solo_member}</div>
					<div class="cell">${solo.solo_min_member }</div>
					<div class="cell">${solo.solo_now_member }</div>
					<div class="cell">
						<button type="button" class="btn btn-success btn-sm"
							onclick="location.href='${pageContext.request.contextPath}/solo/applyForm.do?solo_num=${solo.solo_num}'">확인</button>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="align-center">${pagingHtml}</div>
	</c:if>
	<br><br>
	<c:if test="${spcount > 0}">
		<h2 class="align-center">내가 신청한 개인 라인업 목록</h2>
		<br>
		<div class="write-tb-wid-solo js-center align-center">
			<div class="tbrow header blue">
				<div class="cell">글번호</div>
				<div class="cell">연령대</div>
				<div class="cell">성별</div>
				<div class="cell">경기장</div>
				<div class="cell">경기일자</div>
				<div class="cell">마감일자</div>
				<div class="cell">모집인원</div>
				<div class="cell">최소인원</div>
				<div class="cell">신청인원</div>
				<div class="cell">작성글확인</div>
			</div>
			<c:forEach var="solo" items="${list1}">
				<div class="tbrow">
					<div class="cell">${solo.solo_num}</div>
					<div class="cell">${solo.solo_age }</div>
					<div class="cell">${solo.solo_gen }</div>
					<div class="cell">${solo.loc.loc_name}</div>
					<div class="cell">${solo.solo_date}</div>
					<div class="cell">${solo.solo_end_date}</div>
					<div class="cell">${solo.solo_member}</div>
					<div class="cell">${solo.solo_min_member }</div>
					<div class="cell">${solo.solo_now_member }</div>
					<div class="cell">
						<button type="button" class="btn btn-success btn-sm"
							onclick="location.href='${pageContext.request.contextPath}/solo/applyForm.do?solo_num=${solo.solo_num}'">확인</button>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="align-center">${pagingHtml1}</div>
	</c:if>
	<c:if test="${tpcount > 0}">
		<h2 class="align-center">내가 신청한 팀라인업 목록</h2>
		<br>
		<div class="write-tb-wid js-center align-center">
			<div class="tbrow header blue">
				<div class="cell">글번호</div>
				<div class="cell">연령대</div>
				<div class="cell">경기일자</div>
				<div class="cell">마감일자</div>
				<div class="cell">모집인원</div>
				<div class="cell">신청인원</div>
				<div class="cell">작성글확인</div>
			</div>
			<c:forEach var="team" items="${list2}">
				<div class="tbrow">
					<div class="cell">${team.team_num}</div>
					<div class="cell">${team.team_age}</div>
					<div class="cell">${team.team_date}</div>
					<div class="cell">${team.team_end_date}</div>
					<div class="cell">${team.team_member}</div>
					<div class="cell">${team.team_now_member}</div>
					<div class="cell">
						<button type="button" class="btn btn-success btn-sm"
							onclick="location.href='${pageContext.request.contextPath}/team/applyForm.do?team_num=${team.team_num}'">확인</button>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="align-center">${pagingHtml2}</div>
	</c:if>
	<br>
	<br>
	<div class="list-space js-center">
		<div class="form-group align-center">
			<button type="button" class="js"
				onclick="location.href='${pageContext.request.contextPath}/main/main.do'">홈으로</button>
		</div>
	</div>
</div>
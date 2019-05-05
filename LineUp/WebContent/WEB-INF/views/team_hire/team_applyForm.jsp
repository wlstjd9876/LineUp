<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/table/table.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/teamtab/team.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/teamtab/apply.js"></script>
<script type="text/javascript">
var contextPath = "${pageContext.request.contextPath}";
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/teamtab/applylist.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('#js-delete').click(function(){
		if (confirm("정말 삭제하시겠습니까??") == true){
			location.href='delete.do?team_num='+$(this).attr('value');
		}else{
			return;
		}
	});
	$('#js-modify').click(function(){
		if (confirm("정말 수정하시겠습니까??") == true){
			location.href='modifyform.do?team_num='+$(this).attr('value');
		}else{
			return;
		}
	});
	$('#js-app-cancel').click(function(){
		if (confirm("정말 취소하시겠습니까??") == true){
			location.href='${pageContext.request.contextPath}/teamapp/delete.do?app_num=${team.team_num}&id=${user_id}';
		}else{
			return;
		}
	});
});
</script>
<div class="page-main-style  align-center">
	<div class="form-panel1">
		<div class="form-content">
				<div class="form-group align-center js-style">
					<h3>${team.loc.loc_name } 구장</h3>
					${team.loc.loc_address }
				</div>
				<div class="js-appform">
				<div class="form-group align-center js-solo-style">
					<label for="team_date">경기 날짜</label>
					<input type="hidden" class="form-control" id="team_date" name="team_date" value="${team.team_date}"><p class="js-style">${team.team_date}</p>
				</div>
				<div class="form-group align-center js-solo-style">
					<label for="team_end_date">경기 마감일</label>
					<input type="hidden" class="form-control" id="team_end_date" name="team_end_date" value="${team.team_end_date}"><p class="js-style">${team.team_end_date}</p>
				</div>
				<div class="form-group align-center js-solo-style">
					<label for="team_age">경기 참가 연령대</label>
					<input type="hidden" class="form-control" id="team_age" name="team_age" value="${team.team_age}"><p class="js-style">${team.team_age}대</p>
				</div>
				<div class="form-group align-center js-solo-style">
					<label for="team_member">경기 모집인원</label>
					<input type="hidden" class="form-control" id="team_member" name="team_member" value="${team.team_member}"><p class="js-style">${team.team_member}명</p>
				</div>
					<h3>경기장 정보</h3>
					${team.loc.loc_content }
				<br><br>
				<div class="form-group">
				<c:if test="${!(team.team_id eq user_id) && user_auth eq 1}">
					<c:if test="${!(team.team_member>team.team_now_member && today<=team.end_date[0])}">
						<c:if test="${check}">
							<button type="button" class="js js-cancel" id="js-app-cancel">라인업 취소!</button>
						</c:if>
						<c:if test="${!check}">
							<button type="button" class="js" disabled="disabled" id="js-app">마감되었습니다!</button>
						</c:if>
					</c:if>
					<c:if test="${team.team_member>team.team_now_member && today<=team.end_date[0]}">
						<c:if test="${!check}">
							<button type="button" class="js" id="js-app">라인업 신청!</button>
						</c:if>
						<c:if test="${check}">
							<button type="button" class="js js-cancel" id="js-app-cancel">라인업 취소!</button>
						</c:if>
					</c:if>
				</c:if>
				<c:if test="${team.team_id eq user_id || user_auth eq 3}">
					<button type="button" class="js" id="js-delete" value="${team.team_num }">삭제</button>
					<button type="button" class="js" id="js-modify" value="${team.team_num }">수정</button>
				</c:if>
					<button type="button" class="js" onclick="location.href='${pageContext.request.contextPath}/team/hirelist.do'">목록으로</button>
				</div>
				</div>
		</div>
		
		<div class="applyForm">
		</div>
		<c:if test="${team.team_id eq user_id && team.team_member eq team.team_now_member && lineup}">
			<div class="form-group">
				&nbsp;&nbsp;<button type="button" class="js" onclick="location.href='${pageContext.request.contextPath}/teamapp/lineup.do?app_num=${team.team_num}&status=2&id=${user_id }'">라인업 결정!</button>
			</div>
		</c:if>
		<c:if test="${team.team_id eq user_id && team.team_member eq team.team_now_member && !lineup}">
			<div class="form-group">
				&nbsp;&nbsp;<button type="button" class="js" onclick="location.href='${pageContext.request.contextPath}/teamapp/lineup.do?app_num=${team.team_num}&status=2&id=${user_id }'" disabled="disabled">마감!</button>
			</div>
		</c:if>
		<c:if test="${(team.team_id eq user_id ||user_auth eq 3 ) &&lineup}">
			<!-- 목록 출력 -->
			<div id="appList"></div>
			<div class="paging-button" style="display:none;">
				<input type="button" value="다음글보기">
			</div>
			<div id="loading_appList" style="display:none;">
				<img src="${pageContext.request.contextPath}/css/images/ajax-loader.gif">
			</div>
			<!-- 목록 끝 -->
		</c:if>
	</div>
</div>
				<div class="dia-js-app" title="Basic dialog">
					<form action="${pageContext.request.contextPath}/teamapp/apply.do" method="post" id="apply_form">
						<div class="form-group align-center js-style">
							<input type="hidden" class="form-control" id="app_num" name="app_num" value="${team.team_num}">
						</div>
						<div class="form-group align-center js-style">
							<input type="hidden" class="form-control" id="id" name="id" value="${member.id }">
						</div>
						<div class="form-group align-center js-style">
							<label for="name">NAME</label>
							<input type="hidden" class="form-control" id="name" name="name" value="${member.name }"><p class="js-style">${member.name }</p>
						</div>
						<div class="form-group align-center js-style">
							<label for="nick">nickname</label>
							<input type="hidden" class="form-control" id="nick" name="nick" value="${member.nick }"><p class="js-style">${member.nick }</p>
						</div>
						<div class="form-group align-center js-style">
							<label for="phone">PHONE</label>
							<input type="hidden" class="form-control" id="phone" name="phone" value="${member.phone}"><p class="js-style">${member.phone }</p>
						</div>
						<div id="output"></div>
						<div class="form-group">
							<div class="js-center app-form">
								<button type="submit" id="address_btn" class="js-app-btn">경기 신청</button>
							</div>
						</div>
					</form>
				</div>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/table/table.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/solotab/solo.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/solotab/apply.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/solotab/select.js"></script>
<script type="text/javascript">
var contextPath = "${pageContext.request.contextPath}";
</script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/solotab/applylist.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('#js-app-cancel').click(function(){
		if (confirm("정말 취소하시겠습니까??") == true){
			location.href='${pageContext.request.contextPath}/soloapp/delete.do?app_num=${solo.solo_num}&id=${user_id}';
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
					<h3>${solo.loc.loc_name } 구장</h3>
					${solo.loc.loc_address }
				</div>
				<div class="js-appform">
				<div class="form-group align-center js-solo-style">
					<label for="solo_date">경기 날짜</label>
					<input type="hidden" class="form-control" id="solo_date" name="solo_date" value="${solo.solo_date}"><p class="js-style">${solo.solo_date}</p>
				</div>
				<div class="form-group align-center js-solo-style">
					<label for="solo_end_date">경기 마감일</label>
					<input type="hidden" class="form-control" id="solo_end_date" name="solo_end_date" value="${solo.solo_end_date}"><p class="js-style">${solo.solo_end_date}</p>
				</div>
				<div class="form-group align-center js-solo-style">
					<label for="solo_age">경기 참가 연령대</label>
					<input type="hidden" class="form-control" id="solo_age" name="solo_age" value="${solo.solo_age}"><p class="js-style">${solo.solo_age}대</p>
				</div>
				<div class="form-group align-center js-solo-style">
					<label for="solo_gen">경기 참가 성별</label>
					<input type="hidden" class="form-control" id="solo_gen" name="solo_gen" value="${solo.solo_gen}"><p class="js-style">${solo.solo_gen}</p>
				</div>
					<h3>경기장 정보</h3>
					${solo.loc.loc_content }
				<br><br>
				<div class="form-group">
				<c:if test="${!(solo.solo_id eq user_id) && !(user_auth eq 3)}">
					<c:if test="${!(solo.solo_member>solo.solo_now_member && today<=solo.date[0])}">
						<c:if test="${check}">
							<button type="button" class="js js-cancel" id="js-app-cancel">라인업 취소!</button>
						</c:if>
						<c:if test="${!check}">
							<button type="button" class="js" disabled="disabled" id="js-app">마감되었습니다!</button>
						</c:if>
					</c:if>
					<c:if test="${solo.solo_member>solo.solo_now_member && today<=solo.date[0]}">
						<c:if test="${!check}">
							<button type="button" class="js" id="js-app">라인업 신청!</button>
						</c:if>
						<c:if test="${check}">
							<button type="button" class="js js-cancel" id="js-app-cancel">라인업 취소!</button>
						</c:if>
					</c:if>
				</c:if>
					<button type="button" class="js" onclick="location.href='${pageContext.request.contextPath}/solo/list.do'">목록으로</button>
				</div>
				</div>
		</div>
		
		<div class="applyForm">
		</div>
		<c:if test="${solo.solo_id eq user_id ||user_auth eq 3 }">
			<!-- 목록 출력 -->
			<div id="appList"></div>
			<div class="paging-button" style="display:none;">
				<input type="button" value="다음글보기">
			</div>
			<div id="loading_appList" style="display:none;">
				<img src="${pageContext.request.contextPath}/css/images/ajax-loader.gif">
			</div>
			<!-- 댓글 끝 -->
		</c:if>
	</div>
</div>
				<div class="dia-js-app" title="Basic dialog">
					<form action="${pageContext.request.contextPath}/soloapp/apply.do" method="post" id="apply_form">
						<div class="form-group align-center js-style">
							<input type="hidden" class="form-control" id="app_num" name="app_num" value="${solo.solo_num}">
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
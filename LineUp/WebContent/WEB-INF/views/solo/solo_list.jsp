<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/solotab/solo.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('.js-solo-delete').click(function(){
		if (confirm("정말 삭제하시겠습니까??") == true){
			location.href='delete.do?solo_num='+$(this).attr('value');
		}else{
			return;
		}
	});
	$('#solo_lineup_write').click(function(){
		if($(this).attr('data-check')=='false'){
			location.href='${pageContext.request.contextPath}/solo/writeForm.do';
		}else if (confirm("임시 저장된 글이 있습니다. 불러오시겠습니까?") == true){
			location.href='${pageContext.request.contextPath}/solo/writeForm.do?check=1';
		}else{
			location.href='${pageContext.request.contextPath}/solo/writeForm.do?check=2';
		}
	});
});
</script>
<div class="page-main-style">
	<h2 class="align-center">경기 목록</h2>
	<br>
	<c:if test="${count==0}">
		<div class="align-center">
				<h2>등록된 경기가 없습니다.</h2>
		</div>
	</c:if>
	<c:if test="${count > 0}">
		<c:forEach var="solo" items="${list}">
			<div class="panel panel-default">
				<div class="panel-heading js-padd-bot">
						<c:if test="${solo.solo_id eq user_id || user_auth eq 3}">
							<div class="js-left">
								<button type="button" class="btn btn-warning btn-sm" onclick="location.href='modifyForm.do?solo_num=${solo.solo_num}'">수정</button>
								<button type="button" class="btn btn-danger btn-sm js-solo-delete" value="${solo.solo_num }">삭제</button>
							</div>
						</c:if>
						<div class="js-right">
							<c:if test="${solo.solo_member>solo.solo_now_member && today<=solo.date[0] }">
								<button type="button" class="btn btn-success btn-sm" onclick="location.href='applyForm.do?solo_num=${solo.solo_num}'">신청</button>
							</c:if>
							<c:if test="${!(solo.solo_member>solo.solo_now_member && today<=solo.date[0])}">
								<button type="button" class="btn btn-danger btn-sm" onclick="location.href='applyForm.do?solo_num=${solo.solo_num}'">마감</button>
							</c:if>
						</div>
						<div class="js-center-content"><b>[${solo.solo_age }대 , ${solo.solo_gen }]&nbsp;${solo.loc.loc_name} ${solo.solo_date} ${solo.solo_now_member }/${solo.solo_member} (최소인원 : ${solo.solo_min_member })</b></div>
						
				</div>
			</div>
		</c:forEach>
		<div class="align-center">${pagingHtml}</div>
	</c:if>
	<br><br>
	<div class="list-space js-center">
		<div class="form-group align-center">
			<c:if test="${user_auth eq 3 || user_auth eq 1}">
				<button type="button" class="js" id="solo_lineup_write" data-check="${check}">라인업 등록</button>
			</c:if>
			<button type="button" class="js" onclick="location.href='list.do'">목록으로</button>
			<button type="button" class="js" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">홈으로</button>
		</div>
	</div>
</div>
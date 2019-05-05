<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/table/table.css">
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
});
</script>
<div class="page-main-style">
	<h2 class="align-center">메세지 목록</h2>
	<br>
	<c:if test="${count==0}">
		<div class="align-center">
				<h2>도착한 메세지가 없습니다.</h2>
		</div>
	</c:if>
	<c:if test="${count > 0}">
		<div class="table js-tb">
			<div class="tbrow header blue">
				<div class="cell">보낸사람</div>
				<div class="cell">제목</div>
				<div class="cell">보낸날짜</div>
				<div class="cell">확인여부</div>
				<div class="cell">확인</div>
				<div class="cell">삭제</div>
			</div>
			<c:forEach var="messenger" items="${list}">
				<div class="tbrow">
					<div class="cell">${messenger.sender_id}</div>
					<div class="cell">${messenger.title}</div>
					<div class="cell">${messenger.reg_date}</div>
					<div class="cell"><c:if test="${messenger.check_m > 0}">읽음</c:if><c:if test="${messenger.check_m == 0}">안읽음</c:if></div>
					<div class="cell"><a href="${pageContext.request.contextPath}/message/detail.do?num=${messenger.num}">확인</a></div>
					<div class="cell"><a href="${pageContext.request.contextPath}/message/delete.do?num=${messenger.num}">삭제</a></div> 
				</div>
			</c:forEach>
		</div>
		<div class="align-center">${pagingHtml}</div>
	</c:if>
	<br><br>
</div>
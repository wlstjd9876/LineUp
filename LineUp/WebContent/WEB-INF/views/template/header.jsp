<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">

$(document).ready(function(){
	$('#login-btn').on('click', function(){
		session.setAttribute("uri", request.getRequestURI().substring(request.getContextPath().length()));
	});
	$.ajax({
		url:'${pageContext.request.contextPath }/main/checkm.do',
		type:'post',
		data:{},
		dataType:'json',
		cache:false,
		timeout:30000,
		success:function(data){
			$('#check_m').text(data.count);
		},
		error:function(){
			alert('네트워크 오류 발생');
		}
	});
	setInterval(function(){
		$.ajax({
			url:'${pageContext.request.contextPath }/main/checkm.do',
			type:'post',
			data:{},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				$('#check_m').text(data.count);
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	},30000);
});
</script>

<div class="header">
	<a href="${pageContext.request.contextPath}/main/main.do"><div id="headerlogo"></div></a>
	<div class="headerForm">
		<div class="align-right">
			<ul class="menu">
				<c:choose>
					<c:when test="${empty user_id}">
						<li><button type="button" class="btn btn-lg btn-login" id="login-btn" onclick="location.href='${pageContext.request.contextPath}/member/loginForm.do'">로그인</button></li>
						<li><button type="button" class="btn btn-lg btn-register" onclick="location.href='${pageContext.request.contextPath}/member/authSelect.do'">회원가입</button></li>
					</c:when>
					<c:otherwise>
						<c:if test="${user_auth == 1}">
							<li>${user_id}님 (개인) 로그인 중</li>
							<li><button type="button" class="btn btn-lg btn-login" onclick="location.href='${pageContext.request.contextPath}/member/solo_info.do'">회원정보</button></li>
						</c:if>
						<c:if test="${user_auth ==2}">
							<li>${user_id}님 (팀) 로그인 중</li>
							<li><button type="button" class="btn btn-lg btn-login" onclick="location.href='${pageContext.request.contextPath}/member/team_info.do'">회원정보</button></li>
						</c:if>
						<c:if test="${user_auth == 3}">
							<li>${user_id}(관리자)님 로그인 중</li>
							<li><button type="button" class="btn btn-lg btn-login" onclick="location.href='#'">회원정보</button></li>
						</c:if>
							<li><button type="button" class="btn btn-lg btn-register" onclick="location.href='${pageContext.request.contextPath}/message/list.do'">쪽지함<span class="badge" id="check_m"></span></button></li>
							<li><button type="button" class="btn btn-lg btn-register" onclick="location.href='${pageContext.request.contextPath}/member/logout.do'">로그아웃</button></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>
</div>
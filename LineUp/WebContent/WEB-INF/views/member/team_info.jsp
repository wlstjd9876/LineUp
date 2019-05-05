<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/member_solo.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/pw_check.js"></script>
</head>
<body>
		<div class="form-panel align-center">
			<div class="form-header">
				<h1>${member.team_name}팀의 정보</h1>
			</div> 
			<div class="form-content">
				<form action="team_modifyForm.do" method="post" id="info_form">
					<div class="form-group align-center js-style">
						<label for="id">ID</label>
						<input type="hidden" class="form-control" id="id" name="id" value="${member.id }"><p class="js-style">${member.id }</p>
					</div>
					<div class="form-group align-center js-style">
						<label for="phone">PHONE</label>
						<input type="hidden" class="form-control" id="phone" name="phone" value="${member.phone}"><p class="js-style">${member.phone }</p>
					</div>
					<div class="form-group align-center js-style">
						<label for="email">E-mail</label>
						<input type="hidden" class="form-control" id="email" name="email" value="${member.email }"><p class="js-style">${member.email }</p>
					</div>
					<div class="form-group align-center js-style">
						<label for="team_name">NAME</label>
						<input type="hidden" class="form-control" id="team_name" name="team_name" value="${member.team_name }"><p class="js-style">${member.team_name }</p>
					</div>
					<div class="form-group align-center js-style">
						<label for="team_age">AGE</label>
						<input type="hidden" class="form-control" id="team_age" name="team_age" value="${member.team_age }"><p class="js-style">${member.team_age }</p>
					</div>
					<div class="form-group">
						<button type="submit" class="js">정보 수정</button>
						<button type="button" class="js" onclick="location.href='${pageContext.request.contextPath}/member/deleteForm.do'">회원탈퇴</button>
						<button type="button" class="js" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">홈으로</button>
					</div>
					<div class="form-group">
						<button type="button" onclick="location.href='${pageContext.request.contextPath}/member/team_writelist.do'">작성글 확인</button>
					</div>
				</form>
			</div>
		</div>
</body>
</html>
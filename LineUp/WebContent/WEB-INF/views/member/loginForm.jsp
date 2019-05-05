<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/loginform_check.js"></script>
</head>
<body>
<div class="align-center loginWindow js-center">
	<h4>로그인</h4><br>
	<form action="login.do" method="post" id="login_form">
		<div class="btn-toggle loginForm" data-toggle="buttons">
			<label class="btn btn-primary">
				<input type="radio" name="auth" id="auth1" value="1">개인
			</label>
			&nbsp;&nbsp;&nbsp;
			<label class="btn btn-primary">
				<input type="radio" name="auth" id="auth2" value="2">팀
			</label>
		</div>
		<input type="text" class="form-control" id="id" name="id" placeholder="Enter ID" maxlength="12" autocomplete="off"><br>
		<input type="password" class="form-control" id="pw" name="pw" placeholder="Enter PASSWORD" maxlength="20">
		<br>
		<button type="submit" class="btn btn-info">로그인</button>
		<button type="button" class="btn btn-info" onclick="location.href='../member/authSelect.do'">회원가입</button>
		<button type="button" class="btn btn-info" onclick="location.href='../main/main.do'">홈으로</button>
	</form>
</div>
</body>
</html>
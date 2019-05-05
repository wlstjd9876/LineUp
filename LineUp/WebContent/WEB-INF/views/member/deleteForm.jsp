<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 삭제 확인</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/delete.js"></script>
</head>
<body>
<div class="align-center loginWindow js-center">
	<div class="form-panel align-center">
			<div class="form-header">
				<h1>회원탈퇴</h1>
			</div>
			<div class="form-content">
				<form action="delete.do" method="post" id="delete_form">
					<div class="form-group align-left">
						<label for="id">ID</label>
						<input type="text" class="form-control" id="id" name="id" placeholder="Enter ID" maxlength="12" autocomplete="off">
					</div>
					<div class="form-group align-left">
						<label for="pw">Password</label>
						<input type="password" class="form-control" id="pw" name="pw" placeholder="Enter PASSWORD" maxlength="20">
					</div>
					<div class="form-group align-left">
						<label for="ck_pw">Password Check</label>
						<input type="password" class="form-control" id="ck_pw" name="ck_pw" placeholder="Check PASSWORD" maxlength="20">
						<span id="message_ck_pw"></span>
						<img src="${pageContext.request.contextPath}/css/images/ajax-loader.gif" 
						     width="16" height="16" id="loading_ck_pw" 
				     		 style="display:none;">
					</div>
					<div class="form-group">
						<button type="submit">확인</button>
						<button type="button" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">홈으로</button>
					</div>
				</form>
			</div>
		</div>
</div>
</body>
</html>
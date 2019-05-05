<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보</title>
</head>
<body>
		<div class="form-panel align-center">
			<div class="form-header">
				<h1>${member.id}님의 정보</h1>
			</div> 
			<div class="form-content">
				<form action="solo_modifyForm.do" method="post" id="info_form">
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
						<label for="name">NAME</label>
						<input type="hidden" class="form-control" id="name" name="name" value="${member.name }"><p class="js-style">${member.name }</p>
					</div>
					<div class="form-group align-center js-style">
						<label for="gen">GEN</label>
						<input type="hidden" class="form-control" id="gen" name="gen" value="${member.gen }"><p class="js-style">${member.gen }</p>
					</div>
					<div class="form-group align-center js-style">
						<label for="age">AGE</label>
						<input type="hidden" class="form-control" id="age" name="age" value="${member.age }"><p class="js-style">${member.age }</p>
					</div>
					<div class="form-group align-center js-style">
						<label for="nick">nickname</label>
						<input type="hidden" class="form-control" id="nick" name="nick" value="${member.nick }"><p class="js-style">${member.nick }</p>
					</div>
					<div class="form-group">
						<button type="submit" class="js">정보 수정</button>
						<button type="button" class="js" onclick="location.href='${pageContext.request.contextPath}/member/deleteForm.do'">회원탈퇴</button>
						<button type="button" class="js" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">홈으로</button>
					</div>
					<div class="form-group">
						<button type="button" onclick="location.href='${pageContext.request.contextPath}/member/solo_writelist.do'">작성글 확인</button>
					</div>
				</form>
			</div>
		</div>
</body>
</html>
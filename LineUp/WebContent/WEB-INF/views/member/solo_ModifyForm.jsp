<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인 회원 수정</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/modify_solo.js"></script>
</head>
<body>
		<div class="form-panel align-center">
			<div class="form-header">
				<h1>회원 정보 수정</h1>
			</div>
			<div class="form-content">
				<form action="solo_modify.do" method="post" id="modify_form">
					<div class="form-group align-center js-style">
						<label for="id">ID</label>
						<input type="hidden" class="form-control" id="id" name="id" value="${member.id }"><p class="js-style">${member.id }</p>
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
					<div class="form-group align-left">
						<label for="phone">PHONE</label>
						<input type="tel" class="form-control" id="phone" name="phone" placeholder="Enter Phone" maxlength="15" autocomplete="off" value="${member.phone }">
						<span id="message_phone"></span>
						<img src="${pageContext.request.contextPath}/css/images/ajax-loader.gif" 
						     width="16" height="16" id="loading_phone" 
				     		 style="display:none;">
					</div>
					<div class="form-group align-left">
						<label for="email">E-mail</label>
						<input type="email" class="form-control" id="email" name="email" placeholder="Enter E-mail" maxlength="50" autocomplete="off" value="${member.email }">
						<span id="message_email"></span>
						<img src="${pageContext.request.contextPath}/css/images/ajax-loader.gif" 
						     width="16" height="16" id="loading_email" 
				     		 style="display:none;">
					</div>
					<div class="form-group align-left">
						<label for="name">NAME</label>
						<input type="text" class="form-control" id="name" name="name" placeholder="Enter Name" maxlength="10" autocomplete="off" value="${member.name }">
					</div>
					<div class="form-group align-left">
						<label for="gen">GENDER</label>
						<div class="btn-group btn-group-toggle" data-toggle="buttons">
							<label class="btn btn-info">
								<input type="radio" name="gen" id="gen1" value="man">남자
							</label>
							<label class="btn btn-info">
								<input type="radio" name="gen" id="gen2" value="woman">여자
							</label>
						</div>
					</div>
					<div class="form-group align-left">
						<label for="age">age</label>
						<div class="btn-group btn-group-toggle" data-toggle="buttons">
							<label class="btn btn-info">
								<input type="radio" name="age" id="age1" value="10">10대
							</label>
							<label class="btn btn-info">
								<input type="radio" name="age" id="age2" value="20">20대
							</label>
							<label class="btn btn-info">
								<input type="radio" name="age" id="age3" value="30">30대
							</label>
							<label class="btn btn-info">
								<input type="radio" name="age" id="age4" value="40">40대
							</label>
							<label class="btn btn-info">
								<input type="radio" name="age" id="age5" value="50">50대
							</label>
							<label class="btn btn-info">
								<input type="radio" name="age" id="age6" value="60">60대
							</label>
						</div>
					</div>
					<div class="form-group align-left">
						<label for="nick">nickname</label>
						<input type="text" class="form-control" id="nick" name="nick" placeholder="Enter NickName" maxlength="10"  autocomplete="off" value="${member.nick }">
						<span id="message_nick"></span>
						<img src="${pageContext.request.contextPath}/css/images/ajax-loader.gif" 
						     width="16" height="16" id="loading_nick" 
				     		 style="display:none;">
					</div>
					<div class="form-group">
						<button type="submit">수정</button>
						<button type="button" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">홈으로</button>
					</div>
				</form>
			</div>
		</div>
</body>
</html>
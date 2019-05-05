<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>팀 회원 가입</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/member_team.js"></script>
</head>
<body>
		<div class="form-panel align-center">
			<div class="form-header">
				<h1>팀 회원 가입</h1>
			</div>
			<div class="form-content">
				<form action="team_register.do" method="post" id="register_form">
					<div class="form-group align-left">
						<label for="id">ID</label>
						<input type="text" class="form-control" id="id" name="id" placeholder="Enter ID" maxlength="12" autocomplete="off">
						<span id="message_id"></span>
						<img src="${pageContext.request.contextPath}/css/images/ajax-loader.gif" 
						     width="16" height="16" id="loading_id" 
				     		 style="display:none;">
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
						<input type="tel" class="form-control" id="phone" name="phone" placeholder="Enter Phone" maxlength="15" autocomplete="off">
						<span id="message_phone"></span>
						<img src="${pageContext.request.contextPath}/css/images/ajax-loader.gif" 
						     width="16" height="16" id="loading_phone" 
				     		 style="display:none;">
					</div>
					<div class="form-group align-left">
						<label for="email">E-mail</label>
						<input type="email" class="form-control" id="email" name="email" placeholder="Enter E-mail" maxlength="50" autocomplete="off">
						<span id="message_email"></span>
						<img src="${pageContext.request.contextPath}/css/images/ajax-loader.gif" 
						     width="16" height="16" id="loading_email" 
				     		 style="display:none;">
					</div>
					<div class="form-group align-left">
						<label for="team_name">TEAM_NAME</label>
						<input type="text" class="form-control" id="team_name" name="team_name" placeholder="Enter Team_Name" maxlength="10" autocomplete="off">
					</div>
					<div class="form-group align-left">
						<label for="team_age">age</label>
						<div class="btn-group btn-group-toggle" data-toggle="buttons">
							<label class="btn btn-info">
								<input type="checkbox" name="team_age" id="team_age1" value="10">10대
							</label>
							<label class="btn btn-info">
								<input type="checkbox" name="team_age" id="team_age2" value="20">20대
							</label>
							<label class="btn btn-info">
								<input type="checkbox" name="team_age" id="team_age3" value="30">30대
							</label>
							<label class="btn btn-info">
								<input type="checkbox" name="team_age" id="team_age4" value="40">40대
							</label>
							<label class="btn btn-info">
								<input type="checkbox" name="team_age" id="team_age5" value="50">50대
							</label>
							<label class="btn btn-info">
								<input type="checkbox" name="team_age" id="team_age6" value="60">60대
							</label>
						</div>
					</div>
					<div class="form-group">
						<button type="submit">회원 가입</button>
						<button type="button" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">홈으로</button>
					</div>
				</form>
			</div>
		</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가입 완료!</title>
<script type="text/javascript">
var contextPath = "${pageContext.request.contextPath }";
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/go_main.js"></script>
</head>
<body>
<div class="align-center">
	<br><br><h2>&nbsp;&nbsp;&nbsp;&nbsp;${user_id }님의 정보 수정완료!</h2><br>
	<br><br><h2>&nbsp;&nbsp;&nbsp;&nbsp;LineUp!!</h2><br>
	<br><br><h2 id="count">&nbsp;&nbsp;&nbsp;&nbsp;</h2><br>
	
	<ul class="menu">
		<li><button type="button" class="btn btn-success btn-lg" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">홈 으 로</button></li>
	</ul>
</div>
</body>
</html>
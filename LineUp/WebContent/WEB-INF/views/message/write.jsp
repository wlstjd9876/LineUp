<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메세지 전송 완료!</title>
<script type="text/javascript">
var contextPath = "${pageContext.request.contextPath }";
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/go_main.js"></script>
</head>
<body>
<div class="align-center">
	<br><br><h2>&nbsp;&nbsp;&nbsp;&nbsp;메세지 전송완료!</h2><br>
	<br><br><h2>&nbsp;&nbsp;&nbsp;&nbsp;LineUp!!</h2><br>
	<br><br><h2 id="count">&nbsp;&nbsp;&nbsp;&nbsp;</h2><br>
	
	<ul class="menu">
		<li><button type="button" class="btn btn-success btn-lg" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">홈으로</button></li>
	</ul>
</div>
</body>
</html>
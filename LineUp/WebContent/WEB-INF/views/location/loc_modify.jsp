<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>경기장 정보 수정완료!</title>
<script type="text/javascript">
var contextPath = "${pageContext.request.contextPath }";
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/locationtab/go_list.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/locationtab/location.js"></script>
</head>
<body>
<div class="align-center">
	<br><br><h2>&nbsp;&nbsp;&nbsp;&nbsp;경기장 정보 수정완료!</h2><br>
	<br><br><h2>&nbsp;&nbsp;&nbsp;&nbsp;LineUp!!</h2><br>
	<br><br><h2 id="count">&nbsp;&nbsp;&nbsp;&nbsp;</h2><br>
	
	<ul class="menu">
		<li><button type="button" class="btn btn-success btn-lg" onclick="location.href='${pageContext.request.contextPath}/location/list.do'">목록으로</button></li>
	</ul>
</div>
</body>
</html>
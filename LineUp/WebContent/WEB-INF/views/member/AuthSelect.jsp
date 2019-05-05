<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 종류 선택</title>
<script type="text/javascript">
var xhr = new XMLHttpRequest();
var url = 'http://api.visitkorea.or.kr/openapi/service/rest/PhotoGalleryService/galleryDetailList'; /*URL*/
var queryParams = '?' + encodeURIComponent('ServiceKey') + '='+'서비스키'; /*Service Key*/
queryParams += '&' + encodeURIComponent('ServiceKey') + '=' + encodeURIComponent('-'); /*공공데이터포털에서 받은 인증키*/
queryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1'); /*페이지번호*/
queryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('10'); /*한 페이지 결과 수*/
queryParams += '&' + encodeURIComponent('MobileOS') + '=' + encodeURIComponent('ETC'); /*IOS (아이폰), AND (안드로이드), WIN( 윈도우폰), ETC(WEB이나 기타 등등)*/
queryParams += '&' + encodeURIComponent('MobileApp') + '=' + encodeURIComponent('AppTest'); /*서비스명=어플명*/
queryParams += '&' + encodeURIComponent('title') + '=' + encodeURIComponent('%EC%9D%B4%ED%83%9C%EC%9B%90%EA%B1%B0%EB%A6%AC'); /*요청 키워드(한글 경우,URL인코딩 필요)*/
xhr.open('GET', url + queryParams);
xhr.onreadystatechange = function () {
    if (this.readyState == 4) {
        alert('Status: '+this.status+' Headers: '+JSON.stringify(this.getAllResponseHeaders())+' Body: '+this.responseText);
    }
};

xhr.send('');
</script>

</head>
<body>
<div class="align-center">
	<br><br><h2>&nbsp;&nbsp;&nbsp;&nbsp;회원 종류 선택</h2><br><br>
	<ul class="menu">
		<li><button type="button" class="btn btn-primary btn-lg" onclick="location.href='../member/solo_registerForm.do'">개 인 회 원</button></li>
		<li><button type="button" class="btn btn-info btn-lg" onclick="location.href='../member/team_registerForm.do'">팀 회 원</button></li>
	</ul>
</div>
</body>
</html>
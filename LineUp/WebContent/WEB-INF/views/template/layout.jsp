<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--==================view page 설정 ==================== --%>
<c:set var="viewPage">
	<jsp:include page="${viewURI}"></jsp:include>
</c:set>
<%--==================view page 설정 ==================== --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LineUp</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.min.css" type="text/css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap/bowotstrap-theme.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('div.headerMenu div').click(function(){
		$('div.headerMenu div').removeClass('tab-active');
		$(this).addClass('tab-active');
	});
	$('div.Menu-detail div#detail-tab1').click(function(){
		$('div.headerMenu div').removeClass('tab-active');
		$('div.headerMenu div#Menu-tab1').addClass('tab-active');
	});
	$('div.Menu-detail div#detail-tab2').click(function(){
		$('div.headerMenu div').removeClass('tab-active');
		$('div.headerMenu div#Menu-tab2').addClass('tab-active');
	});
	$('div.Menu-detail div#detail-tab3').click(function(){
		$('div.headerMenu div').removeClass('tab-active');
		$('div.headerMenu div#Menu-tab3').addClass('tab-active');
	});
	$('div.Menu-detail div#detail-tab4').click(function(){
		$('div.headerMenu div').removeClass('tab-active');
		$('div.headerMenu div#Menu-tab4').addClass('tab-active');
	});
	$('.headerMenu').mouseenter(function(){
		$('div.Menu-detail').slideDown(200);
	});
	$('div.MenuForm').mouseleave(function(){		
		$('div.Menu-detail').slideUp(200);
	});
	
	$('div.headerMenu div#Menu-tab1').mouseenter(function(){
		$('div.Menu-detail div#detail-tab1').addClass('hidden');
		$('div.Menu-detail div#detail-tab2').addClass('hidden');
		$('div.Menu-detail div#detail-tab3').addClass('hidden');
		$('div.Menu-detail div#detail-tab4').addClass('hidden');
		$('div.Menu-detail div#detail-tab1').removeClass('hidden');
	});
	
	$('div.headerMenu div#Menu-tab2').mouseenter(function(){
		$('div.Menu-detail div#detail-tab1').addClass('hidden');
		$('div.Menu-detail div#detail-tab2').addClass('hidden');
		$('div.Menu-detail div#detail-tab3').addClass('hidden');
		$('div.Menu-detail div#detail-tab4').addClass('hidden');
		$('div.Menu-detail div#detail-tab2').removeClass('hidden');
	});
	
	$('div.headerMenu div#Menu-tab3').mouseenter(function(){
		$('div.Menu-detail div#detail-tab1').addClass('hidden');
		$('div.Menu-detail div#detail-tab2').addClass('hidden');
		$('div.Menu-detail div#detail-tab3').addClass('hidden');
		$('div.Menu-detail div#detail-tab4').addClass('hidden');
		$('div.Menu-detail div#detail-tab3').removeClass('hidden');
	});
	
	$('div.headerMenu div#Menu-tab4').mouseenter(function(){
		$('div.Menu-detail div#detail-tab1').addClass('hidden');
		$('div.Menu-detail div#detail-tab2').addClass('hidden');
		$('div.Menu-detail div#detail-tab3').addClass('hidden');
		$('div.Menu-detail div#detail-tab4').addClass('hidden');
		$('div.Menu-detail div#detail-tab4').removeClass('hidden');
	});	
});
</script>
</head>
<body>
	<div id="main">
		<div id="main_header">
			<jsp:include page="header.jsp"/>
		</div>
		<div id="main_menu">
			<jsp:include page="menu.jsp"/>
		</div>
		<div id="main_body">${viewPage}</div>
		<div id="main_footer">
			<jsp:include page="footer.jsp"/>
		</div>
	</div>
</body>
</html>
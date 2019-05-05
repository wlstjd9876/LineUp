<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/table/table.css">
<div class="page-main-style  align-center">
	<div class="js-tb-wid js-center">
		<table class="type08">
		    <thead>
		    <tr>
		        <th scope="cols">타이틀</th>
		        <th scope="cols">내용</th>
		    </tr>
		    </thead>
		    <tbody>
		    <tr>
		        <th scope="row">보낸사람</th>
		        <td>${messenger.sender_id }</td>
		    </tr>
		    <tr>
		        <th scope="row">제목</th>
		        <td>${messenger.title }</td>
		    </tr>
		    <tr>
		        <th scope="row">보낸 날짜</th>
		        <td>${messenger.reg_date }</td>
		    </tr>
		    <tr>
		        <th scope="row">내용</th>
		        <td>${messenger.content }</td>
		    </tr>
		    </tbody>
		</table>
	</div>
</div>
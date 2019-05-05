<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="menu">
	<div class="MenuForm">
		<div class="headerMenu">	
			<div class="Menu-tab" id="Menu-tab1">개인 라인업</div>
			<div class="Menu-tab" id="Menu-tab2">팀 라인업</div>
			<div class="Menu-tab" id="Menu-tab3">커뮤니티</div>
			<div class="Menu-tab" id="Menu-tab4">경기장 안내</div>
		</div>
		<div class="Menu-detail">
			<div id="detail-tab1">
				<div class="row">
					<div class="col-md-3"><a href="${pageContext.request.contextPath}/solo/list.do"><div class="detail-tab">개인 라인업</div></a></div>
				</div>
			</div>
			<div id="detail-tab2" class="hidden">
				<div class="row">
					<div class="col-md-offset-3 col-md-3"><a href="${pageContext.request.contextPath}/team/hirelist.do"><div class="detail-tab">용병 구해요</div></a></div>
				</div>
			</div>		
			<div id="detail-tab3" class="hidden">
				<div class="row">
					<div class="col-md-offset-6 col-md-3"><a href="${pageContext.request.contextPath}/com_video/list.do"><div class="detail-tab">영상 게시판</div></a></div>
				</div>
			</div>
			<div id="detail-tab4" class="hidden">
				<div class="row">
					<div class="col-md-offset-9 col-md-3"><a href="${pageContext.request.contextPath}/location/list.do"><div class="detail-tab">경기장 안내</div></a></div>
				</div>
			</div>
		</div>
	</div>
</div>
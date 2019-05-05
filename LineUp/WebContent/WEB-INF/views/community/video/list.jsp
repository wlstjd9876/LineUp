<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/comtab/com.js"></script>
<div class="page-main-style">
	<h2 class="align-center">영상 게시판</h2>
	<form action="list.do" method="get" id="search_form">
		<ul class="search js-center">
			<li class="sel">
				<select class="selectBox01" name="keyfield" id="keyfield">
						<option value="title">영상 제목</option>
						<option value="content">게시물 내용</option>
				</select>
			</li>
			<li><span class="green_window">
				<input type="search" class="input_text" name="keyword" id="keyword" maxlength="33" />
				</span>
				<button type="submit" class="sch_smit">검색</button>
			</li> 
		</ul>
	</form>
	<br>
	<c:if test="${count==0}">
		<div class="align-center">
				<h2>등록된 영상이 없습니다.</h2>
		</div>
	</c:if>
	<c:if test="${count > 0}">
			<div class="row">
				<c:forEach var="video" items="${list}">
				<div class="col-sm-6 col-md-4 align-center">
					<div class="thumbnail">
						<c:if test="${!empty video.thumb}">
							<img
								src="${pageContext.request.contextPath}/upload/${video.thumb}"
								class="thumb-image">
						</c:if>
						<div class="caption">
							<h3 class="video-height">${video.title }</h3>
							<p class="js-loc_address">조회수 : ${video.hit }</p>
							<p class="js-loc_address">${video.reg_date }</p>
							<p>
								<a href="detail.do?num=${video.num}" class="btn btn-primary">자세히 보기</a>
							</p>
						</div>
					</div>
				</div>
				</c:forEach>
			</div>
		<div class="align-center">${pagingHtml}</div>
	</c:if>
	<br><br>
	<div class="list-space js-center">
		<div class="form-group align-center">
			<c:if test="${user_auth eq 3}">
				<button type="button" class="js" onclick="location.href='writeform.do'">영상 등록</button>
			</c:if>
			<button type="button" class="js" onclick="location.href='list.do'">목록으로</button>
			<button type="button" class="js" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">홈으로</button>
		</div>
	</div>
</div>
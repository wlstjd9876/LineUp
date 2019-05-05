<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/table/table.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comtab/com.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('#deleteCheck').click(function(){
		if (confirm("정말 삭제하시겠습니까??") == true){
			location.href='${pageContext.request.contextPath}/com_video/delete.do?num='+${video.num};
		}else{
			return;
		}
	});
});
</script>
<div class="page-main-style  align-center">
	<div class="row">
		<div class="col-sm-6 col-md-6 col-md-offset-3">
			<div class="video">
				${video.content }
			</div>
		</div>
	</div>
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
		        <th scope="row">제목</th>
		        <td>${video.title }</td>
		    </tr>
		    <tr>
		        <th scope="row">작성자</th>
		        <td>${video.id }</td>
		    </tr>
		    <tr>
		        <th scope="row">조회수</th>
		        <td>${video.hit }</td>
		    </tr>
		     <tr>
		        <th scope="row">등록날짜</th>
		        <td>${video.reg_date }</td>
		    </tr>
		    </tbody>
		</table>
	</div>
				<div class="form-group video-wid js-center">
					<c:if test="${video.id eq user_id || user_auth eq 3}">
						<button type="button" class="js" onclick="location.href='${pageContext.request.contextPath}/com_video/modifyform.do?num=${video.num }'">정보 수정</button>	
						<button type="button" class="js" id="deleteCheck">영상삭제</button>
					</c:if>
					<button type="button" class="js" onclick="location.href='${pageContext.request.contextPath}/com_video/list.do'">목록으로</button>
				</div>
</div>
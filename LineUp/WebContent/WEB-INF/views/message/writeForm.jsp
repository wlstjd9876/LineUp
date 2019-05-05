<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="form-panel align-center">
	<div class="form-header">
		<h1>쪽지 보내기</h1>
	</div>
	<div class="form-content">
		<form action="write.do" method="post" id="write_form">
			<div class="form-group align-left">
				<label for="title">메세지 제목</label>
				<input type="text" class="form-control"
					id="title" name="title" placeholder="Enter Title">
				<input type="hidden" class="form-control"
					id="receiver_id" name="receiver_id" value="${receiver_id }">${receiver_id }
			</div>
			<div class="form-group align-left">
				<label for="content">메세지 내용</label>
				<textarea rows="5" cols="66" name="content" id="content" style="resize: none;"></textarea>
			</div>
			<div class="form-group">  
				<button type="submit" class="js">메세지 보내기</button>
				<button type="button" class="js"
					onclick="location.href='${pageContext.request.contextPath}/message/list.do'">목록으로</button>
			</div>
		</form>
	</div>
</div>
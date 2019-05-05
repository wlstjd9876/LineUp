<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/locationtab/location.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$('#deleteCheck').click(function(){
		if (confirm("정말 삭제하시겠습니까??") == true){
			location.href='${pageContext.request.contextPath}/location/delete.do?loc_num='+${loc.loc_num};
		}else{
			return;
		}
	});
});
</script>
<div class="page-main-style  align-center">
	<div class="row">
		<div class="col-sm-6 col-md-6 col-md-offset-3">
			<div class="thumbnail">
				<img src="${pageContext.request.contextPath}/upload/${loc.loc_photo2}" class="thumb-image">
			</div>
		</div>
	</div>
	<div class="form-panel1">
		<div class="form-content">
			<form action="modifyForm.do?loc_num=${loc.loc_num}" method="post" id="info_form">
				<div class="form-group align-center js-style">
					<label for="loc_address">Location ADDRESS</label>
					<input type="hidden" class="form-control" id="loc_address" name="loc_address" value="${loc.loc_address }"><p class="js-style">${loc.loc_address }</p>
				</div>
				<div class="form-group align-center js-style">
					<label for="loc_phone">Location PHONE</label>
					<input type="hidden" class="form-control" id="loc_phone" name="loc_phone" value="${loc.loc_phone}"><p class="js-style">${loc.loc_phone }</p>
				</div>
				<div class="form-group align-center js-style">
					<label for="loc_content">Location CONTENT</label>
					<input type="hidden" class="form-control" id="loc_content" name="loc_content" value="${loc.loc_content }"><p class="js-style">${loc.loc_content }</p>
				</div>
				<div class="form-group">
					<c:if test="${loc.loc_id eq user_id || user_auth eq 3}">
						<button type="submit" class="js">정보 수정</button>	
						<button type="button" class="js" id="deleteCheck">경기장 삭제</button>
					</c:if>
					<button type="button" class="js" onclick="location.href='${pageContext.request.contextPath}/location/list.do'">목록으로</button>
				</div>
			</form>
		</div>
	</div>
</div>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.min.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.min.js">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/locationtab/location.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/locationtab/modifyForm.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="${pageContext.request.contextPath}/js/util/address.js"></script>

<div class="form-panel align-center">
	<div class="form-header">
		<h1>경기장 수정</h1>
	</div>
	<div class="form-content">
		<form action="modify.do" method="post" enctype="multipart/form-data" id="modify_form">
			<input type="hidden" class="form-control" id="loc_num" name="loc_num" value="${loc.loc_num}">
			<div class="form-group align-left">
				<label for="loc_name">경기장 이름</label>
				<input type="text" class="form-control"
					id="loc_name" name="loc_name" placeholder="Enter Name" maxlength="20"
					autocomplete="off" value="${loc.loc_name }">
			</div>
			<div class="form-group align-left">
				<label for="loc_address">경기장 주소</label>
				<input type="text"
					class="form-control js" id="loc_address" name="loc_address"
					placeholder="Search Address" readonly="readonly">
				<button type="button" class="js" id="js-address">경기장 검색</button>
				
				<span id="message_loc_address"></span>
				<img src="${pageContext.request.contextPath}/css/images/ajax-loader.gif" 
					width="16" height="16" id="loading_loc_address" 
				    style="display:none;">
			</div>
			<div class="form-group align-left">
				<label for="loc_phone">경기장 번호</label>
				<input type="tel"
					class="form-control" id="loc_phone" name="loc_phone"
					placeholder="Enter Phone" maxlength="15" autocomplete="off" value="${loc.loc_phone }">
			</div>
			<div class="form-group align-left">
				<label for="loc_photo1">경기장 사진</label>
				<div id="loc_photo_div">
					<input type="button" id="loc_photo_btn" name="loc_photo_btn" value="사진 수정을 원하시면 클릭해주세요">
				</div>
			</div>
			<div class="form-group align-left">
				<label for="loc_content">경기장 내용</label>
				<textarea rows="5" cols="30" class="form-control"
					 id="loc_content" name="loc_content">${loc.loc_content }</textarea>
			</div>
			<div class="form-group">
				<button type="submit">경기장 수정</button>
				<button type="button"
					onclick="location.href='${pageContext.request.contextPath}/location/list.do'">목록으로</button>
			</div>
		</form>
	</div>
</div>
<div class="dia-js-address" title="Basic dialog">
	<input type="text" id="sample4_postcode" class="js-address-input" placeholder="우편번호">
	<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
	<input type="text" class="js-address-input" id="sample4_roadAddress" placeholder="도로명주소">
	<input type="text" class="js-address-input" id="sample4_jibunAddress" placeholder="지번주소">
	<span id="guide" style="color:#999;display:none"></span>
	<input type="text" class="js-address-input" id="sample4_detailAddress" placeholder="상세주소">
	<input type="text" class="js-address-input" id="sample4_extraAddress" placeholder="참고항목">
	<div class="form-group js-address-dia">
		<button type="button" id="address_btn" class="js-address-btn">경기장 등록</button>
	</div>
</div>
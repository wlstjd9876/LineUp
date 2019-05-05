<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/solotab/solo.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/solotab/modifyForm.js"></script>
<script type="text/javascript">
	var openpop;
	function popup(){
		window.name = "writeForm";
		openpop = window.open('search.do','search','left=50, top=50, width=600, height=400, resizable=no');
	}
	
</script>
<div class="form-panel align-center">
	<div class="form-header">
		<h1>경기 정보 수정</h1>
	</div>
	<div class="form-content">
		<form action="modify.do?solo_num=${solo.solo_num}" method="post" id="modify_form">
			<div class="form-group align-left">
				<label for="solo_member">경기 인원</label>
				<input type="number" class="form-control"
					id="solo_member" value="${solo.solo_member}" name="solo_member" placeholder="Enter Number">
				<span id="message_solo_member"></span>
				<img src="${pageContext.request.contextPath}/css/images/ajax-loader.gif" 
						width="16" height="16" id="loading_solo_member" 
						style="display:none;">
			</div>
			<div class="form-group align-left">
				<label for="solo_min_member">경기 최소 인원</label>
				<input type="number" class="form-control"
					id="solo_min_member" value="${solo.solo_min_member}" name="solo_min_member" placeholder="Enter Min_Number">
				<span id="message_solo_min_member"></span>
				<img src="${pageContext.request.contextPath}/css/images/ajax-loader.gif" 
						width="16" height="16" id="loading_solo_min_member" 
						style="display:none;">
			</div>
			<div class="form-group align-left">
				<label for="solo_date">경기 날짜</label>
				<input type="date" class="solo_date" name="solo_date" id="solo_date" value="${solo.date[0]}">
				<input type="time" class="solo_date" name="solo_time" id="solo_time" value="${solo.date[1]}">
				<span id="message_solo_date"></span>
				<img src="${pageContext.request.contextPath}/css/images/ajax-loader.gif" 
						width="16" height="16" id="loading_solo_date" 
						style="display:none;">
			</div>
			<div class="form-group align-left">
				<label for="solo_end_date">신청 마감 날짜</label>
				<input type="date" class="solo_date" name="solo_end_date" id="solo_end_date" value="${solo.end_date[0]}">
				<input type="time" class="solo_date" name="solo_end_time" id="solo_end_time" value="${solo.end_date[1]}">
				<span id="message_solo_end_date"></span>
				<img src="${pageContext.request.contextPath}/css/images/ajax-loader.gif" 
						width="16" height="16" id="loading_solo_end_date" 
						style="display:none;">
			</div>
			<div class="form-group align-left">
				<label for="solo_loc_name">경기장</label>
				<input type="text" class="form-control js"
					id="solo_loc_name" name="solo_loc_name" placeholder="Search Location" value="${solo.loc.loc_name }"
					readonly="readonly">
				<button type="button" class="js"
					onclick="popup()">경기장 검색</button>
			</div>
			<div class="form-group align-left">
				<input type="hidden" class="form-control js"
					id="solo_loc_num" value="${solo.solo_loc_num }" name="solo_loc_num">
			</div>
			<div class="form-group align-left">
				<label for="solo_money">경기 참가 금액</label>
				<input type="text" class="form-control"
					id="solo_money" name="solo_money" value="${solo.solo_money}" placeholder="Enter Money">
			</div>
			<div class="form-group align-left">
				<label for="solo_age">age</label>
				<div class="btn-group btn-group-toggle" data-toggle="buttons">
					<label class="btn btn-info">
						<input type="checkbox" name="solo_age" id="solo_age1" value="10">10대
					</label>
					<label class="btn btn-info">
						<input type="checkbox" name="solo_age" id="solo_age2" value="20">20대
					</label>
					<label class="btn btn-info">
						<input type="checkbox" name="solo_age" id="solo_age3" value="30">30대
					</label>
					<label class="btn btn-info">
						<input type="checkbox" name="solo_age" id="solo_age4" value="40">40대
					</label>
					<label class="btn btn-info">
						<input type="checkbox" name="solo_age" id="solo_age5" value="50">50대
					</label>
					<label class="btn btn-info">
						<input type="checkbox" name="solo_age" id="solo_age6" value="60">60대
					</label>
				</div>
			</div>
			<div class="form-group align-left">
				<label for="solo_gen">GENDER</label>
				<div class="btn-group btn-group-toggle" data-toggle="buttons">
					<label class="btn btn-info">
						<input type="radio" name="solo_gen" id="solo_gen1" value="남자">남자
					</label>
					<label class="btn btn-info">
						<input type="radio" name="solo_gen" id="solo_gen2" value="여자">여자
					</label>
					<label class="btn btn-info">
						<input type="radio" name="solo_gen" id="solo_gen3" value="무관">성별무관
					</label>
				</div>
			</div>
			<div class="form-group">
				<button type="submit">경기 수정</button>
				<button type="button"
					onclick="location.href='${pageContext.request.contextPath}/solo/list.do'">목록으로</button>
			</div>
		</form>
	</div>
</div>
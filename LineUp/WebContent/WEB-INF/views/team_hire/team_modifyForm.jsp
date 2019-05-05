<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/teamtab/team.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/teamtab/modifyForm.js"></script>
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
		<form action="modify.do?team_num=${team.team_num}" method="post" id="modify_form">
			<div class="form-group align-left">
				<label for="team_member">용병 인원</label>
				<input type="number" class="form-control"
					id="team_member" name="team_member" value="${team.team_member }" placeholder="Enter Number" min=1>
			</div>
			<div class="form-group align-left">
				<label for="team_date">경기 날짜</label>
				<input type="date" class="team_date" name="team_date" id="team_date" value="${team.date[0] }">
				<input type="time" class="team_date" name="team_time" id="team_time" value="${team.date[1] }">
				<span id="message_team_date"></span>
				<img src="${pageContext.request.contextPath}/css/images/ajax-loader.gif" 
						width="16" height="16" id="loading_team_date" 
						style="display:none;">
			</div>
			<div class="form-group align-left">
				<label for="team_end_date">신청 마감 날짜</label>
				<input type="date" class="team_date" name="team_end_date" id="team_end_date" value="${team.end_date[0] }">
				<input type="time" class="team_date" name="team_end_time" id="team_end_time" value="${team.end_date[1] }">
				<span id="message_team_end_date"></span>
				<img src="${pageContext.request.contextPath}/css/images/ajax-loader.gif" 
						width="16" height="16" id="loading_team_end_date" 
						style="display:none;">
			</div>
			<div class="form-group align-left">
				<label for="team_loc_name">경기장</label>
				<input type="text" class="form-control js"
					id="loc_name" name="team_loc_name" value="${team.loc.loc_name }" placeholder="Search Location"
					readonly="readonly">
				<button type="button" class="js"
					onclick="popup()">경기장 검색</button>
			</div>
			<div class="form-group align-left">
				<input type="hidden" class="form-control js"
					id="loc_num" name="team_loc_num" value="${team.loc.loc_num }">
			</div>
			<div class="form-group align-left">
				<label for="team_money">경기 참가 금액</label>
				<input type="text" class="form-control"
					id="team_money" name="team_money" value="${team.team_money }" placeholder="Enter Money">
			</div>
			<div class="form-group align-left">
				<label for="team_age">모집 연령대</label>
				<div class="btn-group btn-group-toggle" data-toggle="buttons">
					<label class="btn btn-info">
						<input type="checkbox" name="team_age" id="team_age1" value="10">10대
					</label>
					<label class="btn btn-info">
						<input type="checkbox" name="team_age" id="team_age2" value="20">20대
					</label>
					<label class="btn btn-info">
						<input type="checkbox" name="team_age" id="team_age3" value="30">30대
					</label>
					<label class="btn btn-info">
						<input type="checkbox" name="team_age" id="team_age4" value="40">40대
					</label>
					<label class="btn btn-info">
						<input type="checkbox" name="team_age" id="team_age5" value="50">50대
					</label>
					<label class="btn btn-info">
						<input type="checkbox" name="team_age" id="team_age6" value="60">60대
					</label>
				</div>
			</div>
			<div class="form-group align-left">
				<label for="team_etc">경기관련사항</label>
				<textarea rows="8" cols="66" name="team_etc" id="team_etc" style="resize: none;">${team.team_etc }</textarea>
			</div>
			<div class="form-group">
				<button type="submit" class="js">경기 수정</button>
				<button type="button" class="js"
					onclick="location.href='${pageContext.request.contextPath}/team/hirelist.do'">목록으로</button>
			</div>
		</form>
	</div>
</div>
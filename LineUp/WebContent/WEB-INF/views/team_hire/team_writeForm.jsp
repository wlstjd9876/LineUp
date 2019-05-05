<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/teamtab/team.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/js/teamtab/writeForm.js"></script>
<script type="text/javascript">
	var openpop;
	function popup(){
		window.name = "writeForm";
		openpop = window.open('search.do','search','left=50, top=50, width=600, height=400, resizable=no');
	}
</script>
<div class="form-panel align-center">
	<div class="form-header">
		<h1>팀업 용병 구인 등록</h1>
	</div>
	<div class="form-content">
		<form action="write.do" method="post" id="write_form">
			<div class="form-group align-left">
				<label for="team_member">용병 인원</label>
				<input type="number" class="form-control"
					id="team_member" name="team_member" placeholder="Enter Number">
				<span id="message_team_member"></span>
				<img src="${pageContext.request.contextPath}/css/images/ajax-loader.gif" 
						width="16" height="16" id="loading_team_member"
						style="display:none;">
			</div>
			<div class="form-group align-left">
				<label for="team_date">경기 날짜</label>
				<input type="date" class="team_date" name="team_date" id="team_date">
				<input type="time" class="team_date" name="team_time" id="team_time">
				<span id="message_team_date"></span>
				<img src="${pageContext.request.contextPath}/css/images/ajax-loader.gif" 
						width="16" height="16" id="loading_team_date" 
						style="display:none;">
			</div>
			<div class="form-group align-left">
				<label for="team_end_date">신청 마감 날짜</label>
				<input type="date" class="team_date" name="team_end_date" id="team_end_date">
				<input type="time" class="team_date" name="team_end_time" id="team_end_time">
				<span id="message_team_end_date"></span>
				<img src="${pageContext.request.contextPath}/css/images/ajax-loader.gif" 
						width="16" height="16" id="loading_team_end_date" 
						style="display:none;">
			</div>
			<div class="form-group align-left">
				<label for="team_loc_name">경기장</label>
				<input type="text" class="form-control js"
					id="loc_name" name="team_loc_name" placeholder="Search Location"
					readonly="readonly">
				<button type="button" class="js"
					onclick="popup()">경기장 검색</button>
			</div>
			<div class="form-group align-left">
				<input type="hidden" class="form-control js"
					id="loc_num" name="team_loc_num">
			</div>
			<div class="form-group align-left">
				<label for="team_money">경기 참가 금액</label>
				<input type="text" class="form-control"
					id="team_money" name="team_money" placeholder="Enter Money">
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
				<textarea rows="8" cols="66" name="team_etc" id="team_etc" style="resize: none;"></textarea>
			</div>
			<div class="form-group">
				<button type="submit" class="js">경기 등록</button>
				<button type="button" class="js"
					onclick="location.href='${pageContext.request.contextPath}/team/hirelist.do'">목록으로</button>
			</div>
		</form>
	</div>
</div>
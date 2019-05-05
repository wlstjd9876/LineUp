$(document).ready(function(){
	var dateCheck=0;
	$('#team_date').on('keyup click blur change', function(){
		$('#message_team_date').text('');
		$('#loading_team_date').show();

		$.ajax({
			url:'dateCheck.do',
			type:'post',
			data:{team_date:$('#team_date').val(), team_end_date:$('#team_end_date').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				$('#loading_team_date').hide();
				if(data.result == 'pass'){
					$('#message_team_date').css('color','blue').text('선택가능한 날짜입니다!');
					dateCheck=1;
				}else if(data.result == 'endDate'){
					$('#message_team_date').css('color','red').text('경기일과 마감일은 하루이상 차이나야합니다!');
					dateCheck=0;
				}else if(data.result == 'date_todayDate'){
					$('#message_team_date').css('color','red').text('경기일은 오늘 이후 날짜여야합니다!');
					dateCheck=0;
				}else if(data.result == 'end_todayDate'){
					$('#message_team_end_date').css('color','red').text('경기마감일은 오늘 이후 날짜여야합니다!');
					dateCheck=0;
				}else if(data.result == 'none'){
					$('#message_team_date').css('color','red').text('날짜를 입력해주세요!');
					dateCheck=0;
				}else{
					alert('날짜 선택 오류 발생');
					dateCheck=0;
				}				
			},
			error:function(){
				$('#loading_team_date').hide();
				alert('네트워크 오류 발생');
				dateCheck=0;
			}
		});
	});
	
	var enddateCheck=0;
	$('#team_end_date').on('keyup click blur change', function(){
		if($('#team_date').val()==''){
			alert('경기 날짜를 선택하세요!');
			$('#team_date').focus();
			return false;
		}
		$('#message_team_end_date').text('');
		$('#loading_team_end_date').show();

		$.ajax({
			url:'dateCheck.do',
			type:'post',
			data:{team_date:$('#team_date').val(), team_end_date:$('#team_end_date').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				$('#loading_team_end_date').hide();
				if($('#team_end_date').val()==''){
					$('#message_team_end_date').text('');
				}else if(data.result == 'pass'){
					$('#message_team_end_date').css('color','blue').text('선택가능한 날짜입니다!');
					enddateCheck=1;
				}else if(data.result == 'endDate'){
					$('#message_team_end_date').css('color','red').text('경기일과 마감일은 하루이상 차이나야합니다!');
					enddateCheck=0;
				}else if(data.result == 'date_todayDate'){
					$('#message_team_date').css('color','red').text('경기일은 오눌 이후 날짜여야합니다!');
					enddateCheck=0;
				}else if(data.result == 'end_todayDate'){
					$('#message_team_end_date').css('color','red').text('경기 마감일은 오눌 이후 날짜여야합니다!');
					enddateCheck=0;
				}else if(data.result == 'none'){
					$('#message_team_end_date').css('color','red').text('날짜를 입력해주세요!');
					enddateCheck=0;
				}else{
					alert('날짜 선택 오류 발생');
					enddateCheck=0;
				}				
			},
			error:function(){
				$('#loading_team_end_date').hide();
				alert('네트워크 오류 발생');
				enddateCheck=0;
			}
		});
	});
	
	$('#modify_form').submit(function(){
		if($('#team_member').val()==''){
			alert('경기 인원을 입력하세요!');
			$('#team_member').focus();
			return false;
		}
		if($('#team_date').val()==''){
			alert('경기 날짜를 선택하세요!');
			$('#team_date').focus();
			return false;
		}
		if($('#team_time').val()==''){
			alert('경기 시간을 선택하세요!');
			$('#team_time').focus();
			return false;
		}
		if($('#team_end_date').val()==''){
			alert('경기 마감 날짜를 선택하세요!');
			$('#team_end_date').focus();
			return false;
		}
		if($('#team_end_time').val()==''){
			alert('경기 마감 시간을 선택하세요!');
			$('#team_end_time').focus();
			return false;
		}
		if(dateCheck==0){
			alert('경기날짜를 확인해주세요!');
			$('#team_date').focus();
			return false;
		}
		if(enddateCheck==0){
			alert('경기마감날짜를 확인해주세요!');
			$('#team_end_date').focus();
			return false;
		}
		if($('#team_loc_name').val()==''){
			alert('경기장을 입력하세요!');
			$('#team_loc_name').focus();
			return false;
		}
		if(!$("input:checkbox[name=team_age]").is(":checked")){
			alert("연령대를 선택하세요.");
			return false;
		}
		if($('#team_etc').val()==''){
			alert('경기 기타 사항을 입력하세요!');
			$('#team_etc').focus();
			return false;
		}
	});
});
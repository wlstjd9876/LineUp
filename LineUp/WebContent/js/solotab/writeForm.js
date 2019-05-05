$(document).ready(function(){
	var memberCheck=0;
	$('#solo_member').on('keyup click blur', function(){
		$('#message_solo_member').text('');
		$('#loading_solo_member').show();

		$.ajax({
			url:'memberMinvalue.do',
			type:'post',
			data:{solo_member:$('#solo_member').val(), solo_min_member:$('#solo_min_member').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				$('#loading_solo_member').hide();
				if(data.result == 'under'){
					var num =  $('#solo_min_member').val();
					$('#message_solo_member').css('color','red').text('경기 인원은 최소인원보다 많아야합니다!');
					if($('#solo_min_member').val()!=''){
						num++;
						$('#solo_member').val(num);
					}
					else
						$('#solo_member').val(1);
					memberCheck=0;
				}else if(data.result == 'zero'){
					$('#message_solo_member').css('color','red').text('경기 인원은 1명보다 많아야합니다.!');
					$('#solo_member').val(1);
					memberCheck=0;
				}else if(data.result == 'pass'){
					$('#message_solo_member').css('color','blue').text('사용가능합니다.');
					memberCheck=1;
				}else{
					alert('경기인원 체크 오류 발생');
					memberCheck=0;
				}				
			},
			error:function(){
				$('#loading_solo_member').hide();
				alert('네트워크 오류 발생');
				memberCheck=0;
			}
		});
	});

	$('#solo_min_member').on('keyup click blur', function(){
		$('#message_solo_min_member').text('');
		$('#loading_solo_min_member').show();
		if($('#solo_member').val()==''){
			$('#loading_solo_min_member').hide();
			$('#solo_member').focus();
			$('#message_solo_member').css('color','red').text('먼저 경기인원을 입력해주세요!');
			return false;
		}
		if($('#solo_min_member').val()==''){
			$('#loading_solo_min_member').hide();
			return false;
		}

		$.ajax({
			url:'memberCheck.do',
			type:'post',
			data:{solo_member:$('#solo_member').val(), solo_min_member:$('#solo_min_member').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				$('#loading_solo_min_member').hide();
				if(data.result == 'over'){
					$('#message_solo_min_member').css('color','red').text('최소인원은 경기 인원보다 적어야합니다!');
					$('#solo_min_member').val($('#solo_member').val()-1);
					memberCheck=0;
				}else if(data.result == 'zero'){
					$('#message_solo_member').css('color','red').text('최소인원은 0보다 커야합니다!');
					$('#solo_min_member').val(0);
					memberCheck=0;
				}else if(data.result == 'pass'){
					$('#message_solo_min_member').css('color','blue').text('사용가능합니다.');
					memberCheck=1;
				}else{
					alert('최소인원 체크 오류 발생');
					memberCheck=0;
				}				
			},
			error:function(){
				$('#loading_solo_min_member').hide();
				alert('네트워크 오류 발생');
				memberCheck=0;
			}
		});
	});
	var dateCheck=0;
	$('#solo_date').on('keyup click blur change', function(){
		$('#message_solo_date').text('');
		$('#loading_solo_date').show();

		$.ajax({
			url:'dateCheck.do',
			type:'post',
			data:{solo_date:$('#solo_date').val(), solo_end_date:$('#solo_end_date').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				$('#loading_solo_date').hide();
				if(data.result == 'pass'){
					$('#message_solo_date').css('color','blue').text('선택가능한 날짜입니다!');
					dateCheck=1;
				}else if(data.result == 'endDate'){
					$('#message_solo_date').css('color','red').text('경기일과 마감일은 하루이상 차이나야합니다!');
					dateCheck=0;
				}else if(data.result == 'date_todayDate'){
					$('#message_solo_date').css('color','red').text('경기일은 오늘 이후 날짜여야합니다!');
					dateCheck=0;
				}else if(data.result == 'end_todayDate'){
					$('#message_solo_end_date').css('color','red').text('경기마감일은 오늘 이후 날짜여야합니다!');
					dateCheck=0;
				}else if(data.result == 'none'){
					$('#message_solo_date').css('color','red').text('날짜를 입력해주세요!');
					dateCheck=0;
				}else{
					alert('날짜 선택 오류 발생');
					dateCheck=0;
				}				
			},
			error:function(){
				$('#loading_solo_date').hide();
				alert('네트워크 오류 발생');
				dateCheck=0;
			}
		});
	});

	var enddateCheck=0;
	$('#solo_end_date').on('keyup click blur change', function(){
		if($('#solo_date').val()==''){
			alert('경기 날짜를 선택하세요!');
			$('#solo_date').focus();
			return false;
		}
		$('#message_solo_end_date').text('');
		$('#loading_solo_end_date').show();

		$.ajax({
			url:'dateCheck.do',
			type:'post',
			data:{solo_date:$('#solo_date').val(), solo_end_date:$('#solo_end_date').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				$('#loading_solo_end_date').hide();
				if($('#solo_end_date').val()==''){
					$('#message_solo_end_date').text('');
				}else if(data.result == 'pass'){
					$('#message_solo_end_date').css('color','blue').text('선택가능한 날짜입니다!');
					enddateCheck=1;
				}else if(data.result == 'endDate'){
					$('#message_solo_end_date').css('color','red').text('경기일과 마감일은 하루이상 차이나야합니다!');
					enddateCheck=0;
				}else if(data.result == 'date_todayDate'){
					$('#message_solo_date').css('color','red').text('경기일은 오눌 이후 날짜여야합니다!');
					enddateCheck=0;
				}else if(data.result == 'end_todayDate'){
					$('#message_solo_end_date').css('color','red').text('경기 마감일은 오눌 이후 날짜여야합니다!');
					enddateCheck=0;
				}else if(data.result == 'none'){
					$('#message_solo_end_date').css('color','red').text('날짜를 입력해주세요!');
					enddateCheck=0;
				}else{
					alert('날짜 선택 오류 발생');
					enddateCheck=0;
				}				
			},
			error:function(){
				$('#loading_solo_end_date').hide();
				alert('네트워크 오류 발생');
				enddateCheck=0;
			}
		});
	});

	$('#writeTemp').click(function(){
		var solo_age='';
		for(var i =1 ; i<7 ; i++){
			if($('input:checkbox[id="solo_age'+i+'"]').is(':checked'))
				solo_age += $('#solo_age'+i).val()+',';
		}
		
		$.ajax({
			url:'writeTemp.do',
			type:'post',
			data:{solo_min_member:$('#solo_min_member').val(), solo_member:$('#solo_member').val(), solo_end_date:$('#solo_end_date').val(), solo_end_time:$('#solo_end_time').val()
				,solo_date:$('#solo_date').val(), solo_time:$('#solo_time').val(), solo_loc_num:$('#solo_loc_num').val(), solo_money:$('#solo_money').val()
				,solo_age:solo_age, solo_gen:$('#solo_gen').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				if(data.result=='success')
					alert('임시 저장 완료!');
				else
					alert('임시 저장 실패!');
			},
			error:function(){
				$('#loading_solo_date').hide();
				alert('네트워크 오류 발생');
			}
		});
	});

	$('#write_form').submit(function(){ 
		if($('#solo_member').val()==''){
			alert('경기 인원을 입력하세요!');
			$('#solo_member').focus();
			return false;
		}
		if(memberCheck==0){
			alert('최소인원을 확인해주세요!');
			$('#solo_min_member').focus();
			return false;
		}
		if($('#solo_date').val()==''){
			alert('경기 날짜를 선택하세요!');
			$('#solo_date').focus();
			return false;
		}
		if($('#solo_time').val()==''){
			alert('경기 시간을 선택하세요!');
			$('#solo_time').focus();
			return false;
		}
		if($('#solo_end_date').val()==''){
			alert('경기 마감 날짜를 선택하세요!');
			$('#solo_end_date').focus();
			return false;
		}
		if($('#solo_end_time').val()==''){
			alert('경기 마감 시간을 선택하세요!');
			$('#solo_end_time').focus();
			return false;
		}
		if(dateCheck==0){
			alert('경기날짜를 확인해주세요!');
			$('#solo_date').focus();
			return false;
		}
		if(enddateCheck==0){
			alert('경기마감날짜를 확인해주세요!');
			$('#solo_end_date').focus();
			return false;
		}
		if($('#solo_loc_name').val()==''){
			alert('경기장을 입력하세요!');
			$('#solo_loc_name').focus();
			return false;
		}
		if($('#solo_member').val()==''){
			alert('경기 인원을 입력하세요!');
			$('#solo_member').focus();
			return false;
		}
		if(!$("input:checkbox[name=solo_age]").is(":checked")){
			alert("연령대를 선택하세요.");
			return false;
		}
		var gen = $("input[name=solo_gen]:checked").val();
		if(typeof gen == "undefined"){
			alert("성별을 선택하세요.");
			return false;
		}
	});
});
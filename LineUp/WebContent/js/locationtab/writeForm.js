$(document).ready(function(){
	$('.dia-js-address').dialog({
		autoOpen:false
	});
	
	//이벤트 연결
	$('#js-address').click(function(){
		//다이얼로그 실행
		$('.dia-js-address').dialog('open');
	});
	
	var Loc_addressduplicated = 0;
	function limitlen(id, limit){
		var text = $(id).val();
		var textlength = text.length;
		if(textlength > limit){
			alert('길이제한을 초과했습니다!');
			$(id).val(text.substr(0,limit));
			return false;
		}
	}
	
	$('#loc_name').keyup(function(){
		limitlen(this, 33);
	});
	
	$('.js-address-btn').click(function(){
		var address = '';
		address += $('#sample4_roadAddress').val();
		address += $('#sample4_detailAddress').val();
		address += $('#sample4_extraAddress').val();
		$('#loc_address').val(address);
		
		$('.dia-js-address').dialog("close");
		
		limitlen(this, 33);
		if($('#loc_address').val()==''){
			$('#loc_address').focus();
			$('#loc_address').text('');
			return false;
		}
		$('#message_loc_address').text('');
		$('#loading_loc_address').show();
		$.ajax({
			url:'writecheckDuplicatedElements.do',
			type:'post',
			data:{loc_address:$('#loc_address').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				$('#loading_loc_address').hide();
				if(data.result == 'NotFound'){
					$('#message_loc_address').css('color','blue').text('사용 가능한 주소입니다!!');
					Loc_addressduplicated = 1;
				}else if(data.result == 'Duplicated'){
					$('#message_loc_address').css('color','red').text('이미 있는 주소입니다!!');
					Loc_addressduplicated = 0;
				}else{
					alert('주소 중복 체크 오류 발생');
				}				
			},
			error:function(){
				$('#loading_loc_address').hide();
				alert('네트워크 오류 발생');
			}
		});
	});
	
	$('#loc_phone').keyup(function(){
		limitlen(this, 15);
	});
	

	//경기장 정보 등록 유효성 체크
	$('#write_form').submit(function(){
		if($('#loc_name').val()==''){
			alert('경기장 이름을 입력하세요!');
			$('#loc_name').focus();
			return false;
		}
		if(Loc_addressduplicated==0){
			alert('경기장 주소 중복 체크해주세요!');
			$('#loc_address').focus();
			return false;
		}
		if($('#loc_phone').val()==''){
			alert('경기장 전화번호를 입력하세요!');
			$('#loc_phone').focus();
			return false;
		}
		
		if($('#loc_content').val()==''){
			alert('경기장 정보를 입력하세요!');
			$('#loc_content').focus();
			return false;
		}
	});
});
$(document).ready(function(){
	var Idduplicated = 0;
	var Phoneduplicated = 0;
	var Emailduplicated = 0;
	var pw_check = 0;
	
	//아이디 중복 체크
	$('#id').keyup(function(){	
		if($('#id').val()==''){
			$('#id').focus();
			$('#message_id').text('');//메시지 초기화
			return false;
		}
		$('#message_id').text('');//메시지 초기화
		$('#loading_id').show(); //로딩 이미지 노출
		$.ajax({
			url:'team_checkDuplicatedElements.do',
			type:'post',
			data:{id:$('#id').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				$('#loading_id').hide();//로딩 이미지 감추기
				if(data.result == 'NotFound'){
					$('#message_id').css('color','blue').text('사용 가능한 아이디네요!!');
					Idduplicated = 1;
				}else if(data.result == 'Duplicated'){
					$('#message_id').css('color','red').text('이미 있는 아이디입니다!!');
					Idduplicated = 0;
				}else{
					alert('아이디 중복 체크 오류 발생');
				}				
			},
			error:function(){
				$('#loading_id').hide();//로딩 이미지 감추기
				alert('네트워크 오류 발생');
			}
		});
	});
	
	//비밀번호 체크
	$('#pw').blur(function(){		
		$('#message_ck_pw').text('');//메시지 초기화
		$('#loading_ck_pw').show(); //로딩 이미지 노출
		if($('#pw').val()==''){
			$('#loading_ck_pw').hide();
			$('#message_ck_pw').css('color','red').text('비밀번호를 입력해주세요!!');
			$('#pw').focus();
			pw_check = 0;
			return false;
		}else{
			$('#loading_ck_pw').hide();
			if($('#pw').val()!=$('#ck_pw').val()){
				if($('#ck_pw').val()!=''){
					$('#message_ck_pw').css('color','red').text('비밀 번호가 서로 다릅니다!!');
					pw_check = 0;
					return false;
				}else{
					$('#message_ck_pw').css('color','red').text('비밀번호 체크를 입력해주세요!!');
					pw_check = 0;
					return false;
				}
			}else{
				$('#message_ck_pw').css('color','blue').text('비밀번호가 동일합니다!!');
				pw_check = 1;
			}
		}
		
	});
	$('#ck_pw').blur(function(){		
		$('#message_ck_pw').text('');//메시지 초기화
		$('#loading_ck_pw').show(); //로딩 이미지 노출
		if($('#pw').val()==''){
			$('#loading_ck_pw').hide();
			$('#message_ck_pw').css('color','red').text('비밀번호를 입력해주세요!!');
			$('#pw').focus();
			pw_check = 0;
			return false;
		}else{
			$('#loading_ck_pw').hide();
			if($('#pw').val()!=$('#ck_pw').val()){
				if($('#ck_pw').val()!=''){
					$('#message_ck_pw').css('color','red').text('비밀 번호가 서로 다릅니다!!');
					pw_check = 0;
					return false;
				}else{
					$('#message_ck_pw').css('color','red').text('비밀번호 체크를 입력해주세요!!');
					pw_check = 0;
					return false;
				}
			}else{
				$('#message_ck_pw').css('color','blue').text('비밀번호가 동일합니다!!');
				pw_check = 1;
			}
		}
		
	});
	
	$('#phone').keyup(function(){	
		if($('#phone').val()==''){
			$('#phone').focus();
			$('#message_phone').text('');//메시지 초기화
			return false;
		}
		$('#message_phone').text('');//메시지 초기화
		$('#loading_phone').show(); //로딩 이미지 노출
		$.ajax({
			url:'team_checkDuplicatedElements.do',
			type:'post',
			data:{phone:$('#phone').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				$('#loading_phone').hide();//로딩 이미지 감추기
				if(data.result == 'NotFound'){
					$('#message_phone').css('color','blue').text('사용 가능한 번호네요!!');
					Phoneduplicated = 1;
				}else if(data.result == 'Duplicated'){
					$('#message_phone').css('color','red').text('이미 있는 번호입니다!!');
					Phoneduplicated = 0;
				}else{
					alert('번호 중복 체크 오류 발생');
				}				
			},
			error:function(){
				$('#loading_phone').hide();//로딩 이미지 감추기
				alert('네트워크 오류 발생');
			}
		});
	});
	
	$('#email').keyup(function(){
		if($('#email').val()==''){
			$('#email').focus();
			$('#message_email').text('');//메시지 초기화
			return false;
		}
		$('#message_email').text('');//메시지 초기화
		$('#loading_email').show(); //로딩 이미지 노출
		$.ajax({
			url:'team_checkDuplicatedElements.do',
			type:'post',
			data:{email:$('#email').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				$('#loading_email').hide();//로딩 이미지 감추기
				if(data.result == 'NotFound'){
					$('#message_email').css('color','blue').text('사용 가능한 이메일이네요!!');
					Emailduplicated = 1;
				}else if(data.result == 'Duplicated'){
					$('#message_email').css('color','red').text('이미 있는 이메일입니다!!');
					Emailduplicated = 0;
				}else{
					alert('이메일 중복 체크 오류 발생');
				}				
			},
			error:function(){
				$('#loading_email').hide();//로딩 이미지 감추기
				alert('네트워크 오류 발생');
			}
		});
	});

	//회원 정보 등록 유효성 체크
	$('#register_form').submit(function(){
		if($('#id').val()==''){
			alert('아이디를 입력하세요!');
			$('#id').focus();
			return false;
		}
		 if(Idduplicated==0){
				alert('아이디가 중복되었습니다!');
				$('#id').focus();
				return false;
			}
		if($('#pw').val()==''){
			alert('비밀번호를 입력하세요!');
			$('#pw').focus();
			return false;
		}
		if($('#ck_pw').val()==''){
			alert('비밀번호 체크를 입력하세요!');
			$('#ck_pw').focus();
			return false;
		}
		if(pw_check==0){
			alert('비밀번호를 체크해주세요!');
			$('#ck_pw').focus();
			return false;
		}
		if($('#phone').val()==''){
			alert('전화번호를 입력하세요!');
			$('#phone').focus();
			return false;
		}
		if(Phoneduplicated==0){
			alert('번호가 중복되었습니다!');
			$('#phone').focus();
			return false;
		}
		if($('#email').val()==''){
			alert('이메일을 입력하세요!');
			$('#email').focus();
			return false;
		}
		if(Emailduplicated==0){
			alert('이메일이 중복되었습니다!');
			$('#email').focus();
			return false;
		}
		if($('#team_name').val()==''){
			alert('팀명을 입력하세요!');
			$('#team_name').focus();
			return false;
		}
	    if(!$("input:checkbox[name=team_age]").is(":checked")){
	   		alert("연령대를 선택하세요.");
	   		return false;
	   	}
	    
	   
		
		
	});
});
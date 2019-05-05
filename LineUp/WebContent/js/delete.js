$(document).ready(function(){
	var pw_check = 0;
	
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
	

	//회원 정보 등록 유효성 체크
	$('#delete_form').submit(function(){
		if($('#id').val()==''){
			alert('아이디를 입력하세요!');
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
	});
});
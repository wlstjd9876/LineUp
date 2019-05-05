$(document).ready(function(){
	//회원 정보 등록 유효성 체크
	$('#login_form').submit(function(){
		var auth = $("input[name=auth]:checked").val();
	    if(typeof auth == "undefined"){
	   		alert("회원 종류를 선택하세요.");
	   		return false;
	   	}
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
	});
});
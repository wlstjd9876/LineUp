$(document).ready(function(){
	//경기장 정보 등록 유효성 체크
	$('#modify_form').submit(function(){
		if($('#title').val()==''){
			alert('영상 이름을 입력하세요!');
			$('#title').focus();
			return false;
		}
		if($('#content').val()==''){
			alert('영상 내용을 입력하세요!');
			$('#content').focus();
			return false;
		}
		
		if($('#thumb').val()==''){
			alert('썸네일을 입력하세요!');
			$('#thumb').focus();
			return false;
		}
	});
});
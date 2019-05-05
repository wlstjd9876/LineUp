$(document).ready(function(){
	$('.dia-js-app').dialog({
		autoOpen:false
	});
	
	//이벤트 연결
	$('#js-app').click(function(){
		//다이얼로그 실행
		$('.dia-js-app').dialog('open');
	});
	$('.js-app-btn').click(function(){
		$('.dia-js-address').dialog("close");
	});
});
$(document).ready(function(){
	var cnt = 10;
	var timer = setInterval(function(){
	$('#count').text(cnt+'초후에 영상 목록으로 이동됩니다!!');
	if(cnt <= 0){
		clearInterval(timer);
		location.href=contextPath+'/com_video/list.do';
	}
	cnt--;
	},1000);
});
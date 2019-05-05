$(document).ready(function(){
	var cnt = 10;
	var timer = setInterval(function(){
	$('#count').text(cnt+'초후에 경기 목록으로 이동됩니다!!');
	if(cnt <= 0){
		clearInterval(timer);
		location.href=contextPath+'/solo/list.do';
	}
	cnt--;
	},1000);
});
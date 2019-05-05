$(document).ready(function(){
	var cnt = 5;
	var timer = setInterval(function(){
	$('#count').text(cnt+'초후에 경기장 목록으로 이동됩니다!!');
	if(cnt <= 0){
		clearInterval(timer);
		location.href=contextPath+'/location/list.do';
	}
	cnt--;
	},1000);
});
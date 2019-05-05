$(document).ready(function(){
	var cnt = 10;
	var timer = setInterval(function(){
	$('#count').text(cnt+'초후에 메인 페이지로 이동됩니다!!');
	if(cnt <= 0){
		clearInterval(timer);
		location.href=contextPath+'/main/main.do';
	}
	cnt--;
	},1000);
});
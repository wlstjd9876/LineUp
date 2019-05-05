$(document).ready(function(){
	$('#writeTemp').click(function(){
		$.ajax({
			url:'checkm.do',
			type:'post',
			data:{},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				$('#check_m').text(data.result);
			},
			error:function(){
				alert('네트워크 오류 발생');
			}
		});
	});
});
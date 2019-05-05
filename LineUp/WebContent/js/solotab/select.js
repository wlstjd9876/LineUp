$(document).ready(function(){
		//로딩 이미지 노출
		$('#loading').show();
		//목록 호출
		$.ajax({
			url:'selectajax.do',
			type:'post',
			data:{num:$('#app_num').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				$('#loading').hide();
				member = data.member;
				var output = '<div class="form-group align-center js-style">';
				   output += '  <label for="app_member">인원수</label>';
				   output += '  <select class="app_sel align-center" name="app_member" id="app_member">';
				   for (var i=1 ; i<=member ; i++) {
					   output += '	<option value="'+i+'">'+i+'명</option>';
				   }
				   output += '	</select>';
				   output += '</div>';
				   //문서 객체에 추가
				   $('#output').append(output);  
			},
			error:function(){
				//로딩 이미지 감추기
				$('#loading').hide();
				alert('네트워크 오류 발생');
			}
		});
});
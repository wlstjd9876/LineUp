$(document).ready(function(){
	var currentPage;
	var count;
	var rowCount;
	
	//댓글 목록
	function selectData(pageNum,num){
		currentPage = pageNum;
		
		if(pageNum == 1){
			$('#appList').empty();
		}
		$('#loading_appList').show();
		
		$.ajax({
			url:'listApp.do',
			type:'post',
			data:{pageNum:pageNum,app_num:num},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				//로딩 이미지 감추기
				$('#loading_appList').hide();
				count = data.count;
				rowCount = data.rowCount;
				var list = data.list;
				
				if(count<0 || list == null){
					alert('목록 호출시 오류 발생!');
				}else{
					var output = '<div class="table js-tb">';
					   output += '	<div class="tbrow header blue">';
					   output += '		<div class="cell">아이디</div>';
					   output += '		<div class="cell">이름</div>';
					   output += '		<div class="cell">전화번호</div>';
					   output += '		<div class="cell">성별</div>';
					   output += '		<div class="cell">연령대</div>';
					   output += '		<div class="cell">인원</div>';
					   output += '		<div class="cell">신청취소</div>';
					   output += '</div>'; 
					$(list).each(function(index,app){
						   output += '	<div class="tbrow">';
						   output += '		<div class="cell">'+app.app_id+'</div>';
						   output += '		<div class="cell">'+app.name+'</div>';
						   output += '		<div class="cell">'+app.phone+'</div>';
						   output += '		<div class="cell">'+app.gen+'</div>';
						   output += '		<div class="cell">'+app.age+'대</div>';
						   output += '		<div class="cell">'+app.app_member+'</div>';
						   output += '		<div class="cell"><button type="button" class="btn btn-danger btn-sm" onclick="location.href=\''+contextPath+'/soloapp/delete.do?app_num='+app.app_num+'&id='+app.app_id+'\'">Cancel</button></div>';
						   output += '	</div>';
					});
					output += '</div>';
					$('#appList').append(output);
					//paging button 처리
					if(currentPage>=Math.ceil(count/rowCount)){
						//다음 페이지가 없음
						$('.paging-button').hide();
					}else{
						//다음 페이지가 존재
						$('.paging-button').show();
					}
				}
			},
			error:function(){
				//로딩 이미지 감추기
				$('#loading_appList').hide();
				alert('네트워크 오류 발생');
			}
		});
	}
	
	//다음 댓글 보기 버튼 클릭시 데이터 추가
	$('.paging-button input').click(function(){
		var pageNum = currentPage + 1;
		selectData(pageNum,$('#app_num').val());
	});
	
	
	
	//초기 데이터(목록) 호출
	selectData(1,$('#app_num').val());
	
});

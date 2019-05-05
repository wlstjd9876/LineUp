<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글쓰기</title>
<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" 
   src="${pageContext.request.contextPath}/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" 
   src="${pageContext.request.contextPath}/js/comtab/writeForm.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/comtab/com.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>
<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.11/summernote.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    $('#content').summernote({
   	height:200,
   	fontNames : [ '맑은고딕', 'Arial', 'Arial Black', 'Comic Sans MS', 'Courier New', ],
		fontNamesIgnoreCheck : [ '맑은고딕' ],
		focus: true,
		callbacks: {
				onImageUpload: function(files, editor, welEditable) {
	            	sendFile(files[0], this);
	        }
		} 	
   });
    
   function sendFile(file,el){
	   var form_data = new FormData();
	   form_data.append('file',file);
	   
	   //이미지 전송
	   $.ajax({
		   url:'imageUploader.do',
		   type:'post',
		   data:form_data,
		   enctype:'multipart/form-data',
		   cache:false,
		   contentType:false,
		   processData:false,
		   success:function(img_name){
			   $(el).summernote('editor.insertImage',img_name);
		   }
		   
	   });
	   
   } 
    
});
</script>   
</head>
<body>
<div class="page-main-style">
	<h2>&nbsp;&nbsp;&nbsp;&nbsp;영상 등록</h2>
	<form action="write.do" method="post"
	      enctype="multipart/form-data"
	                         id="write_form">
		<ul>
			<li>
				<label for="title">제목</label>
				<input type="text" name="title" id="title"
				                       maxlength="50">
			</li>
			<li>
				<label for="content">내용</label>
				<textarea rows="5" cols="30" 
				     name="content" id="content"></textarea>
			</li>
			<li>
				<label for="thumb">썸네일</label>
				<input type="file" name="thumb" id="thumb" accept="image/*">
			</li>
		</ul>  
		<div class="align-center">
			<input type="submit" value="등록">
			<input type="button" value="목록으로"
			   onclick="location.href='${pageContext.request.contextPath}/board/list.do'">
		</div>                       
	</form>
</div>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="<%= request.getContextPath() %>/js/jquery-3.6.0.min.js"></script>
<script>
$(function(){
	$('#chk').on('click', function(){
		id = $('#id').val();
		
		$.ajax({
			url : '/webTest/checkId.do',
			data :	{ "id" : id },
			type : 'post',
			success : function(res){
				if(res == "사용 가능"){
					$('#res').html(res);
				}else{
					$('#res').html(res);
				}
			},
			error : function(xhr){
				alert("상태 : " + xhr.status);
			},
			dataType : 'json'
		})
	})
})
</script>
</head>
<body>
<h3>회원 정보 입력 폼</h3>
<form action="<%= request.getContextPath() %>/memberAdd.do" enctype="multipart/form-data" method="post">
	<table border="1">
		<tr>
			<td>회원ID</td>
			<td>
				<input type="text" name="id" id="id">
				<input type="button" value="중복확인" id="chk"><br>
				<span id="res"></span>
			</td>
		</tr>
		
		<tr>
			<td>비밀번호</td>
			<td><input type="text" name="pass"></td>
		</tr>
		
		<tr>
			<td>비밀번호 확인</td>
			<td><input type="text" name="chkpass"></td>
		</tr>
		
		<tr>
			<td>회원이름</td>
			<td><input type="text" name="name"></td>
		</tr>
		
		<tr>
			<td>전화번호</td>
			<td><input type="text" name="tel"></td>
		</tr>
		
		<tr>
			<td>회원주소</td>
			<td><input type="text" name="addr"></td>
		</tr>
		
		<tr>
			<td>프로필 사진</td>
			<td><input type="file" name="photo"></td>
		</tr>
		
		<tr>
			<td colspan="2">
				<input type="submit" value="저장" id="btnAdd">
				<input type="reset" value="취소">
				<input type="button" value="회원목록" onclick="location.href='<%= request.getContextPath() %>/basic/memberHw/memberList.jsp'">
			</td>
		</tr>
		
	</table>
</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lprod 자료 목록</title>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.6.0.min.js"></script>
<script>
$(function(){
	// 비동기 : 화면에서 일부만 변경되고 주소 같은건 변하지 않는다
	$('#btn').on('click', function(){
		$.ajax({
			url : "<%= request.getContextPath() %>/lprodList.do",
			type : 'post',
			success : function(data){
				console.log(data);
				let htmlCode = "<table border = '1'>";
				htmlCode += "<tr><th>LPROD_ID</th><th>LPROD_GU</th><th>LPROD_NM</th></tr>";
				$.each(data, function(i, v){
					htmlCode += "<tr><td>"+ v.lprod_id + "</td><td>" + v.lprod_gu + "</td><td>" + v.lprod_nm+"</td></tr>";
				})
				htmlCode += "</table>";
				
				$('#result').html(htmlCode);
			},
			error : function(xhr){
				alert(xhr.status);
			},
			dataType : 'json'
		})
	})
	
	// 동기 방식
	$('#btn2').on('click', function(){
		location.href = "<%= request.getContextPath() %>/lprodList2.do";
	})
})
</script>
</head>
<body>
<input type="button" id="btn" value="Lprod자료 가져오기(비동기_Ajax)"><br>
<input type="button" id="btn2" value="Lprod자료 가져오기(동기방식_nonAjax)"><br>
<h3>Lprod 자료 가져오기</h3>
<div id="result"></div>
</body>
</html>
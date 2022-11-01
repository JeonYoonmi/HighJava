<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.6.0.min.js"></script>
<script>
$(function(){
	$('#btn').on('click', function(){
		$.ajax({
			url : "<%= request.getContextPath() %>/lprodTest.do",
			type : 'get',
			success : function(res){
				code = "<table border = '1'>";
				code += "<tr><th>LPROD_ID</th><th>LPROD_GU</th><th>LPROD_NM</th></tr>";
				$.each(res, function(i, v){
					code += "<tr><td>"+ v.lprod_id + "</td><td>" + v.lprod_gu + "</td><td>" + v.lprod_nm+"</td></tr>";
				})
				code += "</table>";
				
				$('#result').html(code);
			},
			error : function(xhr){
				alert(xhr.status);
			},
			dataType : 'json'
		})
	})
})
</script>
</head>
<body>
<input type="button" id="btn" value="Lprod자료 가져오기"><br><br>
<div id="result"></div>
</body>
</html>
<%@page import="kr.or.ddit.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../include/header.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<select id="search_key">
	<option value="MEM_ID">아이디</option>
	<option value="MEM_NAME">이름</option>
	<option value="MEM_MAIL">이메일</option>
	<option value="MEM_ADD">주소</option>
</select>
<input type="text" id="search_word">
<button type="button" onclick="search();">검색</button>
<table border="1">
	<thead>
		<tr>
			<th>순번</th>
			<th>아이디</th>
			<th>이름</th>
			<th>이메일</th>
			<th>주소</th>
		</tr>
	</thead>
	<tbody>
		<%
			List<MemberVO> list = (List<MemberVO>)request.getAttribute("memberList");
			int i = 1;
			for(MemberVO memberInfo : list){
		%>
		<tr>
			<td><%= i++ %><br></td>
			<td><%= memberInfo.getMem_id() %><br></td>
			<td><%= memberInfo.getMem_name() %><br></td>
			<td><%= memberInfo.getMem_mail() %><br></td>
			<td><%= memberInfo.getMem_add1() + " " + memberInfo.getMem_add2() %><br></td>
		</tr>
		<%
			}
		%>
	</tbody>
</table>
<script type="text/javascript" src="/MVCStudy/js/jquery-3.6.0.min.js"></script>
<script>
// 사진 참고 - 1
function search(){
// 	var data = {};

	var key = $('#search_key').val();
// 	var key = $('#search_key option:selected').val();

	var word = $('#search_word').val();
	
	if(word.trim() == ''){
		alert("검색어를 입력해주세요.");
		return false;
	}
	
	$.ajax({
		type : 'post',
		dataType : 'json',
		url : '/MVCStudy/member/memberList.do',
// 		data : data,
		data : {
			key : key,
			word : word
		},
		success : function(res){
			console.log(res);
			var html = "";
			$.each(res.memberList, function(i, v){
				html += '<tr>';
				html += '<td>' + (i+1) + '</td>';
				html += '<td>' + v.mem_id + '</td>';
				html += '<td>' + v.mem_name + '</td>';
				html += '<td>' + v.mem_mail + '</td>';
				html += '<td>' + v.mem_add1 + v.mem_add2 + '</td>';
				html += '</tr>';
				
			});
			$('table tbody').html(html);
		}
	});
}
</script>
<jsp:include page="../include/footer.jsp"></jsp:include>
</body>
</html>
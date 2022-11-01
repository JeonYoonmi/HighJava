<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>회원 정보 상세보기</h3>
<form action="<%= request.getContextPath()%>/basic/memberHw/memberInfoModify.jsp">
<table border="1">
	<tr>
		<td colspan="2"><img src="<%= request.getParameter("memId") %>.jpg"></td>
	</tr>
	
	<tr>
		<td>회원ID</td>
		<td><%= request.getParameter("memId") %></td>
	</tr>
	
	<tr>
		<td>비밀번호</td>
		<td></td>
	</tr>
	
	<tr>
		<td>회원이름</td>
		<td></td>
	</tr>
	
	<tr>
		<td>전화번호</td>
		<td></td>
	</tr>
	
	<tr>
		<td>회원주소</td>
		<td></td>
	</tr>
	
	<tr>
		<td colspan="2">
			<input type="submit" value="수정">
			<input type="reset" value="삭제">
			<input type="button" value="회원목록" onclick="location.href='<%= request.getContextPath() %>/memberList.do'">
		</td>
	</tr>
</table>
</form>
</body>
</html>
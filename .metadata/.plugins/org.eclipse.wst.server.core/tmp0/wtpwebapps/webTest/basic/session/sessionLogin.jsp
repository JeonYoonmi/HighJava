<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Session 연습</title>
<style>
table{
	margin: 0px auto;
}

input[type=submit] {
	margin-left: 40%;
}

table {
	border-collapse: collapse;
}
</style>

</head>
<!-- 
	MYMEMBER테이블에 저장된 회원ID와 PassWord로 로그인 처리를 하고,
	환영 메시지는 회원 이름이 출력되도록 작성하시오
 -->


<body>
<%
	//JSP문서에서 세션은 'session'이라는 이름으로 이미 만들어져 있다.
	Enumeration<String> sessionNames = session.getAttributeNames();
	String name = (String) session.getAttribute("name");	// 세션값 가져오기 - 세션값이 없으면  null값이 저장된다.
	if(name == null){
%>
	<form action="<%= request.getContextPath() %>/sessionDBLogin.do" method="post">
		<table border="1">
			<tr>
				<td>ID :</td>
				<td><input type="text" name="id" placeholder="ID를 입력하세요"></td>
			</tr>
			
			<tr>
				<td>PASS :</td>
				<td><input type="text" name="pass" placeholder="PASSWORD를 입력하세요"></td>
			</tr>
			
			<tr>
				<td colspan="2"><input type="submit" value="Login"></td>
			</tr>
		</table>
	</form>
<%
	}else{
%>
		<h3><%= name %>님 반갑습니다.</h3>
		<a href='<%= request.getContextPath() %>/sessionLogout.do'>로그아웃</a>
<%
	}
%>

</body>
</html>
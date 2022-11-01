<%@page import="kr.or.ddit.basic.session.MymemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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

<%
	//JSP문서에서 세션은 'session'이라는 이름으로 이미 만들어져 있다.
	MymemberVO memVo = (MymemberVO)session.getAttribute("loginMember");	//세션 값 가져오기
%>

<body>
<%
	
	if(memVo == null){
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
		<h3><%= memVo.getMem_name() %>님 반갑습니다.</h3>
		<a href='<%= request.getContextPath() %>/sessionDBLogout.do'>로그아웃</a>
<%
	}
%>
</body>
</html>
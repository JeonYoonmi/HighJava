<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cookie 연습</title>
</head>
<!-- 
	'if 기억하기' 체크박스를 체크한 후  'Login'버튼을 누르면 입력했던 'ID'를 쿠키에 저장하고
	쿠키에 'ID'값이 저장되어있으면 'ID'입력창에 쿠키에 저장되어있던 'ID'가 나타나도록하고 체크박스도 채크가 된 상태로 유지되도록 한다. 
	
	체크박스가 해제된 상태에서 'Login'을 누르면 쿠키에 저장된 'ID'를 삭제하고 체크박스
	체크가 해제된 상태가 되도록 한다.
	
	이 사이트에 회원은 ID가 'test'이고 Password는 '1234'이다.
	로그인에 성동하면 'cookieMain.jsp'로 이동하고 실패하면 'cookieLogin.jsp'로 이동되도록한다. 
-->
<body>
<h3>cookie연습용 main페이지 입니다.</h3>
<a href="<%= request.getContextPath() %>/basic/cookie/cookieLogin.jsp">Login 창으로 이동</a>
</body>
</html>
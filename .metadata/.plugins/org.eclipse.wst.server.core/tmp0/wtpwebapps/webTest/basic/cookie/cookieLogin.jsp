<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cookie 연습</title>
<style>
label {
	display: inline-block;
	width: 50px;
	height: 30px;
}

input[type=submit] {
	margin-left: 80px;
}
</style>
</head>
<body>
	<%
		// 쿠키값으로 ID정보 받아오기
		String idvalue = "";
		String chk = "";		// 체크박스 체크용 변수
		Cookie[] cookieArr = request.getCookies();
		if(cookieArr != null){
			for (Cookie cookie : cookieArr) {
				if(cookie.getName().equals("userId")) {	// 내가 원하는 쿠키명을 찾아서 값 저장하기
					idvalue = cookie.getValue();
					chk = "checked";
				}
			}
		}
	%>
	<form action="<%= request.getContextPath() %>/cookieLoginServlet.do" method="post">
		<table style="margin: 0 auto;">
			<tr>
				<td><label>ID : </label><input type="text" placeholder="ID 입력하세요." name="id" value="<%= idvalue %>"></td>
			</tr>
			
			<tr>
				<td><label>PASS : </label><input type="text" placeholder="Password 입력하세요." name="pass" value=""></td>
			</tr>
			
			<tr>
				<td><input type="checkbox" value="check" name="remember" <%= chk %> >id 기억하기</td>
			</tr>
			
			<tr>
				<td><input type="submit" value="Login"></td>
			</tr>
		</table>
	</form>
</body>
</html>
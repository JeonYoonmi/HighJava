<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Setvlet 요청 연습</h2><hr>

<h3>GET방식 요청1 ==> 링크방식</h3>
<a href="http://localhost/webTest/servletTest03.do">Get방식 요청</a>
<hr>
<h3>GET방식 요청2 ==> Form의 submit 방식</h3>
<!-- 
	form태그의 method속성을 생략하거나 'GET'으로 설정하면 get방식으로 요청된다.
-->
<form action="http://localhost/webTest/servletTest03.do">
	<input type="submit" value="GET방식 요청2">
</form>
<hr>

<h3>POST방식 요청 ==> Form의 submit 방식</h3>
<!--  
	form태그의 method속성을 'post'로 설정하면 POST방식으로 요청된다.
-->
<form action="http://localhost/webTest/servletTest03.do" method="post">
	<input type="submit" value="POST방식 요청">
</form>

</body>
</html>
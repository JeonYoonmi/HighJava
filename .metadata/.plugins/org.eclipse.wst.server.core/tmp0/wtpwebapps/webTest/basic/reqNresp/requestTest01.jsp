<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Request객체 연습용 Form</title>
</head>
<body>
<!-- 
	<form>태그의 속성
	1) action => form에서 만들어진 데이터를 받아서 처리할 문서명 또는 서블릿 URL
	2) method => 전송방식(GET 또는 POST) => (기본값 : GET)
	3) enctype => 서버로 파일을 전송할 때 이 속성에  'multipart/form-data'라고 설정한다.
	4) target => from의 데이터를 처리한 응답이 나타날 'frame명'을 지정한다
-->

<%--
	이 영역은 JSP 주석 영역이다.
--%>

<%
	// 자바 주석(한줄)
	/*
		자바주석(여러줄)
	*/
	// 이 영역은 JSP문서에서 Java명령을 사용할 수 있는 영역으로 '스크립트릿'이라고 한다.
	String name = "홍길동";
%>

<%-- 
	<%= 변수명 또는 수식  %> ==> JSP에서 '변수'나 '수식'의 결과를 출력할 때 사용한다.
--%>

<h2> <%= name %> Request 연습용 Form ==> <%= 3 * 4 - 5 %></h2>

<!-- <form action="/webTest/requestTest01.do" method="get"> -->
<form action="<%= request.getContextPath() %>/requestTest01.do" method="post">	<!-- request : jsp에서는 자동으로 객체가 생성되어 있음, response도 -->
	<table border="1">
		<tr>
			<td>이름</td>
			<td><input type="text" name="username" size="10"></td>
		</tr>
		
		<tr>
			<td>직업</td>
			<td>
				<select name="job">
					<option value="무직">=무직=</option>
					<option value="회사원">=회사원=</option>
					<option value="전문직">=전문직=</option>
					<option value="학생">=학생=</option>
				</select>
			</td>
		</tr>
		
		<tr>
			<td>취미</td>
			<td>
				<input type="checkbox" name="hobby" value="여행">여행
				<input type="checkbox" name="hobby" value="독서">독서
				<input type="checkbox" name="hobby" value="게임">게임
				<input type="checkbox" name="hobby" value="테니스">테니스
				<input type="checkbox" name="hobby" value="배드민턴">배드민턴
			</td>
		</tr>
		
		<tr>
			<td colspan="2" style="text-align : center;">
				<input type="submit" value="전송">
				<input type="reset" value="취소">
			</td>
		</tr>
		

	</table>
</form>

</body>
</html>
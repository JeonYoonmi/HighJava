<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Request 연습</title>
</head>
<body>
	<hr>
	<h3>Request연습 Form(숫자 입력은 정수형으로 입력하세요.)</h3>
	<hr>
	<form action="<%= request.getContextPath() %>/requestTest02.do" method="get">
<!-- 		<input type="text" name="num1"> -->
<!-- 		<select name="op"> -->
<!-- 			<option value="+">+</option> -->
<!-- 			<option value="-">-</option> -->
<!-- 			<option value="*">*</option> -->
<!-- 			<option value="/">/</option> -->
<!-- 			<option value="%">%</option> -->
<!-- 		</select> -->
<!-- 		<input type="text" name="num2"> -->
<!-- 		<input type="submit" value="확인"> -->
		
		<table>
			<tr>
			<td><input type="text" size="10" name="num1"></td>
			<td>
				<select name="op">
					<option value="+">+</option>
					<option value="-">-</option>
					<option value="*">*</option>
					<option value="/">/</option>
					<option value="%">%</option>
				</select>
			</td>
			<td><input type="text" size="10" name="num2"></td>
			<td><input type="submit" value="확인"></td>
			</tr>
		</table>
	</form>
</body>
</html>
<%@page import="kr.or.ddit.basic.member.hw.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
tr:first-child{
	text-align: right;
}
</style>
</head>
<body>
<%
	List<MemberVO> memberList = (List<MemberVO>)request.getAttribute("memberList");
%>
<h3>회원 목록 보기</h3>
<form action="<%= request.getContextPath() %>/basic/memberHw/memberAdd.jsp">
<table border="1">
	<tr>
		<td colspan="5"><input type="submit" value="회원추가"></td>
	</tr>
	<tr>
		<th>ID</th>
		<th>비밀번호</th>
		<th>이름</th>
		<th>전화</th>
		<th>주소</th>
	</tr>
<%
	if(memberList == null || memberList.size() == 0){
%>
		<tr>
			<td colspan="5"> 회원이 하나도 없습니다. </td>
		</tr>
<%
	}else{
%>
<%
		for(MemberVO memVo : memberList){
%>
		<tr>
			<td><a href="<%= request.getContextPath() %>/basic/memberHw/memberInfo.jsp?memId=<%= memVo.getMem_id() %>"><%= memVo.getMem_id() %></a></td>
			<td><%= memVo.getMem_pass() %></td>
			<td><%= memVo.getMem_name() %></td>
			<td><%= memVo.getMem_tel() %></td>
			<td><%= memVo.getMem_addr() %></td>
		</tr>
<%
		}
	}
%>
</table>
</form>
</body>
</html>
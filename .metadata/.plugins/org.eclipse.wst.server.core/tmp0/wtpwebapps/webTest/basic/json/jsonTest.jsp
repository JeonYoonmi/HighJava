<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
$(function(){
	$('#strBtn').on('click', function(){
		$.ajax({
			url : "<%= request.getContextPath() %>/JSONTest.do",
			type : "post",
// 			data : "choice=str",
			data : { "choice" : "str" },
			success : function(data){
				$('#result').html(data);
				
			},
			dataType : "json"
			
		})
	})
	
	//-----------------------------------------------------------
      
      //배열 처리
      $('#arrBtn').on("click", function() {
         $.ajax({
            url : "<%=request.getContextPath()%>/JSONTest.do",
            type : "POST",
            data : "choice=array",
            success : function(data) {
               let html = "";
               $.each(data, function(i, v){
                  html += i + "번째 값 : " + v + "<br>";
               });
               $('#result').html(html);
            },
            error : function(xhr) {
               alert(xhr.status);
            },
            dataType : "json"
         });
      });
      //-----------------------------------------------------------
      
      //객체 처리; SampleVO.java파일 작성함
      $('#objBtn').on('click', function() {
         $.ajax({
            url : "<%=request.getContextPath()%>/JSONTest.do",
            type : "POST",
            data : "choice=obj",
            success : function(data) {
               //data = { "num" : 11, "name" : "홍길동", "addr" : "대전시 중구 오류동"}
               let html = "SampleVO객체의 값<br>";
               html += "번호 : " + data.num + "<br>";
               html += "이름 : " + data.name + "<br>";
               html += "주소 : " + data.addr + "<br>";
               $('#result').html(html);
            },
            error : function(xhr) {
               alert(xhr.status);
            },
            dataType : "json"
         });
      });
      //-----------------------------------------------------------
      
      //리스트 처리
      $('#listBtn').on('click', function() {
         $.ajax({
            url : "<%=request.getContextPath()%>/JSONTest.do",
            type : "POST",
            data : "choice=list",
            success : function(data) {
               /*
                  data = [
                     {"num" : 1, "name" : "일지매", "addr" : "대전"},
                     {"num" : 2, "name" : "이순신", "addr" : "광주"},
                     {"num" : 3, "name" : "성춘향", "addr" : "부산"},
                     {"num" : 4, "name" : "변학도", "addr" : "제주"}
                  ];
               */
               let html = "";
               $.each(data, function(i, v) {
                  html += i + "번째 데이터<br>";
                  html += "번호 : " + v.num + "<br>";
                  html += "이름 : " + v.name + "<br>";
                  html += "주소 : " + v.addr + "<hr>";
               });
               $('#result').html(html);
            },
            error : function(xhr) {
               alert(xhr.status);
            },
            dataType : "json"
         });
      });
      //-----------------------------------------------------------
      
	
	$('#mapBtn').on('click', function(){
		$.ajax({
			url : "<%= request.getContextPath() %>/JSONTest.do",
			type : "post",
			data : { "choice" : "map" },
			success : function(data){
				let html = "Map 데이터<br>";
				
				// data가 객체 또는 map이면 i변수는 객체의 변수명 또는 map의 key값이 기억되고,
				//		v변수에는 변수에 설정된 값 또는 Map의 value값이 저장된다.
				$.each(data, function(i, v){
					html += i + " : " + v + "<br>";
				})
				
				$('#result').html(html);
			},
			error : function(xhr){
				alert(xhr.status);
			},
			dataType : "json"
			
		})
	})
})
</script>
</head>
<body>
<form>
	<input type="button" id="strBtn" value="문자열">
	<input type="button" id="arrBtn" value="배  열">
	<input type="button" id="objBtn" value="객  체">
	<input type="button" id="listBtn" value="리스트">
	<input type="button" id="mapBtn" value="Map객체">
</form>
<hr>
<h3>응답 데이터 출력</h3>
<div id="result"></div>
</body>
</html>
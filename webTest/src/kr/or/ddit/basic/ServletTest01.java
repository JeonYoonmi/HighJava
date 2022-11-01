package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
	서블릿이란? ==> 컨테이너에 의헤 관리되는 자바기반 웹 컨포넌트로서 동적인 웹 컨텐츠, 생성을 가능하게하는 기술이다.
	
	URL주소 : http://localhost:80/webTest/servletTest01.do
	 - http 				==> 프로토콜
	 - localhost 			==> 컴퓨터이름(도메인명) 또는 IP주소
	 - :80 					==> 포트번호( 80번일 경우 생략 가능 )
	 - /webTest				==> 컨텍스트 패스(보통 프로잭트명'으로 설정된다.)
	 - /servletTest02.do	==> 서블릿 요청 URL
*/

// Servlet클래스는 HTTPServlet을 상속해서 작성한다.
// 이 예제는 배포서술자(Deployment Description, DD -> web.xml)를 이용해서 실행할 Servlet예제이다.
public class ServletTest01 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/*
		이 곳에서는 대부분 service()메서드 또는 doGet()메서드나 doPost()메서드를 재정의해서 작성한다.
		
		doGet()메서드나 doPost()메서드는 service()메서드에서 전송방식(GET, POST)에 따라 자동으로 호출된다.
		
		HttpServletRequest객체 ==> 서비스 요청에 관련괸 정보 밎 메서드를 관리하는 객체
		HttpServletResponse객체 ==> 서비스 응답에 관련된 정보 및 메서드를 관리하는 객체
	*/
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");	// 응답 문서의 인코딩 방식
		response.setContentType("text/html; charset=utf-8");	// 응답 문서의 ContentType 설정
		
		// 처리한 내용을 응답으로 보내기위한 스트림 객체(PrintWriter객체)를 생성한다
		PrintWriter out = response.getWriter();
		
		// 처리한 내용을 출력한다.
		//방법1)append()메서드 이용하기
		out.append("<!DOCTYPE html>")
			.append("<html>")
			.append("<head>")
			.append("<meta charset='utf-8'>")
			.append("</head>")
			.append("<body>")
			.append("<h2 style='text-align:center;'>")
			.append("안녕하세요 첫번째 서블릿 프로그램입니다.<br>")
			.append(" ContextPath : " + request.getContextPath()) 
			.append("</h2>")
			.append("</body>")
			.append("</html>");
	}
}

package kr.or.ddit.basic.cookie;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieAdd.do")
public class CookieAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// Cookie를 추가하는 방법
		
		// 1. Cookie객체를 생성한다. ==> '쿠키key값'과 '쿠키value값'은 문자열로 지정한다.
		// 형식) Cookie cookie변수 = new Cookie("쿠키key값", "쿠키value값");
		//		==> '쿠키Value값'으로 한글을 사용할 경우 URLEncoder.encode()메서드를 인코딩한 후 저장한다.
		Cookie nameCookie = new Cookie("name", URLEncoder.encode("홍길동", "utf-8"));
		int age = 30;
//		Cookie ageCookie = new Cookie("age" + age + "");
		Cookie ageCookie = new Cookie("age", String.valueOf(age));
		Cookie genderCookie = new Cookie("gender", "Mail");
		
		// 2. 쿠키 속성 설정하기
		// 1) 쿠키변수.setPath("적용경로"); ==> 지정한 경로외 그 하위 경로에서 사용가능하다.
		//							==> 생략하면 쿠키를 설정할 당시의 경로가 기본값으로 설정된다. 
		// 2) 쿠키변수.setMacAge(유지시간) : ==> 단위 : 초,
		//								==> -1 : 브라우저가 종료될 때까지 유지(기본값), 0 : 쿠키가 즉시삭제
		// 3) 쿠키변수.setDimain("적용도메인명");
		//					==> 예) "ddit.or.kr" ==> www.ddit.or.kr, www2.ddit.or.kr
		// 4) 쿠키변수.setSecure(보안여부); ==> true : 적용, false : 미적용
		
		//3. Response객체를 이용하여 쿠키를 웹브라우저로 보내면, 웹브라우저가 이 쿠키를 받아서 저장한다.
		// 형식) response객체변수.addCookie(1번에서 받은 Cookie변수)
		response.addCookie(nameCookie);
		response.addCookie(ageCookie);
		response.addCookie(genderCookie);
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>Cookie 연습</title></head>");
		out.println("<body>");
		out.println("<h3>Cookie 데이터가 저장되었습니다.</h3><br><hr>");
		out.println("<a href='" + request.getContextPath() + "/basic/cookie/cookieTest.jsp'>쿠키 시작문서로 가기</a>");
		out.println("</body></html>");
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

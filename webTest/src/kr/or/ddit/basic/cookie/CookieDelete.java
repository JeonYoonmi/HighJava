package kr.or.ddit.basic.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieDelete.do")
public class CookieDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// 쿠키 정보 삭제하기
		
		// 쿠키 정보의 삭제는 쿠키의 남은 수명을 0으로 설정하는 방법을 사용한다.
		// 쿠키의 수명은 쿠키를 저장하는 addCookie()메서드를 호출하기 전에 삭제하려는 쿠키의 setMaxAge()메서드를 사용해서 처리한다.
		// 형식) cookie객체변수.setMaxAge(시간)
		//		'시간'을 0으로 설정하면 바로 삭제되고 음수를 지정하면 웨브라우저가 닫힐 때 삭제된다.
		Cookie[] cookieArr = request.getCookies();		//전체 쿠키정보 가져오기
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>Cookie 연습</title></head>");
		out.println("<body>");
		out.println("<h3>저장된 Cookie 정보 삭제하기</h3>");
		
		if(cookieArr==null || cookieArr.length==0) {
			out.println("<h3>저장된 Cookie가 하나도 없습니다.</h3>");
		}else {
			// 쿠키 배열에서 삭제할 쿠키를 구한다. (보통 쿠키의 key값으로 확인한다.)
			for (Cookie cookie : cookieArr) {
				String key = cookie.getName();	// 쿠키의 key값을 저장한다.
				
				if("gender".equals(key)) {	// 삭제할 대상 찾기
					cookie.setMaxAge(0);	// 유지시간을 0으로 설정한다.
					response.addCookie(cookie);	// 유지시간을 0으로 설장한 쿠키를 저장한다.
				}
			}
		}
		out.println("<a href='" + request.getContextPath() + "/basic/cookie/cookieTest.jsp'>쿠키 시작문서로 가기</a>");
		out.println("</body></html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

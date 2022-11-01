package kr.or.ddit.basic.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieCountServlet.do")
public class CookieCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// 쿠키 key값 정하기 ==> "count"
		
		// 'count'라는 쿠키가 있는지 검사
		Cookie[] cookieArr = request.getCookies();
		
		int count = 0;
		
		if(cookieArr!= null) {
			for (Cookie cookie : cookieArr) {
				if("count".equals(cookie.getName())) {	// 'count쿠키 찾기
					String value = cookie.getValue();	// 현재의 count값 구하기
					count = Integer.parseInt(value);
					break;
				}
			}
		}
		
		count++;	// 카운트 증가
		
		// 증가된 값을 'count'라는 쿠키key값의 value값으로 저장한다.
		Cookie countCookie = new Cookie("count", String.valueOf(count));
		response.addCookie(countCookie);
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>Cookie 연습</title></head>");
		out.println("<body>");
		out.println("<h3>어서오세요. 당신은 " + count + "번 째 방문입니다.</h3>");
		
		out.println("<a href='" + request.getContextPath() + "/cookieCountServlet.do'>카운트 증가하기</a>");
		out.println("<a href='" + request.getContextPath() + "/basic/cookie/cookieTest02.jsp'>시작문서로 돌아가기</a>");
		out.println("</body></html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

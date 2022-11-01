package kr.or.ddit.basic.cookie;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieLoginServlet.do")
public class CookieLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		// userId, pass, check 정보 받아오기
		String userId = request.getParameter("id");
		String userPass = request.getParameter("pass");
		String check = request.getParameter("remember");
		
		// 쿠키 생성 ==> userId값을 갖는 쿠키 생성
		Cookie cookie = new Cookie("userId", userId);
		
		// 체크 박스의 체크 여부에 따라 쿠키 저장 또는 삭제 처리
		if(check == null) {
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}else {
			response.addCookie(cookie);
		}
		
		// userId와 pass를 이용해서 로그인 성공 여부 검사
		if(userId.equals("test") && userPass.equals("1234")) {
			response.sendRedirect(request.getContextPath() + "/basic/cookie/cookieMain.jsp");
		}else {
			response.sendRedirect(request.getContextPath() + "/basic/cookie/cookieLogin.jsp");
		}
		
}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

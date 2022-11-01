package kr.or.ddit.basic.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessionAdd.do")
public class SessionAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Session 정보 저장하기
		
		// 1. Session객체 생성하거나 현재 Session 가져오기
		// 형식1) Request객체.getSession(); 또는 Request객체.getSession(true);
		//		==> 현재 세젼이 존재하면 현재 세션을 반환하고, 존재하지 않으면 새로운 세션을 생성한다.
		// 형식2) Request객체.getSession(false);
		//		==> 현재 세션이 존재하면 현재 세션을 반환하고, 존재하지 않으면 새로운 세션을 생성하지 않고 null을 반환한다.
		// 주로 1번 방법을 사용
		HttpSession session = request.getSession();
		
		// 2. session객체의 setAttribute()메서드를 이용해서 저장한다.
		// 형식) session객체.setAttribute("key값", session값);
		//		=> 'key'값은 문자열, 'session'값은 모든 종류의 데이터를 사용할 수 있다.
		session.setAttribute("testSession", "연습용 세션입니다.");
		session.setAttribute("userName", "홍길동");
		session.setAttribute("age", 35);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html><head><title>session 연습</title></head>");
		out.println("<body>");
		out.println("<h3>Session데이터가 저장되었습니다.</h3><hr><br>");
		out.println("<a href='" + request.getContextPath() + "/basic/session/sessionTest.jsp'>시작문서로 이동하기</a>");
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

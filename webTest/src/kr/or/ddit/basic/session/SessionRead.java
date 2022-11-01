package kr.or.ddit.basic.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessionRead.do")
public class SessionRead extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// 저장된 Session 읽어오기
		
		// 1. Session객체를 생성하거나 현재 Session가져오기
		HttpSession session = request.getSession();
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>Session 연습</title></head>");
		out.println("<body>");
		out.println("<h3>저장된 Session정보 확인하기</h3><hr>");
		
		out.println("<h3>세션 데이터 1개 확인하기</h3>");
		
		// 2. session객체의 getAttribute()메서드를 이용하여 저장된 Session값을 읽어온다.
		// 형식) session객체.getAttribute("key값");
		String sessionValue = (String) session.getAttribute("testSession");
		
		if(sessionValue==null) {
			out.println("<h3>testSession의 세션값이 없습니다.</h3>");
		}else {
			out.println("<h3>testSession 세션값 : " + sessionValue + "</h3>");
		}
		
		out.println("<hr>");
		out.println("<h3>전체 세션 데이터 확인하기</h3>");
		out.println("<ol>");
		
		// Session의 전체 key값을 가져오는 메서드 ==> session객체의 getAttributeNames()메서드 이용
		Enumeration<String> sessionNames = session.getAttributeNames();	//Enumeration : iterator와 비슷
		
		int cnt = 0;
		while(sessionNames.hasMoreElements()) {
			cnt++;
			String sessionKey = sessionNames.nextElement();
			out.println("<li>" + session.getAttribute(sessionKey) + "</li>");
		}
		if(cnt==0) {
			out.println("세션 데이터가 하나도 없습니다.");
		}
		out.println("</ol>");
		
		out.println("<hr>");
		
		// 세션ID	==> 세션을 구분하기 위한 고유의 값
		out.println("세션ID" + session.getId() + "<br>");
		
		// 생성시간	==> 1970년 1월 1일부터 경과된 시간(밀리세컨드 단위)
		out.println("세션 생성 시간 : " + session.getCreationTime() + "<br>");
		out.println("세션 최근 접근시간 : " + session.getLastAccessedTime() + "<br>");
		
		//세션 유효시간 ==> 단위(초) ==> 세션이 유지되는 시간을 말한다.
		//		유효시간 결정은 sessoion.setMaxInactiveInterval(초단위 시간);
		out.println("세션 유효시간 : " + session.getMaxInactiveInterval() + "<br>");
		
		out.println("<a href='" + request.getContextPath() + "/basic/session/sessionTest.jsp'>시작문서로 이동하기</a>");
		out.println("</body></html>");
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

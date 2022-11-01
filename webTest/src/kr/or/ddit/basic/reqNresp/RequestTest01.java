package kr.or.ddit.basic.reqNresp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/requestTest01.do")
public class RequestTest01 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// POST방식으로 전달되는 데이터의 인코딩 방식을 설정한다.
		request.setCharacterEncoding("utf-8");
		
		// 클라이언트에서 보낸 데이터를 받아서 처리하기 (Request객체에 관련 데이터가 저장되어 온다.)
		
		// request.getParameter("파라미터명");
		//		==> 해당 '파라미터명'에 설정된 '값'을 가져온다.
		//		==> 이 때 가져오는 '값'의 자료형은 'String'형
		String userName = request.getParameter("username");
		String job = request.getParameter("job");
		
		// request객체.getParameterValues("파라미터명");
		//		==> 해당 '파라미터명'이 같은 것이 여러개 일 경우에 사용한다.
		//		==> 이 때 가져오는 '값'의 자료형은 'String[]'이다.
		String[] hobbies = request.getParameterValues("hobby");
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html><head><meta charset='utf-8'>");
		out.println("<title>Request객체 연습</title></head>");
		out.println("<body>");
		out.println("<h3>request 테스트 결과 - 1<h3>");
		out.println("<hr>");
		out.println("<table border='1'");
		out.println("<tr><td>이름</td>");
		out.println("<td>" + userName + "</td></tr>");
		
		out.println("<tr><td>직업</td>");
		out.println("<td>" + job + "</td></tr>");
		
		out.print("<tr><td>취미</td>");
		out.print("<td>");
		if(hobbies!=null) {
			// 배열 크기만큼 루프를 돌면서 값을 출력한다.
			for(int i=0; i<hobbies.length; i++) {
				out.println(hobbies[i] + "<br>");
			}
			out.println("<hr>");
			
			// 향상된 for문 사용
			for(String h : hobbies) {
				out.println(h + "<br>");
			}
		}
		out.println("</td></tr>");
		out.println("</table>");
		out.println("<hr>");
		
		out.println("<h3>Request테스트 결과 - 2</h3>");
		out.println("<ul>");
		out.println("<li>클라이언트의 IP주소 : " + request.getRemoteAddr() + "</li>");
		out.println("<li>요청 메서드 : " + request.getMethod() + "</li>");
		out.println("<li>ContextPath : " + request.getContextPath() + "</li>");
		out.println("<li>프로토콜 : " + request.getProtocol() + "</li>");
		out.println("<li>URL정보 : " + request.getRequestURI() + "</li>");
		out.println("<li>URL정보 : " + request.getRequestURL() + "</li>");
		out.println("</ul>");
		out.println("<hr>");
		
		// request객체.getParameterNames() ==> 전송된 모든 파라미터명을
		//			Enumeration<String>객체에 담아서 반환한다.
		Enumeration<String> params = request.getParameterNames();
		out.println("<h3>request 테스트 결과 - getParameterNames()메서드</h3>");
		out.println("<ol>");
		while(params.hasMoreElements()) {
			String paramName = params.nextElement();
			out.println("<li>" + paramName + "</li>");
		}
		out.println("</ol>");
		
		// request객체.getParameterMap() ==> 전송된 모든 파라미터를 Map에 담아서 반환한다.
		//		이 Map객체의 Key값은 '파라미터명'이고 자료형은 'String'형이다.
		//			그라고 Value값은 해당 파라미터에 지정된 값이 되고 자료형 'String[]'형이다.
		
		Map<String, String[]> paramMap = request.getParameterMap();
		out.println("<table border='1'>");
		out.println("<tr><td colspan='2'> getParameterMap()메서드 내용</td></tr>");
		out.println("<tr><td>파라미터명</td><td>파라미터 value값</td></tr>");
		
		for (String paramName : paramMap.keySet()) {
			out.println("<tr><td>" + paramName + "</td>");
			out.println("<td>");
			
			String[] paramValue = paramMap.get(paramName);	// 파라미터 value값 구하기
			
			if(paramValue==null || paramValue.length == 0) {	// 파라미터 값이 없는 경우
				continue;
			}else if(paramValue.length ==1) {	//같은 파라미터명이 한개인 경우
				out.println(paramValue[0]);
			}else {	// 같은 파라미터명이 여러개인 경우
				for(int i=0; i<paramValue.length; i++) {
					out.println(paramValue[i] + "<br>");
				}
			}
			out.println("</td></tr>");
		}
		out.println("</table>");
		out.println("<hr>");
		
		out.println("</body></html>");
		out.println("<hr>");
		
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);	//처음 만들 때 자동으로 생기는데 이게 있으면 어떤 방식이든 doGet()으로 실행된다.
	}

}

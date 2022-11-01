package kr.or.ddit.basic.reqNresp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/redirectTest.do")
public class RedirectTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String userName = request.getParameter("user");	// 파라미터 값 구하기
		String tel = request.getParameter("tel");
		
		// 이전 형식 문서에서 setAttribute()로 보낸 데이터는 getAttribute()메서드로 받는다.
		// 형식) request객체.getAttribute("키값");
		
//		String tel = (String)request.getAttribute("tel");
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charser=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html><head><meta charset='utf-8'>");
		out.println("<title>Redirect방식 연습</title></head>");
		out.println("<body>");
		out.println("<h3>Redirect 방식으로 넘어온 데이터 출력하기</h3><hr>");
		out.println("이름 : " + userName + "<br>");
		out.println("전화번호 : " + tel + "<br>");
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

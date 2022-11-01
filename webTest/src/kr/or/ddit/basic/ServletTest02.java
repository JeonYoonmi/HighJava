package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
	이 예제는 애노테이션(@WebServlet)을 이용하여 Servlet을 설정하여 처리하는 예제이다.
	@WebServlet 애노테이션은 Servlet버전 3.0이상에서 사용할 수 있다.
	
	 - @WebServlet 애노테이션의 속성들
	 1) name : 서블릿 이름을 설정한다.(시본값 : 빈문자열(""))
	 2) urlPatterns : 서블릿의 URL패턴 목록을 설정한다. (기본값 : 빈배열({}))
	 		예) urlPatterns="/url1" 또는 urlPattern={"/url1"} ==> 패턴이 한개일 경우
	 		예) urlPattern={"/url1", "/url2", ....}		==> 패턴이 2개 이상일 경우
	 3) value : urlPatterns와 동일하다.
	 4) description : 주석(설명글)을 설정한다.
*/
@WebServlet(
		urlPatterns = {"/servletTest02.ddit"}, description = "애노태이션을 이용한 서블릿 연습"
)
public class ServletTest02 extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	//doGet()메서드 ==> GET방식으로 요청할 때 처리되는 메서드
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");	// 응답 문서의 인코딩 방식
		response.setContentType("text/html; charset=utf-8");	// 응답 문서의 ContentType 설정
		
		// 처리한 내용을 응답으로 보내기위한 스트림 객체(PrintWriter객체)를 생성한다
		PrintWriter out = response.getWriter();
		
		
		// 처리한 내용을 출력한다.
		// 방법2) print()메서드 또는 println()메서드 이용하기
		out.println("<!DOCTYPE html>");
		out.println("<html><head><meta charset='utf-8'>");
		out.println("<title>두번쨰 서블릿</title></head>");
		out.println("<body>");
		out.println("<h2 style='text-align:center; color:blue;'>");
		out.println("두번째 Servlet입니다. (@WebServlet애노테이션을 이용한 서블릿)");
		out.println("</h2>");
		out.println("</body></html>");
	}
	
	//doPost()메서드 ==> POST방식으로 요청할때 처리되는 메서드
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(request, response);
	}
}

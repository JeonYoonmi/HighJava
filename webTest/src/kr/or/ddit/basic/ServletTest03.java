package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Servlet의 LifeCycle예제
@WebServlet("/servletTest03.do")
public class ServletTest03 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		System.out.println("Servlet : " + this.getServletName() + "에서 init()메서드 호출...");
	}
	
	@Override
	public void destroy() {
		System.out.println("Servlet : " + this.getServletName() + "에서 destroy()메서드 호출...");
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("service() 메서드 시작");
		
		//GET방식과 POST방식에 맞는 메서드 호출하기
		
		//방법1 ==> HttpServlet(부모클래스)의 servlet()메서드 위임하기
//		super.service(request, response);	//service에 들어와서 get, post방식을 확인해서 doGet, doPost로 넘어가는 부분
		
		//방법2 ==> 클라이언트의 전송방식(GET, POST등)을 구분해서 직접 메서드 호출하기
		String method = request.getMethod();	//request.getMethod() : 전송방식을 알 수 있는 메서드
		System.out.println("전송방식 : " + request.getMethod());
		
		if("GET".equals(method)) {
			this.doGet(request, response);	//this는 붙여도 되고 안붙여도 된다.
		}else if("POST".equals(method)) {	//실제로는 post, get방식만 있는것이 아니기 때문에 else if를 사용해야 한다.
			this.doPost(request, response);
		}
		
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet()메서드 시작");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html><head><meta chatset='utf-8'></head>"
				+ "<body><h2>doGet()메서드 처리 결과입니다.</h2></body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet()메서드 시작");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html><head><meta chatset='utf-8'></head>"
				+ "<body><h2>doPost()메서드 처리 결과입니다.</h2></body></html>");
	}

}

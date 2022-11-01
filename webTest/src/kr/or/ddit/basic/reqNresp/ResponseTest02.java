package kr.or.ddit.basic.reqNresp;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/responseTest02.do")
public class ResponseTest02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		- redirect 방식
			현재 페이지에서 다른 페이지로 제어를 넘길 때 사용한다. (이전 페이지에서 사용하던 파라미터를 직접 넘길 수 없다.)
			응답시 브라우저에게 '이동할 URL'을 전송하여 브라우저가 해당 URL로 이동시키는 방식이다.
			이 때 요청시 get방식으로 이루어진다.
			
			redirect방식은 이전에 사용하던 request 객체를 유지하지 못한다. (브라우저가 새롭게 요청되기 때문에..)
		*/
		
		//redirect는 response객체의 sendRedirect()메서드를 이용한다.
		// 형식) request객체.sendRedirect("이동할 URL");
		//		'이동할 URL' ==> 전체 URL을 모두 기술한다.
		
		String userName = request.getParameter("username");
		String tel = "010-9999-8888";
		
		// 이동할 페이지로 현재 페이지의 데이터를 보내려면 GET방식으로 구성해서 보내면 된다.
		response.sendRedirect(request.getContextPath() + "/redirectTest.do?user=" + userName
												+ "&tel=" + tel);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

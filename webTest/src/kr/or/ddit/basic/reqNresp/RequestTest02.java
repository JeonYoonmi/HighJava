package kr.or.ddit.basic.reqNresp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/requestTest02.do")
public class RequestTest02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String strNum1 = request.getParameter("num1");	// 첫번째 숫자
		String op = request.getParameter("op");		// 연산자
		String strNum2 = request.getParameter("num2");	// 두번째 숫자
		
		int num1 = Integer.parseInt(strNum1);
		int num2 = Integer.parseInt(strNum2);
		
		double result = 0;	// 계산된 결과가 저장될 변수
		boolean calcOk = true;	// 계산 성공 여부가 저장될 변수 ( 계산성공 : true, 실패 : false)
		
		switch(op) {
		case "+" : result = num1 + num2; break;
		case "-" : result = num1 - num2; break;
		case "*" : result = num1 * num2; break;
		case "/" : 
			if(num2!=0) {
				result = (double)num1 / num2; 
			}else {
				calcOk = false;
			}
			break;
		case "%" :
			if(num2!=0) {
				result = num1 % num2; 
			}else {
				calcOk = false;
			}
			break;
		}

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html><head><meta charset='utf-8'>");
		out.println("<title>Request객체 연습 - 계산기</title></head>");
		out.println("<body>");
		out.println("<hr>");
		out.println("<h3>계산 결과</h3><hr>");
		out.printf("%d %s %d = ", num1, op, num2);
		if(calcOk == true) {
			out.println(result);
		}else {
			out.println("계산 불능(0으로 나누기)");
		}
//		out.println("<p>" + num1 + " " + op + " " + num2 + " = " + result + "</p>");
		out.println("</body></html>");
		
	}

}

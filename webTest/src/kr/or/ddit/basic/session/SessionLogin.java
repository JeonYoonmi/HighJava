package kr.or.ddit.basic.session;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/sessionLogin.do")
public class SessionLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String userId = request.getParameter("id");
		String userPass = request.getParameter("pass");
		
		if(userId.equals("admin") && userPass.equals("1234")) {
			HttpSession session = request.getSession();
			
			session.setAttribute("userid", userId);
			session.setAttribute("pass", userPass);
			
			out.println("<html><head><meta charset='utf-8'><title>로그아웃</title></head>");
	         out.println("<body><h3>" + userId + "님 반갑습니다.</h3>");
	         out.println("<a href='" + request.getContextPath() +"/sessionLogout.do'>로그아웃</a>");
	         out.println("</body></html>");
		}else {
			response.sendRedirect(request.getContextPath() + "/basic/session/sessionLogin.jsp");
		}
		
		
		
//		IMymemberService service = MymemberServiceImpl.getInstance();
		
//		String userId = request.getParameter("id");
//		String userPass = request.getParameter("pass");
		
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("id", userId);
//		map.put("pass", userPass);
//		
//		String res = service.selectName(map);
//		HttpSession session = request.getSession();
//		
//		if(res != null) {	// 로그인 성공
//			session.setAttribute("name", res);
//		}
//		response.sendRedirect(request.getContextPath() + "/basic/session/sessionLogin.jsp");
//		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

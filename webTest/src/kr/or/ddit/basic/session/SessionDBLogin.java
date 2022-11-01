package kr.or.ddit.basic.session;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessionDBLogin.do")
public class SessionDBLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//GET방식으로 요청하면 무조건 sessionLoginDBMain.jsp 이용하기
		
		request.getRequestDispatcher("/basic/session2/sessionLoginForm.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// POST방식으로 요청하면 로그인을 검증하는 작업을 수행한다.
		request.setCharacterEncoding("utf-8");
		
		// 로그인에 입력한 ID와 Password를 구한다.
		String mem_id = request.getParameter("id");
		String mem_pass = request.getParameter("pass");
		
		//id와 password를 VO객체에 저장한다.
		MymemberVO memVo = new MymemberVO();
		memVo.setMem_id(mem_id);
		memVo.setMem_pass(mem_pass);
		
		//해당 회원정보가 DB에 있는지 확인
		IMymemberService service = MymemberServiceImpl.getInstance();
		MymemberVO loginMemberVo = service.getLoginMember(memVo);
		System.out.println(loginMemberVo);
		//로그인에 성공하면 해당 회원 정보를 session에 저장한다.
		HttpSession session = request.getSession();
		
		if(loginMemberVo != null) {
			session.setAttribute("loginMember", loginMemberVo);
		}
		
		request.getRequestDispatcher("/basic/session2/sessionLoginForm.jsp").forward(request, response);
	}

}

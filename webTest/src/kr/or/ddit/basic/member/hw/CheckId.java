package kr.or.ddit.basic.member.hw;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/checkId.do")
public class CheckId extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		response.setCharacterEncoding("utf-8");
		
		response.setContentType("application/json; charset=utf-8");
		
		String memId = request.getParameter("id");
		
		IMemberService service = MemberServiceImpl.getInstance();
		
		String result = service.checkId(memId);
		
		Gson gson = new Gson();
		
		String res = "";
		if(result!=null) {
			res = "사용 불가";
		}else {
			res = "사용 가능";
		}
		
		String jsonData = gson.toJson(res);
		
		PrintWriter out = response.getWriter();
		out.write(jsonData);
		
		response.flushBuffer();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

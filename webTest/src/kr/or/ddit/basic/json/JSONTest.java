package kr.or.ddit.basic.json;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/JSONTest.do")
public class JSONTest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		response.setCharacterEncoding("utf-8");
		// JSON데이터로 응답할 때의 Content-Type 설정방법
		response.setContentType("application/json; charset=utf-8");
		
		// 클라이언트에서 보내온 데이터를 받는다.
		String choice = request.getParameter("choice");
		
		// 응답에 필요한 데이터를 만든다. (DB에서 가져오거나, 임의로 만들거나...)
		Gson gson = new Gson();
		
		String jsonData = null;		// JSON문자열이 저장될 변수 선언
		
		switch(choice) {
		case "str":
			String str = "안녕하세요";	//응답용 데이터 만들기
			
			//응답용 데이터를 JSON문자열로 변환한다.
			jsonData = gson.toJson(str);
			
			break;
			
		case "array" :
	         int arr[] = {100, 200, 300, 400, 500};
	         jsonData = gson.toJson(arr);
	         break;
	         
		 case "obj" :
			 SampleVO sample = new SampleVO(11, "홍길동", "대전시 중구 오류동");
			     jsonData = gson.toJson(sample);
		     break;
		     
		  case "list" :
			 ArrayList<SampleVO> samList = new ArrayList<SampleVO>();
			 samList.add(new SampleVO(1, "일지매", "대전"));
			 samList.add(new SampleVO(2, "이순신", "광주"));
			 samList.add(new SampleVO(3, "성춘향", "부산"));
			 samList.add(new SampleVO(4, "변학도", "제주"));
			 jsonData = gson.toJson(samList);
		 break;
			
		case "map":
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("name", "을지문덕");
			map.put("tel", "010-1234-5678");
			map.put("addr", "포항");
			
			jsonData = gson.toJson(map);
			break;
		}
		
		System.out.println("JSON문자열 : " + jsonData);
		
		//변환된 JSON문자열을 응답데이터로 전송한다.
		PrintWriter out = response.getWriter();
		
		out.write(jsonData);
		
		response.flushBuffer();
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

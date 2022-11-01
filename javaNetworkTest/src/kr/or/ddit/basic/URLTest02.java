package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class URLTest02 {

	public static void main(String[] args) throws IOException {
		// URLConnection 클래스 => 애플리케이션과 URL간의 통신 연결을 위한 클래스
		//애플리케이션 : 현재 프로그램
		
		// 특정 서버의 정보와 파일 내용을 가져와 출력하는 연습
		URL url = new URL("https://www.naver.com/index.html");
		
		// URLConnection객체 구하기
		URLConnection urlCon = url.openConnection();
		
		// Header 정보 가져오기
		Map<String, List<String>> headerMap = urlCon.getHeaderFields();
		
		// Header 정보 출력하기
		for (String headerKey : headerMap.keySet()) {
			System.out.println(headerKey + " : " + headerMap.get(headerKey));
		}
		System.out.println("--------------------------------------------------------------------");
		
		// 해당 URL에 설정된 문서를 읽어와 출력하기 (즉 index.html문서의 내용을 읽어온다.)
		
		/*
		// 방법1 => URLConnection객체를 이용하는 방법
		InputStream is = urlCon.getInputStream();	//URLConnection 객체를 이용하여 입력용 스트림 객체 구하기
		
		//바이트 단위의 스트림이기 때문에 문자로 바꿔준다
		InputStreamReader isr = new InputStreamReader(is, "utf-8");
		BufferedReader br = new BufferedReader(isr);
		
		// 자료를 읽어와 출력하기
		while(true) {
			String str = br.readLine();	//줄 단위로 자료 읽기
			if(str == null) {	//읽어온 자료가 마지막인지 검사
				break;
			}
			
			System.out.println(str);	//읽어온 자료 출력하기
		}
		
		br.close();
		*/
		
		// 방법2 => URL객체의 openStream()메서드 이용하기 
		InputStream is2 = url.openStream();
		
		BufferedReader br2 = new BufferedReader(new InputStreamReader(/*url.openStream()*/is2, "utf-8"));
		
	}

}

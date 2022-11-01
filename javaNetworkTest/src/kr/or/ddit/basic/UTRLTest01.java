package kr.or.ddit.basic;

import java.net.MalformedURLException;
import java.net.URL;

public class UTRLTest01 {

	public static void main(String[] args) throws MalformedURLException {
		// URL클래스 => 인터넷에 존재하는 서버들의 자원에 접근할 수 있는 주소를 다루는 클래스
		// 예) http://ddit.or.kr:80/index.php?ttt=123
		//	프로토콜://호스트명:포트번호/경로명/파일명?쿼리스트링#참조
		
		URL url1 = new URL("http://ddit.or.kr:80/index.php?ttt=123");
		URL url2 = new URL("http", "ddit.or.kr", 80, "index.php?ttt=123");
		
		System.out.println("Protocol : " + url2.getProtocol());
		System.out.println("Host : " + url2.getHost());
		System.out.println("Port : " + url2.getPort());
		System.out.println("Path : " + url2.getPath());
		System.out.println("File : " + url2.getFile());
		System.out.println("Query : " + url2.getQuery());

		System.out.println(url2.toExternalForm());
	}

}

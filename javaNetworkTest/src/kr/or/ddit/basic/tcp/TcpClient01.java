package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient01 {

	public static void main(String[] args) throws UnknownHostException, IOException {
		//현재 자신의 컴퓨터를 나타내는 방법
		// 1) 원래의 IP주소 : 예) 192.168.146.16
		// 2) 지정된 IP주소 : 127.0.0.1
		// 3) 원래의 컴퓨터 이름 : 예) pc16
		// 4) 지정된 컴퓨터 이름 : localhost
		
		// 서버의 IP주소와 Port번호를 지정하여 Socket객체를 생성한다.
		// Socket객체는 생성이 완료되면 자동으로 해덩 서버로 연결 요청을 보낸다.
		Socket socket = new Socket("localhost", 7777);
		
		// 이 Socket객체를 생성하는 명령 이후의 내용은 서버와 연결된 후에 실행되는 부분이다.
		System.out.println("서버와 연결되었습니다...");
		System.out.println();
		
		
		//연습용이라 출력 원래 필요 없음
		//접속한 상대방에 대한 정보 출력하기
		System.out.println("접속한 상대방(서버) 정보");
		System.out.println("IP주소 : " + socket.getInetAddress().getHostAddress());
		System.out.println("Port번호 : " + socket.getPort());
		System.out.println();
		
		// 햔재 컴에 대한 정보 출력하기
		System.out.println("연결된 현재 컴(클라이언트) 정보");
		System.out.println("IP주소 : " + socket.getLocalAddress());
		System.out.println("Port번호 : " + socket.getLocalPort());
		System.out.println();
		
		//서버에서 보내온 메세지를 받아서 화면에 출력하기
		//		=> Socket객체의 InputStream객체를 이용하여 데이터를 수신받는다.
		//			(Socket의 getInputStream()메서드를 이용하여 InputStream객체를 구할 수 있다.)
		InputStream in = socket.getInputStream();
		DataInputStream din = new DataInputStream(in);
		
		//메세지 받아서 출력하기
		System.out.println("서버에서 보내온 메세지 : " + din.readUTF());
		System.out.println();
		
		System.out.println("연결을 종료합니다...");
		
		//소켓과 스트림 닫기
		din.close();
		socket.close();
		
	}
	
}

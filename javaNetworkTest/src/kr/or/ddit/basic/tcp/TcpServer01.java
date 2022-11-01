package kr.or.ddit.basic.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer01 {

	public static void main(String[] args) throws IOException {
		
		// TCP소켓 통신을 하기 위해 ServerSocket객체를 생성한다.
		ServerSocket server = new ServerSocket(7777);
		System.out.println("접속을 기다립니다...");
		
		// accept()메서드
		//		=> 클라이언트에서 연결요청이 올 때까지 계속 기다린다.
		//		=> 연결 요청이 오면 새로운 Socket객체를 생성해서 클라이언트의 Socket과 연결하여 반환한다.
		Socket socket = server.accept();
		
		//accept()메서드 이후의 소스는 연결이 완료되어야만 실행되는 부분이다.
		System.out.println("클라이언트와 연결되었습니다...");
		System.out.println();
		
		//접속한 상대방에 대한 정보 출력하기
		System.out.println("접속한 상대방(클라이언트) 정보");
		System.out.println("IP주소 : " + socket.getInetAddress().getHostAddress());
		System.out.println("Port번호 : " + socket.getPort());
		System.out.println();
		
		// 햔재 컴에 대한 정보 출력하기
		System.out.println("연결된 현재 컴(서버) 정보");
		System.out.println("IP주소 : " + socket.getLocalAddress());
		System.out.println("Port번호 : " + socket.getLocalPort());
		System.out.println();
		
		// 클라이언트에게 메세지 보내기
		//		=> Socket의 outputStream객체를 이용해서 데이터를 전송한다.
		//			(Socket의 getOutputStream()메서드를 이용해서 OutputStream객체를 구할 수 있다.
		OutputStream out = socket.getOutputStream();
		DataOutputStream dout = new DataOutputStream(out);
		
		//메세지 보내기
		dout.writeUTF("어서오세요. 환영합니다...");
		System.out.println("메세지를 보냈습니다.");
		
		//소켓과 스트림 닫기
		dout.close();
		socket.close();
		server.close();
		
	}
}

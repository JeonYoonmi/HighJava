package kr.or.ddit.basic.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer02 {
	public static void main(String[] args) throws IOException {
		//ServerSocket객체를 생성하고, 클라이언트가 접속해오면 연결된 Socket을
		//메세지를 받는 쓰레드와 메세지를 보내는 쓰레드에 주입한다.
		ServerSocket server = new ServerSocket(7777);
		System.out.println("서버가 준비 중입니다...");
		System.out.println();
		
		Socket socket = server.accept();	//클라이언트의 접속을 기다린다.
		
		//접속이 되면 연결된 Socket객체를 주입한 쓰레드를 생성하여 실행한다.
		Sender sender = new Sender(socket);
		Receiver receiver = new Receiver(socket);
		
		sender.start();
		receiver.start();
	}
}

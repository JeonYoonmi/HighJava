package kr.or.ddit.basic.tcp;

import java.net.Socket;

public class TcpClient02 {

	public static void main(String[] args) {
		//Socket객체를 생성하여 서버에 연결 요청을 보내고 연결이 완료되면 이 Socket을
		//메세지를 받는 쓰레드와 메세지를 보내는 쓰레드에 주입한다.
		try {
			Socket socket = new Socket("192.168.146.18", 7777);
			System.out.println("서버에 연결되었습니다...");
			System.out.println();
			
			Sender sender = new Sender(socket);
			Receiver receiver = new Receiver(socket);
			
			sender.start();
			receiver.start();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}

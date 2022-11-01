package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TcpMultiChatClient {
	
	public void clientStart() {
		Socket socket = null;
		try {
			socket = new Socket("192.168.146.15", 7777);
			System.out.println("서버에 연결되었습니다...");
			//--------------------------------------
			
			//전송용 쓰레드와 수신용 쓰레드를 생성하고 실행한다.
			ClientSender sender = new ClientSender(socket);
			ClientReciver reciver = new ClientReciver(socket);
			
			sender.start();
			reciver.start();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}// clientStart()메서드 끝...
	
	
	public static void main(String[] args) {
		new TcpMultiChatClient().clientStart();
	}

	//-------------------------------------------
	// 메세지 전송용 쓰레드
	class ClientSender extends Thread{
		private Socket socket;
		private DataOutputStream dout;
		private DataInputStream din;
		
		private String name;	//대화명이 저장될 변수
		private Scanner scan;
		
		//생성자
		public ClientSender(Socket socket) {
			this.socket = socket;
			scan = new Scanner(System.in);
			
			try {
				din = new DataInputStream(this.socket.getInputStream());	//수신용
				dout = new DataOutputStream(this.socket.getOutputStream());	//송신용
				
				if(dout!=null) {
					do {
						//클라이언트용 프로그램은 처음 실행하면 서버에 접속하고 접속에 성공하면
						//첫번째로 '대화명'을 입력받아 전송하고, '대화명'의 중복여부를 응답으로 받아서 확인한다.
						System.out.print("대화명 : ");
						String name = scan.nextLine();	//대화명 입력
						dout.writeUTF(name);	//대화명 전송
						
						//대화명의 중복 여부를 응답으로 받는다.
						String feedBack = din.readUTF();
						
						if("대화명중복".equals(feedBack)) {	//대화명이 중복될 때...
							System.out.println(name + "은 이미 있는 대화명입니다...");
							System.out.println("다른 대화명을 입력하세요...");
						}else {	//대화명이 중복되지 않을 때...
							this.name = name;
							System.out.println("[" + name + "] 대화명으로 대화방에 입장했습니다...");
							break;	//반복문 탈출
						}
					}while(true);
					
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}//생성자 끝...
		
		@Override
		public void run() {
			try {
				while(dout != null) {
					//키보드로 입력한 메세지를 서버로 전송한다.
					dout.writeUTF("[" + name + "] " + scan.nextLine());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			
		}
	}//전송용 쓰레드 끝...
	//----------------------------------------------------
	
	//메세지 수신용 쓰레드
	class ClientReciver extends Thread{
		private Socket socket;
		private DataInputStream din;
		
		//생성자
		public ClientReciver(Socket socket) {
			this.socket = socket;
			try {
				din = new DataInputStream(this.socket.getInputStream());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}//생성자 끝...
		
		@Override
		public void run() {
			try {
				while(din!=null) {
					//서버로부터 받은 메세지를 화면에 출력한다.
					System.out.println(din.readUTF());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		
	}
	
	
	
}

package kr.or.ddit.basic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TcpMultiChatServer {
	// 접속한 클라이언트 정보를 저장할 Map객체변수 선언
	//		=> key값 : 대화명, value값 : 접속한 클라이언트의 Socket객체
	private Map<String, Socket> clientMap;
	
	//생성자
	public TcpMultiChatServer() {
		// clientMap을 동기화 처리가 되도록 생성한다.
		clientMap = Collections.synchronizedMap(new HashMap<String, Socket>());
	}
	
	public void serverStart() {
		ServerSocket server = null;
		Socket socket = null;
		
		try {
			server = new ServerSocket(7777);
			System.out.println("서버가 시작되었습니다...");
			
			while(true) {	//여러개의 클라이언트가 접속할 수 있도록 반복문으로 처리한다.
				socket = server.accept();	//클라이언트의 접속을 기다린다...
				System.out.println("[" + socket.getInetAddress() + " : " 
							+ socket.getPort() + "]에서 접속했습니다...");
				System.out.println();
				//-----------------------------
				
				//쓰레드 객체 생성 밎 실행
				ServerReceiver serverThread = new ServerReceiver(socket);
				serverThread.start();
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}// ServerStart()메서드 끝...
		
	// clientMap에 저장된 전체 사용자에게 메세지를 전송하는 메서드
	private void sendToAll(String msg) {
		//clientMap의 데이처 개수만큼 반복
		for(String name : clientMap.keySet()) {
			try {
				//Key값(대화명)에 대응하는 Socket객페의 출력용 스트림 객체를 사용한다.
				DataOutputStream dout = new DataOutputStream(clientMap.get(name).getOutputStream());
				dout.writeUTF(msg);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}//sendToAll()메서드 끝...
	
	public static void main(String[] args) {
		new TcpMultiChatServer().serverStart();
	}

	//------------------------------------------
	
	// 서버에서 클라이언트로 메세지를 전송하는 Thread를 Inner Class로 작성한다.
	//		=> 이유 : Outer class의 멤버변수(clientMap)를 자유롭게 사용하기 위해서
	class ServerReceiver extends Thread{
		private Socket socket;
		private DataInputStream din;
		private DataOutputStream dout;
		
		//생성자
		public ServerReceiver(Socket socket) {
			this.socket = socket;
			try {
				//송신용 스트림 객체
				dout = new DataOutputStream(this.socket.getOutputStream());
				
				//수신용 스트림 객체 생성
				din = new DataInputStream(this.socket.getInputStream());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}// 생성자 끝...
		
		@Override
		public void run() {
			String name = "";
			
			try {
				//클라이언트가 연결이 성공하면 첫번째로 '대화명'을 입력받아 보낸다.
				//서버에서는 이 '대화명'이 중복되는지 여부를 응답으로 클라이언트에게 보내준다.
				
				//클라이언트가 보내온'대화명'이 중복되지 않을 떄까지 반복한다.
				while(true) {
					name = din.readUTF();	//클라이언트가 보낸 '대화명' 받기
					
					if(clientMap.containsKey(name)) {	//'대화명'이 중복되면...
						dout.writeUTF("대화명 중복");	//'대화명 중복'이라는 메시지를 보낸다.
					}else {	//'대화명'이 중복되지 않으면...
						dout.writeUTF("OK");	//'OK'메세지 를 보낸다.
						break;	//반복문 탈출
					}
				} //while문의 끝
				
				// 현재 접속한 사람의 데화명을 이용하여 다른 전체 클라이언트에게 대화명 참가 메세지 
				sendToAll("[" + name + "]님이 대화방에 입장했습니다...");
				
				//대화명과 접속한 클라이언트의 Socket객페를 ClientMap에 추가한다.
				clientMap.put(name, socket);
			
				System.out.println("현재 접속자 수  : " + clientMap.size() + "명");
				
				//현재 클라이언트가 보낸 메시지를 받아서 전체 클라이언트에게 보낸다.
				while(din != null) {
					sendToAll(din.readUTF());
				}
				
			} catch (Exception e) {
				
				
			}finally {
				//이 finally 영역이 실행된다는 것은 현재 클라이언트가 접속을 종료했다는의미이다.
				sendToAll("[" + name + "]님이 접속을 종료했습니다...");
				
				//사용자 목록(clientMap)에서 해당 대화명을 삭제한다.
				clientMap.remove(name);
				
				System.out.println("[" + socket.getInetAddress() + " : " 
						+ socket.getPort() + "]에서 접속했습니다...");
				
				System.out.println("현재 접속자 수 : " + clientMap.size() + "명");
				System.out.println();
			}
			
		}
	}
	
	
}

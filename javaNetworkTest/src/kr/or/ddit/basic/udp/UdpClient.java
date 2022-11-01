package kr.or.ddit.basic.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UdpClient {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		//송신용, 수신용 패킷객체 변수 선언
		DatagramPacket inpacket, outpacket;
		
		//수신받은 데이처가 저장될 byte형 배열 선언
		byte[] bMsg = new byte[512];
		
		try {
			//Socket객체 생성
			DatagramSocket socket = new DatagramSocket();
			
			//접속할 곳의 IP정보 생성
			InetAddress address = InetAddress.getByName("localhost");
			
			while(true) {
				//전송할 메시지 입력
				System.out.print("보낼 메시지 입력 : ");
				String msg = scan.nextLine();
						
				//전송할 패킷 객체 생성
				outpacket = new DatagramPacket(msg.getBytes("utf-8"), msg.length(), address, 8888);
				
				//전송
				socket.send(outpacket);
				if("/end".equals(msg)) {	//메시지 중지여부 검사
					break;	//반복문 탈출
				}
				
				//---------------------------------------
				//서버에서 보내온 메시지를 받아서 출력하기
				
				//수신용 패킷객체 생성
				inpacket = new DatagramPacket(bMsg, bMsg.length);
				
				//수신
				socket.receive(inpacket);
				
				System.out.println("서버의 응답 메시지 : " + new String(inpacket.getData(), 0, inpacket.getLength(), "utf-8"));
				System.out.println();
			}
			
			System.out.println();
			System.out.println("통신 끝...");
			socket.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}

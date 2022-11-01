package kr.or.ddit.basic.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//서버는 클라이언트가 접속해서 보내온 파일을 받아서 'd:/d_other/연습용'폴더에 저장한다.

//Socket으로 읽어서 파일로 저장한다.
public class TcpFileServer {
	private ServerSocket server;
	private Socket socket;
	private DataInputStream din;
	private BufferedInputStream bin;
	private BufferedOutputStream bout;

	public void serverStart() {
		// 전송된 파일이 저장될 폴더 정보를 갖는 File객체 생성
		File saveDir = new File("d:/d_other/연습용");
		
		if(!saveDir.exists()) {	//저장될 폴다기 없으면
			saveDir.mkdirs();	//저장될 폴더를 만들어준다.
		}
		
		try {
			server = new ServerSocket(7777);
			System.out.println("서버가 준비되었습니다...");
			System.out.println();
			
			socket = server.accept();	//클라이언트의 요청(접속)을 기다린다.
			
			System.out.println("파일 다운로드 시작...");
			
			//접속이 되면 클라이언트가 첫번째로 보내눈 '파일명'을 받아온다.
			din = new DataInputStream(socket.getInputStream());	//파일명을 수신할 입력 스트림 객체 생성
			
			String fileName = din.readUTF();	//파일명 받기
			
			//저장할 파일 위치와 파일명을 저장하여 File객체를 생성
			File file = new File(saveDir, fileName);
			
			//소켓으로 들어오는 데이터를 받아서 파일로 저장하기
			bin = new BufferedInputStream(din);
			bout = new BufferedOutputStream(new FileOutputStream(file));
			
			byte[] temp = new byte[1024];
			int len = 0;
			
			while((len = bin.read(temp))>0) {
				bout.write(temp, 0, len);
			}
			bout.flush();
			
			System.out.println("파일 다운로드 완료...");
			
		} catch (Exception e) {
			System.out.println("파일 전송 실패!!!");
			e.printStackTrace();
		} finally {
			if(bin != null) try { bin.close(); }catch(IOException e) {}
			if(bout != null) try { bout.close(); }catch(IOException e) {}
			if(socket != null) try { socket.close(); }catch(IOException e) {}
			if(server != null) try { server.close(); }catch(IOException e) {}
		}
		
		
	}

	public static void main(String[] args) {
		new TcpFileServer().serverStart();
	}
}

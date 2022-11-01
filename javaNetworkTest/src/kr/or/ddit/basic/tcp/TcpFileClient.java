package kr.or.ddit.basic.tcp;


import java.awt.Panel;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

//클라이언트는 서버에 접속되면 'd:/d_other'폴더에 있는 '펭귄.jpg'파일을 서버로 전송한다.

//파일을 읽어서 Socket으로 출력한다.

public class TcpFileClient {
	private Socket socket;
	private BufferedInputStream bin;
	private BufferedOutputStream bout;
	private DataOutputStream dout;
	
	public void clientStart() {
		//전송할 정보를 갖는 File객체 생성
//		File file = new File("d:/d_other/펭귄.jpg");
		File file = fileSelectDialog("OPEN");
		String fileName = file.getName();	//파일먕 구하기
		
		if(!file.exists()) {	//전송할 파일이 없으면
			System.out.println(fileName + "파일이 없습니다.");
			return;
		}
		
		try {
			socket = new Socket("localhost", 7777);
			System.out.println("서버에 접속되었습니다...");
			System.out.println();
			
			System.out.println("파일 전송 시작...");
			
			//서버에 접속하면 첫번 쨰로 전송할 파일의 파일명을 전송한다.
			dout = new DataOutputStream(socket.getOutputStream());
			dout.writeUTF(fileName);
			
			//파일 내용을 읽어서 소켓으로 전송하기
			
			//파일 읽기용 스트림 객체 생성
			bin = new BufferedInputStream(new FileInputStream(file));
			
			//서버로 전송할 출력용 스트림 객체 생성
			bout = new BufferedOutputStream(dout);
			
			//파일 내용을 읽어서 서버로 보내기
			byte[] temp = new byte[1024];
			int len = 0;
			
			while((len = bin.read(temp))>0) {
				bout.write(temp, 0, len);
			}
			bout.flush();
			
			System.out.println("파일 전송 완료...");
			
		} catch (Exception e) {
			System.out.println("파일 전송 실패!!!");
			e.printStackTrace();
		}finally {
			//사용했던 socket과 스트림 닫기
			if(bin != null) try { bin.close(); }catch(IOException e) {}
			if(bout != null) try { bout.close(); }catch(IOException e) {}
			if(socket != null) try { socket.close(); }catch(IOException e) {}
		}
	}
	
	// option은 "SAVE" 또는 "OPEN"값을 갖는다
	public File fileSelectDialog(String option) {
		// SWING의 파일 열기, 저장 창 연습
		JFileChooser chooser = new JFileChooser();

		// 선택할 파일의 확장자 설정
		FileNameExtensionFilter txt = new FileNameExtensionFilter("text파일(*.txt)", "txt");
		FileNameExtensionFilter img = new FileNameExtensionFilter("이미지파일", new String[] { "png", "jpg", "gif" });
		FileNameExtensionFilter doc = new FileNameExtensionFilter("Ms word 파일", "docx", "doc"); // 가변형 인수처럼도 가능

		// 설정된 확장된 Chooser에 추가한다.
		chooser.addChoosableFileFilter(txt);
		chooser.addChoosableFileFilter(img);
		chooser.addChoosableFileFilter(doc);

		chooser.setFileFilter(txt); // 확장자 목록 중에 기본적으로 선택될 확장자 지정

		// 실행해보면 설정한거는 3개만 있는데 모든파일이라는거도 있음 => 안보이게 하는 방법이 있음.
		// '모든 파일' 목록 표시를 나타낼지 여부 설정(true : 나오기, false : 감추기)
		chooser.setAcceptAllFileFilterUsed(true);

		// 처음 실행 했을때 뜨는 위치를 변경할 수 있음
		// Dialog창이 나타날 때 보여주는 기본 경로 설정하기
		chooser.setCurrentDirectory(new File("d:/d_other"));

		// 바탕화면을 기본경로로 설정하기
//			chooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/desktop"));

		// Dialog창 만들기
		int result;
		if ("OPEN".equals(option.toUpperCase())) {
			result = chooser.showOpenDialog(new Panel()); // 열기용 Dialog창
		} else if ("SAVE".equals(option.toUpperCase())) {
			result = chooser.showSaveDialog(new Panel()); // 저장용 Dialog창
		} else {
			System.out.println("option은 SAVE 또는 OPEN만 가능합니다.");
			return null;
		}
		
		File selectedFile = null;

		// Dialog창에서 파일을 선택한 후 '열기'버튼 또는 '저장' 버튼을 눌렀을 때 선택한 파일 정보 가져오기
		if (result == JFileChooser.APPROVE_OPTION) { // '열기' 또는 '저장' 버튼 눌렀는지 여부 검사
			selectedFile = chooser.getSelectedFile(); // 선택한 파일 정보 가져오기
		}
		
		return selectedFile;

	}
	
	public static void main(String[] args) {
		new TcpFileClient().clientStart();
	}
}

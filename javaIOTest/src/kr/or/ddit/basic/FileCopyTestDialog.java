package kr.or.ddit.basic;

import java.awt.Panel;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileCopyTestDialog {

	public static void main(String[] args) {
		new FileCopyTestDialog().copyStart();
	}
	
	public void copyStart() {
//		File selFile = fileSelectDialog("save");
//		File selFile = fileSelectDialog("OpEn");
//		File selFile = fileSelectDialog("Op");
		
		//복사될 원본 파일 선택하기
		File sourceFile = fileSelectDialog("Open");
		if(sourceFile == null) {
			System.out.println("원본 파일 선택 실패!!");
			return;
		}
		
		if(!sourceFile.exists()) {
			System.out.println(sourceFile.getAbsolutePath() + "파일이 없습니다.");
			System.out.println("복사 작업을 중단합니다.");
			return;
		}
			
		//복사될 대상 파일 선택하기
		File targetFile = fileSelectDialog("save");
		if(targetFile == null) {
			System.out.println("복사될 대상 파일 선택 실패!!!");
			System.out.println("복사 작업을 중단합니다.");
			return;
		}
		
		try {
			//복사할 파일 스트림 객체 생성(원본 파일을 읽어올 스트림 객체)
			FileInputStream fin = new FileInputStream(sourceFile);
			BufferedInputStream bin = new BufferedInputStream(fin);
			
			//복사될 파일 스트림 객체 생성(대상 파일에 출력할 스트림 객체)
		    FileOutputStream fout = new FileOutputStream(targetFile);
		    BufferedOutputStream bout = new BufferedOutputStream(fout);
		    
		    System.out.println("복사 시작...");

		    int data;	//읽어온 데이터가 저장될 변수
		    
		    //버퍼스트림 이용할 경우
		    while((data = bin.read()) != -1) {
	            bout.write(data);
	         }
	         bout.flush();
		    
		    //스트림 닫기
		    bin.close();
		    bout.close();
		    
		    System.out.println("복사 작업 끝...");
		    
		} catch (IOException e) {
			e.printStackTrace();
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
//		chooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/desktop"));

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
}

package kr.or.ddit.basic;

import java.awt.Panel;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DialogTest {	//Dialog : 파일 저장 눌렀을 때 저장 위치 선택하는 창이나 열기 눌렀을 때 열고싶은 파일 선택하는 창

	public static void main(String[] args) {
		// SWING의 파일 열기, 저장 창 연습
		JFileChooser chooser = new JFileChooser();
		
		//선택할 파일의 확장자 설정
		FileNameExtensionFilter txt = new FileNameExtensionFilter("text파일(*.txt)", "txt");
		FileNameExtensionFilter img = new FileNameExtensionFilter("이미지파일", new String[] {"png", "jpg", "gif"});
		FileNameExtensionFilter doc = new FileNameExtensionFilter("Ms word 파일", "docx", "doc"); //가변형 인수처럼도 가능
		
		//설정된 확장된 Chooser에 추가한다.
		chooser.addChoosableFileFilter(txt);
		chooser.addChoosableFileFilter(img);
		chooser.addChoosableFileFilter(doc);
		
		chooser.setFileFilter(txt);	//확장자 목록 중에 기본적으로 선택될 확장자 지정
		
		//실행해보면 설정한거는 3개만 있는데 모든파일이라는거도 있음 => 안보이게 하는 방법이 있음.
		//'모든 파일' 목록 표시를 나타낼지 여부 설정(true : 나오기, false : 감추기)
		chooser.setAcceptAllFileFilterUsed(true);	
		
		//처음 실행 했을때 뜨는 위치를 변경할 수 있음
		//Dialog창이 나타날 때 보여주는 기본 경로 설정하기
//		chooser.setCurrentDirectory(new File("d:/d_other"));
		
		//바탕화면을 기본경로로 설정하기
		chooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/desktop"));
		
		
		//Dialog창 만들기
		int result = chooser.showOpenDialog(new Panel());	// 열기용 Dialog창
//		int result = chooser.showSaveDialog(new Panel());	// 저장용 Dialog창
		
		// Dialog창에서 파일을 선택한 후 '열기'버튼 또는 '저장' 버튼을 눌렀을 때 선택한 파일 정보 가져오기
		if(result == JFileChooser.APPROVE_OPTION) {	//'열기' 또는 '저장' 버튼 눌렀는지 여부 검사
			File selectedFile = chooser.getSelectedFile();	//선택한 파일 정보 가져오기
			
			// 이후에는 선택된 파일을 이용하여 '읽기 작업' 또는 '쓰기 작업'을 구현하면 된다.
			System.out.println("선택한 파일 : " + selectedFile.getAbsolutePath());
		}
		
		
	}

}

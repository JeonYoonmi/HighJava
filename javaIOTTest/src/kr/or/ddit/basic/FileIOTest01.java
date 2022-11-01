package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileIOTest01 {

	public static void main(String[] args) {
		// FileInputStream을 이용한 파일 내용 읽기
		try {
			// FileInputStream객체 생성 => 읽어올 파일 정보를 인수값으로 넣어준다.
			
			//방법1 => 읽어올 파일 정보를 문자열로 지정하기
//			FileInputStream fin = new FileInputStream("D:/d_other/test.txt");
			
			//방법2 => 읽어올 파일 정보를 File객체로 저장하기
			File file = new File("d:/d_other/test.txt");
			FileInputStream fin = new FileInputStream(file);
			
			int c;		//읽어온 데이터가 저장될 변수
			
			while((c = fin.read()) != -1) {
				// 읽어온 문자 화면에 출력하기
				System.out.print((char)c);
			}
			
			//작업 완료 후 스트림 닫기
			fin.close();
			
		} catch (IOException e) {
			System.out.println("입출력 오류입니다.");
		}

	}

}

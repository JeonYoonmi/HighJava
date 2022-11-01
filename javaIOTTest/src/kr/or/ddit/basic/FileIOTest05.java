package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileIOTest05 {

	public static void main(String[] args) {
		/*
			힌글이 저장된 파일 읽어오기 (한글 인코딩 방식을 지정해서 읽어온다.)
		*/
		try {
			//기본 인코딩 방식으로 읽어들인다.
			//기본 인코딩 방식 => 자바프로그램 소스의 인코딩 방식을 따라간다.
//			FileReader fr = new FileReader("d:/d_other/test_utf8.txt");
//			FileReader fr = new FileReader("d:/d_other/test_ansi.txt");
			
			//기반이 되는 스트림 객체 생성
			FileInputStream fin = new FileInputStream("d:/d_other/test_utf8.txt");
//			FileInputStream fin = new FileInputStream("d:/d_other/test_ansi.txt");
			
			//바이트기반 스트림을 문자 기반 스트림으로 변환하는 보조스트림 객체 생성
//			InputStreamReader isr = new InputStreamReader(fin);
			
			//기본 인코딩 방식으로 읽어온다.
			
			//인코딩 방식을 지정해서 읽어오기
			//인코딩 방식 예제
			//	- MS949 (CP949) => 윈도우의 기본 한글 인코딩 방식(ANSI방식과 같다.)
			//	- UTF-8			=> 유나코드 UTF-8 인코딩 방식
			//	- US-ASCII		=> 영문 전용 인코딩 방식
//			InputStreamReader isr = new InputStreamReader(fin, "인코딩 방식 문자열");
			InputStreamReader isr = new InputStreamReader(fin, "MS949");
//			InputStreamReader isr = new InputStreamReader(fin, "utf-8");
			
			int c;
			
//			while((c=fr.read()) != -1){
//				System.out.print((char) c);
//			}
			
			while((c=isr.read()) != -1){
				System.out.print((char) c);
			}
			
//			fr.close();
			isr.close();	//보조스트림을 닫으면 함께 사용한 기반이 되는 스트림도 같이 닫힌다.
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

}

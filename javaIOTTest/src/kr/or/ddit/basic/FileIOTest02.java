package kr.or.ddit.basic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileIOTest02 {

	public static void main(String[] args) {
		// 파일에 데이터 출력하기
		try {
			// 출력용 스트림 객체 생성
//			File f = new File("d:/d_other/out.txt");
//			FileOutputStream fos = new FileOutputStream(f);
			FileOutputStream fos = new FileOutputStream("d:/d_other/out.txt");
			
			for (char ch = 'A'; ch <= 'Z'; ch++) {
				fos.write(ch);
			}
			
			System.out.println("출력 작업 완료...");
			
			fos.close();	//스트림 닫기
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}

package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
	문제) 'd:/d_other'폴더에 있는 '펭귄.jpg'파일을
		'd:/d_other/연습용'폴더에 '펭귄_복사본.jpg'파일러 복사하는 프로그램을 작성하시오.
*/
public class FileCopyTest {

	public static void main(String[] args) {
		File file = new File("d:/d_other/펭귄.jpg");	//이 파일이 있나 없나를 확인하기 위해 사용
		
		if(!file.exists()) {
			System.out.println(file.getPath() + "파일이 없습니다.");
			System.out.println("복사 작업을 중지합니다.");
		}
		
		try {
			//복사할 파일 스트림 객체 생성(원본 파일을 읽어올 스트림 객체)
			FileInputStream fin = new FileInputStream("d:/d_other/펭귄.jpg");
			BufferedInputStream bin = new BufferedInputStream(fin);
			
			//복사될 파일 스트림 객체 생성(대상 파일에 출력할 스트림 객체)
		    FileOutputStream fout = new FileOutputStream("d:/d_other/연습용/펭귄_복사본.jpg");
		    BufferedOutputStream bout = new BufferedOutputStream(fout);
		    
		    System.out.println("복사 시작...");

		    int data;	//읽어온 데이터가 저장될 변수
		    
//		    while((data=fin.read()) != -1) {
//		    	fout.write(data);
//		    }
		    
		    //버퍼스트림 이용할 경우
		    while((data = bin.read()) != -1) {
	            bout.write(data);
	         }
	         bout.flush();
		    
		    //스트림 닫기
//		    fout.close();
//		    fin.close();
		    bin.close();
		    bout.close();
		    
		    System.out.println("복사 작업 끝...");
		    
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		

	}

}

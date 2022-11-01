package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileIOTest04 {

	public static void main(String[] args) {
		// 사용자가 입력한 내용을 그대로 파일로 저장하기
		
		try {
//			Scanner scan = new Scanner(System.in);
			//System.in => 콘솔(표준 입력 장치) 입력장치 (즉, 키보드)
			//InputStreamReader => 입력용 바이트기반 스트림을 문자기반 스크림으로 변환해주는 보조 스트림이다.
			InputStreamReader isr = new InputStreamReader(System.in);
			
			FileWriter fw = new FileWriter("d:/d_other/testChar.txt");	//출력용 스트림은 화일이 없으면 새로 만들고 원래 화일이 있으면 덮어쓴다
			
			System.out.println("아무 내용이나 입력하세요. (입력의 끝은  Ctrl + Z 입니다.)");
			
			int c;
			
			//콘솔에서 입력할 때 입력의 끝은 'Ctrl + Z'키를 누르면 된다.
			while((c=isr.read()) != -1) {
				fw.write(c);	//콘솔로 입력받은 데이터를 파일에 출력한다.
			}
			
			isr.close();
			fw.close();
			
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}

package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class propertiesTest {

	public static void main(String[] args) {
		// Properties객체를 이용하여 파일 내용을 읽어와 출력한다.
		
		// Properties객체 생성
		Properties prop = new Properties();	//key, value 모두 String만 가능
		
		// 읽어올 파일 정보를 갖는 File객체 생성
		File f = new File("res/kr/or/ddit/config/dbinfo.properties");
		FileInputStream fin = null;
		
		try {
			//파일 입력용 스트림 객체 생성
			fin = new FileInputStream(f);
			
			//입력용 스트립을 이용하여 파일 내용을 읽어와 Properties객체에 셋칭
			prop.load(fin);		//파일 내용을 읽어와 key값과 value값을 분류한 휴 Properties객체에 추가한다.
			
			//읽어온 정보 출력하기
			System.out.println("driver : " + prop.getProperty("drover"));
			System.out.println("url : " + prop.getProperty("url"));
			System.out.println("user : " + prop.getProperty("user"));
			System.out.println("pass : " + prop.getProperty("pass"));
			
			
		} catch (IOException e) {
			System.out.println("입출력 오류입니다.");
			e.printStackTrace();
		}

	}

}

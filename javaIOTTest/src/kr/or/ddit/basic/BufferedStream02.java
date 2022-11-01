package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedStream02 {

	public static void main(String[] args) {
		// 문자 기반의 Buffer스트림 사용하기
		try {
			//이클립스에서 자바 프로 그램이 실행되는 현재 위치는 해당 프로젝트 폴더가 현재 위치가 된다.
			
			//D:\A_TeachingMaterial\03_HighJava\workspace\javaIOTest\src\kr\or\ddit\basic\file\FileTest02.java
			FileReader fr = new FileReader("./src/kr/or/ddit/basic/file/FileTest02.java");
			BufferedReader br = new BufferedReader(fr);
			
			// 문자기반의 버퍼스트림은 한 줄 단위로 데이터를 읽어올 수 있다.
			String temp = "";	//읽어온 데이터가 저장될 변수
			for (int i = 1; (temp = br.readLine()) != null; i++) {	//br.readLine() : 한글자씩 읽어온다.
				System.out.printf("%4d : %s\n", i, temp);
			}

			//위의 for문과 동일한 의미
//			int i = 1;
//			while((temp = br.readLine()) != null) {
//				System.out.printf("%4d : %s\n", i, temp);
//				i++;
//			}
			
			br.close();
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}

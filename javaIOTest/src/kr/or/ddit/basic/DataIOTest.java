package kr.or.ddit.basic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataIOTest {

	public static void main(String[] args) {
		// 자료형 단위로 데이터를 출력하고 읽어오는 연습
		
		try {
			// 자료형 단위로 데이터를 파일에 출력하기
			//바이트 단위로 처리해야한다.
			
			//기반이 되는 스트림 객체 생성
			FileOutputStream fout = new FileOutputStream("d:/d_other/test.dat");
			
			// 자료형 단위로 출력하는 보조 스트림인 DataOutputStream객체 생성
			DataOutputStream dout = new DataOutputStream(fout);
			
			//자료 출력하기
			dout.writeInt(200);		//정수형으로 데이터 출력
			dout.writeFloat(123.45f);	//실수형(float)으로 데이터 출력
			
			// 0.12345 *1000 => 0.12345 * 10의 3승
			
			dout.writeBoolean(true);	//논리형으로 데이터 출력
			dout.writeUTF("ABCDabcd12345678");	//문자열형식으로 출력하기
			
			System.out.println("출력 완료...");
			dout.close();		//스트림 닫기
			
			////////////////////////////////////////////////////////////////////////////////
			//출력한 데이터 읽어오기
			FileInputStream fin = new FileInputStream("d:/d_other/test.dat");
			DataInputStream din = new  DataInputStream(fin);
			
			// DataInputStream으로 자료를 읽어올 때는 출력할 때의 순서와 같은 순서로 읽어와야 한다. => 순서가 바뀌면 값이 엉뚱하게 저장된다.
			System.out.println("논리형 : " + din.readBoolean());
			System.out.println("실수형 : " + din.readFloat());
			System.out.println("정수형 : " + din.readInt());
			System.out.println("문자열 : " + din.readUTF());
			
			System.out.println("읽기 작업 완료...");
			
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}

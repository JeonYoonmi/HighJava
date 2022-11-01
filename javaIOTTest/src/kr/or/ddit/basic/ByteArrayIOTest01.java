package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayIOTest01 {
	
	public static void main(String[] args) {
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		
		//스트림 객체 생성
		ByteArrayInputStream input = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		int data;		//읽어온 자료가 저장될 변수
		
		// 처음부터 마지막 자료까지 읽어서 출력하기 위해 반복문을 사용한다.
		// read()메서드 => 더 이상 읽어올 자료가 없으면 -1을 반환한다.
		while((data = input.read()) != -1) {
			//이 반복문 안에서는 읽어온 자료를 처리하는 명령을 기술하면 된다.
			
			//읽어온 자료를 그대로 출력스츠림으로 출력한다.
			output.write(data);
		}
		
		//출력된 스트림값을 배열로 변환해서 저장하기
		outSrc = output.toByteArray();
		
		try {
			//사용했던 스트림 닫기
			input.close();
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(" inSrc =>" + Arrays.toString(inSrc));
		System.out.println("outSrc =>" + Arrays.toString(outSrc));
		
	}
}

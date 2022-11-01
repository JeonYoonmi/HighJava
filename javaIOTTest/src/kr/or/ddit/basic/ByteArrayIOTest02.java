package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayIOTest02 {

	public static void main(String[] args) {
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		
		byte[] temp = new byte[4];	// 4개짜리 byte배열 생성
			
		// 스트림 객체 생성
		ByteArrayInputStream input = new ByteArrayInputStream(inSrc);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		try {
			// 읽어올 데이터가 있는 동안 반복된다.
			while(input.available() > 0) {	//input.available() : 읽어 올 수 있는 데이터의 개수(남은 데이터 개수)
//				input.read(temp);	//한번에 4개씩 읽어서 temp에 저장
//				output.write(temp);
				// inSrc = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
				//outSrc = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 6, 7]
				//이전에 저장되어 있는 6,7이 남아 있어서 값이 달라진다. 마지막에 배열의 개수의 배수에 맞지 않기 때문
				
				int len = input.read(temp);	//배열을 저장하는 것은 같은데 실제로 읽어온 데이터의 개수를 반환해준다.
				output.write(temp, 0, len);
				
				
				System.out.println("반복문 안에서 temp => " + Arrays.toString(temp));
			}
			
			outSrc = output.toByteArray();
			
			System.out.println();
			System.out.println(" inSrc = " + Arrays.toString(inSrc));
			System.out.println("outSrc = " + Arrays.toString(outSrc));
			
			//스트림 닫기
			input.close();
			output.close();
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

}

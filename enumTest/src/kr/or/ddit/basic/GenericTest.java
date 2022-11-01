package kr.or.ddit.basic;

import java.util.List;
import java.util.Map;

public class GenericTest {

	public static void main(String[] args) {
		NonGenericObj ng1 = new NonGenericObj();
		ng1.setData("가나다라");
		
		NonGenericObj ng2 = new NonGenericObj();
		ng2.setData(100);
		
		String returnData1 = (String)ng1.getData();
		System.out.println("문자열 반환 returnData1 => " + returnData1);
		
		Integer returnData2 = (Integer)ng2.getData();
		int returnData3 = (int)ng2.getData();
		System.out.println("정수형 반환값 returnData3 => " + returnData3);
		System.out.println("---------------------------------------");
		
		GenericObj<String> g1 = new GenericObj<String>();
		g1.setData("대한민국");
		
		GenericObj<Integer> g2 = new GenericObj<Integer>();
		g2.setData(255);
		
		String genericReturn1 = g1.getData();
		System.out.println("제네릭 문자열 반환값 genericReturn1 => " + genericReturn1);
		
		int genericReturn2= g2.getData();
		System.out.println("제네릭 문자열 반환값 genericReturn2 => " + genericReturn2);
		
		/*
		NonGenericObj ng3 = new NonGenericObj();
		ng3.setData(123);
		
		String myData = (String)ng3.getData(); //오류가 난다.
		
		System.out.println("myData = " + myData);
		*/
		
		GenericObj<Integer> g3 = new GenericObj<Integer>();
		g3.setData(3333);
		
//		String myData2 = g3.getData();
		int myData2 = g3.getData();
	}

}

//제네릭을 적용하지 않은 클래스 작성
class NonGenericObj{
	private Object data;

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}

//제네릭을 적용하는 클래스 작성

/*
	- 제네릭 클래스를 만드는 방법
	형식)
	class 클래스명<제네릭타입글자>{
	제네릭타입글자 변수명;		//변수 선언에 제네릭을 사용하는 경우
	...
	
	제네릭타입글자 메서드명(){	//반환값이 있는 메서드에서 사용하는 경우
		...
		return 값;
	}
	
	반환값타입 메서드명(제네릭타입글자 변수명, ...){	//메서드의 매개변수에 제네릭을 사용하는 경우
		...
		...
	}
		
	}
*/

class GenericObj<T>{
	private T data;
	
	public void setData(T data) {
		this.data = data;
	}
	
	public T getData()	{
		return data;
	}
}
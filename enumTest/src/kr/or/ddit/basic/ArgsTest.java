package kr.or.ddit.basic;

import javax.print.attribute.IntegerSyntax;

public class ArgsTest {

	//파라미터로 넘겨주는 데이터의 개수가 호출할 떄마다 수시로 변할 때의 방법
	
	//1. 배열을 이용한 방법
	public int sumArr(int[] data) {
		int sum = 0;
		
		for (int i = 0; i < data.length; i++) {
			sum += data[i];
		}
		
		return sum;
	}
	
	/*
		가변형 인수 => 메서드의 파라미터의 개수가 실행될 때마다 다를 때 사용한다.
		- 가변형 인수는 메서드 안에서는 배열로 처리된다.
		- 가변형 인수는 하나의 메서드에 한가지만 사용할 수 있다.
	 */
	
	//2. 가변형 인수를 이용한 메서드
	public int sumArg(int...data) {
		int sum = 0;
		
		for (int i = 0; i < data.length; i++) {
			sum += data[i];
		}
		
		return sum;
	}
	
	//가변형 인수와 일반적인 인수를 같이 사용할 경우에는 가변형 인수를 제일 뒤쪽에 배치해야 한다.
	public String sumArg2(String name, int...data) {
		int sum = 0;
		
		for (int i = 0; i < data.length; i++) {
			sum += data[i];
		}
		
		return name + "씨 총점 : " + sum;
	}
	
	public static void main(String[] args) {
		ArgsTest test = new ArgsTest();
		
		int[] nums = {100, 200, 300};	
//		int[] nums;
//		nums = {100, 200, 300}; => 불가능
		
		int[] a = new int[] {1,2,3,4,5};
//		int[] a;
//		new int[] {1,2,3,4,5}; => 가능
		System.out.println(test.sumArr(nums));
		System.out.println(test.sumArr(new int[] {1,2,3,4,5})); //1, 2, 3, 4, 5를 배열에 넣어서 보내기.
		System.out.println();
		
		System.out.println(test.sumArg(100, 200, 300));
		System.out.println(test.sumArg(1, 2, 3, 4, 5));
		System.out.println();
		
		System.out.println(test.sumArg2("홍길동", 1,2,3,4,5,6,7));
		
	}
	
}

package kr.or.ddit.basic;

import javax.jws.Oneway;

/*
		enum(열거형)	=>	서로 관련있는 상수들의 집합을 나타낸다.
					=>	클래스처럼 보이게 하는 상수
		- 작성방법
			=>	클래스처럼  독립된 java파일에 만들 수 있고, 하나의 java파일 클래스와 같이 만들 수 있고,
				클래스 안에 내부 클래스처럼 만들 수 있다.
				
		- 열거형의 속성 및 메서드
		  1) name()		=> 열거형 상수의 이름을 문자열로 반환한다.
		  2) ordinal	=> 열거형 상수가 정의되 순서값(index값)을 반환한다.(0부터 시작)
		  3) valueOf("열거형 상수명")	=> 지정한 열거형애서 '열거형상수명'과 일치하는 열거형 상수를 반환한다.
		  4) 열거형이름.상수명	=> valueOf()메서드와 결과가 같다.
		  
		- 열거형 상수 정의하기
		방법1)
			enum 열거혈이름{ 상수명1, 상수명2, 상수명3, ...}
			
		방법2) 열거형 상수에 값을 지정해서 사용할 때...
			enum 열거형 이름{
				상수형1 (값들...),
				상수명2(값글...),
				
				상수명n(값들...);
				
				//값들이 저장될 변수들을 성언한다.
				 private 자료형이름 변수명;
				 ...
				 
				 //열거형의 생성자를 만든다.
				 //열거형의 생성자는 '열거형 상수'에 '값들'을 셋탕하는 열할을 수행한다.
				 //열서형의 생성자는 묵시적으로 private이디
				 
				 //'변수명'은 '값들'과 개수가 같고, 각각의 '값들'과 자료형이 맞아야 한다.
				 private 열거형이름(자료형 변수명, ....){
				 	위에 선언된 변수들을 초기화 하는 작업을 수행한다.
				 	...
				 }
				 
				 //위에 선언된 변수들의 값을 외부에서 사용할 수 있도록 getter메서드를 작성한다.
				 
			}
 */
public class EnumTest {
	public enum Color {RED, GREEN, BLUE}
	
	public enum Count {ONE, TWO, THREE}
	
	public enum Season{
		//상수명(값들...) 형식의 선언
		봄("3월부터 5월까지", 15),
		여름("6월부터 8월까지", 30),
		가을("9월부터 11월까지", 17),
		겨울("12월부터 2월까지", 0);
		
		//값들이 저장될 변수 선언
		private String span;
		private int temp;
		
		//생성자
		Season(String months, int data) {	//private를 생략할 수 있는데 생략해도 private(private Season(String months, int data))
			//자료들을 변수에 초기화한다.
			span = months;
			temp = data;
		}

		//getter메서드를 작성한다.
		public String getSpan() {
			return this.span;
		}
		
		public int getTemp() {
			return this.temp;
		}
		
	}
	
	public static void main(String[] args) {
		/*
		System.out.println("RED => " + ConstTest.RED);
		System.out.println("TREE => " + ConstTest.THREE);
		
		if(ConstTest.RED == ConstTest.TWO) {
			System.out.println("@@@@@@@@@@@@@@@");
		}else {
			System.out.println("&&&&&&&&&&&&&&&");
		}
		*/
		Color mycol = Color.valueOf("GREEN");	//Color.GREEN 와 같다.
		Count mycnt = Count.ONE;				//Count.valueOf("ONE")과 같다.
		
		System.out.println("mycol : " + mycol.name());
		System.out.println("mycnt : " + mycnt.name());
		
		System.out.println("mycol의 ordinal값 : " + mycol.ordinal());
		System.out.println("mycnt의 ordinal값 : " + mycnt.ordinal());
		
		/*
		//서로다른 종류의 열거형끼리의 비교는 불가하다. (오류 발생)
		if(Color.RED == Count.ONE) {
			System.out.println("...");
		}
		*/
		
		if(mycol == Color.GREEN) {
			System.out.println("같다");
		}
		System.out.println("-------------------------");
		
		// 열거형을 switch문에서 사용할 수 있다.
		switch (mycnt) {
		//switch문의	case에 열거형 상수를 지정할 때는 '열거형'이름은 생략하고 '상수형'만 기술한다.
		
//		case Count.ONE : System.out.println("ONE");		//잘못된 사용 예
		case ONE: System.out.println("... ONE ...");break;
		case TWO: System.out.println("... TWO ...");break;
		case THREE: System.out.println("... THREE ...");break;
		}
		System.out.println("=================================");
		
		Season ss = Season.valueOf("봄");
		System.out.println("name : " + ss.name());
		System.out.println("ordinal : " + ss.ordinal());
		System.out.println("span : " + ss.getSpan());
		System.out.println("temp : " + ss.getTemp());
		System.out.println();
		
		//열거형이름.values()	=> 모든 상수들을 배열로 변환한다.
		for (Season s : Season.values()) {
			System.out.println(s.name() + " == " + s + " => " + s.getSpan() + " : " + s.getTemp());
		}
		System.out.println();
		
		for (Color col : Color.values()) {
			System.out.println(col + " => " + col.ordinal());
		}
	}

}

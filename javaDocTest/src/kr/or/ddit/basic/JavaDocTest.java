package kr.or.ddit.basic;
// JavaDoc 파일 만들기 예제	=> 한줄 주석

/*
	인터페이스 사용 이유 : 표준을 맞추기 위함
*/	//여려줄 주석

/**
 * 
 * @author 홍길동
 * @version 1.0
 * 
 * <p>
 * 파일명 : JavaDocTest.java<br>
 * 설   명 : JacaDoc문서 작성을 위한 연습용 interface<br><br>
 * 
 * 수정 이력<br>
 * ------------------------<br>
 * 수정 일자 : 2022-05-16<br>
 * 작  성  자 : 홍길동<br>
 * 수정 내용 : 최초 생성<br>
 * ------------------------<br>
 * </p>
 *
 */	//JavaDoc주석, author : 작성자가 누구인지
public interface JavaDocTest {
	/**
	 * 메서드 명 : methodTest<br>
	 * 설       명 : 반환값이 없는 메서드<br>
	 * @param a 첫번 째 매개변수(정수형)
	 * @param b 두 번 째 매개변수(정수형) 
	 */
	public void methodTest(int a, int b);
	
	/**
	 * 메서드 명 : methodAdd<br>
	 * 설       명 : 정수형 데이터 2개를 이용하여 합계를 반환하는 메서드<br>
	 * @param num1 첫 번 째 정수형 데이터
	 * @param num2 두 번 째 정수형 데이터
	 * @return 합계결과를 정수형으로 반환한다.
	 */
	public int methodAdd(int num1, int num2);
	
	/**
	 * 메서드 명 : methodSub<br>
	 * 설       명 : 매개변수가 없는 메서드<br>
	 * @return 정수형으로 반환한다.
	 */
	public int methodSub();
}

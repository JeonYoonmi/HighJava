package kr.or.ddit.basic;

import java.util.Vector;

public class VectorTest {

	public static void main(String[] args) {
		//Collection 객체들은 객체만 저장할 수 있다.
		//wrapper class : 자동으로 객체화 시켜주는 클래스 -> boxing <=> unboxing
		
		//Vector => 자바 초창기부터 지원하는 객체, 동기화처리(두개의 스레드가 접속할 수 있다고 하면 처음에 벡터가 실행되면 실행이 완료될 때까지 다른 쓰레드는 사용할 수 없다.)가 되어 있다.
		//컴퓨터에 자원을 많이 쓴다 -> 무거워진다
		//객체생성
		Vector v1 = new Vector();
		
		System.out.println("처음 size : " + v1.size());
		//데이터 추가하기 => add(추가할 데이터)
		//반환값 => 추가 성공(true), 추가 실패(false)
		v1.add("aaaa");
		v1.add(new Integer(111));//과거에 쓰는 법 wrapper class
		v1.add(123); //오토 박싱이 일어난다.
		v1.add('a');
		v1.add(true);
		v1.add(123.45);
		boolean r = v1.add(123.45);
		
		System.out.println("추가 후 size : " + v1.size());
		System.out.println("반환값 : " + v1.size());
		
		// 데이터 추가하기2 = > addElement(추가할 데이터)
		//		=>이전버전의 프로그램도 사용할 수 있도록 하기 위해 남아 있는 메서드
		v1.addElement("CCC");
		
		System.out.println("v1 => " + v1.toString());
		System.out.println("v1 => " + v1); //toString은 생략 가능
		
		//데이터 추가하기3 => add(index, 데이터);
		//	=> 'index'번째에 '데이터'를 끼워 넣는다
		//	=> 'index'는 0번부터 시작한다.
		//	=> 반환값은 없다.
		v1.add(1, "kkk");
		System.out.println("v1 => " + v1);
		
		//데이터를 꺼내오기 => get(index)
		//	=> 'index'번째의 데이터를 꺼내와 반환한다.
		int data = (int)v1.get(2); //오토 언박싱 작업이 이루어진다. //부모타입에 들어있는 데이터를 자식 타입으로 넣을 때에는 형변화을 해줘야함
		System.out.println("꺼내온 데이터 : " + data);
		
		//데이터 수정하기 => set(index, 새로운 데이터)
		//	=> 'index'번째의 데이터를 '새로운데이터'로 덮어쓴다.
		//	=> 반환값 : 변경되기 전의 데이터
		String temp = (String)v1.set(0, "zzzz");
		
		System.out.println("v1 => : " + v1);
		System.out.println("원래의 데이터 : " + temp);
		System.out.println();
		
		//데이터 삭제하기 => remove(index)
		//	=> 'index'번째의 데이터를 삭제한다.
		//	=> 반환값 : 삭제된 데이터
		String temp2 = (String)v1.remove(0);
		
		System.out.println("삭제 후 v1 : " + v1);
		System.out.println("삭제된 데이터 : " + temp2);
		
		//데이터 삭제하기2 => remove(삭제할 데이터)
		//	=> '삭제할 데이터'를 찾아서 삭제한다.
		//	=> '삭제할 데이터'가 여러개이면 앞에서 부터 삭제된다.
		//	=> 반환값은 : 삭제성공(true), 식제실패(false)
		//	=> '삭제할 데이터'가 '정수형'이거나 'char형'일 경우에는 반드시 객체로 변환하여 사용해야한다.
		v1.remove("CCC");
		System.out.println("삭제 후 v1 => " + v1);
		
//		v1.remove(123); //123번 째 데이터 삭제 //오류 벌생 : Array index out of range: 123 => 우리가 원하는 방식은 두번 쨰 방식인데 첫번쨰 방법으로 자바가 돌아가서 123번째 인덱스가 없다고 오류가 난다
//		v1.remove(new Integer(123));
		v1.remove(Integer.valueOf(123));
		System.out.println("삭제 후 v1 => " + v1);
		//오버라이딩 : 부모가 가지고 있는 매서드를 자식이 변경하는것
		//오버로딩 : 이름은 같은데 매게변수 개수나 타입을 다르게 해서 똑같은 이름의 메서드를 만드는 것
		
//		v1.remove('a'); //오류 : Array index out of range: 97 => a를 작은따옴표로 감싸면 java는 97로 인식한다.
//		v1.remove(new Character('a'));
		v1.remove(Character.valueOf('a'));
		System.out.println("삭제 후 v1 => " + v1);
		
		v1.remove(true);
		System.out.println("삭제 후 v1 => " + v1);
		
		v1.remove(123.45);
		System.out.println("삭제 후 v1 => " + v1);
		System.out.println();
		/////////////////////////////////////////////////////////////////////////////////
		/*
		 * 제네릭 타입(Generic Type)  => 클래스 내부에서 사용할 데이터의 타입을 외부에서 지정하는 기법으로
		 * 			객체를 선언할 때 < >괄호 안에 그 객체의 내부에서 사용할 데이터의 타입을 정해주는 것을 말한다.
		 * 			이런식으로 선언하게 되면 지정된 데이터 타입 이외의 다른 종류의 데이터를 저장할 수 없다.
		 * 			이 때 제네릭으로 선언될 수 있는 데이터 타입은 클래스형 이어야 한다.
		 * 			그래서, int는 Integer, boolean은 Boolean, char은 Character등으로 대체해서 사용해야 한다.
		 * 
		 * 제네릭 타입으로 선언하게 되면 데이터를 꺼내올 때도 별도의 형변환이 필요없다.
		*/
		Vector<String> v2 = new Vector<String>(); //String만 저장할 수 있는 벡터
		Vector<Integer> v3 = new Vector<>(); //int형만 저장할 수 있는 벡터
		
		v2.add("안녕하세요");
//		v2.add(100); //오류 : 다른 종류의 데이터르르 저장할 수 없다.
		
		String temp3 = v2.get(0); //데이터를 꺼내올 때 형변환이 필요없다.
		System.out.println("temp2 => " + temp3);
		System.out.println("--------------------------------");
		
		//전체 데이터 삭제하기 => clear()
		v2.clear();
		System.out.println("v2의 size : " + v2.size());
		System.out.println();
		
		v2.add("AAAA");
		v2.add("BBBB");
		v2.add("CCCC");
		v2.add("DDDD");
		v2.add("EEEE");
		
		Vector<String> v4 = new Vector<>();
		v4.add("BBBB");
		v4.add("EEEE");
		
		System.out.println("v2 => " + v2);
		System.out.println("v4 => " + v4);
		
		//데이터 삭제하기 => removeAll(Coolection 객체)
		//	=> 현재 벡터에 있는 데이터 중에서 'Collection객체'가 가지고 있는 데이터를 모두 삭제한다.
		//	=> 반환값 : 식제성공(true), 삭제실패(false)
		v2.removeAll(v4); //v2애 있는 데이터 중 v4에 있는 데이터를 모두 사용한다.
		
		System.out.println("v2 => " + v2);
		System.out.println();
		System.out.println("------------------------------------");
		
		v2.clear();
		v2.add("AAAA");
		v2.add("BBBB");
		v2.add("CCCC");
		v2.add("DDDD");
		v2.add("EEEE");
		
		// 벡터 데이터를 순서대로 모두 가져와 사용하고 싶으면 반복문을 사용하면 된다.
		// (주로 for문을 사용한다.)
		
		for(int i = 0; i < v2.size(); i++) {
			System.out.println(i + "번 째 데이터 : " + v2.get(i));
		}
		System.out.println();
		
		//향상된 for문
		for(String str : v2) {
			System.out.println(str);
		}
		
		
		
		
		
		
		
		
	}

}

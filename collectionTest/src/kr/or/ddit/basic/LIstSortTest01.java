package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * 정렬과 관계된 interface는 Comparable, Comparator 이렇게 두가지가 있다.
 * 
 * - Comparable은 Collection에 추가되는 데이터 자체에 정렬 기준을 넣고 싶을 떄 구현하는 인터페이스이다.
 * 				(이것은 내부 정렬 기준이라 한다.)
 * 
 * - Comparator는 외부에 별도로 정렬 기준을 구현하고 싶을 때 사용하는 인터페이스 이다.
 * 				(이것은 외부 정렬 기준이라 한다.)
 * 
 * - Comparable은 compareTo()메서드를 재정의 해야하거,
 * 	 Comparator는 compare()메서드를 재정의 해야한다.
 * 
 * - String클래스, Wrapper클래스, Date클래스, File클래스 등에는 내부 정렬 기준이 구현되어 있다.
 *   (이 내부 정렬 기준은 기본적으로 오름차순을 처리되도록 구현되어 있다.)
 */
public class LIstSortTest01 {

	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		
		list.add("일지매");
		list.add("홍갈동");
		list.add("성춘향");
		list.add("변학도");
		list.add("이순신");
		//한글은 정렬을 어떻게 하나? => 문자별로 번호가 부여 있음
		System.out.println("정렬전 : " + list);
		
		//정렬은 Collections.sort()메서드를 이용하여 정렬한다.
		//Collections.sort()메서드는 기본적으로 내부 정렬 기준으로 정렬한다
		Collections.sort(list);
		
		System.out.println("정렬 후 : " + list);
		
		Collections.shuffle(list);	//지료 섞기
		
		System.out.println("자료 섞기 후 : " + list);
		
		//외부 정렬 기준을 적용해서 정렬하기
		Collections.sort(list, new Desc());
		
		System.out.println("내림차순 정렬 후 : " + list);
		
	}

}

//정렬 방식을 정해주는 Class를 작성한다.(외부정렬 기분 클래스라고 한다.)
//	=> Comparator인터페이스를 구현해서 작성한다.
class Desc implements Comparator<String>{
	// compare()메서드를 이용해서 정렬하고자 하는 기준을 정한다.
	
	// compare()메서드의 반환값
	// 0은 두 값이 같다.
	// 양수는 앞, 뒤의 순서를 바꾼다.
	// 음수는 앞, 뒤의 순서를 바꾸지 않는다.	
	@Override
	public int compare(String str1, String str2 /*바로 인접해있는 두개의 데이터*/) {
		//내림차순으로 정렬하고자 한다.
		//compareTo()는 오름차순으로 정렬하는 메서드.
		if(str1.compareTo(str2) > 0) {
			return -1;
		}else if(str1.compareTo(str2) < 0) {
			return 1;
		}
		return 0;
	}
}



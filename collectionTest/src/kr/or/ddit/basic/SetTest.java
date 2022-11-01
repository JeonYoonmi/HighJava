package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class SetTest {

	public static void main(String[] args) {
		/*
		 * List와 Set의 차이점
		 * 
		 * 1. List
		 * 	- 데이터의 순서(index)가 있다.
		 *  - 중복되는 데이터를 저장할 수 있다.
		 *  
		 *  2. Set => 중복되는 데이터를 빼고 싶을 때 많이 사용한다.
		 *   - 데이터의 순서(index)가 없다.
		 *   - 중복되는 데이터를 저장할 수 없다.
		 */
		HashSet hs1 = new HashSet();
		
		//Set에 데이터를 추가할 때도 add()메서드를 사용한다.
		hs1.add("DD");
		hs1.add("AA");
		hs1.add(2);
		hs1.add("CC");
		hs1.add("BB");
		hs1.add(1);
		hs1.add(3);
		
		System.out.println("Set의 size : " + hs1.size());
		System.out.println("Set의 데이터 : " + hs1); //출력되서 나오는건 순서가 없다.
		
		//Set에 중복되는 데이터를 추가하면 false를 반환하고, 데이터를 추가하지 않는다.
		boolean isAdd = hs1.add("FF");
		System.out.println("중복되지 않을 때 : " + isAdd);
		System.out.println("Set의 데이터 : " + hs1);
		
		isAdd = hs1.add("CC");
		System.out.println("중복될 때 : " + isAdd);
		System.out.println("Set의 데이터 : " + hs1);
		System.out.println();
		
		//Set의 데이터 수정하기 => Set에는 수정하는 명령이 따로 없기 떄문에
		//		헤당 자료를 삭제한 후 새로 추가해 주는 방법을 사용한다.
		
		//삭제하는 메서드 : remove(삭제할 자료) => 반환값 : 삭제 성공(true), 삭제 실패(false)
		//				clear() => 전체삭제
		
		//예) "FF"데이터를 "EE"로 변경하기
		hs1.remove("FF");	//"FF" 데이처 삭제
		System.out.println("삭재후 Set : " + hs1);
		hs1.add("EE");
		System.out.println("Set 데이터 : " + hs1);
		System.out.println();
		
//		hs1.clear();
//		System.out.println("Set 데이터 : " + hs1);
//		System.out.println();
		
		/*
		 * Set에 데이터는 순서(index)가 없기 떄문에 List처럼 index로 데이탈,ㄹ 하나씩 불러올 수 있다.
		 * 그래서 게이처를 하나씩 얻기 위해서는 Iterator형 객체로 변환해야 한다
		 * 
		 *   - Set형의 데이터를 Iterator형 객체에 주는 메서드 =>  Iterator()
		*/
		
		System.out.println("출력 시작...");
		
		Iterator it = hs1.iterator(); //Set데이터를 Iterator로 변환한다.
		
		//Iterator의 hasNext()메서드
		//		=> Iterator의 포인터가 가리키는 곳의 다음번째에 데이터가 있는지 검사
		//			(데이터가 있으면 true, 없으면 false를 반환한다.)
		while(it.hasNext()){
			//Iterator의 next()메서드,
			//		=>Iterator의 포인터를 다음 위치로 이동시킨 후, 이동한 위치의 데이터를 반환한다.
			System.out.println(it.next());
		}
		System.out.println("출력 종료...");
		
		//우리반 학생들 중 번호를 이용하여 추첨하는 프로그램을 작성해보자.
		//번호는 1번부터 25번까지 있고, 추첨할 인원은 3명이다.
		//당첨자를 출력해 보시오.
		
		//최솟값 ~ 최댓값 사이의 정수형 난수 만들기
		//(int)(Math.random() * (최대값 - 최소값 + 1) + 최소값)
		HashSet<Integer> testSet = new HashSet<Integer>();
		while(testSet.size() < 3) {
			testSet.add((int)(Math.random() * 26 + 1));
		}
		
		System.out.println(testSet);
		
		//Iterator대신 향상된 for문을 사용하기
		System.out.println("향상된 for문 이용하기");
		for(Object obj : hs1) {
			System.out.println(obj);
		}
		System.out.println("----------------------");
		
		//Set유형의 자료를 List형으로 변환하기
		ArrayList testList = new ArrayList(hs1);
		for(int i=0; i<testList.size(); i++) {
			System.out.println(testList.get(i));
		}
		
		
	}

}

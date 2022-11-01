package kr.or.ddit.basic;

import java.util.ArrayList;

public class ArrayListTest01 {

	public static void main(String[] args) {
		// ArrayList는 기본적으로 Vector와 사용법이 같다.
		
		ArrayList list1 = new ArrayList();
		
		//add()메서드를 이용해서 데이터를 추가한다.
		list1.add("aaa");
		list1.add("bbb");
		list1.add(123);
		list1.add('k');
		list1.add(true);
		list1.add(3.14);
		
		System.out.println("size => " + list1.size());
		System.out.println("listt1 => " + list1);
		
		//get()메서드로 데이터를 꺼내온다.
		System.out.println("1번 째 자료 : " + list1.get(1));
		
		//set()메서드로 데이터 변경하기
		String temp = (String)list1.set(1, "zzz");
		System.out.println("temp => " + temp);
		System.out.println("list1 => " + list1);
		
		//삭제도 같다.
		list1.remove(3);
		System.out.println("삭제 후 list1 => " + list1);
		
		list1.remove("zzz");
		System.out.println("삭제 후 list1 => " + list1);
		System.out.println("------------------------------");
		
		//제네릭을 사용할 수 있다.
		ArrayList<String> list2 = new ArrayList<String>();
		list2.add("AAAA");
		list2.add("BBBB");
		list2.add("CCCC");
		list2.add("DDDD");
		list2.add("EEEE");
		
		for(int i = 0; i < list2.size(); i++) {
			System.out.println(i + "번째 : " + list2.get(i));
		}
		System.out.println("--------------------------------");
		
		for (String s : list2) {
			System.out.println(s);
		}
		System.out.println("--------------------------------");
		
		// contains(비교객체) => 리스트에 '비교객체'가 있으면 true, 없으면 false 반환한다.
		System.out.println("DDDD값 : " + list2.contains("DDDD"));
		System.out.println("ZZZZ값 : " + list2.contains("ZZZZ"));
		
		
		//indexOf(비교객체) => 앞에서부터 뒤쪽으로 검색, lastIndexOf(비교객체) => 뒤에서 앞쪽으로 검색
		//	=> 리스트에 '비교객체'가 있으면 '비교객체'가 위치한 index값을 반환하고, 없으면 '-1'을 반환한다.
		System.out.println("DDDD값의 위치 : " + list2.indexOf("DDDD"));
		System.out.println("ZZZZ값의 위치 : " + list2.indexOf("ZZZZ"));
		System.out.println("-------------------------------------");
		
		//toArray() => 리스트 안의 데이터를 배열로 변환하여 반환한다.
		//			=> 기본적으로 Object형 배열로 변환한다.
		
		Object[] strArr = list2.toArray();
//		String[] strArr = (String[])list2.toArray(); //이 방법은 안된다(형변환이 불가능)
		System.out.println("배열의 개수 : " + strArr.length);

		String[] strArr2 = list2.toArray(new String[0]);
		for(int i = 0; i < strArr2.length; i++) {
			System.out.println(i + "번째 : " + strArr2[i]);
		}
		
		
	}

}

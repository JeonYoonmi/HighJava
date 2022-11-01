package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapTest {

	public static void main(String[] args) {
		/*
		 * Map객채 => key값과 value값을 한 쌍으로 관리하는 객체
		 * 		- key값의 특징 : 중복을 허용하지 않고 순서(index)가 없다. (Set의 특징을 갖는다.)
		 * 		- Value값의 특장 : 중복을 허용한다.
		 */
		HashMap<String, String> map = new HashMap<String, String>();
		
		//자료 추가 => put(key값, value값)
		map.put("name", "홍길동");
		map.put("addr", "대전");
		map.put("tel", "010-1234-5678");
		System.out.println("map => " + map);
		
		//자료 수정 => 데이터를 추가할 때 key값이 같으면 나중에 입력한 값이 저장된다.
		map.put("addr", "서울");
		System.out.println("map => " + map);
		
		//자료 읽기 => get(key갑)
		//	=> 주어진 'key값'에 맞는 'value값'을 반환한다.
		System.out.println("이름 : " + map.get("name"));
		System.out.println("주소 : " + map.get("addr"));
		System.out.println("전화번호 : " + map.get("tel"));
		
		//자료 삭제 => remove(key값) => 'key값'이 같은 자료를 찾아서 삭제한다.
		//						=> 반환값 : 삭제되는 데이터의 'value값'
//		String removeTel = map.remove("tel");
//		System.out.println("삭제 후 map => " + map);
//		System.out.println("삭제된 데이터 => " + removeTel);
		
		//key값의 존재하는지 여부를 검사하는 메서드 : containsKey('key값');
		//			=> 주어진 'key값'이 있으면 true, 없으면 false 반환
		System.out.println("tel 키값의 존재 여부 : " + map.containsKey("tel"));
		System.out.println("nase 키값의 존재 여부 : " + map.containsKey("name"));
		
		//맵에 저장된 모든 데이터를 읽어와 사용하는 방법
		
		// - key값 이용하기
		
		// 방법1. keySet()메서드 이용하기
		//		=>맵의 모든 key값들을 읽어와 Set형으로 반환한다.
		Set<String> keySet = map.keySet();	//Map에서 Key값들을 가져와 Set형으로 반환한다.
		
		Iterator<String> it = keySet.iterator();
		while(it.hasNext()) {
			String key = it.next();
			String value = map.get(key);
			System.out.println(key + " : " + value);
		}
		System.out.println("-----------------------------------");
		
		//방법2. kwySet을 향상된 for문으로 사용하기 => 주로 많이 사용함.
		for(String key : keySet) {
			String value = map.get(key);
			System.out.println(key + " : " + value);
		}
		System.out.println("-----------------------------------");
		
		// - value값 이용하기
		
		//방법 3. values()메서드 => value값들만 읽어온다.
		System.out.println("Value값만 가져와 출력하기");
		for(String value : map.values()) {
			System.out.println(value);
		}
		System.out.println("-----------------------------------");
		
		//방법4. => Map에는 Entry라는 내부 Class가 만들어져 있다.
		//			이 Entry클래스는 key라는 변수와 value라는 변수로 구성되어 있다.
		//			Map에서는 이 Entry클래스를 Set형식으로 저장하여 관리한다.
		
		//Entry객체 전체를 가져와 처리하기 => 기져온 Entry객체는 Set형으로 되어있다.
		//	=> entrySet()메서드를 이용한다.
		
		//Entry라는 내부객체 전체 가져오기
		Set<Map.Entry<String, String>> mapSet = map.entrySet();
		
		//가져온 전체 Entry객체를 처리할 때는 iterator나 향상된 for문을 이용해서 처리하면 된다.
		Iterator<Map.Entry<String, String>> enIt = mapSet.iterator();
		while(enIt.hasNext()){
			Map.Entry<String, String> entry = enIt.next();
			
			System.out.println("key 값 : " + entry.getKey());
			System.out.println("value 값 : " + entry.getValue());
			System.out.println();
		}
		System.out.println("-----------------------------------");
		
		
		/////////////////////////////////////////////////////////////////////
		HashMap<String, Member> memberMap = new HashMap<String, Member>();
		Member mem = new Member(1, "홍길동", "010-111-1111");
		memberMap.putIfAbsent("회원1", mem);
				
		
	}

}

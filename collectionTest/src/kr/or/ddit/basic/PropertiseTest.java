package kr.or.ddit.basic;

import java.util.Properties;

public class PropertiseTest {

	public static void main(String[] args) {
		/*
		 * Properties객체는 Map보다 축소된 기능의 객체라고 할 수 있다.
		 * 
		 * Map은 Key값과 value값에 모든 형태의 객체를 사용할 수 있지만
		 * Properties는 key와 value에 String만 사용할 수 있다. => 제네릭이 필요 없음
		 * 
		 * Map은 put(), get()메서드를 이용하여 데이터를 입출력하지만
		 * Properties는 setProperty(), getProperty()메서드를 이용하여 입출력한다.
		 * 
		 * Properties는 데이터를 파일로 입출력할 수 있다.
		 */
		Properties prop = new Properties();
		
		//데이터 추가
		prop.setProperty("name", "이순신");
		prop.setProperty("age1", 20 + "");
		prop.setProperty("age2", String.valueOf(20));
		prop.setProperty("tel", "010-1234-5676");
		prop.setProperty("addr", "대전");
		
		//데이터 읽기
		String name = prop.getProperty("name");
		String age1 = prop.getProperty("age1");
		String age2 = prop.getProperty("age2");
		String tel = prop.getProperty("tel");
		String addr = prop.getProperty("addr");
		
		System.out.println("이름 : " + name);
		System.out.println("나이1 : " + age1);
		System.out.println("나이2 : " + age2);
		System.out.println("전화 : " + tel);
		System.out.println("주소 : " + addr);
		
		
		
	}

}

package kr.or.ddit.basic;

import java.util.HashSet;

public class EqualsHashcodeTest {
//자바는 jvm이라는 자바 가상머신이 프로그램을 돌려주는 것임.
	public static void main(String[] args) {
		Person p1 = new Person();
		p1.setId(1);
		p1.setName("홍길동");
		
		Person p2 = new Person();
		p2.setId(2);
		p2.setName("일지매");
//		p2.setId(1);
//		p2.setName("홍길동");
		
		Person p3 = p1;
		
		System.out.println("== -> " + (p1 == p2)); 
		
		System.out.println("equals -> " + p1.equals(p2)); //객체를 비교
		
		System.out.println("== -> " + (p1 == p3));
		System.out.println("equals -> " + p1.equals(p3));
		System.out.println();
		
		HashSet<Person> testSet = new HashSet<Person>();
		
		testSet.add(p1);
		testSet.add(p2);
		testSet.add(p3); //들어가지 않는다 => P1과 중복
		
		System.out.println("size = " + testSet.size());
		
		System.out.println("p1 hashCode : " + p1.hashCode());
		System.out.println("p2 hashCode : " + p2.hashCode());
		System.out.println("p3 hashCode : " + p3.hashCode());
		
		/*
		 * - equals() 메서드 =>  두 객체의 내용이 같은지 검사하는 연산자 메서드
		 * - hashCode() 메서드 => 두 객체의 동일성 여부를 검사하는 연산자 메서드
		 * 
		 * - HashSet, HashTable, HashMap과 같이 Hsah로 시작하는 컬랙션 객체들은
		 * 		객체의 의미상의 동일성을 비교하기 위해 hashCode()메서드를 호출해서 비교한다.
		 * 		그러므로, 객체가 같은지 여부를 결정하려면 equals()메서드와 hashCode()메서드를 재정의 해야한다.
		 */
		
	}
}

class Person{
	private int id;
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	//우리가 만든 equals
//	@Override
//	public boolean equals(Object obj) {
//		if(this == obj) return true; //참조값이 같다.
//		
//		if(obj == null) return false;
//		
//		//같은 유형의 클래스인지 검사
//		if(this.getClass() != obj.getClass()) return false; //getClass() : 클래스의 유형을 검사해주는 메서드 Object 클래스에서 제공해주는 메서드
//	
//		//매개변수의 값을 현재 객체 유형으로 형변환 한다.
//		Person that = (Person) obj;
//		
//		if(this.name == null && that.name != null) {
//			return false;			
//		}
//		
//		if(this.id == that.id && this.name == that.name){//참조값이 같은지 확인하는거 //name이 둘 다 null일 경우 비교...
//			return true;
//		}
//		
//		if(this.id == that.id && this.name.equals(that.name)) {
//			return true;
//		}
//		
//		return false;
//		
//	}
	
}

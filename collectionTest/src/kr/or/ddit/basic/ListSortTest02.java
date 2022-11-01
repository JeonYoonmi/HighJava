package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListSortTest02 {

	public static void main(String[] args) {
		ArrayList<Member> memList = new ArrayList<Member>();
		
		memList.add(new Member(1, "홍길동", "010-1111-1111"));
		memList.add(new Member(5, "이순신", "010-2222-1111"));
		memList.add(new Member(9, "성춘행", "010-3333-1111"));
		memList.add(new Member(3, "강감찬", "010-4444-1111"));
		memList.add(new Member(6, "일지매", "010-5555-1111"));
		memList.add(new Member(2, "변학도", "010-6666-1111"));
		
		System.out.println("정렬전...");
		for(Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("--------------------------------------");
		
		Collections.sort(memList);
		
		System.out.println("정렬후...");
		for(Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("--------------------------------------");
		
		Collections.sort(memList, new SortNameAsc());
		System.out.println("이름의 오름차순 정렬 후");
		for(Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("--------------------------------------");
	}
}

// 회원관리를 위한 Member클래스 작성하기
// 회원번호의 오름차순으로 정렬될 수 있는 내부 정렬 기준을 추가해서 작성한다.
class Member implements Comparable<Member> {
	private int num; // 회원번호
	private String name; // 회원이름
	private String tel; // 전화번호

	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}

	// 회원 번호의 오름차순을 기준으로 한다.
	@Override
	public int compareTo(Member mem) {
//		if (this.getNum() > mem.getNum()) {
//			return 1;
//		} else if (this.getNum() < mem.getNum()) {
//			return -1;
//		} else {
//			return 0;
//		}
		
		//Wrapper클래스를 이용하는 방법 1
//		return new Integer(this.getNum()).compareTo(mem.getNum()); //오름차순
//		return new Integer(this.getNum()).compareTo(mem.getNum()) * -1; //내림차순
		
		//Wrapper클래스를 이용하는 방법 2
		return Integer.compare(this.getNum(), mem.getNum());// 오름차순
//		return Integer.compare(this.getNum(), mem.getNum()); * -1// 내림차순
	}
}

// 회원 이름의 오름차순으로 정렬될 수 있는 외부 정렬 기준 클래스를 작성하시오
// 클래스명 : SortNameAsc
class SortNameAsc implements Comparator<Member> {

	@Override
	public int compare(Member mem1, Member mem2) {
		return mem1.getName().compareTo(mem2.getName()); // 이름의 오름차순일 때
//		return mem1.getName().compareTo(mem2.getName()) * -1; // 이름의 내림차순
		
//		if (name1.getName().compareTo(name2.getName()) > 0) {
//			return 1;
//		} else if (name1.getName().compareTo(name2.getName()) < 0) {
//			return -1;
//		} else {
//			return 0;
//		}
	}

}
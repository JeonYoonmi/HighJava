package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ArrayListTest02 {
/*
 * 문제) 5명의 사람 이름을 입력 받아 ArrayList에 저장한 후에 이들 중에 '김'씨 성의 이름을 모두 출력하시오
 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		ArrayList<String> nameList = new ArrayList<String>();
		System.out.println("5명의 이름을 입력하세요.");
		for(int i = 1; i <= 5; i ++) {
			System.out.print(i + "번째 이름 입력 : ");
			String name = scan.next();
			nameList.add(name);
		}
		
		System.out.println();
		System.out.println("김씨 성을 가진 사람들...");
		for(int i=0; i<nameList.size(); i++) {
//			if(nameList.get(i).charAt(0) == '김') {
//				System.out.println(nameList.get(i));
//			}
			
//			if(nameList.get(i).substring(0,1).equals("김")) {
//				System.out.println(nameList.get(i));
//			}

//			if(nameList.get(i).indexOf("김") == 0) {
//				System.out.println(nameList.get(i));
//			}
			
			if(nameList.get(i).startsWith("김")) {
				System.out.println(nameList.get(i));
			}
			
			//중간의 '김'자도 찾아 준다.
//			if(nameList.get(i).contains("김")) {
//				System.out.println(nameList.get(i));
//			}
		}
		
//		ArrayList<String> mem = new ArrayList<String>();
//		
//		System.out.println("5명의 이름을 입력해주세요");
//		for(int i = 0; i < 5; i++) {			
//			System.out.print("이름>");
//			mem.add(s.nextLine());
//		}
//		
//		
//		for (String m : mem) {
//			if(m.substring(0,1).equals("김") == true) {				
//				System.out.println(m);
//			}
//		}
//
//		for (String m : mem) {
//			if(String.valueOf(m.charAt(0)).equals("김")) {				
//				System.out.println(m);
//			}
//		}
//		
//		for (String m : mem) {
//			if(m.indexOf("김") == 0) {				
//				System.out.println(m);
//			}
//		}
//		
//		for (String m : mem) {
//			String regex = "[김].{2}";
//			if(Pattern.matches(regex, m) == true) {
//				System.out.println(m);
//			}
//		}
		
	}

}

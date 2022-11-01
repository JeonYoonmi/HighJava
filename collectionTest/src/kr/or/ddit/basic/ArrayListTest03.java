package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

public class ArrayListTest03 {
/*
 *  문제) 5명의 별명을 입력받아 ArrayList에 저장한 후 이들 중 별명의 길이가 제일 긴 별명들을 출력하시오.
 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		ArrayList<String> aliasList = new ArrayList<String>();
		System.out.println("5명의 별명을 입력하세요");
		for(int i=1; i<=5; i++) {
			System.out.print(i + "번째 별명 : ");
			String alias = scan.nextLine();
			aliasList.add(alias);
		}
		
		//제일 긴 별명의 길이가 저장될 변수를 선언한다.
		//		=> 이 변수에는 리스트의 첫번째 데이터의 길이로 초가화 한다.
		int maxLength = aliasList.get(0).length();
		
		for(int i=1; i<aliasList.size(); i++) {
			if(maxLength < aliasList.get(i).length()) {
				maxLength = aliasList.get(i).length();
			}
		}
		////////////////////////////////////////////////////
		System.out.println("제일 긴 별명들...");
		for(String alise : aliasList) {
			if(alise.length() == maxLength) {
				System.out.println(alise);
			}
		}
		
		
//		ArrayList<String> nameList = new ArrayList<String>();
//		
//		System.out.println("5명의 별명을 입력해주세요.");
//		int max = 0;
//		for(int i=0; i<5; i++) {
//			System.out.print(i+1 + "번째 별명 입력 : ");
//			String name = scan.next();
//			nameList.add(name);
//			
//			if(name.length() > max) {
//				max = name.length();
//			}
//		}
//		
//		for (String name : nameList) {
//			if(name.length() == max) {
//				System.out.println(name);
//			}
//		}
	}

}

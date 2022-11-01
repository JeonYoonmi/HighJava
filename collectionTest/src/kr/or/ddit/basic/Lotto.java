package kr.or.ddit.basic;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
/*
 로또를 구매하는 프로그램 작성하기
 
 사용자는 로또를 구매할 때 구매할 금액을 입력하고
 입력한 금액에 맞게 로또번호를 출력한다.
 (단, 로또 한장의 금액은 1000원이며 최대 100장까지만 구입할 수 있고,
      거스름돈도 계산하여 출력한다.)

	==========================
         	Lotto 프로그램
	--------------------------
	 1. Lotto 구입
	 2. 프로그램 종료
	==========================		 
	메뉴선택 : 1  <-- 입력
			
	 Lotto 구입 시작
		 
	(1000원에 로또번호 하나입니다.)
	금액 입력 : 2500  <-- 입력
			
	행운의 로또번호는 아래와 같습니다.
	로또번호1 : 2,3,4,5,6,7
	로또번호2 : 20,21,22,23,24,25
			u
	받은 금액은 2500원이고 거스름돈은 500원입니다.

	==========================
         Lotto 프로그램
	--------------------------
	 1. Lotto 구입
	 2. 프로그램 종료
	==========================		 
	메뉴선택 : 1  <-- 입력
			
	 Lotto 구입 시작
		 
	(1000원에 로또번호 하나입니다.)
	금액 입력 : 900  <-- 입력
	
	입력 금액이 너무 적습니다. 로또번호 구입 실패!!!

	==========================
         Lotto 프로그램
	--------------------------
	 1. Lotto 구입
	 2. 프로그램 종료
	==========================		 
	메뉴선택 : 1  <-- 입력
			
	 Lotto 구입 시작
		 
	(1000원에 로또번호 하나입니다.)
	금액 입력 : 101000  <-- 입력
	
	입력 금액이 너무 많습니다. 로또번호 구입 실패!!!
			
   	 ==========================
         Lotto 프로그램
	--------------------------
	  1. Lotto 구입
	  2. 프로그램 종료
	==========================		 
	메뉴선택 : 2  <-- 입력
		
	감사합니다
 */
public class Lotto {
	Set<Integer> userList;
	List<Integer> lotto;
	
	Scanner s = new Scanner(System.in);
	
	int money;
	int change;
	int num;
	
	public static void main(String[] args) {
			new Lotto().start();
	}

	public void start() {
		while (true) {
			System.out.println("==========================");
			System.out.println("Lotto 프로그램");
			System.out.println("--------------------------");
			System.out.println("1. Lotto 구입");
			System.out.println("2. 프로그램 종료");
			System.out.println("==========================");
			System.out.print("메뉴선택 : ");
			int input = s.nextInt();
			switch (input) {
			case 1:
				store();
				break;
			case 2:
				System.out.println("감사합니다.");
				System.exit(0);
			default:
				System.out.println("번호를 잘못 입력했습니다.");
			}
		}

	}
	
	//1~45 사이의 서로 다른 난수 6개를 만들어서 저장
	public void createNum() {
		userList = new HashSet<Integer>();
		
		//1~45사이의 난수를 저장
		while(userList.size()<6) {
			userList.add((int)(Math.random() * 45 + 1));
		}
		
		lotto = new ArrayList<Integer>(userList);
		Collections.sort(lotto);
		
	}
	
	public void store() {
		do {
			System.out.println("Lotto 구입 시작");
			System.out.println("(1000원에 로또번호 하나입니다.)");
			System.out.println();
			System.out.print("금액 입력 : ");
			money = s.nextInt();
			if(money < 1000) {
				System.out.println("입력 금액이 적습니다. 로또번호 구입 실패!");
			}else if(money >= 101000) {
				System.out.println("입력 금액이 너무 많습니다. 로또번호 구입 실패!!!");
			}
		}while(money < 1000 || money >= 101000);
		System.out.println("행운의 로또번호는 아래와 같습니다.");
		num = money / 1000;
		change = money % 1000;
		for(int i=0; i<num; i++) {
			createNum();
			System.out.println("로또번호" + i + " : " + lotto);
		}
		System.out.println("받은 금액은 " + money + "원이고 거스름돈은 " + change + "원 입니다.");
	}
	
//	Scanner scan = new Scanner(System.in);
//	//풀이
//	public static void main(String[] args) {
//		new Lotto().lottoStart();
//	}
//	
//	//로또 시작 메서드
//	public void lottoStart() {
//		while(true) {
//			int choice = displayMenu();
//			
//			switch (choice) {
//			case 1:
//				buyLotto();
//				break;
//			case 2:
//				System.out.println();
//				System.out.println("감사합니다.");
//				return;
//
//			default:
//				System.out.println("작업 번호를 잘못 입력하셨습니다.");
//				break;
//			}
//		}
//	}
//	
//	//로또 구매처리를 하는 메서드
//	private void buyLotto() {
//		System.out.println();
//		
//		System.out.println("Lotto 구입 시작");
//		System.out.println();
//		System.out.println("1000원에 로또번호 하나입니다.");
//		System.out.print("금액입력 : ");
//		int money = scan.nextInt();
//		
//		if(money < 1000) {
//			System.out.println("입력 금액이 너무 적습니다. 로또번호 구입 실패!!");
//			return;
//		}else if(money >= 101000){
//			System.out.println("입력 금액이 너무 많습니다. 로또번호 구입 실패!!");
//			return;
//		}
//		
//		//////////////////////////////////////////////////////////
//		//로또 구매 작업 시작...
//		HashSet<Integer> lottoSet = new HashSet<Integer>();
//		
//		int count = money / 1000; //구매할 매수 개산하기
//		
//		System.out.println();
//		System.out.println("행운의 로또번호는 아래와 같습니다...");
//		
//		for(int i = 1; i <= count; i++) { //구매할 매수만큼 반혹
//			//로또번호 생성
//			while(lottoSet.size() < 6) {
//				lottoSet.add((int)(Math.random() * 45 +1)); //난수를 만들어서 Set에 추가하기
//			}
//			
//			ArrayList<Integer> lottoList = new ArrayList<Integer>(lottoSet);
//			Collections.sort(lottoList); //만들어진 로또번호 정렬하기
//			System.out.println("로또번호" + i + " :" + lottoList);
//			
//			lottoSet.clear();
//		}
//		
//		System.out.println("받은 금액은 " + money + "원이고, 거스름돈은 " + (money % 1000) + "원 입니다.");
//		
//	}
//
//	//메뉴를 출력하고 입력한 작업번호를 반환하는 메서드
//	private int displayMenu() {
//		System.out.println("==========================");
//		System.out.println("Lotto 프로그램");
//		System.out.println("--------------------------");
//		System.out.println("1. Lotto 구입");
//		System.out.println("2. 프로그램 종료");
//		System.out.println("==========================");
//		System.out.print("메뉴선택 : ");
//		return scan.nextInt();
//	}
}

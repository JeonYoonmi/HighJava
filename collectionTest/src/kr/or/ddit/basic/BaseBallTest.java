package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BaseBallTest {
/*
 * 문제) Set을 이용하여 숫자야구 게임 프로그램을 작성하시오.
 * 		컴퓨터의 숫자는 난수를 이용하여 구한다.(이 때 Set을 이용한다.)
 * 		(출력할 때 스트라이크는 'S', 볼은  'B'로 나타낸다.)
 * 
 * 예시)
 * 		컴퓨터의 난수 => 9	5	7
 * 
 * 실행예시)
 * 숫자입력 => 3 5 6
 * 3 5 6 => 1S 0B
 * 숫자입력 => 7 8 9
 * 7 8 9 => 0S 2B
 * 숫자입력 =>  9 7 5
 * 9 7 5 => 1S 2B
 * 숫자입력 =>  9 5 7
 * 9 5 7 => 3S
 * 
 * 축하합니다.
 * 당신은 4번만에 맞췄군요!!
 */
	
	ArrayList<Integer> numList;		//난수가 저장될 List
	ArrayList<Integer> userList;	//사용자가 입력한 값이 저장될 List
	
	int strike;	//스트라이크의 개수가 저장될 개수
	int ball;	//볼의 개수가 저장될 변수
	
	Scanner scan = new Scanner(System.in);
	
	public void gameStart() {
		//난수 만드는 메서드 호출
		createNum();
		
		//확인용
		System.out.println("텀퓨터 난수 :" + numList);
		
		int cnt = 0; //몇번만에 맞췄는지를 저장하는 변수 선언
		
		do {
			cnt++;
			inputNum();	//사용자가 입력하는 메서드 호출
			
			ballCount();	//볼카운트를 구하는 메서드 호출
		}while(strike != 3);	//3 strike가 될 때까지 반복한다. 
		
		System.out.println();
		System.out.println("축하합니다...");
		System.out.println("당신은 " + cnt + "번째만에 맞췄습니다.");
	}
	
	//1~9 사이의 서로 다른 난수 3개를 만들어서 List에 저장하는 메서드 (Set 이용)
	public void createNum() {
		Set<Integer> numSet = new HashSet<Integer>();
		
		//1~9사이의 난수 3개 만들기
		while(numSet.size()<3) {
			numSet.add((int)(Math.random() * 9 + 1));
		}
		
		//만들어진 난수를 List에 저장하기
		numList = new ArrayList<Integer>(numSet);
		
		//List의 데이터를 섞어준다.(set은 한자리수만 정렬이 된다.)
		Collections.shuffle(numList);
		
	}
	
	//사용자로부터 3개의 정수를 입력받아 List에 저장하는 메서드
	public void inputNum() {
		int num1, num2, num3;
		do {
			System.out.println("숫자입력 => ");
			num1 = scan.nextInt();
			num2 = scan.nextInt();
			num3 = scan.nextInt();
			if(num1 == num2 || num1 == num3 || num2 == num3) {
				System.out.println("중복되는 숫자는 입력할 수 없습니다. 다시 입력해주세요.");
			}
		}while(num1 == num2 || num1 == num3 || num2 == num3);
		
		userList = new ArrayList<Integer>();
		
		userList.add(num1);
		userList.add(num2);
		userList.add(num3);
		
	}	
	
	//스트라이크와 볼을 판정하고 결과를 출력하는 메서드
	public void ballCount() {
		strike = 0;
		ball = 0;	//스트라이크와 볼의 개수를 0으로 초기화한다.
		
		for(int i=0; i<numList.size(); i++) {
			for(int j=0; j<userList.size(); j++) {
				if(numList.get(i) == userList.get(j)) {	//값이 같은지 검사
					if(i == j) {
						strike++;
					}else {
						ball++;
					}
				}
			}
		}
		
		//볼카운트 결과 출력하기
		System.out.println(userList.get(0) + ", " + userList.get(1) + ", " + userList.get(2) + " ==> " + strike + "S" + ball + "B");
	}
	
	public static void main(String[] args) {
		new BaseBallTest().gameStart();
//		HashSet<Integer> random = new HashSet<Integer>();
//		while(random.size() < 3) {
//			random.add((int)(Math.random() * 9 + 1));
//		}
//		
//		List<Integer> ran = new ArrayList<Integer>(random);
//		Collections.shuffle(ran);
//		
//		Scanner sc = new Scanner(System.in);
//		List<Integer> input = new ArrayList<Integer>();
//		
//		int count =0;
//		int b = 0;
//		int s = 0;
////		int o = 0;
//		do {
//			 s = 0;
//			 b = 0;
////			 o = 0;
//			count++;
//			for (int i = 0; i < 3; i++) {
//				System.out.print("숫자를 입력해주세요>");
//				input.add(sc.nextInt());
//			}
//			
//			for (Integer num : input) {
//				boolean isAdd = random.add(num);
//				if(isAdd == false) {
//					b++;
//				}else {
//					random.remove(num);
//				}
//			}
//			
//			for (int i=0; i<3; i++) {
//				if(input.get(i) == ran.get(i)) {
//					s++;
//					b--;
//				}
//			}
//			
////			if (input.get(0) == ran.get(1) || input.get(0) == ran.get(2)) {
////				b++;
////			}else if(input.get(1) == ran.get(0) || input.get(1) == ran.get(2)) {
////				b++;
////			}else if(input.get(2) == ran.get(0) || input.get(2) == ran.get(1)) {
////				b++;
////			}
////			for (Integer num2 : input) {
////				if (ran.contains(num2) == false) {
////					o++;
////				}
////			}
////			s = 3 - (o + b);
//			System.out.println(input + " => " + s + "s " + b + "b " /*+ o + "o"*/);
//			input.clear();
//		}while(s != 3);
//		System.out.println("축하합니다!!");
//		System.out.println("당신은 " + count + "번만에 맞췄군요!");
	}

}

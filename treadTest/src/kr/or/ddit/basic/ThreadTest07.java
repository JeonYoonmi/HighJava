package kr.or.ddit.basic;

import java.util.ArrayList;

import java.util.List;

import javax.swing.JOptionPane;
import javax.xml.crypto.Data;

/*
	컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오.
	
	컴퓨터의 가위 바위 보는 난수를 이용해서 결정하고,
	사용자의 입력은 showInputDaialog()메서드를 이용해서 입력 받는다.
	
	입력시간은 5초로 제한하고 카운트 다운을 진행한다.
	5초안에 입력이 없으면 게임에 진 것으로 처리한다.
	
	5초안에 입력이 있으면 승패를 구해서 출력한다.
	
	결과 예시1) => 5초안에 입력을 못했을 경우
		-- 결  과 --
		시간초과로 당신이 졌습니다.
		
	결과 예시2) => 5초 안에 입력을 했을 경우
		-- 결  과 --
		컴퓨터 : 가위
		사용자 : 바위
		결   과 : 당신이 이겼습니다.
 */
public class ThreadTest07 {
	public static boolean inputCheck = false;
	
	public static void main(String[] args) {
		GameTimer gt = new GameTimer();
		
		//컴퓨터의 가위, 바위, 보 정하기(난수를 이용한다.)
		String[] data = {"가위", "바위", "보"};
		int index = (int)(Math.random() * 3);	//0~2사이의 난수만들기
		String com = data[index];
		
		//사용자의 가위 바위 보 입력 받기
		gt.start(); //카운트 다운 시작...
		String man = null;
		do {
			man = JOptionPane.showInputDialog("가위 바위 보를 입력하세요");
//		}while(!(man != null && (man.equals("가위") || man.equals("바위") || man.equals("보"))));
		}while(man == null || (!man.equals("가위") && !man.equals("바위") && !man.equals("보")));
		
		inputCheck = true;
		
		//결과 판정하기
		String result = "";
//		if(com.equals(man)) {
//			result = "비겼습니다...";
//		}else if(com.contentEquals("가위") && man.equals("보") || com.contentEquals("바위") && man.equals("가위") || com.contentEquals("보") && man.equals("바위")) {
//			result = "당신이 졌습니다.";
//		}else{
//			result = "당신이 이겼습니다.";
//		}
		
		switch(com + man) {
		case "가위가위" :
		case "바위바위" :
		case "보보" : result = "비겼습니다."; break;
		case "가위보" :
		case "가위바위" :
		case "보바위" : result = "당신이 졌습니다."; break;
		default : result = "당신이 이겼습니다.";
		}
		
		System.out.println("   -- 결과  --");
		System.out.println("사용자 : " + man);
		System.out.println("컴퓨터 : " + com);
		System.out.println("결   과 : " + result);
	}
	
}

class GameTimer extends Thread{
	@Override
	public void run() {
		System.out.println("카운트 다운 시작...");
		
		for(int i=5; i>=1; i--) {
			if(ThreadTest07.inputCheck == true) {
				return;
			}
			
			System.out.println(i);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		System.out.println("   -- 결과  --");
		System.out.println("시간초과로 당신이 졌습니다.");
		System.exit(0);
	}
}


//	public static void main(String[] args) {	//메인 메소드도 하나의 쓰레드로 볼 수 있음
//		Thread th1 = new Count();
//		Thread th2 = new UserData();
//		
//		th1.start();
//		th2.start();
//		
//	}
//}
//
////결과를 확인하는 쓰레드
//class result extends Thread{
//	@Override
//	public void run() {
//		System.out.println("-- 결과 --");
//		System.out.println("사용자 : " + UserData.user);
//		System.out.println("컴퓨터 : " + ComData.com);
//		switch(UserData.user) {
//		case "가위":
//			System.out.println(ComData.com.equals("바위") ? "컴퓨터가 이겼습니다." : (ComData.com.equals("보") ? "사용자가 이겼습니다." : "비겼습니다."));
//			break;
//		case "바위":
//			System.out.println(ComData.com.equals("보") ? "컴퓨터가 이겼습니다." : (ComData.com.equals("가위") ? "사용자가 이겼습니다." : "비겼습니다."));
//			break;
//		case "보":
//			System.out.println(ComData.com.equals("가위") ? "컴퓨터가 이겼습니다." : (ComData.com.equals("주먹") ? "사용자가 이겼습니다." : "비겼습니다."));
//			break;
//		}
//
//	}
//	
//}
//
////데이터를 입력 받는 쓰레드
//class UserData extends Thread{
//	public static String user;
//	public static boolean inputCheck = false;
//	
//	@Override
//	public void run() {
//		while(true) {			
//			user = JOptionPane.showInputDialog("가위, 바위, 보 중 하나를 입력해주세요");
//			if(user.equals("가위") || user.equals("바위") || user.equals("보")) {			
//				inputCheck = true;
//				return;
//			}else {
//				System.out.println("다시 입력해주세요.");
//			}
//		}
//	}
//	
//}
//
////컴퓨터
//class ComData extends Thread {
//	public static String com;
//	Thread th = new result();
//
//	@Override
//	public void run() {
//		ArrayList<String> data = new ArrayList<String>();
//		data.add("가위");
//		data.add("바위");
//		data.add("보");
//		
//		if (UserData.inputCheck == true) {
//			int ran = (int) (Math.random() * 3);
//			com = data.get(ran);
//			th.start();
//		}
//
//	}
//
//}
//
////카운트 다운을 진행하는 쓰레드
//class Count extends Thread{
//	@Override
//	public void run() {
//		Thread th = new ComData();
//		
//		for(int i=5; i>=1; i--) {			
//			if(UserData.inputCheck == true) {
//				th.start();
//				return;
//			}
//			
//			System.out.println(i);
//			
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO: handle exception
//			}
//		}
//		System.out.println("-- 결과 --");
//		System.out.println("시간초과로 당신이 졌습니다.");
//		System.exit(0);
//	}
//	
//}
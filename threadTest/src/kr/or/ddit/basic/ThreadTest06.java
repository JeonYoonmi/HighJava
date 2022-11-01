package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class ThreadTest06 {

	public static void main(String[] args) {
		Thread th1 = new DataInput();	//DataInput가 상속받았기 때문에 이렇게 사용해도 된다. 원래는 DataInput th1 = new DataInput();
		Thread th2 = new CountDown();
		
		th1.start();
		th2.start();
	}

}

class DataInput extends Thread{
	
	public static boolean inputCheck = false;	//입력이 완료되면 true로 변경된다.
	
	@Override
	public void run() {
		String str = JOptionPane.showInputDialog("아무거나 입력하세요.");
		
		inputCheck = true;
		
		System.out.println("입력한 값 : " + str);
	}
}

//class 카운트 다운을 진행하는 쓰레드
class CountDown extends Thread{
	@Override
	public void run() {
		for(int i=10; i>=1; i--){
			
			//입력이 완료되었는지 여부를 검사한다. => 입력이 완료되었으면 쓰레드를 종료시킨다.
			if(DataInput.inputCheck==true) {
				return;
			}
			
			System.out.println(i);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		System.out.println("10초가 지났습니다. 프로그램을 종료합니다.");
		System.exit(0);
	}
}
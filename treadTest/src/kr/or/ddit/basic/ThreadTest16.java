package kr.or.ddit.basic;

//은행의 입출금을 쓰레드로 처리하는 예제

public class ThreadTest16 {

	private int balance;		//잔액이 저장될 변수
	
	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	//입금하는 메서드
	public void deposit(int money) {
		balance += money;
	}
	
	//출금하는 메서드(반환값 => 출금성공 :  true, 출금실패 : false)
	public synchronized boolean withdraw(int money) {	//synchronized : 메소드 하나가 실행되고 있으면 그 메소드가 끝날때까지 기다리고 있다
		if(balance >= money){
			for(int i=1; i<=1000000000; i++);	//공회전
			balance -= money;
			System.out.println("메서드 안에서 balance = " + balance);
			return true;
		}else {
			return false;
		}
	}
	//초기화 블럭으로 하는 방법
//	public boolean withdraw(int money) { // synchronized : 메소드 하나가 실행되고 있으면 그 메소드가 끝날때까지 기다리고 있다
//		synchronized (this) {
//			if (balance >= money) {
//				for (int i = 1; i <= 1000000000; i++)
//					; // 공회전
//				balance -= money;
//				System.out.println("메서드 안에서 balance = " + balance);
//				return true;
//			} else {
//				return false;
//			}
//		}
//	}
	
	public static void main(String[] args) {
		ThreadTest16 acount = new ThreadTest16();
		acount.setBalance(10000);	//잔액을 만원으로 설정한다.
		
		//익명 구현체로 쓰레드 구현
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				boolean result = acount.withdraw(6000);	//6000원 출금하기
				System.out.println("쓰레드에서 result = " + result + ", balance = " + acount.getBalance());
				
			}
		};
		
		Thread th1 = new Thread(r);
		Thread th2 = new Thread(r);
		
		th1.start();
		th2.start();
		
	}

}

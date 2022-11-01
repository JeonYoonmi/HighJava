package kr.or.ddit.basic;

public class ThreadTest15 {

	public static void main(String[] args) {
		ShareObject sObj = new ShareObject();
		
		TestThread th1 = new TestThread("첫번 째 쓰레드", sObj);
		TestThread th2 = new TestThread("두번 째 쓰레드", sObj);
		
		th1.start();
		th2.start();
		
		
	}

}

class TestThread extends Thread{
	private ShareObject sObj;

	public TestThread(String name, ShareObject sObj) {
		super(name);	//쓰레드의 이름 설정
		this.sObj = sObj;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=10; i++) {
			sObj.add();
		}
	}
	
}

//공통 객체
class ShareObject{
	private int sum = 0;
	
	//동기화 처리 하기
	public void add() {
//	public synchronized void add() {	//메서드 자체에 동기화를 처리하는 방법
		
		synchronized (this) {
			int n = sum;
		
			n += 10;
		
			sum = n;
		
			System.out.println(Thread.currentThread().getName() + " 합계 : " + sum);
		}
		
	}
	
}

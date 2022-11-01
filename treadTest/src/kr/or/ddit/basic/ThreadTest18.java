package kr.or.ddit.basic;

public class ThreadTest18 {

	public static void main(String[] args) {
		//wait(), notify()를 이용한 두 쓰레드에서 번갈아 한 번 씩 실행하는 예제
		//wait(), notify(), notifyAll()은 동기화 영역에서만 사용 가능하다.
		
		WorkObject workObj = new WorkObject();
		
		ThreadA th1 = new ThreadA(workObj);
		ThreadB th2 = new ThreadB(workObj);
		
		th1.start();
		th2.start();
	}

}

//WOrkObject와 a()메서드를 호출하는 쓰레드
class ThreadA extends Thread{
	private WorkObject workObj;

	public ThreadA(WorkObject workObj) {
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i=0; i<10; i++) {
			workObj.a();	//a()메서드 호출...
		}
		
		synchronized (workObj) {
			workObj.notify();
		}
	}
}

//WorkObject의 b()메서드를 호출하는 쓰레드
class ThreadB extends Thread{
	private WorkObject workObj;
	
	public ThreadB(WorkObject workObJ) {
		this.workObj = workObJ;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			workObj.b();	//a()메서드 호출
		}
		
		synchronized (workObj) {
			workObj.notify();
		}
	}
}


//공통으로 사용할 클래스
class WorkObject {
	public synchronized void a() {
		System.out.println("a() 매서드 실행중...");

		notify();
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}

	public synchronized void b() {
		System.out.println("b() 매서드 실행중...");

		notify();
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}

}

package kr.or.ddit.basic;

public class ThreadTest02 {

	public static void main(String[] args) {
		//멀티 쓰레드 프로그램
		
		//Tread를 사용하는 방법
		
		//방법1 => Thread클래스를 상속하는 방법
		//Tread클래스를 상속한 class를 작성한 후 
		//이 인스턴스의 start()메서드를 호출해서 실행한다.
		MyThread1 th1 = new MyThread1();	//인스턴스 생성
		th1.start();		//Thread가 실행할 수 있도록 공간을 만들어 준 후 run을 호출해준다. 그 후 사라짐.
		
		//방법2-1 => Runnable인터페이스를 구현하는 방법
		//Runnable인터페이스를 구현한 class의 인스턴스를 생성한다.
		//실행할 Thread객체를 생성하는데 이 떄 Thread객체의 생성자의 Runnable을 구현한 class의 인스턴스를 인수값으로 넣어준다.
		//이 때 생성된 Thread객체의 start()메서드를 호출해서 실행한다.
		MyThread2 r = new MyThread2();	//인스턴스 생성
		Thread th2 = new Thread(r);		//Thread객체 생성
		th2.start();
		
		//방법2-2 => 익명구현체를 이용하는 방법
		//인터페이스 안에 있는 메서드는 구현이 안되어 있음
		//추상클래스는 일부만 구현되어 있음 => 객체를 생성할 수 없음.
		Runnable r2 = new Runnable() {	//일회용, 클래스 이름이 없음 => 익명
			
			@Override
			public void run() {
				for(int i=1; i<=200; i++) {
					System.out.print("@");
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						
					}
				}
			}
		};
		Thread th3 = new Thread(r2);
		th3.start();
		
		System.out.println("main메서드 끝...");	 //메인 메서드는 끝났지만 쓰레드가 끝날때까지 계속 동작하고 있다. 각각의 쓰레드는 런이라는 메서드가 처리가 다 끝나면 끝난다.
	}

}


//방법1에 해당하는 class작성
class MyThread1 extends Thread{	//이 상태에서 implements를 또 사용할 수 있다.
	//run메서드를 재정의 한다.
	@Override
	public void run() {
		//이 run()메서드는 쓰레드가 처리할 내용을 기술하는 영역이다.
		for(int i=1; i<=200; i++) {
			System.out.print("*");
			try {
				//Thead.sleep(시간); => 주어진 '시간'동안 작업을 잠시 멈춘다.
				//					=> '시간'은 밀리세컨드 단위를 사용한다. 즉, 1초를 1000으로 표현한다.
				Thread.sleep(100);
			} catch (InterruptedException e) {

			}
		}
	}
}

//방법2에서 해당하는 class작성
class MyThread2 implements Runnable{
	//run()메서드 재정의
	@Override
	public void run() {
		for(int i=1; i<=200; i++) {
			System.out.print("$");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				
			}
		}
	}
}
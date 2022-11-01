package kr.or.ddit.basic;

public class ThreadTest12 {

	public static void main(String[] args) {
		YieldTest th1 = new YieldTest("1번 쓰레드");
		YieldTest th2 = new YieldTest("2번 쓰레드");

		th1.start();
		th2.start();

		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("************************ 1111111111111111111111111111111");
		th1.work = false;

		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("************************ 2222222222222222222222222222222");

		th1.stop = true;
		th2.stop = true;
	}

}

//yield()메서드 연습용 쓰레드
class YieldTest extends Thread {
	public boolean stop = false;
	public boolean work = true;

	public YieldTest(String name) {
		super(name); // 쓰레드의 이름 설정
		// this : 자기 자신 참조값을 가지고 있는 참조 변수
		// this() : 자기 자신 생성자 중에 처음에 실행되는 생성자가 아닌 ()안에 들어 있는 타입을 가진 생성자를 부른다.
		// super : 부모의 참조 값을 가지고 있는 변수
		// super() : 부모의 생성자 중에 처음에 실행되는 생성자가 아닌 ()안에 들어있는 타입을 가진 생성자를 부른다.
	}

	@Override
	public void run() {
		while (!stop) {
			if (work) {
				System.out.println(getName() + "작업 중..."); // getName() : super(name);여기에서 생성된 이름을 가지고 온다.
			} else {
				System.out.println(getName() + "양보중...");
				Thread.yield();
			}
		}
		System.out.println(getName() + "쓰레드 탈출...");
	}
}
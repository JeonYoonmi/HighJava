package kr.or.ddit.basic;

//데몬 쓰레드 연습 => 자동 저장하는 쓰레드

public class ThreadTest08 {

	public static void main(String[] args) {
		AutoSave auto = new AutoSave();
		
		//데몬 쓰레드로 설정하기 => 반드시 start()메서드  호출 전에 실행한다.
		auto.setDaemon(true);;
		
		for(int i=1; i<=20; i++) {
			System.out.println(i);
			try {
				Thread.sleep(1000);

			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		System.out.println("main 쓰레드 종료...");
	}
}

//자동 저장하는 쓰레드(3초에 한 번씩 자동 저장하는 쓰레드)
class AutoSave extends Thread{
	
	public void save() {
		System.out.println("작업 내용을 저장합니다...");
	}
	
	@Override
	public void run() {
		while(true) {	//무한루프 형식으로 반복문을 만든다.
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
			save();
		}
	}
}
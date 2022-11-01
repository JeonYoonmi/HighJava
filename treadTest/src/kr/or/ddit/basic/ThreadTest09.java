package kr.or.ddit.basic;

//쓰레드의 상태를 출력히는 예제

public class ThreadTest09 {

	public static void main(String[] args) {
		TargetThread target = new TargetThread();
		
		displayThreadState th = new displayThreadState(target);
		
		th.start();
		
	}
}

//TargetThread의 상태값을 출력하는 쓰레드
class displayThreadState extends Thread{
	private TargetThread target;

	//생성자
	public displayThreadState(TargetThread target) {
		this.target = target;
	}
	
	@Override
	public void run() {
		while(true) {
			//쓰레드의 현재 상태값 구하기
			Thread.State state = target.getState();
			System.out.println("TargetThread의 상태값 : " + state);
			
			if(state == Thread.State.NEW) {	//쓰레드의 상태가 NEW상태면,,,
				target.start();
			}
			
			if(state == Thread.State.TERMINATED) {	//쓰레드의 상태가 종료 상태이면
				break;	//반복문을 빠져나간다.
			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
			
			
		}
	}
	
}

//쓰레드 상태의 검사 대상이 되는 쓰레드
class TargetThread extends Thread{
	@Override
	public void run() {
		for(long i=1L; i<=10_000_000_000L; i++) {}	//시간 지연용
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
		for(long i=1L; i<=10_000_000_000L; i++) {}	//시간 지연용
				
	}
}
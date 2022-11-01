package kr.or.ddit.basic;

/*
	1 ~ 20억까지의 합계를 구하는 프로그램을 하나의 쓰레드가 단독으로 처리할 때와
	여러개의 쓰레드가 협력해서 처리할 때의 경과시간을 비교해보자
*/
public class ThreadTest04 {

	public static void main(String[] args) {
		//단독으로 처리하는 경우
		SumThread sm = new SumThread(1L, 2_000_000_000L);
		
		long startTime = System.currentTimeMillis();
		sm.start();
		
		try {
			sm.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		long endTime = System.currentTimeMillis();
		
		System.out.println("단독으로 처리했을 때의 경과시간 : " + (endTime - startTime));
		System.out.println();
		/////////////////////////////////////////////////////////////////////////
		
		//여럿이 협력해서 처리하는 경우
//		SumThread sm1 = new SumThread(1L, 500_000_000L);
//		SumThread sm2 = new SumThread(500_000_000L, 1_000_000_000L);
//		SumThread sm3 = new SumThread(1_000_000_000L, 1_500_000_000L);
//		SumThread sm4 = new SumThread(1_500_000_000L, 2_000_000_000L);
//		
//		startTime = System.currentTimeMillis();
//		
//		sm1.start();
//		sm2.start();
//		sm3.start();
//		sm4.start();
//		
//		try {
//			sm1.join();
//			sm2.join();
//			sm3.join();
//			sm4.join();
//		} catch (InterruptedException e) {
//			// TODO: handle exception
//		}
//		
//		endTime = System.currentTimeMillis();

		SumThread[] sumArr = new SumThread[] {
				new SumThread(            1L,   500_000_000L),
				new SumThread(  500_000_001L, 1_000_000_000L),
				new SumThread(1_000_000_001L, 1_500_000_000L),
				new SumThread(1_500_000_001L, 2_000_000_000L)
		};
		
		startTime = System.currentTimeMillis();
		
		for (SumThread th : sumArr) {
			th.start();
		}
		
		for (SumThread th : sumArr) {
			try {
				th.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		endTime = System.currentTimeMillis();
		
		System.out.println("협력해서 처리한 경과 시간 : " + (endTime - startTime));
	}

}

class SumThread extends Thread{
	//합계를 구할 영역의 시작값과 종료값이 저장될 변수 선언
	private long start;
	private long end;
	
	//생성자
	public SumThread(long start, long end) {
		super();
		this.start = start;
		this.end = end;
	}
	
	@Override
	public void run() {
		long sum = 0L;
		for(long i = start; i <= end; i++) {
			sum += i;
		}
		System.out.println(start + "부터 " + end + "까지의 합계 : " + sum);
	}
	
}
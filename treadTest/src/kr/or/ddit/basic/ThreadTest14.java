package kr.or.ddit.basic;

/*
	쓰레드에서 객체를 공통으로 사용하는 예제
	
	- 원주율을 계산하는 쓰레드와 계산된 원주율을 출력하는 쓰레드가 있다.
	
	- 원주율을 저장하는 객체가 필요하다.
	 이 객체를 생성해서 두 쓰레드에서 공통으로 사용할 수 있도록 처리한다.
*/
public class ThreadTest14 {

	public static void main(String[] args) {
		//공통으로 사용할 객체 생성
		ShareData sd = new ShareData();
		
		//쓰레드 객체를 생성하고 공통으로 사용할 객체를 쓰레드에 주입한다.
		CalcPIThread th1 = new CalcPIThread();
		th1.setSd(sd);
		
		PrintPITread th2 = new PrintPITread(sd);
		
		th1.start();
		th2.start();
	}

}

//원주율을 계산하는 쓰레드
class CalcPIThread extends Thread{
	private ShareData sd;

	//공통으로 사용할 객체를 setter를 이용해서 초기화
	public void setSd(ShareData sd) {
		this.sd = sd;
	}

	@Override
	public void run() {
		/*
			원주율 = (1/1 - 1/3 + 1/5 - 1/7 + 1/9 - ....) * 4;
					1 - 3 + 5 - 7 + 9 - ...
					0 - 1 + 2 - 3 + 4
		*/
		double sum = 0.0;
		for(int i=1; i<1_000_000_000; i+=2) {
			if((i/2) % 2 == 0) {		//몫이 짝수
				sum += 1.0 / i;
			}else {		//몫이 홀수
				sum -= 1.0 / i;
			}
		}
		
		sd.result = sum * 4;	//계산이 완료된 값을 공통 객체에 저장
		sd.isOk = true;
	}
	
}

//계산이 완료되면 계산된 원주율을 출력하는 쓰레드
class PrintPITread extends Thread{
	private ShareData sd;
	
	//생성자에서 공통으로 사용할 객체를 주입한다.
	public PrintPITread(ShareData sd) {
		this.sd = sd;
	}
	
	@Override
	public void run() {
		while(true) {
			if(sd.isOk == true) {	//계산이 완료 되었는지 여부 검사
				break;
			}
		}
		
		System.out.println();
		System.out.println("결과 : " + sd.result);
		System.out.println("PI : " + Math.PI);
	}
	
}

//원주율을 관리하는 클래스 (공통으로 사용할 클래스)
class ShareData{
	public double result;		//계상된 원주율이 저장될 변수
	public volatile boolean isOk = false;		//계산이 완료되었는지 여부를 나타내는 변수(계산이 완료되면 true로 변경한다.)
	//volatile => CPU의 각 코어의 캐시가 있는데 
	//이 캐시(버퍼, 임시저장장소 등은 하는 역할이 비슷한데 장치들마다 각자의 처리하는 속도가 있는데
	//		두 장치가 있을 때 한 장치가 느리고 나머지는 빠르다고 하면 빠른애는 일을 다 처리하고 기다려야 한다.
	//		그래서 빠른거와 느린거의 중간 지점. 
	// 		// 블러왔던 데이터를 잠시 저장해서 느린곳에 데이터를 가지고 가서 사용하고 그 데이터를 캐시에도 저장
	//		문제점 : 캐시에 있는 값만 사용하면 새로 변경된 데이터가 있어도 캐시에 있는 데이터를 사용해버린다.
	//		그래서 캐시에 있는 데이터를 사용하다가 필요하면 원본데이터를 사용한다.)
	//를 사용하지 않고 직접 메모리에 데이터 값을 입출력한다.
	
	
}
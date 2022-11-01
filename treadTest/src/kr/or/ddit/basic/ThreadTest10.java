package kr.or.ddit.basic;

//3개의 쓰레드가 각각 알파벳 A~Z까지 출력하는데 출력을 끝낸 순서대로 결과를 나타내는 프로그램 작성하기 
public class ThreadTest10 {

	public static void main(String[] args) {
		DisplayCharacter[] charArr = new DisplayCharacter[] {
				new DisplayCharacter("홍길동"),
				new DisplayCharacter("이순신"),
				new DisplayCharacter("강감찬")
		};
		
		for (DisplayCharacter d : charArr) {
			d.start();
		}
		
		try {
			for (DisplayCharacter d : charArr) {
				d.join();
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
		System.out.println();
		System.out.println("경기결과");
		System.out.println("순위 : " + DisplayCharacter.setRank);
		
	}

}


//A~Z까지 출력하는 쓰레드
class DisplayCharacter extends Thread{
	public static String setRank ="";	//출력을 끝낸 순서대로 쓰레드 이름이 추가될 변수
	private String name;
	
	public DisplayCharacter(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		for (char c = 'A'; c < 'Z'; c++) {
			System.out.println(name + "씨의 출력 문자 : " + c);
			try {
				Thread.sleep((int)(Math.random() * 500));
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		System.out.println(name + "씨 출력 끝...");
		setRank += name + " ";
	}
	
}
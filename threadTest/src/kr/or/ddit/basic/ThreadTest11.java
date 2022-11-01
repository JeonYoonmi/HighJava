package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
	10마리의 말들이 경주하는 경마 프로그램을 작성해보자.
	
	말은 Horse라는 이름의 쓰레드 클래스를 작성하는데 , 이 클래스는 말 이름(String), 등수(int), 현재위치(int)를
	메게 변수로 갖는다. 그리고 이 클래스는 등수를 오름차순으로 처리할 수 있는 내부 정렬 기준이 있다.(Comparable 인터페이스 구현)
	
	경기 구간은 1 ~ 50구간으로 되어있다. (말의 현재 위치는 현재 말이 달리고 있는 구간을 말한다.)
	
	경기 중 중간 중간에 각 말들의 위치를 아래와 같이 나타내시오.
	예)
	01번말 --->---------------------------------------------
	02번말 ------->-----------------------------------------
	03번말 ------>------------------------------------------
	04번말 ->-----------------------------------------------
	...
	10번말 ---->--------------------------------------------
	
	경기가 끝나면 등수 순으로 출력한다.
	
*/
public class ThreadTest11{
	
	public static void main(String[] args) {
		Horse[] horses = new Horse[] {
			new Horse("01번말"),	new Horse("02번말"),	new Horse("03번말"),	
			new Horse("04번말"),	new Horse("05번말"),	new Horse("06번말"),	
			new Horse("07번말"),	new Horse("08번말"),	new Horse("09번말"),	new Horse("10번말")
		};
		
		GameState gs = new GameState(horses);
		
		for (Horse h : horses) {
			h.start();	//말들의 경주 시작...
		}
		
		gs.start();		//말의 현재 위치를 나타내는 쓰레드로 시작한다.
		
		for (Horse h : horses) {
			try {
				h.join();	//모든 밀들의 경기가 끝날 때까지 기다린다.
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		try {
			gs.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
		System.out.println();
		System.out.println("경기 끝...");
		System.out.println();
		
		//등수의 오름차순으로 정렬
		/*
		Arrays.sort(horses);	//배열 정렬하기
		
		for (Horse h : horses) {
			System.out.println(h);
		}
		*/
		
		//배열의 값들을 List에 담고, List를 정렬하여 출력한다.
		ArrayList<Horse> horseList = new ArrayList<Horse>();
		for (Horse h : horses) {
			horseList.add(h);
		}
		
		Collections.sort(horseList);
		for (Horse h : horseList) {
			System.out.println(h);
		}
	}
	
}

class Horse extends Thread implements Comparable<Horse>{
	public static int currentRank;
	
	private String horseName;	//말이름
	private int rank;			//등수
	private int location;		//현재위치
	
	//생성자
	public Horse(String horseName) {
		super();
		this.horseName = horseName;
	}

	public String getHorseName() {
		return horseName;
	}

	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getLocation() {
		return location;
	}

	public void setLocation(int location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "경주마 " + horseName + "은(는) " + rank + "등 입니다.";
	}
	
	//등수를 오름차순
	@Override
	public int compareTo(Horse horse) {
		return Integer.compare(this.rank, horse.getRank());
	}
	
	@Override
	public void run() {
		for(int i=1; i<=50; i++) {
			this.location = i;
			try {
				Thread.sleep((int)(Math.random() * 500));
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	
		//등수를 구해서 현재 말의 등수(rank)에 셋팅한다.
		currentRank++;
		this.rank = currentRank;
	}
	
}

//경기중에 말의 현재 위치를 나타내는 쓰레드
class GameState extends Thread{
	private Horse[] horses;	//현재 경주에 참가한 말들에 대한 정보가 저장된 배열

	//생성자
	public GameState(Horse[] horses) {
		this.horses = horses;
	}
	
	@Override
	public void run() {
		while(true) {
			//모든 말의 경주가 종료되었는지 여부 검사
			if(Horse.currentRank == horses.length) {
				break;
			}

			for(int i=1; i<10; i++) {
				System.out.println();	//빈 줄 출력하기
			}
			
			for(int i=0; i<horses.length; i++) {
				System.out.print(horses[i].getHorseName() + " : ");
				for(int j=1; j<=50; j++) {
					if(horses[i].getLocation() == j) {	//말의 현재 위치일 경우
						System.out.print(">");
					}else {	//현재 위치가 아닐 때
						System.out.print("-");
					}
				}
				System.out.println();
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}
	
	
	
}







//public class ThreadTest11 {
//	public static List<Horse> horseList = new ArrayList<Horse>();
//	public static int horseRank = 1;
//	
//
//
//
//	public static void main(String[] args) {
//		horseList.add(new Horse("01번말"));
//		horseList.add(new Horse("02번말"));
//		horseList.add(new Horse("03번말"));
//		horseList.add(new Horse("04번말"));
//		horseList.add(new Horse("05번말"));
//		horseList.add(new Horse("06번말"));
//		horseList.add(new Horse("07번말"));
//		horseList.add(new Horse("08번말"));
//		horseList.add(new Horse("09번말"));
//		horseList.add(new Horse("10번말"));
//		
//		for (Horse h : horseList) {
//			h.start();
//		}
//		
//		Location l = new Location();
//		
//		l.start();
//
//		try {
//			for (Horse h : horseList) {
//				h.join();
//			}
//			l.join();
//		} catch (InterruptedException e) {
//			// TODO: handle exception
//		}
//		
//		Collections.sort(horseList);
//		System.out.println();
//		System.out.println("===== 등수 =====");
//		for(Horse h : horseList) {
//			System.out.println(h.rank + "등 : " + h.name);
//		}
//		System.out.println("==============");
//	}
//}
//
//class Horse extends Thread implements Comparable<Horse>{
//	public String name;
//	public int rank;
//	public int location;
//
//	public Horse(String name) {
//		this.name = name;
//	}
//
//	@Override
//	public void run() {
//		for (int i = 1; i <= 50; i++) {
//			location = i;
//			try {
//				Thread.sleep((int) (Math.random() * 500));
//			} catch (InterruptedException e) {
//				// TODO: handle exception
//			}
//		}
//		this.rank = ThreadTest11.horseRank;
//		ThreadTest11.horseRank++;
//	}
//	@Override
//	public int compareTo(Horse h) {
//		return Integer.compare(this.rank, h.rank);
//	}
//}
//
//
//
//class Location extends Thread {
//	@Override
//	public void run() {
//		while (true) {
//			System.out.println("=======================================================");
//			String temp = "";
//			for (int i = 0; i < 10; i++) {
//				for (int j = 1; j <= 50; j++) {
//					if (j == ThreadTest11.horseList.get(i).location) {
//						temp += ">";
//					} else {
//						temp += "-";
//					}
//				}
//				System.out.println(ThreadTest11.horseList.get(i).name + temp);
//				temp = "";
//			}
//			System.out.println("=======================================================");
//			
//			if(ThreadTest11.horseRank == 11) {
//				break;
//			}
//			
//			try {
//				Thread.sleep(200);
//			} catch (InterruptedException e) {
//				// TODO: handle exception
//			}
//			
//		}
//	}
//}
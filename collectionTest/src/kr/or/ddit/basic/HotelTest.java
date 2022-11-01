package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class HotelTest {
	HashMap<Integer, Room> hotelMap;
	Scanner scan;
	
	//생성자
	public HotelTest() {
		hotelMap = new HashMap<Integer, Room>();
		scan = new Scanner(System.in);
		
		//객실 초기화
		for(int i=2; i<=4; i++) {
			String roomType = null;
			switch(i) {
				case 2 : roomType = "싱글룸"; break;
				case 3 : roomType = "더블룸"; break;
				case 4 : roomType = "스위트룸"; break;
			}
			
			for(int j=1; j<=9; j++) {
				int roomNum = i * 100 + j;
//				Room room = new Room(roomNum, roomType);
//				hotelMap.put(roomNum, room);
				
				hotelMap.put(roomNum,  new Room(roomNum, roomType));
			}
		}
	}	//생성자 끝,,,
	
	public static void main(String[] args) {
		new HotelTest().hotelStart();
	}

	public void hotelStart() {
		System.out.println("****************************************");
		System.out.println("호텔문을 열었습니다. 어서오십시요.");
		System.out.println("****************************************");
		System.out.println();
		
		while(true) {
			int choice = displayMenu();
			
			switch(choice) {
				case 1:			//체크인
					checkIn();
					break;
				case 2:			//체크아웃
					checkOut();
					break;
				case 3:
					showRoom();	//객실상태
					break;
				case 4:	//업무종료
					System.out.println("****************************************");
					System.out.println("호텔문을 닫았습니다.");
					System.out.println("****************************************");
					System.out.println();
					return;
				default:
					System.out.println("잘못입력했습니다.");
			}
		}
	}
	
	//객실 상태를 출력하는 메서드
	private void showRoom() {
		//방번호를 순서대로 나오게 하기 위해서 방번호만 List에 넣은 후 정렬하여 사용한다.
		//방번호는 Map의 Key값
		ArrayList<Integer> roomNumList = new ArrayList<Integer>(hotelMap.keySet());
		
		//방번호의 오름차순으로 정렬한다.
		Collections.sort(roomNumList);
		
		System.out.println("----------------------------------------");
		System.out.println("	현  재  객  실  상  태");
		System.out.println("----------------------------------------");
		System.out.println("방 번호\t방 종류\t투숙객 이름");
		System.out.println("----------------------------------------");
		
		//List에서 방번호를 하나씩 꺼내와 Map에서 해당 방번호에 해당하는 Room객체를 구해서 출력한다.
		for (int num : roomNumList) {
			Room r = hotelMap.get(num);
			System.out.print(r.getRoomNum() + "\t");
			System.out.print(r.getRoomType() + "\t");
			System.out.println(r.getGuestName() == null ? " -" : r.getGuestName());
		}
		System.out.println("----------------------------------------");
		System.out.println();
	}
	
	//체크아웃 하는 메서드
	private void checkOut() {
		System.out.println("----------------------------------------");
		System.out.println("\t체크아웃 작업");
		System.out.println("----------------------------------------");
		System.out.println("체크아웃 할 방 번호를 입력하세요.");
		System.out.print("방 번호 입력 >> ");
		int num = scan.nextInt();
		
		if(!hotelMap.containsKey(num)) {
			System.out.println(num + "호 객실은 존재하지 않습니다.");
			return;
		}
		
		if(hotelMap.get(num).getGuestName()==null) {
			System.out.println(num + "호 객실에는 체크인한 손님이 없습니다.");
			return;
		}
		
		//체크아웃 작업은 해당 객실의 투숙객 이름은 null로 변경하면 된다.
		String name = hotelMap.get(num).getGuestName();		//현재 투숙객 이름 구하기
		
		hotelMap.get(num).setGuestName(null);	// 투숙객 이름을 null로 변경
		
		System.out.println(num + "호 객실의 " + name + "님이 체크아웃을 완료했습니다.");
	}

	//체크인 하는 메서드
	private void checkIn() {
		System.out.println("----------------------------------------");
		System.out.println("체크인 작업");
		System.out.println("----------------------------------------");
		System.out.println("* 201 ~ 209 : 싱글룸");
		System.out.println("* 301 ~ 309 : 더블룸");
		System.out.println("* 401 ~ 409 : 스위트룸");
		System.out.println("----------------------------------------");
		System.out.println("방 번호 입력 >> ");
		int num = scan.nextInt();
		
		//입력한 값이 Map의 Key가 없으면 업는 방번호이다.
		if(!hotelMap.containsKey(num)) {
			System.out.println(num + "호 객실은 존재하지 않습니다.");
			return;
		}else if(hotelMap.get(num).getGuestName()!=null){
			System.out.println(num + "호 객실에는 이미 다른 손님이 있습니다.");
		}else {
			System.out.println("누구를 체크인 하시겠습니까?");
			System.out.println("이름 입력 >> ");
			String name = scan.next();
			
			//입력받은 투숙객 이름을 해당 객실의 투숙객 명단에 저장한다.
			hotelMap.get(num).setGuestName(name);
			
			System.out.println(name + "씨가 " + num + "호 객실에 체크인 되었습니다...");
		}
	}

	
	private int displayMenu() {
		System.out.println("----------------------------------------");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1. 체크인  2. 체크아웃  3. 객실상태  4. 업무종료");
		System.out.println("----------------------------------------");
		System.out.print("선택>> ");
		
		return scan.nextInt();
	}
	
}

class Room{
	private int roomNum;
	private String roomType;
	private String guestName;
	public Room(int roomNum, String roomType) {
		super();
		this.roomNum = roomNum;
		this.roomType = roomType;
	}
	public int getRoomNum() {
		return roomNum;
	}
	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
			
}


//public class HotelTest {
//
//	Scanner scan;
//	
//	Map<Integer, Room> roomMap;
//	
//	public HotelTest() {
//		scan = new Scanner(System.in);
//		roomMap = new HashMap<Integer, Room>();
//	}
//
//	public static void main(String[] args) {
//		new HotelTest().start();
//	}
//
//	//시작하는 메서드
//	private void start() {
//		addRoom();
//		while(true) {
//			int choice = displayMenu();
//			
//			switch(choice) {
//			case 1: checkIn(); break;
//			case 2: checkOut(); break;
//			case 3: roomState(); break;
//			case 4: 
//				System.out.println("****************************************");
//				System.out.println("호텔문을 닫았습니다.");
//				System.out.println("****************************************");
//				System.exit(0);
//			default: System.out.println("작업번호를 잘못입력하였습니다. 다시 입력해주세요.");
//			}
//		}
//		
//	}
//	
//	//객실 상태
//	private void roomState() {
//		Set<Integer> roomSet = roomMap.keySet();
//		System.out.println("----------------------------------------");
//		System.out.println("현재 객실 상태");
//		System.out.println("----------------------------------------");
//		System.out.println("방 번호	방 종류	투숙객 이름");
//		System.out.println("----------------------------------------");
//		
//		List<Integer> roomList = new ArrayList<Integer>(roomSet);
//		Collections.sort(roomList);
//		
//		for (Integer room : roomList) {
//			Room r = roomMap.get(room);
//			System.out.println(r.getRoomNum() + "\t" + r.getRoomType() + "\t" + r.getName());
//		}
//		System.out.println("----------------------------------------");
//	}
//
//	//체크아웃
//	private void checkOut() {
//		System.out.println("----------------------------------------");
//		System.out.println("\t체크아웃 작업");
//		System.out.println("----------------------------------------");
//		System.out.println("체크아웃 할 방 번호를 입력하세요.");
//		System.out.print("방 번호 입력 >> ");
//		int roomNum = Integer.parseInt(scan.nextLine());
//		if((roomNum >= 201 && roomNum <= 209) || (roomNum >= 301 && roomNum <= 309) || (roomNum >= 401 && roomNum <= 409)) {
//			Room r = roomMap.get(roomNum);
//			if(r.getName().equals("")) {
//				System.out.println(roomNum + "호 객실에는 체크인 한 사람이 없습니다.");
//			}else {
//				System.out.println(roomNum + "호 객실의" + r.getName() + "님 체크아웃을 완료하였습니다.");
//				roomMap.put(roomNum, new Room(r.getRoomNum(), r.getRoomType(), "-"));
//			}
//		}else {
//			System.out.println(roomNum + "호 객실은 존재하지 않습니다.");
//		}
//	}
//
//	//체크인
//	private void checkIn() {		
//		System.out.println("----------------------------------------");
//		System.out.println("체크인 작업");
//		System.out.println("----------------------------------------");
//		System.out.println("* 201 ~ 209 : 싱글룸");
//		System.out.println("* 301 ~ 309 : 더블룸");
//		System.out.println("* 401 ~ 409 : 스위트룸");
//		System.out.println("----------------------------------------");
//		System.out.print("방 번호 입력 >> ");
//		int roomNum = Integer.parseInt(scan.nextLine());
//		if((roomNum >= 201 && roomNum <= 209) || (roomNum >= 301 && roomNum <= 309) || (roomNum >= 401 && roomNum <= 409)) {
//			if(!roomMap.get(roomNum).getName().equals("")) {
//				System.out.println(roomNum + "호 객실은 이미 손님이 있습니다.");
//			}else {
//				System.out.print("이름 입력 >> ");
//				String name = scan.nextLine();
//				Room r = roomMap.get(roomNum);
//				roomMap.put(roomNum, new Room(r.getRoomNum(), r.getRoomType(), name));
//				System.out.println("체크인이 완료되었습니다.");
//			}
//		}else {
//			System.out.println(roomNum + "호 객실은 존재하지 않습니다.");
//		}
//		
//	}
//
//	//메뉴를 출력하고 작업번호를 반환하는 메서드
//	private int displayMenu() {
//		System.out.println("****************************************");
//		System.out.println("호텔문을 열었습니다. 어서오십시요.");
//		System.out.println("****************************************");
//		System.out.println();
//		System.out.println("----------------------------------------");
//		System.out.println("어떤 업무를 하시겠습니까?");
//		System.out.println("1. 체크인  2. 체크아웃  3. 객실상태  4. 업무종료");
//		System.out.println("----------------------------------------");
//		System.out.print("선택>> ");
//		
//		return Integer.parseInt(scan.nextLine());
//	}
//	
//	//방 데이터를 넣는 메서드
//	private void addRoom() {
//		for(int i=1; i<10; i++) {
//			roomMap.put(200 + i, new Room(200 + i, "싱글룸", "-"));
//			roomMap.put(300 + i, new Room(300 + i, "더블룸", "-"));
//			roomMap.put(400 + i, new Room(400 + i, "스위트룸", "-"));
//		}
//	}
//}
//
//class Room{
//	private int roomNum;
//	private String roomType;
//	private String name;
//	
//	public Room(int roomNum, String roomType, String name) {
//		super();
//		this.roomNum = roomNum;
//		this.roomType = roomType;
//		this.name = name;
//	}
//	
//	public int getRoomNum() {
//		return roomNum;
//	}
//	public void setRoomNum(int roomNum) {
//		this.roomNum = roomNum;
//	}
//	public String getRoomType() {
//		return roomType;
//	}
//	public void setRoomType(String roomType) {
//		this.roomType = roomType;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	
//}
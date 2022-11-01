package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

/*
  	문제) 이름, 주소, 전화번호를 멤버변수로 갖는 Phone클래스를 만들고
  		Map을 이용하여 전화번호 정보를 관리하는 프로그램을 작성하시오
  		(Map의 구조는 key갑으로 '이름'을 사용하고 value값으로 'Phone클래스의 인스턴스'를 사용한다.)		
  
  		추가조건)
  			1) 메뉴에 '6. 전화번호 정보 저장' 메뉴를 추가하고 기능을 구현한다.
  				(이 때 저장파일명은 'phoneData.dat'로 한다.)
  				
  			2) 프로그램이 시작될 때 저장된 파일이 있으면 그 데이터를 읽어와 Map에 셋팅되도록 한다.
  			
  			3) 프로그램을 종료할 때 Map의 데이터가 변경되거나 추가 또는 삭제되는 데이터가 있으면 저장한 후에 종료되도록 한다.
  
  		아래의 실행예시에 맞게 구현하시오.
  	실행예시)
  		---------------------
  		다음 메뉴에서 작업을 선택하세요.
  		1. 전화번호 등록	
  		2. 전화번호 수정
  		3. 전화번호 삭제
  		4. 전화번호 검색
  		5. 전화번호 전체 출력
  		0. 프로그램 종료
  		---------------------
  		번호입력>> 1
  
  		새롭게 등록할 전화번호 정보를 입력하세요.
  		이름 >> 홍길동
  		전화번호 >> 010-1111-1111
  		주소 >> 대전시 중구 오류동
  		
  		'홍길동'전화번호 등록 완료!!
  
  		---------------------
  		다음 메뉴에서 작업을 선택하세요.
  		1. 전화번호 등록	
  		2. 전화번호 수정
  		3. 전화번호 삭제
  		4. 전화번호 검색
  		5. 전화번호 전체 출력
  		0. 프로그램 종료
  		---------------------
  		번호입력>> 1
  		
  		새롭게 등록할 전화번호 정보를 입력하세요.
  		이름 >> 홍길동
  
  		'홍길동'은 이미 등록된 사람입니다..
  
  		---------------------
  		다음 메뉴에서 작업을 선택하세요.
  		1. 전화번호 등록	
  		2. 전화번호 수정
  		3. 전화번호 삭제
  		4. 전화번호 검색
  		5. 전화번호 전체 출력
  		0. 프로그램 종료
  		---------------------
  		번호입력>> 5
  
  		==============================================
  		번호		이름		전화번호			주소
  		==============================================
  		1		홍길동	010-1111-1111	대전시 중구 오류동
  			~~~
  			~~~
  		==============================================
  		출력완료...
  
  		---------------------
  		다음 메뉴에서 작업을 선택하세요.
  		1. 전화번호 등록	
  		2. 전화번호 수정
  		3. 전화번호 삭제
  		4. 전화번호 검색
  		5. 전화번호 전체 출력
  		0. 프로그램 종료
  		---------------------
  		번호입력>> 0
  
  		프로그램을 종료합니다...
  		==============================================
  		- 삭제와 검색 가능은 '이름'을 입력받아서 처리한다.
  		- 수정 기능에서 '이름'은 변경되지 않는다.
 */
public class PhoneBookTest{
	private HashMap<String, Phone>phoneBookMap;
	private Scanner scan;
//	private boolean flag;
	private String fileName = "d:/d_other/phoneData.dat";	//저장 파일명 설정
	
	//데이터가 변경되었는지 여부를 나타내는 변수 선언 => 데이터가 변경되면 true값이 된다.
	private boolean dataChange;
	
	public PhoneBookTest() {
//		phoneBookMap = new HashMap<String, Phone>();
		
		phoneBookMap = load();	//파일을 읽어와 Map에 셋팅하기
		
		if(phoneBookMap == null) {	//파일이 없거나 잘못되었을 때...
			phoneBookMap = new HashMap<String, Phone>();
		}
		
		scan = new Scanner(System.in);

		
//		File phoneData = new File("d:/d_other/phoneData.dat");

//		if (phoneData.exists()) {
//			ObjectInputStream oin = null;
//			try {
//				oin = new ObjectInputStream(new BufferedInputStream(new FileInputStream("d:/d_other/phoneData.dat")));
//
//				Object obj;
//
//				while ((obj = oin.readObject()) != null) {
//					Phone p = (Phone)obj;
//
//					phoneBookMap.put(p.getName(), new Phone(p.getName(), p.getTel(), p.getAddr()));
//				}
//
//			} catch (IOException e) {
//				e.printStackTrace();
//			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//			} finally {
//				if (oin != null) {
//					try {
//						oin.close(); // 스트림 닫기
//					} catch (IOException e2) {
//
//					}
//				}
//			}
//		}
	}

	public static void main(String[] args) {
		new PhoneBookTest().phoneBookStart();
	}

	//프로그램을 시작하는 메서드
	private void phoneBookStart() {
		System.out.println("***************************");
		System.out.println("          전 화 번 호 관 리 프 로 그 램");
		System.out.println("***************************");
		System.out.println();
		
		while(true) {
			int choice = displayMenu();
			switch (choice) {
			case 1:			//등록
//				flag = true;
				insert();
				break;
			case 2:			//수정
//				flag = true;
				update();
				break;
			case 3:			//삭제
//				flag = true;
				delete();
				break;
			case 4:			//검색
				serch();
				break;
			case 5:			//전체출력
				displayAll();
				break;
			case 6:			//저장
//				flag = false;
				save();
				break;
			case 0:			//프로그램 종료
//				if(flag == true) {
//					save();
//				}
				if(dataChange == true) {	//데이터가 변경되면...
					System.out.println("변경된 데이터를 저장합니다...");
					save();
				}
				System.out.println("프로그램을 종료합니다.");
				return;
			default:
				System.out.println("작업 번호를 잘못 입력했습니다. 다시 입력해주세요.");
			}
		}
		
		
	}
	
	//전화번호 정보가 저장된 파일을 읽어오는 메서드
	private HashMap<String, Phone> load(){
		//읽어온 데이터가 저장될 변수 선언
		HashMap<String, Phone> pMap = null;
		
		File file = new File(fileName);
		if(!file.exists()) {	//저장된 파일이 없으면...
			return null;
		}
		
		//저장된 파일이 있으면 이 이후에는 내용이 처리된다.
		ObjectInputStream oin = null;
		try {
			//입력용 스트림 객체 생성
			oin = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
			
			/*
			//방법 1
//			pMap = (HashMap<String, Phone>) oin.readObject();
			*/
			
			//방법2
			pMap = new HashMap<String, Phone>();
			while(true) {
				Phone p = (Phone)oin.readObject();
				if(p==null) {	//파일의 끝이면 반복문을 빠져나간다.
					break;
				}
				pMap.put(p.getName(), p);
			}
			
		} catch (IOException e) {
			return null;
		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
			return null;
		}finally {
			if(oin != null) try { oin.close();	} catch (Exception e) {	}
		}
		
		return pMap;
	}
	
	// 전화번호 정보를 파일로 저장하는 메서드
	private void save() {
		
		//객체를 저장할 스트림 객체변수 선언
		ObjectOutputStream oout = null;
		try {
			//객체 출력용 스트림 객체 생성
			oout = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
			
			/*
			//방법 1) Map객체를 파일로 저장한다.
			oout.writeObject(phoneBookMap);
			 */
			
			///////////////////////////////////////////////
			// 방법2) Map에 저장된 Phone객체를 파일로 저장하기
			for(String name : phoneBookMap.keySet()) {
				oout.writeObject(phoneBookMap.get(name));
			}
			oout.writeObject(null);	// 끝을 나타내는 값 저장
			///////////////////////////////////////////////
			
			System.out.println("저장이 완료되었습니다...");
			
			//데이터를 파일로 저장하면 현재 메모리의 데이터와 파일의 데이터가 일치하므로 변경된 데이터가 없는 셈이 된다.
			dataChange = false;
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(oout != null) try { oout.close(); } catch(IOException e) {}
		}
//		try {
//			ObjectOutputStream oout = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("d:/d_other/phoneData.dat")));
//			
//			System.out.println("전화번호 정보를 저장합니다.");
//			
//			Set<String> phoneBookSet = phoneBookMap.keySet();
//			
//			for (String name : phoneBookSet) {
//				Phone p = phoneBookMap.get(name);
//				oout.writeObject(p);
//			}
//			oout.writeObject(null);
//			System.out.println("저장 완료");
//			System.out.println("---------------------");
//			oout.close();
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	//전화번호를 검색하는 메서드
	private void serch() {
		System.out.println();
		System.out.println("검색할 전화번호 정보를 입력하세요...");
		System.out.print("이름 >> ");
		String name = scan.next();

		if(!phoneBookMap.containsKey(name)) {	//해당 사람이 없으면 삭제작업을 못한다.
			System.out.println(name + "씨의 전화번호 정보가 없습니다...");
			return;
		}
		
		Phone p = phoneBookMap.get(name);
		
		System.out.println(name + "씨 전화번호 정보");
		System.out.println("--------------------");
		System.out.println("이름 : " + p.getName());
		System.out.println("전화번호 : " + p.getTel());
		System.out.println("주소 : " + p.getAddr());
		
	}

	//전화번호 정보를 삭제하는 메서드
	private void delete() {
		System.out.println();
		System.out.println("삭재할 전화번호 정보를 입력하세요...");
		
		System.out.print("이름 >> ");
		String name = scan.next();
		
		if(!phoneBookMap.containsKey(name)) {	//해당 사람이 없으면 삭제작업을 못한다.
			System.out.println(name + "씨의 전화번호 정보가 없습니다...");
			return;
		}
		
		phoneBookMap.remove(name);	//Map에서 Key값을 이용한 데이터 삭제
		
		System.out.println(name + "씨 전화번호 정보가 삭제되었습니다.");
		
		dataChange = true;
		
	}

	//전화번호를 수정하는 메서드
	private void update() {
		System.out.println();
		System.out.println("수정할 전화번호 정보를 입력하세요...");
		
		System.out.print("이름 >> ");
		String name = scan.next();
		
		if(!phoneBookMap.containsKey(name)) {
			System.out.println(name + "씨의 전화번호 정보가 없습니다...");
			return;
		}
		
		System.out.print("수정할 전화번호 >> ");
		String newTel = scan.next();
		
		scan.nextLine();	//입력 버퍼 비우기
		System.out.print("수정할 주소 >> ");
//		String newAddr = scan.next();
		String newAddr = scan.nextLine();
		
		//같은 key값에 새로운 전화번호 정보를 저장한다. => 수정작업
		phoneBookMap.put(name, new Phone(name, newTel, newAddr));
		
		System.out.println(name + "씨 전화번호 정보를 변경했습니다.");
		
		dataChange = true;
	}

	//전체 자료를 출력하는 메서드
	private void displayAll() {
		System.out.println();
		
		//Map에 등록된 모든 key값 구하기
		Set<String> phoneBookSet = phoneBookMap.keySet();
		
		System.out.println("----------------------------------------");
		System.out.println("번호	이름	전화번호		주소");
		System.out.println("----------------------------------------");
		if(phoneBookSet.size() == 0) {
			System.out.println("	등록된 전화번호 정보가 하나도 없습니다.");
		}else {
			int cnt = 0;	//번호를 출력하시 위한 변수
			
			for(String name : phoneBookSet) {
				cnt++;
				Phone p = phoneBookMap.get(name);
				
				System.out.println(" " + cnt + "\t" + p.getName() + "\t" + p.getTel() + "\t" + p.getAddr());
			}
		}
		System.out.println("----------------------------------------");
		System.out.println(" 출력 끝...");
	}

	//새로운 전화번호 정보를 등록하는 메서드 - 이미 등록된 사람은 등록되지 않는다.
	private void insert() {
		System.out.println();
		System.out.println("새롭게 등록할 전화번호 정보를 입력하시오");
		System.out.print("이름 >> ");
		String name = scan.next();		//여기서 입력한 이름이 key값으로 입력된다.
		
		//이미 등록된 사람인지 검사
		if(phoneBookMap.containsKey(name)) {
			System.out.println(name + "씨는 이미 등록된 사람입니다.");
			return;
		}
		
		System.out.print("전화번호 >> ");
		String tel = scan.next();
		
		scan.nextLine();	//입력버퍼 비우기
		System.out.print("주소 >> ");
//		String addr = scan.next();
		String addr = scan.nextLine();
		
		//입력받은 데이터를 Phone객체에 셋팅한 후 Map에 추가한다.
//		Phone p = new Phone(name, tel, addr);
//		phoneBookMap.put(name, p);
		
		phoneBookMap.put(name, new Phone(name, tel, addr));
		
		System.out.println(name + "씨 전화번호 등록 완효");
		
		dataChange = true;
	}

	/*
	 - Scanner의 입력방식
	 
	 1. next(), nextInt(), nextDouble(), ....
	 	=> 사이띄기, Tab키, Enter키를 구분 문자로 분리해서 분리한 자료만 읽어간다.
	 	
	 2. nextLine()
	  	=> 한 줄 단위로 입력한다. 즉, 자료를 입력하고 Enter키를 누르면 Enter키 까지 읽어간 후
	  		Enter키를 제외한 문자열을 반환한다.
	  		
	 - Scanner는 입력 버퍼에 데이터가 있으면 입력 버퍼의 자료를 가져가고 없으면 새로 입력 받는다.
	 
	 -  nextLine()메서드 이전에 next(), nextInt()와 같은 종류의 메서드가 호출되었으면
	 	입력버퍼를 비워줘야 한다.
	*/
	
	//메뉴를 출력하고 작업번호를 입력하여 반환하는 메서드
	private int displayMenu() {
		System.out.println("---------------------");
		System.out.println("다음 메뉴에서 작업을 선택하세요.");
		System.out.println("1. 전화번호 등록");
		System.out.println("2. 전화번호 수정");
		System.out.println("3. 전화번호 삭제");
		System.out.println("4. 전화번호 검색");
		System.out.println("5. 전화번호 전체 출력");
		System.out.println("6. 전화번호 정보 저장");
		System.out.println("0. 프로그램 종료");
		System.out.println("---------------------");
		System.out.print("번호입력>> ");
		return scan.nextInt();
	}
}

//하나의 전화번호 정보를 저장할 class 작성
class Phone  implements Serializable{
//	private static final long serialVersionUID = 5212671553148841438L;
	private static final long serialVersionUID = 1L;
	
	//이름, 주소, 전화번호
	private String name;
	private String tel;
	private String addr;
	
	//생성자
	public Phone(String name, String tel, String addr) {
		super();
		this.name = name;
		this.tel = tel;
		this.addr = addr;
	}
	
	//게터세터
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
}

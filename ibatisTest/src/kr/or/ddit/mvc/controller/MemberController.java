package kr.or.ddit.mvc.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import kr.or.ddit.mvc.service.IMemberService;
import kr.or.ddit.mvc.service.MemberServiceimpl;
import kr.or.ddit.mvc.vo.MemberVO;

public class MemberController {

	private Scanner scan = new Scanner(System.in);
	
	private IMemberService service;		//Service객체가 저장될 변수 선언
	
	//생성자
	public MemberController() {
//		service = new MemberServiceimpl();
		service = MemberServiceimpl.getInstance();
	}
	
	public static void main(String[] args) {
		new MemberController().start();
	}
	
	public void start() {
		while(true) {
			int input = displayMenu();
			
			switch(input) {
			case 1:			//지료 삭제
				insert();
				break;
			case 2:			//자료 삭제
				delete();
				break;
			case 3:			//자료 수정
				update();
				break;
			case 4:			//자료 출력
				displayAll();
				break;
			case 5:			//자료 수정2
				update2();
				break;
			case 0:			//종료
				System.out.println("작업이 종료됩니다.");
				return;
			default:
				System.out.println("번호를 잘못입력하셨습니다.");
				System.out.println("다시 입력해주세요.");
			}
		}
	}
	
	private int displayMenu() {
		System.out.println("===============");
		System.out.println("1. 자료 추가   ");
		System.out.println("2. 자료 삭제   ");
		System.out.println("3. 자료 수정   ");
		System.out.println("4. 전체 자료 출력");
		System.out.println("5. 자료 수정2   ");
		System.out.println("0. 작업 끝.    ");
		System.out.println("===============");
		
		System.out.print("번호 입력  : ");
		return Integer.parseInt(scan.nextLine());
	}
	
	// 전체 회원 정보를 출력하는 메서드
	private void displayAll() {
		System.out.println();
		System.out.println("-----------------------------------------------");
		System.out.println("ID \t 비밀번호 \t 회원이름 \t 전화번호 \t 회원주소");
		System.out.println("-----------------------------------------------");
		
		List<MemberVO> memList = service.getAllMember();
		if(memList==null || memList.size()==0) {
			System.out.println("\t출력할 회원정보가 하나도 없습니다.");
		}else {
			//List갯수만큼 반복 처리
			for (MemberVO memVo : memList) {
				System.out.println(memVo.getMem_id() + " \t " + memVo.getMem_pass()
								+ " \t " + memVo.getMem_name() + " \t " + memVo.getMem_tel() 
								+ "\t" + memVo.getMem_addr());
			}
			System.out.println("-----------------------------------------------");
		}
	}
	
	// 회원 정보를 수정하는 메서드 ==> 원하는 항목만 수정
	private void update2() {
		System.out.println();
		System.out.println("수정할 회원 정보를 입력하세요.");
		System.out.print("수정할 회원ID >> ");
		String memId = scan.next();
		
		int count = service.getMemberCount(memId);
		
		if(count==0) {	// 없는 회원일 경우...
			System.out.println(memId + "은(는) 없는 회원ID 입니다.");
			System.out.println("수정 작업을 종료합니다.");
			return;
		}
		
		int num;
		String updateField = null;
		String updateTitle = null;
		do {
			System.out.println();
			System.out.println("수정할 항목을 선택하세요.");
			System.out.println("1. 비밀번호      2. 회원이름     3. 전화번호    4. 회원주소");
			System.out.println("------------------------------------------");
			System.out.print("수정 항목 선택 >> ");
			num = scan.nextInt();
			
			switch(num) {
				case 1 : updateField = "mem_pass"; updateTitle="비밀번호";
					break;
				case 2 : updateField = "mem_name"; updateTitle="회원이름";
					break;
				case 3 : updateField = "mem_tel"; updateTitle="전화번호";
					break;
				case 4 : updateField = "mem_addr"; updateTitle="회원주소";
					break;
				default : 
					System.out.println("수정 항목을 잘못 선택했습니다. 다시 선택하세요.");
			}
			
		}while(num<1 || num>4);
		
		System.out.println();
		scan.nextLine();  // 버퍼 비우기
		System.out.print("새로운 " + updateTitle + " >> ");
		String updateData = scan.nextLine();
		
		// 수정할 정보를 Map에 추가한다.
		// Key값 정보 ==> 회원ID(memId),	수정할 컬럼명(field), 수정할 데이터(data)
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("memId", memId);
		paramMap.put("field", updateField);
		paramMap.put("data", updateData);
		
		int cnt = service.updateMember2(paramMap);
		
		if(cnt>0) {
			System.out.println(memId+ " 회원의 " + updateField + "를 수정했습니다.");
		}else {
			System.out.println("수정 작업 실패!!!");
		}
		
	}
	
	// 회원정보를 수정하는 메서드
	private void update() {

		System.out.print("수정할 회원 아이디 입력 : ");
		String memId = scan.nextLine();
		int count = service.getMemberCount(memId);

		if (count == 0) {
			System.out.println(memId + "는(은) 존재하지 않는 아이디입니다.");
			System.out.println("다시 입력하세요...");
		}

		System.out.print("수정할 비밀번호 입력 : ");
		String newMemPass = scan.nextLine();

		System.out.print("수정할 이름 입력 : ");
		String newMemName = scan.nextLine();

		System.out.print("수정할 전화번호 입력 : ");
		String newMemTel = scan.nextLine();

		System.out.print("수정할 주소 입력 : ");
		String newMemAddr = scan.nextLine();


		//입력 받은 데이터를 VO객체에 저장한다.
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(memId);
		memVo.setMem_pass(newMemPass);
		memVo.setMem_name(newMemName);
		memVo.setMem_tel(newMemTel);
		memVo.setMem_addr(newMemAddr);
		
		int cnt = service.updateMember(memVo);

		if (cnt > 0) {
			System.out.println("회원 정보 수정 성공 !!!");
		} else {
			System.out.println("회원 정보 수정 실패...");
		}
	}
	

	//회원정보를 삭제하는 메서드
	private void delete() {
		System.out.print("삭제할 회원 아이디 입력 : ");
		String memId = scan.nextLine();
		
		int cnt = service.deleteMember(memId);
		
		if(cnt > 0) {
			System.out.println(memId + "회원 정보 삭제 성공!!!");
		}else {
			System.out.println(memId + "삭제 실패...");
		}
	}
	
	private void insert() {
		int count = 0;
		String memId = null; // 회원 ID가 저장될 변수
		do {
			System.out.print("아이디 입력 : ");
			memId = scan.nextLine();

			count = service.getMemberCount(memId);

			if (count > 0) {
				System.out.println(memId + "는(은) 이미 존재하는 아이디입니다.");
				System.out.println("다시 입력하세요...");
			}

		} while (count > 0);

		System.out.print("비밀번호 입력 : ");
		String memPass = scan.nextLine();

		System.out.print("이름 입력 : ");
		String memName = scan.nextLine();

		System.out.print("전화번호 입력 : ");
		String memTel = scan.nextLine();

		System.out.print("주소 입력 : ");
		String memAddr = scan.nextLine();

		//입력 받은 데이터를 VO객체에 저장한다.
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(memId);
		memVo.setMem_pass(memPass);
		memVo.setMem_name(memName);
		memVo.setMem_tel(memTel);
		memVo.setMem_addr(memAddr);
		
		
		int cnt = service.insertMember(memVo);

		if (cnt > 0) {
			System.out.println("회원정보 추가 완료!!!");
		} else {
			System.out.println("자료추가 실패.");
		}

	}
	
}

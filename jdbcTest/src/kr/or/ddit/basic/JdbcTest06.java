package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;
import kr.or.ddit.util.DBUtil2;
import kr.or.ddit.util.DBUtil3;

/*
	회원관리를 하는 프로그램을 작성하시오. (  MYMEMBER 테이블 이용 )
	
	아래 메뉴의 기능을 모두 구현하시오. ( CRUD기능 구현하기 )
	메뉴예시)
	===============
	1. 자료 추가			--> insert
	2. 자료 삭제			--> delete
	3. 자료 수정			--> update
	4. 전체 지료 출력			--> select
	0. 작업 끝.
	===============

	처리조건)
	1) '자료 추가'에서 '회원ID'는 중복되지 않는다. (중복되면 다시 입력받는다.)
	2) '자료 삭제'는 '회원ID'를 입력 받아서 처리한다.
	3) '자료 수정'에서 '회원ID'는 변경되지 않는다.

*/

public class JdbcTest06 {

	private Scanner scan = new Scanner(System.in);;
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public static void main(String[] args) {
		new JdbcTest06().start();
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
				select();
				break;
			case 5:			//자료 수정2
				update2();
				break;
			case 0:			//종료
				System.out.println("작업이 종료됩니다.");
//				System.exit(0);
				return;
			default:
				System.out.println("번호를 잘못입력하셨습니다.");
				System.out.println("다시 입력해주세요.");
//				break;
			}
		}
	}
	
	//화원정보를 수정하는 메서드 => 원하는 항목만 수정
	private void update2() {
		System.out.print("수정할 회원 아이디 입력 : ");
		String memId = scan.nextLine();
		
		int count = getMemberCount(memId);

		if (count == 0) {
			System.out.println(memId + "는(은) 존재하지 않는 아이디입니다.");
			System.out.println("다시 입력하세요...");
			return;
		}
		
		int num;
		String updateField = null;
		String updateTitle = null;
		do {
			System.out.println();				
			System.out.println("수정할 항목울 선택하세요.");
			System.out.println("1. 비밀번호	2. 회원이름	3.전화번호		4.회원주고");				
			System.out.println("수정 항목 선택>>");
			num = scan.nextInt();
			
			switch(num) {
			case 1: updateField = "mem_pass"; updateTitle="비밀번호"; break;
			case 2: updateField = "mem_name"; updateTitle="회원이름"; break;
			case 3: updateField = "mem_tel"; updateTitle="전화번호"; break;
			case 4: updateField = "mem_addr"; updateTitle="회원주소"; break;
			default: System.out.println("수정항목을 잘못 선택했습니다. 다시 선택 하세요.");
			}
			
			System.out.println();
			scan.nextLine();	//버퍼비우기
			System.out.print("새로운" + updateField + ">>");
			String updateData = scan.nextLine();
			
			try {
				conn = DBUtil.getConnection();
				
				String sql = "update mymember set " 
				+ updateField + "= ? where mem_id = ?";	//컬럼명은 ?로 받으면 안된다 db로 넘어갈 때 따옴표가 자동으로 붙음
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, updateData);
				pstmt.setString(2, memId);
				
				int cnt = pstmt.executeUpdate();
				
				if(cnt > 0){
					System.out.println(memId + "회원의 " + updateField + "를 수정했습니다.");
				}else {
					System.out.println("수정 작업 실패!!!");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				disConnect();
			}
			
		}while(num > 4 || num <1);
	}

	//DB작업과 관련된 자원을 닫아주는 메서드
	private void disConnect() {
		if(rs!=null) try { rs.close(); } catch(SQLException e) {} 
		if(stmt!=null) try { stmt.close(); } catch(SQLException e) {} 
		if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {} 
		if(conn!=null) try { conn.close(); } catch(SQLException e) {} 
	}

	
	private void insert() {

		int count = 0;
		String memId = null; // 회원 ID가 저장될 변수
		do {
			System.out.print("아이디 입력 : ");
			memId = scan.nextLine();

//				String sql1 = "select count(*) cnt from mymember where mem_id = ?";
//				pstmt = conn.prepareStatement(sql1);
//				pstmt.setString(1, memId);
//				
//				rs = pstmt.executeQuery();
//				
//				if(rs.next()) {
//					count = rs.getInt("cnt");
//				}

			count = getMemberCount(memId);

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

		try {
			conn = DBUtil.getConnection();
			String sql2 = "insert into mymember (mem_id, mem_pass, mem_name, mem_tel, mem_addr)"
					+ "     values(?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, memId);
			pstmt.setString(2, memPass);
			pstmt.setString(3, memName);
			pstmt.setString(4, memTel);
			pstmt.setString(5, memAddr);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("회원정보 추가 완료!!!");
			} else {
				System.out.println("자료추가 실패.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
//			if(rs!=null) try { rs.close(); } catch(SQLException e) {} 
//			if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {} 
//			if(conn!=null) try { conn.close(); } catch(SQLException e) {} 
		}
	}
	
	//회원ID를 인수값으로 받아서 해당 회원ID의 개수를 반환하는 메서드
	private int getMemberCount(String memId) {
		int count = 0;
		try {
			conn = DBUtil.getConnection();
			
			String sql = "select count(*) cnt from mymember where mem_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt("cnt");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
		}
		return count;
	}
	
	//회원정보를 삭제하는 메서드
	private void delete() {
		conn = DBUtil.getConnection();
		try {
			System.out.print("삭제할 회원 아이디 입력 : ");
			String memId = scan.nextLine();
			
			String sql = "delete from mymember" + 
					" where mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println(memId + "회원 정보 삭제 성공!!!");
			}else {
				System.out.println(memId + "삭제 실패...");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
//			if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {} 
//			if(conn!=null) try { conn.close(); } catch(SQLException e) {} 
		}
	}
	
	// 회원정보를 수정하는 메서드
	private void update() {

//			String memId;
//			int count = 0;
//			do {
		System.out.print("수정할 회원 아이디 입력 : ");
		String memId = scan.nextLine();
//				
//				String sql1 = "select count(*) cnt from mymember where mem_id = ?";
//				pstmt = conn.prepareStatement(sql1);
//				pstmt.setString(1, memId);
//				
//				rs = pstmt.executeQuery();
//				
//				if(rs.next()) {
//					count = rs.getInt("cnt");
//				}
//				
		int count = getMemberCount(memId);

		if (count == 0) {
			System.out.println(memId + "는(은) 존재하지 않는 아이디입니다.");
			System.out.println("다시 입력하세요...");
		}
//				
//			}while(count == 0);

		System.out.print("수정할 비밀번호 입력 : ");
		String memPass = scan.nextLine();

		System.out.print("수정할 이름 입력 : ");
		String memName = scan.nextLine();

		System.out.print("수정할 전화번호 입력 : ");
		String memTel = scan.nextLine();

		System.out.print("수정할 주소 입력 : ");
		String mrmAddr = scan.nextLine();
		try {
			conn = DBUtil.getConnection();

			String sql2 = "update mymember"
					+ "    set mem_pass = ?, mem_name = ?, mem_tel = ?, mem_addr = ?"
					+ "    where mem_id = ?";

			pstmt = conn.prepareStatement(sql2);
			pstmt.setString(1, memPass);
			pstmt.setString(2, memName);
			pstmt.setString(3, memTel);
			pstmt.setString(4, mrmAddr);
			pstmt.setString(5, memId);

			int cnt = pstmt.executeUpdate();

			if (cnt > 0) {
				System.out.println("회원 정보 수정 성공 !!!");
			} else {
				System.out.println("회원 정보 수정 실패...");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disConnect();
//			if(rs!=null) try { rs.close(); } catch(SQLException e) {} 
//			if(stmt!=null) try { stmt.close(); } catch(SQLException e) {} 
//			if(conn!=null) try { conn.close(); } catch(SQLException e) {} 
		}
	}

	//전체 회원정보를 출력하는 메서드
	private void select() {
		
		try {
//			conn = DBUtil.getConnection();
//			conn = DBUtil2.getConnection();
			conn = DBUtil3.getConnection();
			String sql = "select * from mymember";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			System.out.println("============================");
			while(rs.next()) {
				System.out.println("회원 아이디 : " + rs.getString(1));
				System.out.println("회원 비밀번호 : " + rs.getString(2));
				System.out.println("회원 이름 : " + rs.getString(3));
				System.out.println("회원 전화번호 : " + rs.getString(4));
				System.out.println("회원 주소 : " + rs.getString(5));
				System.out.println("============================");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disConnect();
//			if(rs!=null) try { rs.close(); } catch(SQLException e) {} 
//			if(stmt!=null) try { stmt.close(); } catch(SQLException e) {} 
//			if(conn!=null) try { conn.close(); } catch(SQLException e) {} 
		}
	}

	//메뉴를 출력하고 작업번호를 입력받아 반환하는 메서드
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
}

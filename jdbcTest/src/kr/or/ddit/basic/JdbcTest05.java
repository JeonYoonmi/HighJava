package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

/*
	LPROD테이블에 새로운 데이터 추가하기
	
	- lprod_gu와 lprod_nm은 사용자로부터 직접 입력 받아서 처리하고, lprod_id는 현재의 lprod_id중에서
	 제일 큰 값보다 1크게 한다.
	- 입력받은 lprod_gu가 이미 등록되어 있으면 다시 입력 받아서 처리한다.
	
*/

public class JdbcTest05 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "pc16", "java");
			
			conn = DBUtil.getConnection();
			//Lprod_id는 현재의 Lprod_id값 중에서 제일 큰 값보다 1 크게한다.
			String sql = "select max(lprod_id) maxnum from lprod";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			int maxnum = 0;	// 제일 큰 lprod_id가 저장될 변수
			// select한 결과가 1개의 레코드일 경우에는 반복문보다는 if문으로 데이터를 확인한다.
			if(rs.next()) {
//				maxnum = rs.getInt(1);					// 컬럼번 호 이용
//				maxnum = rs.getInt("max(lprod_id)");	//alias가 없으면 식 내용을 이용
				maxnum = rs.getInt("maxnum");			// 컬럼의 alias를 이용
			}
			maxnum++;
			//---------------------------------------------------------------------
			
			//입력받은 lprod_gu(상품분류코드)가 이미 등록되어 있으면 다시 입력받아서 처리한다.
			String gu;		// 상품분류코드가 저장될 변수 선언
			int count = 0;	//입력한 상품 분류 코드가 DB에 저장된 개수를 저장할 변수
			do {
				System.out.print("상품 분류 코드(LPROD_GU) 입력 : ");
				gu = scan.next();
				
				String sql2 = "select count(*) cnt from lprod where lprod_gu = ?";
				pstmt = conn.prepareStatement(sql2);
				pstmt.setNString(1, gu);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt("cnt");
				}
				
				if(count > 0) {
					System.out.println("입력한 상품분튜코드 : " + gu + "는(은) 이미 등록된 코드입니다.");
					System.out.println("다시 입력하세요...");
				}
				
			}while(count > 0);
			//---------------------------------------------------------------------------
			
			System.out.print("상품 분류명(LPROD_NM) 입력 : ");
			String nm = scan.next();
			
			String sql3 = "insert into lprod (lprod_id, lprod_gu, lprod_nm)"
					+ "   values(?, ?, ?)";
			pstmt = conn.prepareStatement(sql3);
			pstmt.setInt(1, maxnum);
			pstmt.setString(2, gu);
			pstmt.setString(3, nm);
			
			int cnt = pstmt.executeUpdate();
			
			if(cnt > 0) {
				System.out.println("insert 성공~~~");
			}else {
				System.out.println("insert 실패!!!");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
		}finally {
			if(rs!=null) try { rs.close(); }catch(SQLException e) {}
			if(stmt!=null) try { stmt.close(); }catch(SQLException e) {}
			if(pstmt!=null) try { pstmt.close(); }catch(SQLException e) {}
			if(conn!=null) try { conn.close(); }catch(SQLException e) {}
		}
		
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "pc16", "java");
//			
//			System.out.print("상품 분류 번호 : ");
//			String lprodGu = scan.next();
//			
//			System.out.print("상품 분류명 : ");
//			String lprodNm = scan.next();
//			
//			while (true) {
//				String sql = "select count(*) from lprod where lprod_gu=?";
//
//				pstmt = conn.prepareStatement(sql);
//
//				pstmt.setString(1, lprodGu);
//
//				rs = pstmt.executeQuery();
//
//				while (rs.next()) {
//					if (rs.getInt("count(*)") > 0) {
//						System.out.println("이미 존재하는 상품 분류 코드입니다...");
//						return;
//					} else {
//						break;
//					}
//				}
//				break;
//			}
//			
//			String sql = "insert into lprod (lprod_id, lprod_gu, lprod_nm)" + 
//					"   values((select max(lprod_id)+1 from lprod), ?, ?)";
//			
//			pstmt = conn.prepareStatement(sql);
//			
//			pstmt.setString(1, lprodGu);
//			pstmt.setString(2, lprodNm);
//			
//			int cnt = pstmt.executeUpdate();
//			
//			if(cnt > 0) {
//				System.out.println("등록이 완료되었습니다.");
//			}else {
//				System.out.println("등록 실패");
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}finally {
//			if(rs!=null) try { rs.close(); } catch(SQLException e) {}
//			if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
//			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
//		}
	}

}

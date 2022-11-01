package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcTest04 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "pc16", "java");
			
			System.out.println("계좌 번호 정보 추가하기");
			
			System.out.print("계좌번호 : ");
			String bankNo = scan.next();
			
			System.out.print("은행이름 : ");
			String bankName = scan.next();
			
			System.out.print("예금주이름 : ");
			String bankUserName = scan.next();
			
			/*
			//Statement 객체를 이용하여 데이터 추가
			String sql = "insert into bankinfo (bank_no, bank_name, bank_user_name, bank_date) "
					+ " values('" + bankNo + "', '" + bankName + "', '" + bankUserName + 
					"', sysdate)";
			
			System.out.println(sql);
			
			stmt = conn.createStatement();
			
			// select문을 실행할 때는 excuteQuery()메서드를 사용하고,
			
			// insert문, update문, delete문, select문이 아닌 쿼리문을 실행할 떄는
			// excuteUpadate()메서드를 사용한다.
			// excuteUpdate()메서드의 반환값 ==> '작업에 성공한 레코드 수'가 된다.
			
			int cnt = stmt.executeUpdate(sql);
			*/
			
			// PreparedStatement객체를 이용하여 처리하기
			
			// 쿼리문에서 데이터가 들어갈 자리를 물음표(?)로 표시하여 작성한다.
			String sql = "insert into bankinfo (bank_no, bank_name, bank_user_name, bank_date) "
					+ " values(?, ?, ?, sysdate)";
			
			// PreparedStatement객체 생성 ==> 생성할 쿼리문을 인수값으로 넘겨준다.
			pstmt = conn.prepareStatement(sql);
			
			// 쿼리문의 물음표(?)자리에 들어갈 데이터를 셋팅한다.
			// 형식) pstmt.set자료형이름(물음표번호, 셋팅할데이터)
			// 물음표에 순서대로 1번부터 시작
			pstmt.setString(1, bankNo);
			pstmt.setString(2, bankName);
			pstmt.setString(3, bankUserName);
			
			// 데이터 셋팅이 완료되면 쿼리문을 실행한다.
			int cnt = pstmt.executeUpdate();
			
			System.out.println("반환값 : " + cnt);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(stmt!=null) try { stmt.close(); } catch(SQLException e) {}
			if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
		}

	}

}

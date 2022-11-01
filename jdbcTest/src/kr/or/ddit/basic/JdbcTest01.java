package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//JDBC(Java DataBase Connectivity)라이브러리를 이용한 DB자료 처리하기

/*
	데이터베이스 처리 순서
1.JDBC드라이버 로딩 ==> JDBC라이브러리를 사용할 수 있도록 메모리로 읽어 들이는 작업
	Class.forName("oracle.jdbc.driver.OracleDriver");
	==> JDBC버전 4이상에서는 getConnection()메서드에서 자동으로 로딩해 주기 때문에 생략할 수 있다.
		(그렇지만 생략하지 않고 사용할 예정임...)
	
2. DB에 접속하기 ==> DriverManager.getConnection()메서드를 이용한다. 
			==>접속이 완료되면 Connection객체가 반환된다.

3. 질의 ==> SQL문장을 DB서버로 보내서 결과를 얻어온다.
		(Statrment객체나 PreparedStateemnt객체를 이용하여 작업한다.)
		
4. 결과 처리 ==> 질의 결과를 받아서 원하는 작업을 수행한다.
	1) SQL문이 select문일 경우에는 select한 결과가 ResultSet객체에 저장되어 반환된다.
	2) SQL문이 select문이 아닐 경우(insert, update, delete문 등일 경우)에는 정수값이 반롼된다.
		(보통 이 정수값은 실행에 성공한 레코드 수를 의미한다.)

5. 사용한 자원들 반납하기  ==> close()메서드를 이용한다.

*/
public class JdbcTest01 {

	public static void main(String[] args) {
		// DB작업에 필요한 객체 변수 선언
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. DB연결 ==> Connection객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "pc16", "java");
			
			// 3. 질의
			
			//.3-1. SQL문 작성
			String sql = "select lprod_id, lprod_gu gu, lprod_nm nm from lprod";
			
			//.3-2. Statement객체 생성 ==> 질의를 서버로 전송하고 결과를 얻어오는 객체
			//				(Connection객체를 이용해서 생성한다.)
			stmt = conn.createStatement();
			
			// 3-3. SQL문을 서버로 보내서 결과를 얻어온다.
			//		(실행할 SQL문아 select문이기 떄문에 처리 결과가 ResultSet객체에 저장되어 반환한다.)
			rs = stmt.executeQuery(sql);
			
			// 4. 결과 처리하기 ==> 한 레코드씩 화면에 출력하기
			//				==> select한 결과가 저장된 ResultSet객체에서 데이터를 차례로 꺼내오려면
			//				==> 반복문과 close()메서드를 이용한다.
			System.out.println(" == 쿼리문 처리 결과 == ");
			
			// re.next() ==> ResultSet객체의 데이터를 가리키는 포인터를 다음번째 레코드로 이동시키고
			//				그 곳에 데이터기 있으면 true, 없으면 false를 반환한다.
			while(rs.next()) {
				// 포인터가 가리키는 곳의 자료를 가져오는 방법
				// 형식1) rs.get자료형이름("컬럼명" 또는 "컬럼의 alias명");
				// 형식2) rs.get자료형이름(컬럼번호) ==> 컬럼번호는 1부터 시작한다.
				
				System.out.println("Lprod_id : " + rs.getInt("lprod_id"));	//컬럼명
				System.out.println("Lprod_gu : " + rs.getString(2));			//컬럼번호
				System.out.println("Lprod.nm : " + rs.getString("nm"));
				System.out.println("-----------------------------------");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			// 5. 자원 반납
			if(rs!=null) try { rs.close(); }catch(SQLException e) {}
			if(stmt!=null) try { stmt.close(); }catch(SQLException e) {}
			if(conn!=null) try { conn.close(); }catch(SQLException e) {}
		}
	}
}

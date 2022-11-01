package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//JDBC드라이버를 로딩하고 Connection객체를 생성하여 반환하는 메서드로 구성된 class

public class DBUtil {
	//정적(static) 초기화블럭 => 드라이버 로딩작업 수행
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패~~");
			e.printStackTrace();
		}
	}
	
	// Connection객체를 생성하여 반환하는 메서드
	public static Connection getConnection(){
		try {
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "pc16", "java");
		} catch (SQLException e) {
			System.out.println("DB연결 실패");
			return null;
		}
	}
	
}

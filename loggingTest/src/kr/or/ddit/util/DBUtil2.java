package kr.or.ddit.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

//JDBC드라이버를 로딩하고 Connection객체를 생성하여 반환하는 메서드로 구성된 class
// (dbinfo.properties 파일의 내용으로 설정하는 방법)

public class DBUtil2 {
	static Properties prop;
	
	//정적(static) 초기화블럭 => 드라이버 로딩작업 수행
	static {
		prop = new Properties();		//properties객체 생성
		File f = new File("res/kr/or/ddit/config/dbinfo.properties");
		FileInputStream fin = null;
		
		try {
			fin = new FileInputStream(f);	//스트림 객체 생성
			prop.load(fin);		//파일 내용 읽어와 Properties객체에 셋팅하기
			
//			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName(prop.getProperty("driver"));
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("드라이버 로딩 실패~~");
			e.printStackTrace();
		}
	}
	
	// Connection객체를 생성하여 반환하는 메서드
	public static Connection getConnection(){
		try {
//			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "pc16", "java");
			return DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"), prop.getProperty("pass"));
		} catch (SQLException e) {
			System.out.println("DB연결 실패");
			return null;
		}
	}
	
}

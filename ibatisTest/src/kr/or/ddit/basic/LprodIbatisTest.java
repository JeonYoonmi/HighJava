package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

//iBatis를 이용하여 DB자료를 처리하는 순서 및 방법
public class LprodIbatisTest {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		// 1. iBatis의 환경설정 파일(sqlMapConfig.xml)을 읽어와서 환경 설정을 진행한다.
		try {
			// 1-1. 문자 인코딩 케릭터 셋 설정
			Charset charset = Charset.forName("utf-8");
			Resources.setCharset(charset);
			
			// 1-2. 환경 설정 파일 읽어올 스트림 객체를 구한다.
			Reader rd = Resources.getResourceAsReader("kr/or/ddit/ibatis/config/SqlMapConfig.xml");
			
			// 1-3. 스트림 객체를 이용하여 환경 설정 파일을 읽어와 필요한 환경 설정을 진행하고
			//		환경 설정이 완료되면 작성된 SQL문을 호출해서 실행할 수 있는 iBatis객체를 생성한다.
			SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			
			rd.close();	//스트림 객체 닫기
			//---------------------------------------------------------
			
			// 2. 실행할 SQL문에 맞는 쿼리문을 호출해서 원하는 작업을 수행한다.
			/*
			// 2-1. insert연습
			System.out.println("insert 작업 시작...");
			System.out.print("lprod_id 입력 : ");
			int lprodId = scan.nextInt();
			
			System.out.print("lprod_gu 입력 : ");
			String lprodGu = scan.next();
			
			
			System.out.print("lprod_nm 입력 : ");
			String lprodNm = scan.next();
			
			// 입력값을 VO객체에 담는다.
			LprodVO lvo = new LprodVO();
			lvo.setLprod_id(lprodId);
			lvo.setLprod_gu(lprodGu);
			lvo.setLprod_nm(lprodNm);
			
			// SqlMapClient객체(iBatis용객체)를 이용하여 처리할 쿼리문을 호출하여 실행한다.
			// 형식) smc.insert("namespace속성값.id속성값", 파라미터클래스)
			//		반환값 : insert 성공 : null, 실패 : 오류 객체
			Object obj = smc.insert("lprod.insertLprod", lvo);
			if(obj==null) {
				System.out.println("insert 작업 성공~~~");
			}else {
				System.out.println("insert 작업 실패!!!");
			}
			System.out.println("-----------------------------------");
			*/
			
			/*
			// 2-2. update 연습
			System.out.println("update 시작...");
			
			System.out.println("수정할 Lprod_gu 입력 : ");
			String lprodGu = scan.next();
			
			System.out.println("새로운 Lprod_id 입력 : ");
			int lprodId = scan.nextInt();
			
			System.out.println("새로운 Lprod_nm 입력 : ");
			String lprodNm = scan.next();
			
			//입력받은 데이터를 VO객체에 저장하기
			LprodVO lvo2 = new LprodVO();
			lvo2.setLprod_gu(lprodGu);
			lvo2.setLprod_id(lprodId);
			lvo2.setLprod_nm(lprodNm);
			
			//SQL문 실행하기
			// 형식) smc.update("namespace속성값.id속성값", 파라미터클래스)
			//		반환값 : 작업에 성공한 레코드 수
			int cnt = smc.update("lprod.updateLprod", lvo2);
			
			if(cnt>0) {
				System.out.println("update 성공~~~");
			}else {
				System.out.println("update 실패!!!");
			}
			System.out.println("-----------------------------------");
			*/

			/*
			// 2-3. delete 작업
			System.out.println("delete 작업 시작...");
			
			System.out.print("삭제할 Lprod_gu 입력 : ");
			String lprodGu3 = scan.next();
			
			// SQL문 실행하기
			// 형식) smc.delete("namespace속성값.id속성값", 파라미터클래스)
			//		반환값 : 작업에 성공한 레코드 수
			int cnt2 = smc.delete("lprod.deleteLprod", lprodGu3);
			
			if(cnt2>0) {
				System.out.println("delete 작업 성공~~~");
			}else {
				System.out.println("delete 작업 실패!!!");
			}
			System.out.println("-----------------------------------");
			*/
			
			// 2-4. select 작업
			/*
			// 1) selcet한 결과가 여러개인 경우
			System.out.println("select 연습 시작 (결과가 여러개일 경우)...");
			
			// select한 결과가 여러개일 경우에는 queryForList()메서드를 사용하는데
			// 이 메서드는 여러개의 레코드 각각을 VO에 담은 후 이 VO객체를 List에 추가해주는 작업을 자동으로 수행한다.
			// 형식) smc.queryForlIST("namespace속성값.id속성값", 파라미터클래스)
			List<LprodVO> lprodList = smc.queryForList("lprod.getAllLprod");
			
			for (LprodVO lvo3 : lprodList) {
				System.out.println("Lprod_id : " + lvo3.getLprod_id());
				System.out.println("Lprod_gu : " + lvo3.getLprod_gu());
				System.out.println("Lprod_nm : " + lvo3.getLprod_nm());
				System.out.println("-----------------------------------");
			}
			System.out.println("출력 끝...");
			*/
			
			// 2) select한 결과가 1개안 경우
			System.out.println("select 연습 (결과가 1개일 경우)...");
			System.out.print("검색할 Lprod_gu 입력 : ");
			String lprodGu4 = scan.next();
			
			// select한 결과가 1개가 확실할 경우에는 queryForObject()메서드를 사용한다.
			// 형식) smc.queryForObject("namespace속성값.id속성값, 파라미터클래스)
			//		반환값 : - 처리 결과가 여러개일 경우 Exception 객체 반환
			//			   - 1개일 경우 : 해당 객체 반환(정상)
			//			   - 없을경우 : null 반환
			LprodVO lvo4 = (LprodVO) smc.queryForObject("lprod.getLprod", lprodGu4);
			
			if(lvo4 == null) {
				System.out.println("검색한 데이터가 하나도 없습니다.");
				return;
			}
			
			System.out.println("-----------------------------------");
			System.out.println("Lprod_id : " + lvo4.getLprod_id());
			System.out.println("Lprod_gu : " + lvo4.getLprod_gu());
			System.out.println("Lprod_nm : " + lvo4.getLprod_nm());
			System.out.println("-----------------------------------");
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}

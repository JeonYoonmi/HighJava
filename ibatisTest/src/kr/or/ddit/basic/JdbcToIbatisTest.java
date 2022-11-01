package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.Scanner;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.util.SqlMapClientFactory;

public class JdbcToIbatisTest {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		SqlMapClient smc = null;
		
		try {
//			Charset charset = Charset.forName("utf-8");
//			Resources.setCharset(charset);
//			
//			Reader rd = Resources.getResourceAsReader("kr/or/ddit/ibatis/config/SqlMapConfig.xml");

			smc = SqlMapClientFactory.getSqlMapClient();
			
//			rd.close();
			//===========================================================
			
			//Lprod_id는 현재의 Lprod_id값 중에서 제일 큰 값보다 1크게 한다.
			int max = (int) smc.queryForObject("jdbc.maxnum");
			int maxnum = max++;
			
			//입력받은 lprod_gu(상품분류코드)가 이미 등록되어 있으면 다시 입력받아서 처리한다.
			String gu;
			int num = 0;
			do {
				System.out.print("상품 분류 코드(LPROD_GU) 입력 : ");
				gu = scan.next();
				
				num = (int) smc.queryForObject("jdbc.checkLprodGu", gu);
				
				if(num > 0) {
					System.out.println("입력한 상품분튜코드 : " + gu + "는(은) 이미 등록된 코드입니다.");
					System.out.println("다시 입력하세요...");
				}
				
			}while(num > 0);
			
			System.out.print("상품 분류명(LPROD_NM) 입력 : ");
			String nm = scan.next();
			
			//입력받은 데이터들을 VO객체에 담는다.
			LprodVO lvo = new LprodVO();
			lvo.setLprod_id(maxnum);
			lvo.setLprod_gu(gu);
			lvo.setLprod_nm(nm);
			
			int cnt = smc.update("jdbc.insertLprod", lvo);
			
			if(cnt>0) {
				System.out.println("등록 성공!!");
			}else {
				System.out.println("등록 실패!!");
			}
			
//		} catch (IOException e) {
//			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}

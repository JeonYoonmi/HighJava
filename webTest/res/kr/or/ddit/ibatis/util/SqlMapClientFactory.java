package kr.or.ddit.ibatis.util;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

public class SqlMapClientFactory {
	private static SqlMapClient smc;
	
	static {
		Charset charset = Charset.forName("utf-8");
		Resources.setCharset(charset);
		
		Reader rd = null;
		try {
			rd = Resources.getResourceAsReader("kr/or/ddit/ibatis/config/SqlMapConfig.xml");
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			
		} catch (IOException e) {
			System.out.println("SqlMapClient객체 생성 실패!!!");
			e.printStackTrace();
		}finally {
			if(rd!=null) try { rd.close(); } catch(IOException e) {}
		}
	}
	
	public static SqlMapClient getSqlMapClient() {
		return smc;
	}
}

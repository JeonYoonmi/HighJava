package kr.or.ddit.basic.json.hw;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.basic.json.LprodVO;

public class LprodDaoimpl implements ILprodDao{

	private static LprodDaoimpl dao;
	
	private LprodDaoimpl() {}
	
	public static LprodDaoimpl getInctance() {
		if(dao==null) dao = new LprodDaoimpl();
		
		return dao;
		
	}
	
	@Override
	public List<LprodVO> getLprod(SqlMapClient smc) throws SQLException {
		
		return smc.queryForList("lprod.getLprod");
	}

}

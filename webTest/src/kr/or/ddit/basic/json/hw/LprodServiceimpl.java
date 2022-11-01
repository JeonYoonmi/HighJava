package kr.or.ddit.basic.json.hw;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.basic.json.LprodVO;
import kr.or.ddit.ibatis.util.SqlMapClientFactory;

public class LprodServiceimpl implements ILprodService{
	private ILprodDao dao;
	private SqlMapClient smc;
	
	private static LprodServiceimpl service;
	
	private LprodServiceimpl() {
		dao = LprodDaoimpl.getInctance();
		smc = SqlMapClientFactory.getSqlMapClient();
	}
	
	public static LprodServiceimpl getInstance() {
		if(service == null) service = new LprodServiceimpl();
		
		return service;
	}
	
	@Override
	public List<LprodVO> getLprod() {
		List<LprodVO> lprodVo = null;
		try {
			lprodVo = dao.getLprod(smc);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lprodVo;
	}

}

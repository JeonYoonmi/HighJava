package kr.or.ddit.basic.session;

import java.sql.SQLException;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.ibatis.util.SqlMapClientFactory;

public class MymemberDaoImpl implements IMymemberDao {
	
	private static MymemberDaoImpl dao;
	
	private MymemberDaoImpl() {
		
	}
	
	public static MymemberDaoImpl getInstance() {
		if(dao==null) dao = new MymemberDaoImpl();
		
		return dao;
	}
	
	@Override
	public String selectName(SqlMapClient smc, Map<String, String> map) throws SQLException {
		return (String) smc.queryForObject("member.selectName", map);
	}

	@Override
	public MymemberVO getLoginMember(SqlMapClient smc, MymemberVO memVo) throws SQLException {
		return (MymemberVO) smc.queryForObject("member.getLoginMember", memVo);
	}

}

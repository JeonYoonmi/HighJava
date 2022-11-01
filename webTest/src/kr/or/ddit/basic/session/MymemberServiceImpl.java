package kr.or.ddit.basic.session;

import java.sql.SQLException;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.ibatis.util.SqlMapClientFactory;

public class MymemberServiceImpl implements IMymemberService {
	private IMymemberDao dao;
	private SqlMapClient smc;
	
	private static MymemberServiceImpl service;
	
	private MymemberServiceImpl() {
		dao = MymemberDaoImpl.getInstance();
		smc = SqlMapClientFactory.getSqlMapClient();
	}
	
	public static MymemberServiceImpl getInstance() {
		if(service == null) service = new MymemberServiceImpl();
		
		return service;
	}
	
	@Override
	public String selectName(Map<String, String> map) {
		SqlMapClient smc = null;
		
		String name = null;
		
		try {
			name = dao.selectName(smc, map);
		} catch (SQLException e) {
			name = null;
			e.printStackTrace();
		}
		return name;
	}

	@Override
	public MymemberVO getLoginMember(MymemberVO memVo) {
		MymemberVO returnMemVo = null;
		try {
			returnMemVo = dao.getLoginMember(smc, memVo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnMemVo;
	}

}

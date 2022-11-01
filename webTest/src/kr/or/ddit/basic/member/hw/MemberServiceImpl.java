package kr.or.ddit.basic.member.hw;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.ibatis.util.SqlMapClientFactory;

public class MemberServiceImpl implements IMemberService {
	private SqlMapClient smc;
	private IMemberDao dao;
	
	private static MemberServiceImpl service;
	
	private MemberServiceImpl() {
		smc = SqlMapClientFactory.getSqlMapClient();
		dao = MemberDaoImpl.getInstance();
	}
	
	public static MemberServiceImpl getInstance() {
		if(service == null) service = new MemberServiceImpl();
		
		return service;
	}
	
	@Override
	public List<MemberVO> getAllMember() {
		List<MemberVO> memberList = null;
		try {
			memberList = dao.getAllMember(smc);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memberList;
	}

	@Override
	public String checkId(String memId) {
		String res = null;
		try {
			res = dao.checkId(smc, memId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public int insertMember(MemberVO memVo) {
		int cnt = 0;
		try {
			cnt = dao.insertMember(smc, memVo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

}

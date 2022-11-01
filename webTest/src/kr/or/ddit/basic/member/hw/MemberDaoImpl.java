package kr.or.ddit.basic.member.hw;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public class MemberDaoImpl implements IMemberDao {

	private static MemberDaoImpl dao;
	
	private MemberDaoImpl() {}
	
	public static MemberDaoImpl getInstance() {
		if(dao==null) dao = new MemberDaoImpl();
		
		return dao;
	}
	
	@Override
	public List<MemberVO> getAllMember(SqlMapClient smc) throws SQLException {
		
		return smc.queryForList("member.getAllMember");
	}

	@Override
	public String checkId(SqlMapClient smc, String memId) throws SQLException {
		
		return (String) smc.queryForObject("member.checkId", memId);
	}

	@Override

	public int insertMember(SqlMapClient smc, MemberVO memVo) throws SQLException {
		return (int) smc.insert("member.insertMember", memVo);
	}

}

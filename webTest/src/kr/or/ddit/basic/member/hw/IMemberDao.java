package kr.or.ddit.basic.member.hw;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

public interface IMemberDao {
	public List<MemberVO> getAllMember(SqlMapClient smc) throws SQLException;
	
	public String checkId(SqlMapClient smc, String memId) throws SQLException;
	
	public int insertMember(SqlMapClient smc, MemberVO memVo) throws SQLException;
}

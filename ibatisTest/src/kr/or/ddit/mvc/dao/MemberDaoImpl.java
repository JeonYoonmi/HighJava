package kr.or.ddit.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.mvc.vo.MemberVO;
import kr.or.ddit.util.SqlMapClientFactory;

public class MemberDaoImpl implements IMemberDAO{
	// 1번
	private static MemberDaoImpl dao;
	
	// 2번
	private MemberDaoImpl() {}
	
	// 3번
	public static MemberDaoImpl getInstance() {
		if(dao==null) dao = new MemberDaoImpl();
		
		return dao;
	}
	
	
	@Override
	public int insertMember(SqlMapClient smc, MemberVO memVo) throws SQLException {
		int cnt = 0;
		
		Object obj = smc.insert("member.insertMember", memVo);
		
		if(obj == null) {
			cnt = 1;
		}
		
		return cnt;
	}

	@Override
	public int deleteMember(SqlMapClient smc, String memId) throws SQLException {
//		int cnt =(int) smc.delete("member.deleteMember", memId);
//		return cnt;
		return smc.delete("member.deleteMember", memId);
	}

	@Override
	public int updateMember(SqlMapClient smc, MemberVO memVo) throws SQLException {
//		int cnt = (int) smc.update("member.updateMember", memVo);
//		return cnt;
		return smc.update("member.updateMember", memVo);
	}

	@Override
	public List<MemberVO> getAllMember(SqlMapClient smc) throws SQLException {
//		List<MemberVO> memVoList = smc.queryForList("member.getAllMember");
//		return memVoList;
		return smc.queryForList("member.getAllMember");
	}

	@Override
	public int getMemberCount(SqlMapClient smc, String memId) throws SQLException {
		int count = (int) smc.queryForObject("member.getMemberCount", memId);
		
		return count;
	}

	@Override
	public int updateMember2(SqlMapClient smc, Map<String, String> paramMap) throws SQLException {
//		int cnt = (int) smc.update("member.updateMember2", paramMap);
		//key값 정보 ==> 회원ID(memId), 수정할 컬럼명(field), 수정할 데이터(data)
		
		return smc.update("member.updateMember2", paramMap);
	}
	
}

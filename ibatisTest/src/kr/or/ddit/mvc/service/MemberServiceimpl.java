package kr.or.ddit.mvc.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.mvc.dao.IMemberDAO;
import kr.or.ddit.mvc.dao.MemberDaoImpl;
import kr.or.ddit.mvc.vo.MemberVO;
import kr.or.ddit.util.SqlMapClientFactory;

public class MemberServiceimpl implements IMemberService {
	private IMemberDAO dao;		//Dao객체가 저장될 변수 선언
	
	// 1번
	private static MemberServiceimpl service;
	
	//생성자, 2번
	private MemberServiceimpl() {
		//dao = new MemberDaoImpl();
		dao = MemberDaoImpl.getInstance();
	}
	
	// 3번
	public static MemberServiceimpl getInstance() {
		if(service==null) service = new MemberServiceimpl();
		
		return service;
	}

	@Override
	public int insertMember(MemberVO memVo) {
		SqlMapClient smc = null;
		
		int cnt = 0;		//반환값이 저장될 변수 선언
		
		try {
			smc = SqlMapClientFactory.getSqlMapClient();
			//DAO의 메서드 호출
			cnt = dao.insertMember(smc, memVo);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		SqlMapClient smc = null;
		
		int cnt = 0;	//반환값이 저장될 변수 선언
		
		try {
			smc = SqlMapClientFactory.getSqlMapClient();
			
			cnt = dao.deleteMember(smc, memId);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		SqlMapClient smc = null;
		
		int cnt = 0;
		
		try {
			smc = SqlMapClientFactory.getSqlMapClient();
			
			cnt = dao.updateMember(smc, memVo);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMember() {
		List<MemberVO> memList = null;
		SqlMapClient smc = null;
		
		try {
			smc = SqlMapClientFactory.getSqlMapClient();
			
			memList = dao.getAllMember(smc);
		} catch (SQLException e) {
			memList = null;
			e.printStackTrace();
		}
		
		return memList;
	}

	@Override
	public int getMemberCount(String memId) {
		int count = 0;
		SqlMapClient smc = null;
		
		try {
			smc = SqlMapClientFactory.getSqlMapClient();
			
			count = dao.getMemberCount(smc, memId);
			
		} catch (SQLException e) {
			count = 0;
			e.printStackTrace();
		}

		return count;
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		SqlMapClient smc = null;
		int cnt = 0;
		
		try {
			smc = SqlMapClientFactory.getSqlMapClient();
			
			cnt = dao.updateMember2(smc, paramMap);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		
		return cnt;
	}

}

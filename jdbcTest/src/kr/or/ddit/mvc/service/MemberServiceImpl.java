package kr.or.ddit.mvc.service;

import java.sql.Connection;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.mvc.dao.IMemberDAO;
import kr.or.ddit.mvc.dao.MemberDaoImpl;
import kr.or.ddit.mvc.vo.MemberVO;
import kr.or.ddit.util.DBUtil3;

public class MemberServiceImpl implements IMemberService {
	private IMemberDAO dao;		//Dao객체가 저장될 변수 선언
	
	// 1번
	private static MemberServiceImpl service;
	
	//생성자, 2번
	private MemberServiceImpl() {
		//dao = new MemberDaoImpl();
		dao = MemberDaoImpl.getInstance();
	}
	
	// 3번
	public static MemberServiceImpl getInstance() {
		if(service==null) service = new MemberServiceImpl();
		
		return service;
	}

	@Override
	public int insertMember(MemberVO memVo) {
		Connection conn = null;
		
		int cnt = 0;		//반환값이 저장될 변수 선언
		
		try {
			conn = DBUtil3.getConnection();
			
			//DAO의 메서드 호출
			cnt = dao.insertMember(conn, memVo);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}finally {
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		Connection conn = null;
		
		int cnt = 0;	//반환값이 저장될 변수 선언
		
		try {
			conn = DBUtil3.getConnection();
			
			cnt = dao.deleteMember(conn, memId);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}finally {
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
		}
		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		Connection conn = null;
		
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			
			cnt = dao.updateMember(conn, memVo);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}finally {
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
		}
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMember() {
		Connection conn =null;
		
		List<MemberVO> memList = null;
		
		try {
			conn = DBUtil3.getConnection();
			memList = dao.getAllMember(conn);
		} catch (SQLException e) {
			memList = null;
			e.printStackTrace();
		}finally {
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
		}
		
		return memList;
	}

	@Override
	public int getMemberCount(String memId) {
		Connection conn = null;
		
		int count = 0;
		
		try {
			conn = DBUtil3.getConnection();
			
			count = dao.getMemberCount(conn, memId);
		} catch (SQLException e) {
			count = 0;
			e.printStackTrace();
		}finally {
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
		}
		return count;
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		Connection conn = null;
		
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			
			cnt = dao.updateMember2(conn, paramMap);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}finally {
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
		}
		return cnt;
	}

}

package kr.or.ddit.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import kr.or.ddit.mvc.vo.MemberVO;

public class MemberDaoImpl implements IMemberDAO{
	static Logger logger = Logger.getLogger(MemberDaoImpl.class);
	
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
	public int insertMember(Connection conn, MemberVO memVo) throws SQLException {
		String sql = "insert into mymember (mem_id, mem_pass, mem_name, mem_tel, mem_addr)"
				+ "     values(?, ?, ?, ?, ?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memVo.getMem_id());
		pstmt.setString(2, memVo.getMem_pass());
		pstmt.setString(3, memVo.getMem_name());
		pstmt.setString(4, memVo.getMem_tel());
		pstmt.setString(5, memVo.getMem_addr());
		
		logger.debug("PreparedStatement객체 생성");
		logger.debug("실행 SQL : " + sql);
		logger.debug("파라미터 데이터 : [" + memVo.getMem_id() + ", " 
									+ memVo.getMem_pass() + ", " 
									+ memVo.getMem_name() + ", " 
									+ memVo.getMem_tel() + ", " 
									+ memVo.getMem_addr() + "]");
		
		int cnt = pstmt.executeUpdate();
		logger.info("작업 성공~~");
		
		if(pstmt!=null) pstmt.close();	//자원 반납
		
		return cnt;
	}

	@Override
	public int deleteMember(Connection conn, String memId) throws SQLException {
		String sql = "delete from mymember where mem_id = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memId);
		
		logger.debug("PreparedStatement객체 생성");
		logger.debug("실행 SQL : " + sql);
		logger.debug("파라미터 데이터 : " + memId);
		
		int cnt = pstmt.executeUpdate();
		logger.info("작업 성공");
		
		if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
		
		return cnt;
	}

	@Override
	public int updateMember(Connection conn, MemberVO memVo) throws SQLException {
		String sql = "update mymember"
				+ "   set mem_pass = ?, mem_name = ?, mem_tel = ?, mem_addr = ?"
				+ "   where mem_id = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memVo.getMem_pass());
		pstmt.setString(2, memVo.getMem_name());
		pstmt.setString(3, memVo.getMem_tel());
		pstmt.setString(4, memVo.getMem_addr());
		pstmt.setString(5, memVo.getMem_id());
		logger.debug("PreparedStatement객체 생성");
		logger.debug("실행 SQL : " + sql);
		logger.debug("파라미터 데이터 : [" + memVo.getMem_pass() + ", " 
									+ memVo.getMem_name() + ", "
									+ memVo.getMem_tel() + ", "
									+ memVo.getMem_addr() + ", "
									+ memVo.getMem_id() + "]");
		
		int cnt = pstmt.executeUpdate();
		logger.info("작업 성공");
		
		if(pstmt!=null) try{ pstmt.close(); } catch(SQLException e) {}
		
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMember(Connection conn) throws SQLException {
		List<MemberVO> memVoList = null;	//반환시킬값이 저장될 변수
		
		String sql = "select * from mymember";
		
		Statement stmt = conn.createStatement();
		logger.debug("Statement객체 생성");
		logger.debug("실행 SQL : " + sql);
		
		ResultSet rs = stmt.executeQuery(sql);
		logger.info("작업 성공");
		
		memVoList = new ArrayList<MemberVO>();
		
		while(rs.next()) {
			MemberVO memVo = new MemberVO();	//1개의 레코드가 저장될 VO객체 생성
			
			memVo.setMem_id(rs.getString("mem_id"));
			memVo.setMem_pass(rs.getString("mem_pass"));
			memVo.setMem_name(rs.getString("mem_name"));
			memVo.setMem_tel(rs.getString("mem_tel"));
			memVo.setMem_addr(rs.getString("mem_addr"));
			
			memVoList.add(memVo);		//List에 MemberVO객체 추가
		}
		
		if(rs!=null) rs.close();
		if(stmt!=null) stmt.close();
		
		return memVoList;
	}

	@Override
	public int getMemberCount(Connection conn, String memId) throws SQLException {
		String sql = "select count(*) cnt from mymember where mem_id = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, memId);
		
		logger.debug("PreparedStatement객체 생성");
		logger.debug("실행 SQL : " + sql);
		logger.debug("파라미터 데이터 : " + memId);
		
		ResultSet rs = pstmt.executeQuery();
		logger.info("작업 성공");
		
		int count = 0;
		if(rs.next()) count = rs.getInt("cnt");
		
		if(rs!=null) rs.close();
		if(pstmt!=null) pstmt.close();
		
		return count;
	}

	@Override
	public int updateMember2(Connection conn, Map<String, String> paramMap) throws SQLException {
		// key값 정보 ==> 회원ID(memId),		수정할 컬럼명(field),		수정할 데이터(data)
		String sql = "update mymember set " + paramMap.get("field") + " = ? "
				+ " where mem_id = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, paramMap.get("data"));
		pstmt.setString(2, paramMap.get("memId"));
		
		logger.debug("PreparedStatement객체 생성");
		logger.debug("실행 SQL : " + sql);
		logger.debug("파라미터 데이터 : [" + paramMap.get("data") + ", "
									+ paramMap.get("memId") + "]");
		
		int cnt = pstmt.executeUpdate();
		logger.info("작업 성공");
		
		if(pstmt!=null) pstmt.close();
		
		return cnt;
	}
	
}

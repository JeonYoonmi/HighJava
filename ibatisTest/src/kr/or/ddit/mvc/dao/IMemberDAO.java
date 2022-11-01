package kr.or.ddit.mvc.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.mvc.vo.MemberVO;

/**
 * 실제 DB와 연결해서 SQL문을 수행하여 결과를 작성해서 Service에 전달하는 DAO의 interface<br>
 * 
 * 메서드 하나가 DB와 관련된 작업 1개를 수행하도록 작성한다.<br>
 * 
 * @author 406-16
 * 
 */

public interface IMemberDAO {
	/**
	 * MemberVo객체에 담겨진 자료를 DB에 insert하는 메서드
	 * 
	 * @param conn java.sql.Connection객체
	 * @param memVo DB에 insert할 자료가 저장된 MemberVO객체
	 * @return insert작업 성공 : 1, insert작업 실패 : 0;
	 * @throws SQLException
	 */
	public int insertMember(SqlMapClient smc, MemberVO memVo) throws SQLException;
	
	/**
	 * 회원ID를 인수값으로 받아서 해당 회원 정보를 삭제하는 메서드
	 * 
	 * @param conn Connection객체
	 * @param memId 삭제할 회원ID
	 * @return 삭제 성공 : 1, 삭제 실패 : 2
	 * @throws SQLException
	 */
	public int deleteMember(SqlMapClient smc, String memId) throws SQLException;
	
	/**
	 * MemberVo자료를 이용하여 회원 정보를 update하는 메서드
	 * 
	 * @param conn Connection객체
	 * @param memVo update할 회원정보가 저장된 MemberVo객체
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 * @throws SQLException
	 */
	public int updateMember(SqlMapClient smc, MemberVO memVo) throws SQLException;
	
	/**
	 * DB의 전체 회원 정보를 가져와서 List에 담아서 반환하는 메서드
	 * 
	 * @param conn Connection객체
	 * @return MemberVo객체가 저장된 List
	 * @throws SQLException
	 */
	public List<MemberVO> getAllMember(SqlMapClient smc) throws SQLException;
	
	/**
	 * 회원ID를 인수값으로 받아서 해당 회원ID의 개수를 반환하는 메서드
	 * 
	 * @param conn Connection객체
	 * @param memId 검색할 회원ID
	 * @return 검색된 회원ID의 개수
	 * @throws SQLException
	 */
	public int getMemberCount(SqlMapClient smc, String memId) throws SQLException;

	/**
	 * Map의 정보를 이용하여 회원 정보 중 원하는 컬럼을 수정하는 메서드
	 *  Key값 정보 ==> 회원ID(memId),	수정할 컬럼명(field), 수정할 데이터(data)
	 * 
	 * @param conn Connection객체
	 * @param paramMap 회원ID, 수정할 컬럼명, 수정할 데이터가 저장된 Map객체
	 * @return 작업 성공 : 1, 작업 실패 : 0
	 * @throws SQLException
	 */
	public int updateMember2(SqlMapClient smc, Map<String, String> paramMap) throws SQLException;
}

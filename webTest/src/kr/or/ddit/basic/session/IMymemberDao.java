package kr.or.ddit.basic.session;

import java.sql.SQLException;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

public interface IMymemberDao {
	public String selectName(SqlMapClient smc, Map<String, String> map) throws SQLException;
	
	/**
	 * 회원ID와 Password가 저장된 MymemberVO객체를 인수값으로 받아서 해당 회원정보를 반환하는 메서드
	 * @param smc SqlMapClient객체
	 * @param vo 회원ID와 PassWord가 저장된 MymemberVO객체
	 * @return 로그인 성공시  : 해당회원정보가 저장된 MymemberVO객체, 로그인실패시  : null
	 * @throws SQLException
	 */
	public MymemberVO getLoginMember(SqlMapClient smc, MymemberVO memVo) throws SQLException;
	
}

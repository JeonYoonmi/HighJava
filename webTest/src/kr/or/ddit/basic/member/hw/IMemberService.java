package kr.or.ddit.basic.member.hw;

import java.util.List;

public interface IMemberService {
	public List<MemberVO> getAllMember();
	
	public String checkId(String memId);
	
	public int insertMember(MemberVO memVo);
}

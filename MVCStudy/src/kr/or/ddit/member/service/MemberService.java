package kr.or.ddit.member.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.MemberVO;

public interface MemberService {

	List<MemberVO> getAllMemberList();

	List<MemberVO> getMemberList(Map<String, Object> paramMap);
}

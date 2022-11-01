package kr.or.ddit.member.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

public class MemberServiceImpl implements MemberService {
	private static MemberService instance;
	
	private MemberServiceImpl() {}
	
	public static MemberService getInstance() {
		if(instance==null) instance = new MemberServiceImpl();
		
		return instance;
	}
	
	private MemberDAO dao = MemberDAOImpl.getInstance();

	@Override
	public List<MemberVO> getAllMemberList() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		// 사진 참고 - 3
		list = dao.getAllMemberList();
		
		/// 사진 참고 - 7
		return list;
	}

	@Override
	public List<MemberVO> getMemberList(Map<String, Object> paramMap) {
		List<MemberVO> list = new ArrayList<MemberVO>();
		// 사진 참고 - 3
		list = dao.getMemberList(paramMap);
		
		/// 사진 참고 - 7
		return list;
	}
	
	
}

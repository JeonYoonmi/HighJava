package kr.or.ddit.board.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.board.dao.IJdbcBoardDao;
import kr.or.ddit.board.dao.JdbcBoardDaoImpl;
import kr.or.ddit.board.vo.JdbcBoardVO;
import kr.or.ddit.util.DBUtil;

public class JdbcBoardServiceImpl implements IJdbcBoardService {

	private IJdbcBoardDao dao;
	
	private static JdbcBoardServiceImpl service;
	
	private JdbcBoardServiceImpl() {
		dao = JdbcBoardDaoImpl.getInstance();
	}
	
	public static JdbcBoardServiceImpl getInstance() {
		if(service == null) service = new JdbcBoardServiceImpl();
		
		return service;
	}

	@Override
	public int insertBoard(JdbcBoardVO boardVo){
		Connection conn = null;
		
		int cnt = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			cnt = dao.insertBoard(conn, boardVo);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}finally {
			if(conn!=null) try { conn.close(); } catch (SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		Connection conn = null;
		
		int cnt = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			cnt = dao.deleteBoard(conn, boardNo);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}finally {
			if(conn!=null) try { conn.close(); } catch (SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public int updateBoard(JdbcBoardVO boardVo) {
		Connection conn = null;
		
		int cnt = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			cnt = dao.updateBoard(conn, boardVo);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}finally {
			if(conn!=null) try { conn.close(); } catch (SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public JdbcBoardVO getBoard(int boardNo) {
		Connection conn = null;
		JdbcBoardVO boardVo = null;
		
		try {
			conn = DBUtil.getConnection();
			
			//조회수 증가하기를 실행한다.
			if(dao.setCountIncrement(conn, boardNo) == 0) {
				return null;	//조회수 증가 작업이 실패 했을 때...
			}
			
			boardVo = dao.getBoard(conn, boardNo);
		} catch (SQLException e) {
			boardVo = null;
			e.printStackTrace();
		}finally {
			if(conn!=null) try { conn.close(); } catch (SQLException e) {}
		}
		
		return boardVo;
	}

	@Override
	public List<JdbcBoardVO> getAllBoardList() {
		Connection conn = null;
		List<JdbcBoardVO> boardList = null;
		
		try {
			conn = DBUtil.getConnection();
			
			boardList = dao.getAllBoardList(conn);
			
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		}finally {
			if(conn!=null) try { conn.close(); } catch (SQLException e) {}
		}
		return boardList;
	}

	@Override
	public List<JdbcBoardVO> getSearchBoardList(String title) {
		Connection conn = null;
		List<JdbcBoardVO> boardList = null;
		
		try {
			conn = DBUtil.getConnection();
			
			boardList = dao.getSearchBoardList(conn, title);
			
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		}finally {
			if(conn!=null) try { conn.close(); } catch (SQLException e) {}
		}
		return boardList;
	}

	@Override
	public int setCountIncrement(int boardNo) {
		Connection conn = null;
		int cnt = 0;
		
		try {
			conn = DBUtil.getConnection();
			cnt = dao.setCountIncrement(conn, boardNo);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}
	

}

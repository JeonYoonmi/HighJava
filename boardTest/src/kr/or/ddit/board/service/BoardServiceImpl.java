package kr.or.ddit.board.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.util.DBUtil;

public class BoardServiceImpl implements IBoardService{
	private IBoardDao dao;
	
	private static BoardServiceImpl service;
	
	private BoardServiceImpl() {
		dao = BoardDaoImpl.getInstance();
	}
	
	public static BoardServiceImpl getInstance() {
		if(service==null) service = new BoardServiceImpl();
		
		return service;
	}

	@Override
	public int insertBoard(BoardVO boardVo) {
		Connection conn = null;
		
		int cnt = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			cnt = dao.insertBoard(conn, boardVo);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}finally {
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
		}
		return cnt;
	}

	@Override
	public int updateBoard(BoardVO boardVo) {
		Connection conn = null;
		
		int cnt = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			cnt = dao.updateBoard(conn, boardVo);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}finally {
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
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
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
		}
		return cnt;
	}

	@Override
	public List<BoardVO> getAllBoard() {
		Connection conn = null;
		
		List<BoardVO> boardList = null;
		
		try {
			conn = DBUtil.getConnection();
			
			boardList = dao.getAllBoard(conn);
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		}finally {
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
		}
		return boardList;
	}

	@Override
	public List<BoardVO> searchBoard(String boardTitle) {
		Connection conn = null;
		
		List<BoardVO> boardList = null;
		
		try {
			conn = DBUtil.getConnection();
			
			boardList = dao.searchBoard(conn, boardTitle);
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		}finally {
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
		}
		return boardList;
	}

	@Override
	public int updateBoardCnt(int boardCnt) {
		Connection conn = null;
		
		int cnt = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			cnt = dao.updateBoardCnt(conn, boardCnt);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}finally {
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
		}
		return cnt;
	}

	@Override
	public Map<Integer, BoardVO> selectBoard(int boardNo) {
		Connection conn = null;
		
		Map<Integer, BoardVO> boardMap = null;
		
		try {
			conn = DBUtil.getConnection();
			
			boardMap = dao.selectBoard(conn, boardNo);
		} catch (SQLException e) {
			boardMap = null;
			e.printStackTrace();
		}finally {
			if(conn!=null) try { conn.close(); } catch(SQLException e) {}
		}
		return boardMap;
	}
	
	
}

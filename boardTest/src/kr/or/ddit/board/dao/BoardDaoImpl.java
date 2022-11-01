package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.vo.BoardVO;

public class BoardDaoImpl implements IBoardDao {

	private static BoardDaoImpl dao;
	
	private BoardDaoImpl() { }
	
	public static BoardDaoImpl getInstance() {
		if(dao==null) dao = new BoardDaoImpl();
		
		return dao;
	}
	
	@Override
	public int insertBoard(Connection conn, BoardVO boardVo) throws SQLException {
		String sql = "insert into jdbc_board(board_no, board_title, board_writer, board_date, board_cnt, board_content) "
				+ "		values(board_seq.NEXTVAL, ?, ?, sysdate, 0, ?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, boardVo.getBoard_title());
		pstmt.setString(2, boardVo.getBoard_writer());
		pstmt.setString(3, boardVo.getBoard_content());
		
		int cnt = pstmt.executeUpdate();
		
		if(pstmt!=null) try { pstmt.close(); } catch(SQLException e) {}
		
		return cnt;
	}

	@Override
	public int updateBoard(Connection conn, BoardVO boardVo) throws SQLException {
		String sql = "update jdbc_board"
				+ "	set board_title = ?, board_content = ?"
				+ "	where board_no = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, boardVo.getBoard_title());
		pstmt.setString(2, boardVo.getBoard_content());
		pstmt.setInt(3, boardVo.getBoard_no());
		
		int cnt = pstmt.executeUpdate();
		
		if(pstmt!=null) pstmt.close();
		
		return cnt;
	}

	@Override
	public int deleteBoard(Connection conn, int boardNo) throws SQLException {
		String sql = "delete from jdbc_board where board_no = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, boardNo);
		
		int cnt = pstmt.executeUpdate();
		
		if(pstmt!=null) pstmt.close();
		
		return cnt;
	}

	@Override
	public List<BoardVO> getAllBoard(Connection conn) throws SQLException {
		List<BoardVO> boardList = null;
		
		String sql = "select  board_no, board_title, board_writer, to_char(board_date, 'YYYY-MM-DD') board_date, board_cnt, board_content"
				+ " from jdbc_board";
		
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		boardList = new ArrayList<BoardVO>();
		
		while(rs.next()) {
			BoardVO boardVo = new BoardVO();
			
			boardVo.setBoard_no(rs.getInt("board_no"));
			boardVo.setBoard_title(rs.getString("board_title"));
			boardVo.setBoard_writer(rs.getString("board_writer"));
			boardVo.setBoard_content(rs.getString("board_content"));
			boardVo.setBoard_date(rs.getString("board_date"));
			boardVo.setBoard_cnt(rs.getString("board_cnt"));
			
			boardList.add(boardVo);
		}
		return boardList;
	}

	@Override
	public List<BoardVO> searchBoard(Connection conn, String boardTitle) throws SQLException {
		List<BoardVO> boardList = null;
		
		String sql = "select  board_no, board_title, board_writer, to_char(board_date, 'YYYY-MM-DD') board_date, board_cnt, board_content"
				+ " from jdbc_board where board_title like ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, boardTitle);
		
		ResultSet rs = pstmt.executeQuery();
		
		boardList = new ArrayList<BoardVO>();
		
		while(rs.next()) {
			BoardVO boardVo = new BoardVO();
			
			boardVo.setBoard_no(rs.getInt("board_no"));
			boardVo.setBoard_title(rs.getString("board_title"));
			boardVo.setBoard_writer(rs.getString("board_writer"));
			boardVo.setBoard_content(rs.getString("board_content"));
			boardVo.setBoard_date(rs.getString("board_date"));
			boardVo.setBoard_cnt(rs.getString("board_cnt"));
			
			boardList.add(boardVo);
		}
		return boardList;
	}

	@Override
	public int updateBoardCnt(Connection conn, int boardNo) throws SQLException {
		String sql = "update jdbc_board"
				+ "	set board_cnt = (select board_cnt+1 from jdbc_board where board_no = ?)" 
				+ "	where board_no = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, boardNo);
		pstmt.setInt(2, boardNo);
		
		int cnt = pstmt.executeUpdate();
		
		if(pstmt!=null) pstmt.close();
		
		return cnt;
	}

	@Override
	public Map<Integer, BoardVO> selectBoard(Connection conn, int boardNo) throws SQLException {
		Map<Integer, BoardVO> boardMap = null;
		
		String sql = "select board_no, board_title, board_writer, to_char(board_date, 'YYYY-MM-DD') board_date, board_cnt, board_content"
				+ " from jdbc_board where board_no = " + boardNo;
		
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		boardMap = new HashMap<Integer, BoardVO>();
		
		while(rs.next()) {
			BoardVO boardVo = new BoardVO();
			
			boardVo.setBoard_no(rs.getInt("board_no"));
			boardVo.setBoard_title(rs.getString("board_title"));
			boardVo.setBoard_writer(rs.getString("board_writer"));
			boardVo.setBoard_content(rs.getString("board_content"));
			boardVo.setBoard_date(rs.getString("board_date"));
			boardVo.setBoard_cnt(rs.getString("board_cnt"));
			
			boardMap.put(boardNo, boardVo);
		}
		return boardMap;
	}

}

package kr.or.ddit.basic.json.hw;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.basic.json.LprodVO;
import kr.or.ddit.ibatis.util.DBUtil3;

public class LprodDao {
	public List<LprodVO> getLprodList(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<LprodVO> lprodList = null;
		
		try {
			String sql = "select * from lprod";
			
			conn = DBUtil3.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			lprodList = new ArrayList<LprodVO>();
			while (rs.next()) {
				LprodVO lvo = new LprodVO();
				lvo.setLprod_id(rs.getInt("lprod_id"));
				lvo.setLprod_gu(rs.getString("lprod_gu"));
				lvo.setLprod_nm(rs.getString("lprod_nm"));
				
				lprodList.add(lvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs != null) try { rs.close(); } catch (SQLException e2) {}
			if(pstmt != null) try { pstmt.close(); } catch (SQLException e2) {}
			if(conn != null) try { conn.close(); } catch (SQLException e2) {}
		}
		
		return lprodList;
	}
}

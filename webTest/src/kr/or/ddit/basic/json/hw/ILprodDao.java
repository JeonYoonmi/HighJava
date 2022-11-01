package kr.or.ddit.basic.json.hw;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.basic.json.LprodVO;

public interface ILprodDao {
	public List<LprodVO> getLprod(SqlMapClient smc) throws SQLException;
}

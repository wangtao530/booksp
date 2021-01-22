package book.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class BaseDao {

	protected Connection conn;
	protected PreparedStatement ps;
	protected ResultSet rs;
	protected CallableStatement cs;

	public BaseDao(Connection conn) {
		super();
		this.conn = conn;
	}

	public BaseDao() {
		super();
	}

	// 获取数据库连接
	public boolean getConn() {
		if (this.conn != null) {
			return true;
		} else {
			return false;
		}
	}

	// 注入数据库连接
	public void setConn(Connection conn) {
		this.conn = conn;
	}

	// 增删改
	// insert into news_detail(x,x,x)values(?,?,?)
	// delete from news_detail where id=? and title=?
	// update news_detail set title=? where id=?
	public int executeUpdate(String sql, Object[] params) {
		int updateRows = 0;
		if (this.getConn()) {
			try {
				ps = conn.prepareStatement(sql);
				// 填充占位符
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
				updateRows = ps.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return updateRows;
	}

	// 查询
	public ResultSet executeSQL(String sql, Object[] params) {
		if (this.getConn()) {
			try {
				ps = conn.prepareStatement(sql);
				// 填充占位符
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
				rs = ps.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rs;
	}

	public boolean closeResource() {
		try {
			if (rs != null) {
				rs.close();
			}
			rs = null;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		try {
			if (ps != null) {
				ps.close();
			}
			ps = null;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		try {
			if (cs != null) {
				cs.close();
			}
			cs = null;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		try {
			if (conn != null) {
				conn.close();
			}
			conn = null;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}

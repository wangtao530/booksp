package book.web.servlet;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

/** 用于从ServletContext获取连接 */
public class DataSourceServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected Connection getConn() {
		DataSource ds = (DataSource) this.getServletContext().getAttribute("DS");
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

}

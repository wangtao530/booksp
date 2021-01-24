package book.web.servlet;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

/** 用于从ServletContext获取连接 */
@WebServlet(description = "获取数据源连接", urlPatterns = { "/dss" })
public class DataSourceServlet extends HttpServlet {


	/**
	 * 
	 */
	private static final long serialVersionUID = -4002775472524985604L;

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

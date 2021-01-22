package book.web.listener;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
@WebListener
public class DataSourceListener implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc = sce.getServletContext();
		try {
			// 初始化上下文
			Context cxt = new InitialContext();
			// 获取与逻辑名相关联的数据源对象
			DataSource ds = (DataSource) cxt.lookup("java:comp/env/jdbc/book");
			sc.setAttribute("DS", ds);
			System.out.println("获取数据源连接池");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/** 解决tomcat重启报错 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// 注销驱动
		Enumeration<Driver> drivers = DriverManager.getDrivers();
		Driver driver = null;
		while (drivers.hasMoreElements()) {
			try {
				driver = drivers.nextElement();
				DriverManager.deregisterDriver(driver);
				System.out.println("deregister success : driver" + driver.toString());
			} catch (SQLException e) {
				System.out.println("deregister error :" + e + ": driver" + driver.toString());
			}
		}
		AbandonedConnectionCleanupThread.uncheckedShutdown();
	}

}

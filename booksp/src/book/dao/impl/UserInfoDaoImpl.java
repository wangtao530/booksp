package book.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import book.dao.BaseDao;
import book.dao.IDao;
import book.entity.UserInfo;
import book.util.PageSupport;

public class UserInfoDaoImpl extends BaseDao implements IDao {

	public UserInfoDaoImpl() {
		super();
	}

	public UserInfoDaoImpl(Connection conn) {
		super(conn);
	}

	/** 添加用户 */
	@Override
	public <T> boolean add(T t) {
		UserInfo u = (UserInfo) t;
		try {
			String sql = "insert into userInfo(userid, username, password, email) values (null, ?, ?, ?)";
			Object[] params = { u.getUserName(), u.getPassword(), u.getEmail() };
			int i = super.executeUpdate(sql, params);
			if (i > 0) {
				System.out.println("-------->插入成功。");
				return true;
			}
		} finally {
			super.closeResource();
		}
		return false;
	}

	@Override
	public <T> boolean delete(T t) {
		return false;
	}

	@Override
	public <T> boolean update(T t) {
		return false;
	}

	@Override
	public <T> List<T> getList() {
		return null;
	}

	@Override
	public <T> T get(T t) {
		UserInfo u = (UserInfo) t;
		String userName = u.getUserName();
		String password = u.getPassword();
		if (userName != null && password != null) {
			return get(userName, password);
		} else if (password == null) {
			return get(userName);
		}
		return null;
	}

	/**
	 * 注册用户名验证
	 * 
	 * @param u 用户名
	 */
	@SuppressWarnings("unchecked")
	private <T> T get(String u) {
		String sql = "select username, password, email from userInfo where username=?";
		Object[] params = { u };
		ResultSet rs = super.executeSQL(sql, params);
		UserInfo udb = null;
		try {
			while (rs.next()) {
				udb = new UserInfo();
				udb.setUserName(rs.getString(1));
				udb.setPassword(rs.getString(2));
				udb.setEmail(rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeResource();
		}
		return (T) udb;
	}

	/**
	 * 登陆用户名密码验证
	 * 
	 * @param u 用户名
	 * @param p 密码
	 */
	@SuppressWarnings("unchecked")
	private <T> T get(String u, String p) {
		String sql = "select username, password, email from userInfo where username=? and password=?";
		Object[] params = { u, p };
		ResultSet rs = super.executeSQL(sql, params);
		UserInfo udb = null;
		try {
			while (rs.next()) {
				udb = new UserInfo();
				udb.setUserName(rs.getString(1));
				udb.setPassword(rs.getString(2));
				udb.setEmail(rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeResource();
		}
		return (T) udb;
	}

	@Override
	public <T> int getCount() {
		return 0;
	}

	@Override
	public <T> List<T> getPageList(PageSupport pageSupport) {
		return null;
	}

}

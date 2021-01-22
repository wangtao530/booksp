package book.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import book.dao.BaseDao;
import book.dao.IDao;
import book.entity.Order;
import book.util.PageSupport;

public class OrderDaoImpl extends BaseDao implements IDao {

	public OrderDaoImpl() {
		super();
	}

	public OrderDaoImpl(Connection conn) {
		super(conn);
	}
//
//	@Override
//	public <T> boolean add(T t) {
//		Order o = (Order) t;
//		try {
//			String sql = "insert into orders(oid, username) values (seq_orders.nextval, ?)";
//			Object[] params = { o.getUserName() };
//			int i = super.executeUpdate(sql, params);
//			if (i > 0) {
//				System.out.println("-------->插入成功。");
//				return true;
//			}
//		} finally {
//			super.closeResource();
//		}
//		return false;
//	}

	@Override
	public <T> boolean add(T t) {
		Order order = (Order) t;
		boolean flag = false;
		if (super.getConn()) {
			try {
				String sql = "{call insert_orders(?,?)}";
				cs = conn.prepareCall(sql);
				cs.setString(1, order.getUserName());
				cs.registerOutParameter(2, Types.INTEGER);
				flag = cs.execute();
				// 获取存储过程的返回值
				if (!flag) {
					System.out.println("------>订单插入成功");
				}
				int oid = cs.getInt(2);
				order.setOid(oid);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				super.closeResource();
			}
		}
		return flag;
	}

	// 执行存储过程
//	public void exeProcedure() {
//
//	}

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
		return null;
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

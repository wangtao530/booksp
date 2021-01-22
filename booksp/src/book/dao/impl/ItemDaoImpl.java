package book.dao.impl;

import java.sql.Connection;
import java.util.List;

import book.dao.BaseDao;
import book.dao.IDao;
import book.entity.Item;
import book.util.PageSupport;

public class ItemDaoImpl extends BaseDao implements IDao {

	public ItemDaoImpl() {
		super();
	}

	public ItemDaoImpl(Connection conn) {
		super(conn);
	}

	@Override
	public <T> boolean add(T t) {
		Item m = (Item) t;
		try {
			String sql = "insert into items (iid, oid, bid, createdate, count, price, total_price) values (null, ?, ?, ?, ?, ?, ?)";
			Object[] params = { m.getOid(), m.getBid(), m.getCreateDate(), m.getCount(), m.getPrice(),
					m.getTotalPrice() };
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public <T> boolean update(T t) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return null;
	}

}

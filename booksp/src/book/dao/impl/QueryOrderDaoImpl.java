package book.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import book.dao.BaseDao;
import book.dao.IPageDao;
import book.entity.business.QueryOrder;
import book.util.PageSupport;

public class QueryOrderDaoImpl extends BaseDao implements IPageDao {

	public QueryOrderDaoImpl() {
		super();
	}

	public QueryOrderDaoImpl(Connection conn) {
		super(conn);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T, K> List<T> getPageList(PageSupport pageSupport, K k) {
		List<QueryOrder> list = null;
		// 编写分页查询的SQL语句
		String sql = "select iid,username,createdate,total_price,image,bookname,b_price,count from queryorder where username=? order by createdate desc limit ?,?";
		Object[] params = { k, pageSupport.getStartRowForMysql(), pageSupport.getPageSize() };
		rs = super.executeSQL(sql, params);
		try {
			QueryOrder q = new QueryOrder();
			while (rs.next()) {
				if (list == null) {
					list = new ArrayList<QueryOrder>();
				}
				QueryOrder cq = q.clone();
				int iid = rs.getInt(1);
				String username = rs.getString(2);
				String createdate = rs.getString(3);
				double totalprice = rs.getDouble(4);
				String image = rs.getString(5);
				String name = rs.getString(6);
				double price = rs.getDouble(7);
				int count = rs.getInt(8);
				cq.setIid(iid);
				cq.setUsername(username);
				cq.setCreatedate(createdate);
				cq.setTotalprice(totalprice);
				cq.setImage(image);
				cq.setName(name);
				cq.setPrice(price);
				cq.setCount(count);
				list.add(cq);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		} finally {
			super.closeResource();
		}
		return (List<T>) list;
	}

	@Override
	public <T> int getCount(T t) {
		QueryOrder q = (QueryOrder) t;
		int totalCount = 0;
		try {
			String sql = "select count(0) from queryorder where username=?";
			Object[] params = { q.getUsername() };
			rs = super.executeSQL(sql, params);
			while (rs.next()) {
				totalCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeResource();
		}
		return totalCount;
	}

}

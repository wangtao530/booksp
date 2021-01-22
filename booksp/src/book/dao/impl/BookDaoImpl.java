package book.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import book.dao.BaseDao;
import book.dao.IDao;
import book.entity.Book;
import book.util.PageSupport;

public class BookDaoImpl extends BaseDao implements IDao {

	public BookDaoImpl() {
		super();
	}

	public BookDaoImpl(Connection conn) {
		super(conn);
	}

	/**插入mysql带Auto Incremental字段时，可以把字段值设null或者插入时不加这个字段*/
	@Override
	public <T> boolean add(T t) {
		Book b = (Book) t;
		try {
			
			String sql = "insert into books(bid,bookname, b_price, image, stock) values (null, ?, ?, ?, ?)";
			Object[] params = { b.getName(), b.getPrice(), b.getImage(), b.getStock() };
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
		Book b = (Book) t;
		try {
			String sql = "delete from books where bid=" + b.getBid();
			Object[] params = {};
			int i = super.executeUpdate(sql, params);
			if (i > 0) {
				System.out.println("-------->删除成功。");
				return true;
			}
		} finally {
			super.closeResource();
		}
		return false;
	}

	@Override
	public <T> boolean update(T t) {
		Book b = (Book) t;
		try {
			String sql = "update books set bid = ?,bookname = ?,b_price = ?,image = ?,stock = ? where bid = ?";
			Object[] params = { b.getBid(), b.getName(), b.getPrice(), b.getImage(), b.getStock(), b.getBid() };
			int i = super.executeUpdate(sql, params);
			if (i > 0) {
				System.out.println("-------->更新成功。");
				return true;
			}
		} finally {
			super.closeResource();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getList() {
		List<Book> list = null;
		String sql = "select  bid, bookname, b_price, image, stock from books order by bid asc";
		Object[] params = {};
		rs = super.executeSQL(sql, params);
		try {
			Book obook = new Book();
			while (rs.next()) {
				if (list == null) {
					list = new ArrayList<Book>();
				}
				Book book = obook.clone();
				int bid = rs.getInt("bid");
				String name = rs.getString("bookname");
				double price = rs.getDouble("b_price");
				String image = rs.getString("image");
				int stock = rs.getInt("stock");
				book.setBid(bid);
				book.setName(name);
				book.setPrice(price);
				book.setImage(image);
				book.setStock(stock);
				list.add(book);
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
	public <T> T get(T t) {
		return null;
	}

	@Override
	public <T> int getCount() {
		int totalCount = 0;
		try {
			String sql = "select count(0) from books";
			Object[] params = {};
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

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getPageList(PageSupport pageSupport) {
		List<Book> list = null;
		// 编写分页查询的SQL语句
		// mysql的分页查询语句：select id,title,author,createdate from news_detail order by
		// createdate limit ?,?(其中第1个参数为返回行的偏移量，第2个为显示行的数量，初始行的依稀量为0)
		String sql = "select  bid, bookname, b_price, image, stock from books order by bid asc limit ?,?";
		Object[] params = { pageSupport.getStartRowForMysql(), pageSupport.getPageSize()};
		rs = super.executeSQL(sql, params);
		try {
			Book book = new Book();
			while (rs.next()) {
				if (list == null) {
					list = new ArrayList<Book>();
				}
				Book clone = book.clone();
				int bid = rs.getInt("bid");
				String name = rs.getString("bookname");
				Double price = rs.getDouble("b_price");
				String image = rs.getString("image");
				int stock = rs.getInt("stock");
				// System.out.println(book == clone);
				clone.setBid(bid);
				clone.setName(name);
				clone.setPrice(price);
				clone.setImage(image);
				clone.setStock(stock);
				list.add(clone);
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

}

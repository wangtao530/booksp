package book.dao;

import java.util.List;

import book.util.PageSupport;

public interface IDao {
	public <T> boolean add(T t);
	public <T> boolean delete(T t);
	public <T> boolean update(T t);
	public <T> List<T> getList();
	public <T> T get(T t);
	public <T> int getCount();
	public <T> List<T> getPageList(PageSupport pageSupport);
}
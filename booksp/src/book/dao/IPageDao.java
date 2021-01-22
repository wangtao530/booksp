package book.dao;

import java.util.List;

import book.util.PageSupport;

public interface IPageDao {
	public <T, K> List<T> getPageList(PageSupport pageSupport,K k);
	public <T> int getCount(T t);

}

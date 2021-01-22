package book.service;

import java.util.List;

import book.util.PageSupport;

public interface IServicePage {
	public <T, K> List<T> getPageList(PageSupport pageSupport,K k);
	public <T> int getCount(T t);
}

package book.service.impl;

import java.util.List;

import book.dao.IDao;
import book.dao.IPageDao;
import book.service.IServicePage;
import book.service.IService;
import book.util.PageSupport;

public class ServiceImpl implements IService,IServicePage {
	private IDao dao;
	private IPageDao pageDao;
	public ServiceImpl() {
		super();
	}

	public ServiceImpl(IPageDao pageDao) {
		super();
		this.pageDao = pageDao;
	}

	public ServiceImpl(IDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public <T> boolean add(T t) {
		return this.dao.add(t);
	}

	@Override
	public <T> boolean delete(T t) {
		return this.dao.delete(t);
	}

	@Override
	public <T> boolean update(T t) {
		return this.dao.update(t);
	}

	@Override
	public <T> List<T> getList() {
		return this.dao.getList();
	}

	@Override
	public <T> T get(T t) {
		return this.dao.get(t);
	}

	@Override
	public <T> int getCount() {
		return this.dao.getCount();
	}

	@Override
	public <T> List<T> getPageList(PageSupport pageSupport) {
		return this.dao.getPageList(pageSupport);
	}

	public IDao getDao() {
		return dao;
	}

	public void setDao(IDao dao) {
		this.dao = dao;
	}

	@Override
	public <T,K> List<T> getPageList(PageSupport pageSupport, K k) {
		return this.pageDao.getPageList(pageSupport, k);
	}

	public IPageDao getPageDao() {
		return pageDao;
	}

	public void setPageDao(IPageDao pageDao) {
		this.pageDao = pageDao;
	}

	@Override
	public <T> int getCount(T t) {
		return this.pageDao.getCount(t);
	}

}

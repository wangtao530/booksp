package book.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.dao.impl.BookDaoImpl;
import book.entity.Book;
import book.service.impl.ServiceImpl;
import book.util.PageSupport;

@WebServlet(description = "分页查询图书", urlPatterns = { "/pgQue" })
public class PageQueryServlet extends DataSourceServlet {

	private static final long serialVersionUID = 7215019873333459228L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int records = new ServiceImpl(new BookDaoImpl(super.getConn())).getCount();
		int size = 3;
		String index = req.getParameter("pageIndex");
		//System.out.println("index--------->" + index);
		if (index == null) {
			index = "1";
		}
		int curIdex = Integer.parseInt(index);
		PageSupport curPage = new PageSupport(records, size, curIdex);

		List<Book> list = new ServiceImpl(new BookDaoImpl(super.getConn())).getPageList(curPage);
		req.getSession().setAttribute("list", list);
		req.getSession().setAttribute("curPage", curPage);
		resp.sendRedirect("jsp/index.jsp");
	}

}

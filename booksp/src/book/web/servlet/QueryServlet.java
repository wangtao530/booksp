package book.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.dao.impl.BookDaoImpl;
import book.entity.Book;
import book.service.impl.ServiceImpl;

public class QueryServlet extends DataSourceServlet {

	private static final long serialVersionUID = -8174220844032218039L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<Book> list = new ServiceImpl(new BookDaoImpl(super.getConn())).getList();
		req.getSession().setAttribute("list", list);

		resp.sendRedirect("jsp/index.jsp");
	}

}

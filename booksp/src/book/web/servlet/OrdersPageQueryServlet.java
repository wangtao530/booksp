package book.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.dao.impl.QueryOrderDaoImpl;
import book.entity.UserInfo;
import book.entity.business.QueryOrder;
import book.service.impl.ServiceImpl;
import book.util.PageSupport;

/**
 * Servlet implementation class OrdersPageQueryServlet
 */
@WebServlet(description = "用户以往的订单查询", urlPatterns = { "/OrdersPageQuery" })
public class OrdersPageQueryServlet extends DataSourceServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public OrdersPageQueryServlet() {
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
		// 4、查看以往订单
		UserInfo user = (UserInfo) request.getSession().getAttribute("user");
		String username = user.getUserName();
		QueryOrder qo = new QueryOrder();
		if (username != null && !username.equals("")) {
			qo.setUsername(username);
		} else {
			// null
		}
		int records = new ServiceImpl(new QueryOrderDaoImpl(super.getConn())).getCount(qo);
		int size = 3;
		String index = request.getParameter("pageIndex");
		//System.out.println("index--------->" + index);
		if (index == null) {
			index = "1";
		}
		int curIdex = Integer.parseInt(index);
		PageSupport curPage = new PageSupport(records, size, curIdex);
		List<QueryOrder> list = new ServiceImpl(new QueryOrderDaoImpl(super.getConn())).getPageList(curPage,
				qo.getUsername());
		request.getSession().setAttribute("list", list);
		request.getSession().setAttribute("curPage", curPage);
		response.sendRedirect("jsp/orderlist.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

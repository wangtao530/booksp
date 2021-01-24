package book.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import book.entity.business.BBook;

@WebServlet(description = "删除购物车中图书", urlPatterns = { "/delShoppingCart" })
public class DelBookFromShoppingCartServlet extends HttpServlet {

	private static final long serialVersionUID = 6780130568397832391L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String d_id = request.getParameter("id");
		if (d_id != null && !d_id.equals("")) {
			int id = Integer.parseInt(d_id);
			HttpSession session = request.getSession();
			Object o_b = session.getAttribute("b_list");
			// Object o_i = session.getAttribute("i_list");
			List<BBook> b_list = (ArrayList<BBook>) o_b;
			// List<Item> i_list = (ArrayList<Item>) o_i;
			Iterator<BBook> it = b_list.iterator();
			while (it.hasNext()) {
				BBook b = it.next();
				if (b.getBid() == id) {
					it.remove();
					break;
				}
			}
//			for (BBook b : b_list) {
//				if (b.getBid() == id) {
//					b_list.remove(b);
//					break;
//				}
//			}
//			for (Item i : i_list) {
//				if (i.getBid() == id) {
//					i_list.remove(i);
//					break;
//				}
//			}
			// session.removeAttribute("b_list");
			// session.removeAttribute("i_list");

			// session.setAttribute("b_list", b_list);
			/// session.setAttribute("i_list", i_list);
			//b_list.forEach(System.out::println);
		}
		response.sendRedirect("jsp/shopping.jsp");
	}

}

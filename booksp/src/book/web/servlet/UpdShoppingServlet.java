package book.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import book.entity.business.BBook;

/**
 * Servlet implementation class PayShoppingServlet
 */
@WebServlet(description = "处理购物车支付", urlPatterns = { "/UpdShopping" })
public class UpdShoppingServlet extends DataSourceServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8814335706668358145L;

	public UpdShoppingServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 页面数据与session中数据统一
		HttpSession session = request.getSession();
		Object ob_list = session.getAttribute("b_list");
		@SuppressWarnings("unchecked")
		List<BBook> b_list = (ArrayList<BBook>) ob_list;
		String b_id = request.getParameter("u_b_id");
		String b_nums = request.getParameter("u_b_nums");
		int pb_id = Integer.parseInt(b_id);
		int pb_nums = Integer.parseInt(b_nums);
		for (BBook b : b_list) {
			if (pb_id == b.getBid() && pb_nums != b.getBcount()) {
				b.setBcount(pb_nums);
				break;
			}
		}
		//System.out.println("购物车图书数量更新完毕");
		response.sendRedirect("jsp/shopping.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

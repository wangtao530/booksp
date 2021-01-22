package book.web.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.dao.impl.BookDaoImpl;
import book.dao.impl.ItemDaoImpl;
import book.dao.impl.OrderDaoImpl;
import book.entity.Item;
import book.entity.Order;
import book.entity.UserInfo;
import book.entity.business.BBook;
import book.service.impl.ServiceImpl;

/**
 * Servlet implementation class PayShoppingServlet
 */
@WebServlet(description = "处理购物车支付", urlPatterns = { "/PayShoppingServlet" })
public class PayShoppingServlet extends DataSourceServlet {
	private static final long serialVersionUID = 1L;

	public PayShoppingServlet() {
		super();
	}

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Object ob_list = request.getSession().getAttribute("b_list");
		List<BBook> b_list = (ArrayList<BBook>) ob_list;
		// 生成订单
		// 1、生成Order
		Order order = new Order();
		order.setUserName(((UserInfo) request.getSession().getAttribute("user")).getUserName());
		new ServiceImpl(new OrderDaoImpl(super.getConn())).add(order);
		// 2、生成订单明细
		Item item = new Item();
		for (BBook b : b_list) {
			Item ite;
			try {
				ite = item.clone();
				ite.setOid(order.getOid());
				ite.setBid(b.getBid());
				ite.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				ite.setCount(b.getBcount());
				ite.setPrice(Double.toString(b.getPrice()));
				ite.setTotalPrice(Double.toString(b.getPrice() * b.getBcount()));
				// 3、修改Book库存
				// 保证购物车中图书的整体性，应该在用户选择图书数量时就验证库存
				b.setStock(b.getStock() - b.getBcount());
				new ServiceImpl(new BookDaoImpl(super.getConn())).update(b);
				new ServiceImpl(new ItemDaoImpl(super.getConn())).add(ite);
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
		// 清除购物车
		request.getSession().removeAttribute("b_list");
		//System.out.println("===============================");
		//b_list.forEach(System.out::println);
		// 4、查看以往订单
		response.sendRedirect("jsp/shoppingsuccess.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

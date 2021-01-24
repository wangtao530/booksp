package book.web.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//import java.util.function.Consumer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import book.entity.business.BBook;

@WebServlet(description = "商品添加到购物车", urlPatterns = { "/shopping" })
public class ShoppingServlet extends HttpServlet {

	private static final long serialVersionUID = 6780130568397832391L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String[] ids = request.getParameterValues("b_id");
		String[] names = request.getParameterValues("b_name");
		String[] prices = request.getParameterValues("b_price");
		String[] stocks = request.getParameterValues("b_stock");
		String[] images = request.getParameterValues("b_image");
		if (ids != null) {

			HttpSession session = request.getSession();
			Object o_b = session.getAttribute("b_list");
			List<BBook> b_list = null;
			if (o_b == null) {
				b_list = new ArrayList<BBook>();
				for (int i = 0; i < ids.length; i++) {
					BBook bk = new BBook();
					bk.setBid(Integer.parseInt(ids[i]));
					bk.setName(names[i]);
					bk.setPrice(Double.parseDouble(prices[i]));
					bk.setStock(Integer.parseInt(stocks[i]));
					bk.setImage(images[i]);
					bk.setBcount(1);
					b_list.add(bk);
				}
				session.setAttribute("b_list", b_list);
			} else {
				b_list = (ArrayList<BBook>) o_b;
				for (int i = 0; i < ids.length; i++) {
					BBook bk = new BBook();
					bk.setBid(Integer.parseInt(ids[i]));
					bk.setName(names[i]);
					bk.setPrice(Double.parseDouble(prices[i]));
					bk.setStock(Integer.parseInt(stocks[i]));
					bk.setImage(images[i]);
					bk.setBcount(1);
					boolean exist = false;// 购物车中是否存在该书
					for (BBook b : b_list) {
						if (b.getBid() == bk.getBid()) {
							exist = true;
							b.setBcount(b.getBcount() + 1);
							break;
						}
					}
					if (!exist) {
						b_list.add(bk);
					}

				}
//				session.removeAttribute("b_list");
//				session.setAttribute("b_list", b_list);
			}

			// 遍历集合方式
//			b_list.forEach(new Consumer<BBook>() {
//				@Override
//				public void accept(BBook b) {
//					System.out.println(b);
//				}
//			});
			// 遍历集合方式
			// b_list.forEach(System.out::println);
			// 遍历集合方式
//			Iterator<BBook> it = b_list.iterator();
//			while (it.hasNext()) {
//				BBook b = it.next();
//				System.out.println(b);
//			}
			response.sendRedirect("jsp/shopping.jsp");
		} else {
			request.getRequestDispatcher("/pgQue").forward(request, response);
		}

	}

}

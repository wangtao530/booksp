package book.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.dao.impl.UserInfoDaoImpl;
import book.entity.UserInfo;
import book.service.impl.ServiceImpl;
import book.util.SpecialTokenFilter;

public class LoginServlet extends DataSourceServlet {

	private static final long serialVersionUID = 6780130568397832391L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 用户名防止SQL注入
		String uName = SpecialTokenFilter.StringFilter(request.getParameter("userName"));
		//String pwd = EncryptData.encryptData("SHA-256", request.getParameter("passWord"));
		String pwd = request.getParameter("passWord");
		String y = request.getParameter("yzm");
		String yzm = (String) request.getSession().getAttribute("vc");
		if (yzm.equalsIgnoreCase(y)) {// 验证码验证,字母忽略大小写
			if (uName != null && !uName.equals("")) {
				UserInfo u = new UserInfo();
				u.setUserName(uName);
				u.setPassword(pwd);
				// 登陆验证
				UserInfo ui = new ServiceImpl(new UserInfoDaoImpl(super.getConn())).get(u);
				if (ui != null) {
					//
					Cookie cookie = new Cookie("username", ui.getUserName());
					response.addCookie(cookie);
					request.getSession().setAttribute("user", u);
					System.out.println("---在线人数---" + book.util.Constants.ONLINE_USER_COUNT);
					response.sendRedirect(request.getContextPath() + "/pgQue");
				} else {
					request.setAttribute("mess", "用户名密码错误，请重新输入");
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
				ui = null;
				u = null;
			} else {
				request.setAttribute("mess", "用户名密码错误，请重新输入");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		} else {
			request.setAttribute("mess", "验证码错误，请重新输入");
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}

	}

}

package book.web.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.dao.impl.UserInfoDaoImpl;
import book.entity.UserInfo;
import book.service.impl.ServiceImpl;
import book.util.EncryptData;
import book.util.SpecialTokenFilter;

@WebServlet(description = "用户登陆", urlPatterns = { "/login" })
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

		String pwd = null;
		try {
			pwd = EncryptData.encryptData("SHA-256", request.getParameter("passWord"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// String pwd = request.getParameter("passWord");
		String y = request.getParameter("yzm");
		String yzm = (String) request.getSession().getAttribute("vc");
		String mess = null;
		if (!yzm.equalsIgnoreCase(y)) {// 验证码验证,字母忽略大小写
			mess = "验证码错误，请重新输入";
			response.sendRedirect("index.jsp?mess=" + URLEncoder.encode(mess, "utf-8"));
			return;
		}
		if (uName == null || uName.equals("")) {
			mess = "用户名不能为空，请重新输入";
			response.sendRedirect("index.jsp?mess=" + URLEncoder.encode(mess, "utf-8"));
			return;
		}
		UserInfo u = new UserInfo();
		u.setUserName(uName);
		u.setPassword(pwd);
		// 登陆验证
		UserInfo ui = new ServiceImpl(new UserInfoDaoImpl(super.getConn())).get(u);
		if (ui == null) {
			mess = "用户名密码错误，请重新输入";
			response.sendRedirect("index.jsp?mess=" + URLEncoder.encode(mess, "utf-8"));
			return;
		}
		//
		Cookie cookie = new Cookie("username", ui.getUserName());
		response.addCookie(cookie);
		request.getSession().setAttribute("user", u);
		System.out.println("---在线人数---" + book.util.Constants.ONLINE_USER_COUNT);
		response.sendRedirect(request.getContextPath() + "/pgQue");

		// ui = null;
		// u = null;

	}

}

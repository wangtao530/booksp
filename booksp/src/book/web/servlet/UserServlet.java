package book.web.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

@WebServlet(description = "用户注册、登陆、注销", urlPatterns = { "*.do" })
public class UserServlet extends DataSourceServlet {

	private static final long serialVersionUID = 6780130568397832391L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		try {
			Method method = UserServlet.class.getDeclaredMethod(
					uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf(".")), HttpServletRequest.class,
					HttpServletResponse.class);
			method.setAccessible(true);
			method.invoke(this, request, response);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private void regr(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String uName = request.getParameter("userName");
		UserInfo u = new UserInfo();
		u.setUserName(uName);
		// 根据用户名从db中查询用户是否存在，不存在则允许注册
		UserInfo dbu = new ServiceImpl(new UserInfoDaoImpl(super.getConn())).get(u);
		if (dbu != null) {
			request.setAttribute("mess", "用户名已存在");
			request.getRequestDispatcher("jsp/register.jsp").forward(request, response);
			return;
		}
		// 注册验证
		// 加密
		try {
			String pwd = EncryptData.encryptData("SHA-256", request.getParameter("passWord"));
			System.out.println(pwd.length());
			/** 网络安全，email输入验证 */
			String email = request.getParameter("email");
			String mes = null;
			boolean validate = false;
			if (null == email) {
				mes = "邮箱不能为空";
				request.getSession().setAttribute("mess", mes);
				return;
			}
			String regex = "([a-zA-Z0-9]-*){1,}[0-9]{0,}@(([a-zA-Z0-9]-*){1,}\\.){1,3}[a-zA-Z\\-]{1,}";
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(email);
			validate = m.matches();
			if (!validate) {
				mes = "邮箱格式不正确";
				request.getSession().setAttribute("mess", mes);
				return;
			}
			System.out.println("邮箱不为空且格式正确：" + email);
//			mes = "邮箱不为空且格式正确：" + email;
//			request.getSession().setAttribute("mess", mes);
			u.setPassword(pwd);
			u.setEmail(email);
			// 注册成功，添加用户
			new ServiceImpl(new UserInfoDaoImpl(super.getConn())).add(u);
			response.sendRedirect("jsp/register_success.html");
			dbu = null;
			u = null;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unused")
	private void login(HttpServletRequest request, HttpServletResponse response)
			throws UnsupportedEncodingException, IOException {
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

	@SuppressWarnings("unused")
	private void logOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().invalidate();
		response.sendRedirect("index.jsp");
	}

}

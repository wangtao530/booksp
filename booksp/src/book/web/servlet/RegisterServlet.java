package book.web.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.dao.impl.UserInfoDaoImpl;
import book.entity.UserInfo;
import book.service.impl.ServiceImpl;
import book.util.EncryptData;

@WebServlet(description = "用户注册", urlPatterns = { "/regr" })
public class RegisterServlet extends DataSourceServlet {

	private static final long serialVersionUID = 6780130568397832391L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uName = req.getParameter("userName");
		UserInfo u = new UserInfo();
		u.setUserName(uName);
		// 根据用户名从db中查询用户是否存在，不存在则允许注册
		UserInfo dbu = new ServiceImpl(new UserInfoDaoImpl(super.getConn())).get(u);
		if (dbu != null) {
			req.setAttribute("mess", "用户名已存在");
			req.getRequestDispatcher("jsp/register.jsp").forward(req, resp);
			return;
		}
		// 注册验证
		// 加密
		try {
			String pwd = EncryptData.encryptData("SHA-256", req.getParameter("passWord"));
			System.out.println(pwd.length());
			/** 网络安全，email输入验证 */
			String email = req.getParameter("email");
			String mes = null;
			boolean validate = false;
			if (null == email) {
				mes = "邮箱不能为空";
				req.getSession().setAttribute("mess", mes);
				return;
			}
			String regex = "([a-zA-Z0-9]-*){1,}[0-9]{0,}@(([a-zA-Z0-9]-*){1,}\\.){1,3}[a-zA-Z\\-]{1,}";
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(email);
			validate = m.matches();
			if (!validate) {
				mes = "邮箱格式不正确";
				req.getSession().setAttribute("mess", mes);
				return;
			}
			System.out.println("邮箱不为空且格式正确：" + email);
//			mes = "邮箱不为空且格式正确：" + email;
//			req.getSession().setAttribute("mess", mes);
			u.setPassword(pwd);
			u.setEmail(email);
			// 注册成功，添加用户
			new ServiceImpl(new UserInfoDaoImpl(super.getConn())).add(u);
			resp.sendRedirect("jsp/register_success.html");
			dbu = null;
			u = null;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

}

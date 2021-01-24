package book.web.servlet;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import book.util.VerifyCode;

/**
 * Servlet implementation class ValidateCodeServlet
 */
@WebServlet(description = "生成验证码", urlPatterns = { "/ValidateCode" })
public class ValidateCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidateCodeServlet() {
        super();
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");
		VerifyCode v = new VerifyCode();
		BufferedImage i = v.getImage();
		String c = v.getText();
		request.getSession().setAttribute("vc", c);
		VerifyCode.output(i, response.getOutputStream());
	}

}

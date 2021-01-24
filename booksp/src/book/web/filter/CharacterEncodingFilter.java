package book.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter(description = "设置请求响应编码", urlPatterns = { "/*" })
public class CharacterEncodingFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Filter.super.init(filterConfig);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 设置请求时的编码方式
		request.setCharacterEncoding("UTF-8");
		// 调用web资源，也可以调用其他过滤器
		chain.doFilter(request, response);
		// 设置响应时的编码方式
		response.setCharacterEncoding("UTF-8");
		System.out.println("设置请求响应时的编码方式");
	}

	@Override
	public void destroy() {
		Filter.super.destroy();
	}

}

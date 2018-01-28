package servletdemo;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class LogFilter
 */
public class LogFilter implements Filter {

	// 用于访问Filter的配置信息
	private FilterConfig config;
	
    /**
     * Default constructor. 
     * 默认构造器
     */
    public LogFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 * 实现销毁方法
	 */
	public void destroy() {
		this.config = null;
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		// 获取ServletContext 对象
		ServletContext context = this.config.getServletContext();
		long before = System.currentTimeMillis();
		System.out.println("开始过滤...");
		// 获得请求
		HttpServletRequest hrequest = (HttpServletRequest) request;
		// 记录日志
		context.log("拦截器拦截的请求地址：" + hrequest.getServletPath());
		
		chain.doFilter(request, response);
		
		long after = System.currentTimeMillis();
		// 记录日志
		context.log("请求被定位到：" + ((HttpServletRequest)request).getRequestURI() + 
				"所消耗的时间为：" + (after - before));
	}

	/**
	 * 实现初始化方法
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.config = fConfig;
	}

}

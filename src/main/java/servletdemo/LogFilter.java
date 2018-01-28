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

	// ���ڷ���Filter��������Ϣ
	private FilterConfig config;
	
    /**
     * Default constructor. 
     * Ĭ�Ϲ�����
     */
    public LogFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 * ʵ�����ٷ���
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
		// ��ȡServletContext ����
		ServletContext context = this.config.getServletContext();
		long before = System.currentTimeMillis();
		System.out.println("��ʼ����...");
		// �������
		HttpServletRequest hrequest = (HttpServletRequest) request;
		// ��¼��־
		context.log("���������ص������ַ��" + hrequest.getServletPath());
		
		chain.doFilter(request, response);
		
		long after = System.currentTimeMillis();
		// ��¼��־
		context.log("���󱻶�λ����" + ((HttpServletRequest)request).getRequestURI() + 
				"�����ĵ�ʱ��Ϊ��" + (after - before));
	}

	/**
	 * ʵ�ֳ�ʼ������
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.config = fConfig;
	}

}

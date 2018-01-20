package servlet.demo;

import java.io.IOException;
import java.io.Writer;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletDemo
 */
public class ServletDemo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDemo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取用户名和密码
		System.out.println(request.getParameter("username"));
		System.out.println(request.getParameter("password"));
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String checkin = request.getParameter("checkin");
		
		response.setContentType("text/html;charset=UTF-8");
//		Writer webWriter = response.getWriter();
//		webWriter.write("<html>");
//		webWriter.write("<body>");
//		webWriter.write("<p>"+username+"</p>");
//		webWriter.write("<p>"+password+"</p>");
//		webWriter.write("<p>"+checkin+"</p>");
//		webWriter.write("</p>");
//		webWriter.write("</body>");
//		webWriter.write("</html>");
		
		Cookie ucookie = new Cookie("username", URLEncoder.encode(username,"UTF-8"));
		Cookie pcookie = new Cookie("password", password);
		
		response.addCookie(ucookie);
		response.addCookie(pcookie);
		
		response.sendRedirect("readCookie.jsp");
	}

}

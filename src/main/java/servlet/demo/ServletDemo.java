package servlet.demo;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
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
		// TODO Auto-generated method stub
		System.out.println(request.getParameter("username"));
		System.out.println(request.getParameter("password"));
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		response.setContentType("text/html;charset=UTF-8");
		Writer webWriter = response.getWriter();
		webWriter.write("<html>");
		webWriter.write("<body>");
		webWriter.write("<p>"+username+"</p>");
		webWriter.write("<p>"+password+"</p>");
		webWriter.write("</p>");
		webWriter.write("</body>");
		webWriter.write("</html>");
	}

}

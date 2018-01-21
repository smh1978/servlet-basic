package servletdemo;

import java.io.IOException;
import java.io.PrintStream;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ParamServlet
 */
public class ParamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println(request.getParameter("username"));
		System.out.println(request.getParameter("pass"));
		
		ServletConfig config = getServletConfig();
		String driver = config.getInitParameter("driver");
		String url = config.getInitParameter("url");
		String user = config.getInitParameter("user");
		String pass = config.getInitParameter("pass");
		
		try {
			Class.forName(driver);
			Connection conn =DriverManager.getConnection(url, user, pass);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select id, name from t_user");
			
			response.setContentType("text/html; charSet=utf-8");
			PrintStream out = new PrintStream(response.getOutputStream());
			out.println("<html><head><title>Servlet Init Param Test</title></head>");
			out.println("<body>");
			out.println("<table>");
			
			while (rs.next()) {
				out.println("<tr><td>" + rs.getString(1) + "</td><td>" + rs.getString(2) + "</td></tr>");
				System.out.println(rs.getString(1) + rs.getString(2));
			}
			
			out.println("</table>");
			out.println("</body>");
			out.println("</html>");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
//		String username = request.getParameter("username");
//		String pass1 = request.getParameter("pass");
//		
//		response.setContentType("text/html;charset=UTF-8");
//		Writer webWriter = response.getWriter();
//		webWriter.write("<html>");
//		webWriter.write("<body>");
//		webWriter.write("<p>"+username+"</p>");
//		webWriter.write("<p>"+pass1+"</p>");
//		webWriter.write("</p>");
//		webWriter.write("</body>");
//		webWriter.write("</html>");
	}

}

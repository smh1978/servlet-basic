package servletdemo;

import java.io.IOException;
import java.io.PrintStream;
import java.io.Writer;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ParamServlet
 */
public class ParamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println(request.getParameter("username"));
		System.out.println(request.getParameter("pass"));
		
//		ServletConfig config = getServletConfig();
//		String driver = config.getInitParameter("driver");
//		String url = config.getInitParameter("url");
//		String user = config.getInitParameter("user");
//		String pass = config.getInitParameter("pass");
		
//		try {
//			Class.forName(driver);
//			Connection conn =DriverManager.getConnection(url, user, pass);
//			Statement stmt = conn.createStatement();
//			ResultSet rs = stmt.executeQuery("select id, name from t_user");
//			
//			response.setContentType("text/html; charSet=utf-8");
//			PrintStream out = new PrintStream(response.getOutputStream());
//			out.println("<html><head><title>Servlet Init Param Test</title></head>");
//			out.println("<body>");
//			out.println("<table>");
//			
//			while (rs.next()) {
//				out.println("<tr><td>" + rs.getString(1) + "</td><td>" + rs.getString(2) + "</td></tr>");
//				System.out.println(rs.getString(1) + rs.getString(2));
//			}
//			
//			out.println("</table>");
//			out.println("</body>");
//			out.println("</html>");
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
		
		
//		String username = URLEncoder.encode(request.getParameter("username"),"UTF-8");
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
		
		String errMsg = "";
		RequestDispatcher rd;
		String username = request.getParameter("username");
		String pass = request.getParameter("pass");
		try {
			System.out.println("is here");
			BaseDao bdo = new BaseDao ("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8",
					"root", "001007");
			System.out.println(bdo.getPass());
			System.out.println(username);
			
		ResultSet rs = bdo.query("select name, pass from t_user where name = ?", username);
			
//			ResultSet rs = bdo.query("select name, pass from t_user where id = ?", 6);

			if (rs.next()) {
				System.out.println(rs.getString("pass")+"--------------");
				System.out.println(pass+"<----------");
				if (rs.getString("pass").equals(pass)) {
					System.out.println("into if");
					HttpSession session = request.getSession();
					session.setAttribute("name", username);
					rd = request.getRequestDispatcher("/welcome.jsp");
					rd.forward(request, response);
				} else {
					errMsg = "密码与用户名不符";
				}
			} else {
				errMsg = "用户不存在";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (errMsg != null && !errMsg.equals("")) {
			System.out.println("进入if文");
			rd = request.getRequestDispatcher("login.jsp");
			request.setAttribute("err", errMsg);
			rd.forward(request, response);
		}
	}

}
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
		String errMsg = "";
		RequestDispatcher rd;
		String username = request.getParameter("username");
		String pass = request.getParameter("pass");
		try {
			System.out.println("is here");
//			BaseDao bdo = new BaseDao ("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/test",
//					"root", "001007");
			BaseDao bdo = new BaseDao ("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=UTF-8",
					"root", "001007");
			ResultSet rs = bdo.query("select name, pass from t_user where name = ?", username);

			if (rs.next()) {
				if (rs.getString("pass").equals(pass)) {
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
			rd = request.getRequestDispatcher("login.jsp");
			request.setAttribute("err", errMsg);
			rd.forward(request, response);
		}
	}
}
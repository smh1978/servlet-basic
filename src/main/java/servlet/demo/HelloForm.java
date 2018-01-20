package servlet.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HelloForm
 */
public class HelloForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloForm() {
        super();
        // TODO Auto-generated constructor stub
    }
 
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String title = "•Ì©`•…•πçuëÈ”õ";
		
		String name = new String(request.getParameter("name"));
		String docType = "<!DOCTYPE html> \n";
		
		out.println(docType + 
				"<html>\n" +
				"<heal><title>" + title + "</title></heal> \n" +
				"<body style=\"background-color:#ccbbaa\"> \n" +
				"<h1 align=\"center\">" + title + "</h1> \n" +
				"<ul> \n" +
				"  <li><b>Õ¯’æ√˚</b>: " +
				name + "\n" +
				"  <li><b>Õ¯÷∑</b>: " +
				request.getParameter("url") + "\n" +
				"</ul> \n" +
				"<img src=\"rodosu.jpg\" />" +
				"</body> \n" +
				"</html>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

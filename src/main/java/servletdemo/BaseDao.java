package servletdemo;

import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDao {
	private Connection conn;
	private String driver;
	private String url;
	private String username;
	private String pass;
	
	public BaseDao () {
		
	}
	
	public BaseDao (String driver, String url, String username, String pass) {
		this.driver = driver;
		this.url = url;
		this.username = username;
		this.pass = pass;
	}
	
	public Connection getConnection () throws Exception {
		if (conn == null) {
			Class.forName(this.driver);
			conn = DriverManager.getConnection(url, username, pass);
		}		
		return conn; 
	}
	
	public boolean insert (String sql, Object...args) throws SQLException, Exception {
		PreparedStatement pstmt = getConnection().prepareStatement(sql);
		for (int i = 0; i < args.length; i++) {
			pstmt.setObject(i + 1, args[i]);
		}
		if (pstmt.executeUpdate() != 0) {
			return false;
		}
		
		return true;
	}
	
	public ResultSet query (String sql, Object...args) throws SQLException, Exception {
		System.out.println("sql:"+sql);
		System.out.println("args.length:"+args.length);
		
		PreparedStatement pstmt = getConnection().prepareStatement(sql);
		for (int i = 0; i < args.length; i++) {
			String str = args[i].toString();
			System.out.println("str:"+str);
			System.out.println("---------------------------------->"+URLEncoder.encode(str,"UTF-8"));
			
			
//			pstmt.setObject(i + 1, URLEncoder.encode(str,"UTF-8"));	
			
			
			pstmt.setObject(i + 1, str);	
			
			
			
			//pstmt.setObject(i + 1, args[i]);
		}
		System.out.println(pstmt.toString());
		return pstmt.executeQuery();
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
	
}

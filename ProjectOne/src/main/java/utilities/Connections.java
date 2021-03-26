package utilities;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Connections {
	private static Connections c = null;
	private static Properties prop;
	private String url;
	private String password;
	private String username;
	private Connection conn;
	
	private Connections() {
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream is = classLoader.getResourceAsStream("database.properties");
		prop = new Properties();
		
		try {
			prop.load(is);
			this.url = (String) prop.getProperty("url");
			this.username = (String) prop.getProperty("user");
			this.password = (String) prop.getProperty("pass");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static synchronized Connections getConnections() {
		if(c == null)
			c = new Connections();
		return c;
	}
	
	public Connection getConnection() {
		try {
			Class.forName("org.postgresql.Driver");
			conn = DriverManager.getConnection(this.url, this.username, this.password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}

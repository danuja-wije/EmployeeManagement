package syzcogen.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionUtil {

	private static final String USERNAME = "root";
	private static final String URL = "jdbc:mysql://localhost:3306/syzcogen?autoReconnect=true&useSSL=false";
	private static final String PASSWORD = "password";
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static Connection connection = null;

	public static Connection createConnection() throws SQLException {

		if (connection != null && !connection.isClosed())
			return connection;

		else {
			try {
				// load the driver
				Class.forName(DRIVER);

				// getConnection

				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

			} catch (Exception e) {
				e.printStackTrace();
			}
			// return connection

			return connection;

		}

	}

}

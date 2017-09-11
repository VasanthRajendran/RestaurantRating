package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	private Connection connect = null;
	public Connection connect()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/RestaurentRating?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");
			return connect;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return connect;
	}
}

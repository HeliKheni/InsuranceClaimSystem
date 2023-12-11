package dbo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dbo.DbConfig;

public class DbConnection {
	
    public static Connection getConnection() throws SQLException {
    	try {
			Class.forName(DbConfig.driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return DriverManager.getConnection(DbConfig.url, DbConfig.username, DbConfig.password);
    }
}


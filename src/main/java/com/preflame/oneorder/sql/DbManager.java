package com.preflame.oneorder.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbManager {
    // private static String url = "jdbc:mysql://IPアドレス(不変的なやつ)/U22Test";
    private static String url = "jdbc:mysql://localhost/OneOrder";
    private static String username = "U22User";
    private static String password = "pass";
    
    private static DbManager self;

    private DbManager() {
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
    	}
    }
    
    public static DbManager getInstance() {
    	if(self == null) {
    		self = new DbManager();
    	}
    	return self;
    }
    
    public Connection getConnection() throws SQLException {
    	return DriverManager.getConnection(url, username, password);
    }

}

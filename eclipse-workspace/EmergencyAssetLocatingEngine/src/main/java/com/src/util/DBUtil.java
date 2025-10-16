package com.src.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private static final String URL = "jdbc:mysql://localhost:3306/emergencylocater";
    private static final String USER = "root";  
    private static final String PASS = "Macbookm3"; 

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Thin driver
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection(URL, USER, PASS);
        System.out.println("DataBase Connectted Successfully");
        return con;
    }
}

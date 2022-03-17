package jm.task.core.jdbc.util;


import java.sql.*;


public class Util {

    private static final String url = "jdbc:mysql://localhost:3306";
    public static final String userName = "root";
    public static final String password = "";

    private static Connection connection;

    public Util() {
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection( url, userName ,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
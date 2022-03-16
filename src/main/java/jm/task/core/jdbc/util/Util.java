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
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE schema if not exists UsersZadaniye");  //создаю свою, чтобы не мусорить
            statement.execute("USE UsersZadaniye");
            statement.execute("set autocommit = 0");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // реализуйте настройку соеденения с БД

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP schema if exists UsersZadaniye");  // убираю за собой
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
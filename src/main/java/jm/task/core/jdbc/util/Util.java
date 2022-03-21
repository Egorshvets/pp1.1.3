package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import org.hibernate.service.ServiceRegistry;
import java.sql.*;


public class Util {

    private static final String url = "jdbc:mysql://localhost:3306/test1";
    public static final String userName = "root";
    public static final String password = "";

    private static Connection connection;

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();

            configuration.setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            configuration.setProperty(Environment.URL, url);
            configuration.setProperty(Environment.USER, userName);
            configuration.setProperty(Environment.PASS, password);
            configuration.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
            configuration.setProperty(Environment.FORMAT_SQL, "true");
            configuration.setProperty(Environment.SHOW_SQL, "true");

            configuration.addAnnotatedClass(User.class);

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }

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
        sessionFactory.close();
    }
}


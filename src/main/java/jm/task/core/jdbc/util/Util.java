package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // configuration using JDBC
    private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/my_db?useSSL=false&serverTimezone=UTC";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "volkata";

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DB_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Ошибка соединения");
        }
        return connection;
    }

    // configuration using Hibernate
    private static SessionFactory buildSessionFactory() {
        try {
            Properties prop = new Properties();
            prop.put(Environment.DRIVER, DB_DRIVER);
            prop.put(Environment.URL, DB_URL);
            prop.put(Environment.USER, DB_USERNAME);
            prop.put(Environment.PASS, DB_PASSWORD);
            return new Configuration()
                    .setProperties(prop)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("Ошибка соединения");
        }
        return null;
    }
}



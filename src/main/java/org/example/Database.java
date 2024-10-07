package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Database instance;
    private Connection connection;
    private Database() {
        try {
            String url = "jdbc:mysql://localhost:3306/your_database_name";
            String user = "your_username";
            String password = "your_password";

            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Не вдалося підключитися до БД: " + e.getMessage());
        }
    }
    public static Database getInstance() {
        if (instance == null) {
            synchronized (Database.class) {
                if (instance == null) {
                    instance = new Database();
                }
            }
        }
        return instance;
    }
    public Connection getConnection() {
        return connection;
    }
}

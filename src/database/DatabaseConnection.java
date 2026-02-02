package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // ⚠️ ИЗМЕНИ на "hospital_db" (так называется твоя база на скриншоте)
    private static final String URL = "jdbc:postgresql://localhost:5432/hospital_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "12345678"; // твой пароль

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("SUCCESS! Connected to: " + URL);
            return connection;
        } catch (SQLException e) {
            System.out.println("CONNECTION FAILED!");
            System.out.println("=== DETAILS ===");
            System.out.println("URL: " + URL);
            System.out.println("User: " + USER);
            System.out.println("Password: " + PASSWORD);
            System.out.println("Error: " + e.getMessage());
            System.out.println("================");
            return null;
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("🔒 Connection closed");
            } catch (SQLException e) {
                System.out.println("⚠️ Error closing connection");
            }
        }
    }
}
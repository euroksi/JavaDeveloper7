package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitService {

    public static void main(String[] args) {
        String sqlFilePath = "sql/init_db.sql";

        try (Connection conn = Database.getInstance().getConnection()) {
            if (conn != null) {
                executeSqlFile(conn, sqlFilePath);
                System.out.println("База даних успішно проініціалізована!");
            } else {
                System.out.println("Не вдалося отримати з'єднання з базою даних.");
            }
        } catch (SQLException e) {
            System.out.println("Помилка під час виконання SQL запитів: " + e.getMessage());
        }
    }

    private static void executeSqlFile(Connection conn, String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath));
             Statement stmt = conn.createStatement()) {

            StringBuilder sqlQuery = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty() || line.startsWith("--")) {
                    continue;
                }
                sqlQuery.append(line);
                if (line.endsWith(";")) {
                    stmt.executeUpdate(sqlQuery.toString());
                    sqlQuery.setLength(0);
                }
            }
        } catch (IOException e) {
            System.out.println("Помилка при читанні SQL файлу: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Помилка при виконанні запиту: " + e.getMessage());
        }
    }
}

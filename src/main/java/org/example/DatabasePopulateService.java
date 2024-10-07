package org.example;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabasePopulateService {

    public static void main(String[] args) {
        String sqlFilePath = "sql/populate_db.sql";
        try (Connection con = Database.getInstance().getConnection()) {
            if (con != null) {
                executeSqlFile(con, sqlFilePath);
                System.out.println("База даних успішно наповнена!");
            } else {
                System.out.println("Не вдалося отримати з'єднання з базою даних.");
            }
        } catch (SQLException e) {
            System.out.println("Помилка під час виконання SQL запитів: " + e.getMessage());
        }
    }

    private static void executeSqlFile(Connection conn, String filePath) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
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

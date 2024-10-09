package org.example;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseQueryService {

    public List<Map<String, Object>> executeSelectQuery(String sqlFilePath) {
        List<Map<String, Object>> result = new ArrayList<>();

        try (Connection conn = Database.getInstance().getConnection();
             Statement stmt = conn.createStatement()) {

            String query = readSqlFile(sqlFilePath);
            ResultSet rs = stmt.executeQuery(query);

            int columnCount = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = rs.getMetaData().getColumnName(i);
                    row.put(columnName, rs.getObject(i));  // Зберігаємо значення будь-якого типу
                }
                result.add(row);
            }
        } catch (SQLException e) {
            System.out.println("Помилка під час виконання запиту: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Помилка при читанні SQL файлу: " + e.getMessage());
        }

        return result;
    }

    private String readSqlFile(String filePath) throws IOException {
        StringBuilder sqlQuery = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                sqlQuery.append(line).append(" ");
            }
        }

        return sqlQuery.toString().trim();
    }

    public List<Map<String, Object>> executeSelectQueryFromString(String query) {
        List<Map<String, Object>> result = new ArrayList<>();

        try (Connection conn = Database.getInstance().getConnection();
             Statement stmt = conn.createStatement()) {

            ResultSet rs = stmt.executeQuery(query);
            int columnCount = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = rs.getMetaData().getColumnName(i);
                    row.put(columnName, rs.getObject(i));  // Зберігаємо значення будь-якого типу
                }
                result.add(row);
            }
        } catch (SQLException e) {
            System.out.println("Помилка під час виконання запиту: " + e.getMessage());
        }

        return result;
    }
    public List<MaxProjectCountClient> findMaxProjectsClient() {
        String sqlFilePath = "sql/find_max_projects_client.sql";  // Шлях до SQL файлу
        List<MaxProjectCountClient> result = new ArrayList<>();

        try (Connection conn = Database.getInstance().getConnection();
             Statement stmt = conn.createStatement()) {

            String query = readSqlFile(sqlFilePath);
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String name = rs.getString("name");
                int projectCount = rs.getInt("project_count");
                result.add(new MaxProjectCountClient(name, projectCount));
            }
        } catch (SQLException e) {
            System.out.println("Помилка під час виконання запиту: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Помилка при читанні SQL файлу: " + e.getMessage());
        }

        return result;
    }

}

package org.example;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseQueryService {

    public List<MaxProjectCountClient> findMaxProjectsClient() {
        String sqlFilePath = "sql/find_max_projects_client.sql";
        List<MaxProjectCountClient> result = new ArrayList<>();

        try (Connection conn = Database.getInstance().getConnection()) {

            String query = readSqlFile(sqlFilePath);
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    String name = rs.getString("name");
                    int projectCount = rs.getInt("project_count");
                    result.add(new MaxProjectCountClient(name, projectCount));
                }
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
}

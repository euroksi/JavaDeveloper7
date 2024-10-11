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
import java.util.List;

public class DatabaseQueryService {

    public List<MaxProjectCountClient> findMaxProjectsClient() {
        String sqlFilePath = "sql/find_max_projects_client.sql";
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
        } catch (SQLException | IOException e) {
            System.out.println("Помилка під час виконання запиту: " + e.getMessage());
        }

        return result;
    }

    public List<EmployeeInfo> findEmployeeInfo() {
        String sqlFilePath = "sql/find_employee_info.sql";
        List<EmployeeInfo> result = new ArrayList<>();

        try (Connection conn = Database.getInstance().getConnection();
             Statement stmt = conn.createStatement()) {

            String query = readSqlFile(sqlFilePath);
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String name = rs.getString("name");
                String position = rs.getString("position");
                int salary = rs.getInt("salary");
                result.add(new EmployeeInfo(name, position, salary));
            }
        } catch (SQLException | IOException e) {
            System.out.println("Помилка під час виконання запиту: " + e.getMessage());
        }

        return result;
    }

    public List<ProjectInfo> findProjectInfo() {
        String sqlFilePath = "sql/find_project_info.sql";
        List<ProjectInfo> result = new ArrayList<>();

        try (Connection conn = Database.getInstance().getConnection();
             Statement stmt = conn.createStatement()) {

            String query = readSqlFile(sqlFilePath);
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                String projectName = rs.getString("project_name");
                String clientName = rs.getString("client_name");
                int budget = rs.getInt("budget");
                result.add(new ProjectInfo(projectName, clientName, budget));
            }
        } catch (SQLException | IOException e) {
            System.out.println("Помилка під час виконання запиту: " + e.getMessage());
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

    public static class MaxProjectCountClient {
        private String name;
        private int projectCount;

        public MaxProjectCountClient(String name, int projectCount) {
            this.name = name;
            this.projectCount = projectCount;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getProjectCount() {
            return projectCount;
        }

        public void setProjectCount(int projectCount) {
            this.projectCount = projectCount;
        }
    }

    public static class EmployeeInfo {
        private String name;
        private String position;
        private int salary;

        public EmployeeInfo(String name, String position, int salary) {
            this.name = name;
            this.position = position;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public int getSalary() {
            return salary;
        }

        public void setSalary(int salary) {
            this.salary = salary;
        }
    }

    public static class ProjectInfo {
        private String projectName;
        private String clientName;
        private int budget;

        public ProjectInfo(String projectName, String clientName, int budget) {
            this.projectName = projectName;
            this.clientName = clientName;
            this.budget = budget;
        }

        public String getProjectName() {
            return projectName;
        }

        public void setProjectName(String projectName) {
            this.projectName = projectName;
        }

        public String getClientName() {
            return clientName;
        }

        public void setClientName(String clientName) {
            this.clientName = clientName;
        }

        public int getBudget() {
            return budget;
        }

        public void setBudget(int budget) {
            this.budget = budget;
        }
    }
}

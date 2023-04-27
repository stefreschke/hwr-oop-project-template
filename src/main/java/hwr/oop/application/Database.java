package hwr.oop.application;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;

public class Database {

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(System.getenv("DATABASE_FILE_URL"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
        public static void main(String[] args){
            initTables();
        }

        public static void initTables() {
            String url = "jdbc:sqlite:/path/to/database.db"; // Replace with the path to your SQLite database file
            String sqlFile = "/path/to/sql/file.sql"; // Replace with the path to your SQL file

            try (Connection conn = DriverManager.getConnection(System.getenv("DATABASE_FILE_URL"));
                 Statement stmt = conn.createStatement()) {
                String sql = new String(Files.readAllBytes(Paths.get(System.getenv("SQL_FILE_URL"))));
                stmt.executeUpdate(sql);
            } catch (SQLException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
}
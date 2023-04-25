package hwr.oop.application;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
public class Database {
        /**
         * Connect to a sample database
         *
         * @param fileName the database file name
         */
        public static void createNewDatabase(String fileName) {

            String url = "jdbc:sqlite:src/main/java/hwr/oop/database/" + fileName;

            try (Connection conn = DriverManager.getConnection(url)) {
                if (conn != null) {
                    DatabaseMetaData meta = conn.getMetaData();
                    System.out.println("The driver name is " + meta.getDriverName());
                    System.out.println("A new database has been created.");
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        /**
         * @param args the command line arguments
         */
        public static void main(String[] args) {
            createNewDatabase("to_do_list.db");
        }
    }

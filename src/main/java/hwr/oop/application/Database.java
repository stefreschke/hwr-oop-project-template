package hwr.oop.application;
import java.sql.*;

public class Database {

    //opens connection to database
    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(System.getenv("DATABASE_FILE_URL"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
        /**
         * Connect to database
         *
         */
        //createNewDatabase
        public static void createNewDatabase() {

            //url = "jdbc:sqlite:src/main/java/hwr/oop/database/" + fileName;

            try (Connection conn = DriverManager.getConnection(System.getenv("DATABASE_FILE_URL"))) {
                if (conn != null) {
                    DatabaseMetaData meta = conn.getMetaData();
                    System.out.println("The driver name is " + meta.getDriverName());
                    System.out.println("A new database has been created.");
                }

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        public void insert(String tableName, String tableCol){
            String sql = "INSERT INTO " +tableName +;
            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)){

            }
        }
    public static void createTableTags() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS tags (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	discription text \n"
                + ");";

        try (Connection conn = DriverManager.getConnection(System.getenv("DATABASE_FILE_URL"));
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createTableProjects() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS projects (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	name text NOT NULL,\n"
                + "	discription text, \n"
                + " dateTimeCreated , \n"
                + " dateTimeDeadLine text \n"
                + ");";

        try (Connection conn = DriverManager.getConnection(System.getenv("DATABASE_FILE_URL"));
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void createTableTasks() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS tasks (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	title text NOT NULL,\n"
                + "	discription text, \n"
                + " status text, \n"
                + " dateTimeCreated text, \n"
                + " dateTimeDone text, \n"
                + " dateTimePlannedStart text, \n"
                + " dateTimeDeadLine text \n"
                + ");";

        try (Connection conn = DriverManager.getConnection(System.getenv("DATABASE_FILE_URL"));
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        }
    public static void main (String []args){
            createNewDatabase();
            createTableTasks();
            createTableTags();
            createTableProjects();

    }
}


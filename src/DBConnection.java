import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;

    private static final String URL = "jdbc:mysql://localhost:3306/assignment";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    private DBConnection() {}

    public static Connection getConnection() {
        if (connection == null) {
            synchronized (DBConnection.class) {
                if (connection == null) {
                    try {
                        connection = DriverManager.getConnection(URL, USER, PASSWORD);
                    } catch (SQLException e) {
                        System.err.println("Database connection failed.");
                        e.printStackTrace();
                    }
                }
            }
        }
        return connection;
    }
}

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() throws Exception {

        String url = "jdbc:oracle:thin:@localhost:1521/FREE";
        String username = "system";
        String password = "25BDS0066";

        return DriverManager.getConnection(url, username, password);
    }
}
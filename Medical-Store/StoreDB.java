import java.sql.Connection;
import java.sql.DriverManager;

public class StoreDB {
    static Connection conn;

    public static Connection connect() {
        try {
            String url = "jdbc:mysql://localhost:3306/";
            String dbName = "Medical_Store"; // write it here your dbName
            String userName = "root";
            String password = "a$,;24ai<PrMsQI"; // write it here your sql password

            conn = DriverManager.getConnection(url + dbName, userName, password);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
